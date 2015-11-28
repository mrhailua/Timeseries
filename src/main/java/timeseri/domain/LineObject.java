package timeseri.domain;

import java.util.Date;

public class LineObject {

	private String seriId;
	private String data;
	private Date date;
	private Long duration;

	public LineObject(String seriId, String data, Date date, Long duration) {
		super();
		this.seriId = seriId;
		this.data = data;
		this.setDate(date);
		this.setDuration(duration);
	}

	public String getSeriId() {
		return seriId;
	}

	public void setSeriId(String seriId) {
		this.seriId = seriId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getDuration() {
	    return duration;
    }

	public void setDuration(Long duration) {
	    this.duration = duration;
    }

}
