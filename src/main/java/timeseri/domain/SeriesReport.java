package timeseri.domain;

import java.util.Date;

public class SeriesReport {

	private String seriId;
	private Integer count;
	private Date minActionDate;
	private Date maxActionDate;
	private Long duration;
	private String mobilePhone;

	public SeriesReport(String seriId, Integer count, Date minActionDate, Date maxActionDate, Long duration,
	        String mobilePhone) {
		super();
		this.seriId = seriId;
		this.count = count;
		this.minActionDate = minActionDate;
		this.maxActionDate = maxActionDate;
		this.duration = duration;
		this.setMobilePhone(mobilePhone);
	}

	public String getSeriId() {
		return seriId;
	}

	public void setSeriId(String seriId) {
		this.seriId = seriId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getMinActionDate() {
		return minActionDate;
	}

	public void setMinActionDate(Date minActionDate) {
		this.minActionDate = minActionDate;
	}

	public Date getMaxActionDate() {
		return maxActionDate;
	}

	public void setMaxActionDate(Date maxActionDate) {
		this.maxActionDate = maxActionDate;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
}
