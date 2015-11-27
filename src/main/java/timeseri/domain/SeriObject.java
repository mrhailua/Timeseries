package timeseri.domain;

import java.util.List;

public class SeriObject {

	private String phone;
	private String transactionId;
	private List<LineObject> lineObjects;

	public SeriObject(String phone, String transactionId, List<LineObject> lineObjects) {
		super();
		this.phone = phone;
		this.transactionId = transactionId;
		this.lineObjects = lineObjects;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<LineObject> getLineObjects() {
		return lineObjects;
	}

	public void setLineObjects(List<LineObject> lineObjects) {
		this.lineObjects = lineObjects;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
