package timeseri.domain;

public class Configure {

	private String jobName;
	private String logFolder;
	private String startText;
	private String endText;

	public Configure(String jobName, String logFolder, String startText, String endText) {
		super();
		this.jobName = jobName;
		this.logFolder = logFolder;
		this.startText = startText;
		this.endText = endText;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getLogFolder() {
		return logFolder;
	}

	public void setLogFolder(String logFolder) {
		this.logFolder = logFolder;
	}

	public String getStartText() {
		return startText;
	}

	public void setStartText(String startText) {
		this.startText = startText;
	}

	public String getEndText() {
		return endText;
	}

	public void setEndText(String endText) {
		this.endText = endText;
	}
}
