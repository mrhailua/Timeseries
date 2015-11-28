package timeseri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import timeseri.domain.LineObject;
import timeseri.domain.SeriObject;

@Service
public class InquiryAnalyzer {
	static Logger logger = Logger.getLogger(InquiryAnalyzer.class);

	@Autowired
	private MapInquirySeri inquiryMapper;

	public void analyze(List<File> files, String starter, String finish) throws FileNotFoundException {
		for (File fileName : files) {
			analyze(fileName, starter, finish);
		}
	}

	private void analyze(File file, String starter, String finish) throws FileNotFoundException {
		try {
			FileInputStream fstream = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			List<LineObject> lineObjects = new ArrayList<LineObject>();
			String strLine;
			String uuid = UUID.randomUUID().toString();
			String phone = "";
			String transactionId = "";
			boolean isStart = false;
			Date startTime = null;
			while ((strLine = br.readLine()) != null) {
				if (!isStart && strLine.contains(starter)) {
					isStart = true;
				}

				if (isStart) {
					Date actionDate = getDate(strLine);
					if (startTime == null) {
						startTime = actionDate;
					}

					if (strLine.contains("<mobileNumber>")) {
						String tmpStr = StringUtils.remove(StringUtils.strip(strLine), "<mobileNumber>");
						phone = StringUtils.remove(tmpStr, "</mobileNumber>");
					}

					if (strLine.contains("<transactionId>")) {
						String tmpStr = StringUtils.remove(StringUtils.strip(strLine), "<transactionId>");
						transactionId = StringUtils.remove(tmpStr, "</transactionId>");
					}

					if (actionDate != null) {
						lineObjects.add(new LineObject(uuid, strLine, actionDate, actionDate.getTime()
						        - startTime.getTime()));

						if (strLine.contains(finish)) {
							isStart = false;
							inquiryMapper.map(new SeriObject(phone, transactionId, lineObjects));
							phone = "";
							transactionId = "";
							startTime = null;
							uuid = UUID.randomUUID().toString();
							lineObjects.clear();
						}
					}
				}
			}

			br.close();
		} catch (Exception ex) {
			logger.error("Analyze fail", ex);
		}
	}

	private Date getDate(String strLine) {
		Date actionDate = null;
		try {
			if (StringUtils.isNotEmpty(strLine) && strLine.length() > 23) {
				String date = StringUtils.substring(strLine, 0, 23);
				actionDate = DateUtils.parseDate(date, "yyyy-MM-dd hh:mm:ss,SSS");
			}
		} catch (Exception ex) {
		}

		return actionDate;
	}
}
