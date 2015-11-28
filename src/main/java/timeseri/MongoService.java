package timeseri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import timeseri.domain.Configure;
import timeseri.domain.LineObject;
import timeseri.domain.SeriObject;
import timeseri.domain.SeriesReport;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

@Service
public class MongoService implements InitializingBean {
	private MongoClient mongoClient;
	private DB pichinchaTimeseri;

	@Override
	public void afterPropertiesSet() throws Exception {
		mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017)));

		pichinchaTimeseri = mongoClient.getDB("PichinchaTimeseri");
	}

	public void insertSeri(List<DBObject> series) {
		pichinchaTimeseri.getCollection("inquirySeri").insert(series);
	}

	public void saveConfigure(Configure configure) {
		pichinchaTimeseri.getCollection("configure").insert(
		        new BasicDBObject("RunFolder", configure.getLogFolder()).append("JobName", configure.getJobName())
		                .append("StartText", configure.getStartText()).append("EndText", configure.getEndText()));
	}

	public List<Configure> getConfigures() {
		List<DBObject> configures = pichinchaTimeseri.getCollection("configure").find().limit(100).toArray();
		List<Configure> result = new ArrayList<Configure>();

		for (DBObject dbobj : configures) {
			Configure conf = new Configure((String) dbobj.get("JobName"), (String) dbobj.get("RunFolder"),
			        (String) dbobj.get("StartText"), (String) dbobj.get("EndText"));
			result.add(conf);
		}
		return result;
	}

	public List<SeriesReport> getTimeSeri(Integer duration) {

		List<DBObject> aggregateQuery = new ArrayList<DBObject>();
		aggregateQuery.add(new BasicDBObject("$group", new BasicDBObject("_id", "$SeriId")
		        .append("count", new Document("$sum", 1)).append("minActionDate", new Document("$min", "$actionDate"))
		        .append("maxActionDate", new Document("$max", "$actionDate"))
		        .append("maxDuration", new Document("$max", "$TimeLapse"))
		        .append("MobileNumber", new Document("$max", "$phone"))));

		aggregateQuery.add(new BasicDBObject("$sort", new Document("maxDuration", -1)));
		aggregateQuery.add(new BasicDBObject("$limit", 100));

		List<SeriesReport> result = new ArrayList<SeriesReport>();
		Iterator<DBObject> iterator = pichinchaTimeseri.getCollection("inquirySeri").aggregate(aggregateQuery)
		        .results().iterator();
		while (iterator.hasNext()) {
			DBObject data = iterator.next();
			result.add(new SeriesReport((String) data.get("_id"), (Integer) data.get("count"), (Date) data
			        .get("minActionDate"), (Date) data.get("maxActionDate"), (Long) data.get("maxDuration"),
			        (String) data.get("MobileNumber")));
		}

		return result;
	}

	public SeriObject getSeri(String seriId) {

		List<DBObject> lineObjects = pichinchaTimeseri.getCollection("inquirySeri")
		        .find(new BasicDBObject("SeriId", seriId)).limit(300).sort(new BasicDBObject("TimeLapse", 1))
		        .toArray();
		List<LineObject> lines = new ArrayList<LineObject>();

		for (DBObject dbobj : lineObjects) {
			LineObject conf = new LineObject((String) dbobj.get("SeriId"), (String) dbobj.get("Data"),
			        (Date) dbobj.get("actionDate"), (Long) dbobj.get("TimeLapse"));
			lines.add(conf);

		}

		return new SeriObject(seriId, "", lines);
	}

}
