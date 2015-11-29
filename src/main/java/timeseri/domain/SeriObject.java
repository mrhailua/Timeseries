package timeseri.domain;

import java.util.Date;
import java.util.List;

public class SeriObject {

	private List<LineObject> lineObjects;

	public SeriObject(List<LineObject> lineObjects) {
		super();

		this.lineObjects = lineObjects;
	}

	public List<LineObject> getLineObjects() {
		return lineObjects;
	}

	public void setLineObjects(List<LineObject> lineObjects) {
		this.lineObjects = lineObjects;
	}

	public Object[] getSeriData() {
		Object[] result = new Object[lineObjects.size()];
		int i = 0;
		for (LineObject obj : this.lineObjects) {
			result[i] = new Object[] { obj.getDuration() };
			i++;
		}
		return result;
	}
}
