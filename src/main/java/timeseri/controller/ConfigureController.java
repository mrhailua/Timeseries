package timeseri.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import timeseri.InquiryAnalyzer;

@Controller
public class ConfigureController {
	static Logger logger = Logger.getLogger(ConfigureController.class);

	@Autowired
	private InquiryAnalyzer inquiryAnalyzer;

	@RequestMapping("/configure")
	public ModelAndView home() {
		final Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("configure", model);
	}

	public void executeAnalyse() {
		try {
			List<String> files = new ArrayList<String>();

			files.add("D:\\tmp\\TempLog\\pichinchaV4.log.1");
			files.add("D:\\tmp\\TempLog\\pichinchaV4.log.2");
			files.add("D:\\tmp\\TempLog\\pichinchaV4.log.3");
			files.add("D:\\tmp\\TempLog\\pichinchaV4.log.4");
			files.add("D:\\tmp\\TempLog\\pichinchaV4.log.5");
			files.add("D:\\tmp\\TempLog\\pichinchaV4.log.6");
			files.add("D:\\tmp\\TempLog\\pichinchaV4.log.7");
			files.add("D:\\tmp\\TempLog\\pichinchaV4.log.8");
			files.add("D:\\tmp\\TempLog\\pichinchaV4.log.9");
			files.add("D:\\tmp\\TempLog\\pichinchaV4.log.10");
			files.add("D:\\tmp\\TempLog\\pichinchaV4.log.11");
			files.add("D:\\tmp\\TempLog\\pichinchaV4.log.12");
			files.add("D:\\tmp\\TempLog\\pichinchaV4.log.13");
			files.add("D:\\tmp\\TempLog\\pichinchaV4.log.14");
			files.add("D:\\tmp\\TempLog\\server.log.2015-11-23");

			inquiryAnalyzer.analyze(files, "InquiryAccountListMessageBuilder", "\"Action\":\"inquiryAccountList\"");
		} catch (Exception ex) {
			logger.error("Analyse error", ex);
		}
	}
}
