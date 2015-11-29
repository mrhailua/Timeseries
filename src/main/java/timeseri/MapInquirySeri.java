package timeseri;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import timeseri.domain.LineObject;
import timeseri.domain.SeriObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class MapInquirySeri {

	@Autowired
	private MongoService mongoService;

	public void map(SeriObject seriObject) {
		List<DBObject> dbObjects = new ArrayList<DBObject>();
		for (LineObject obj : seriObject.getLineObjects()) {
			dbObjects.add(createSeri(obj));
		}
		mongoService.insertSeri(dbObjects);
	}

	private DBObject createSeri(LineObject data) {
		return new BasicDBObject("phone", data.getPhone()).append("transactionId", data.getTransactionId())
		        .append("SeriId", data.getSeriId()).append("Data", data.getData()).append("actionDate", data.getDate())
		        .append("TimeLapse", data.getDuration());
	}
}
