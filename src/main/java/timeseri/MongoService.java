package timeseri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import timeseri.domain.SeriObject;

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

	public List<SeriObject> getTimeSeri(Integer duration) {
		return convertToSeri(pichinchaTimeseri.getCollection("inquirySeri")
		        .find(getTimeSeriQuery(), getTimeSeriMapping()).limit(1000).toArray());
	}

	private List<SeriObject> convertToSeri(List<DBObject> dbObjects) {
		return new ArrayList<SeriObject>();
	}

	private DBObject getTimeSeriQuery() {
		return new BasicDBObject();
	}

	private DBObject getTimeSeriMapping() {
		return new BasicDBObject();
	}

}
