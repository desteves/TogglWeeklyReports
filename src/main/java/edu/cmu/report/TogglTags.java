package main.java.edu.cmu.report;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author dianawesteves All the tags for one project's time entries.
 */
public class TogglTags {

	private List<TogglTag> tags;

	public TogglTags() {
		tags = new ArrayList<TogglTag>();
	}

	public boolean updateTags(TogglTag tt) {
		if (tags.contains(tt)) {
			int i = tags.indexOf(tt);
			// update studentTimes and totalDuration
			tags.get(i).updateStudentTimes(tt.getStudent(),
					Double.valueOf(tt.getTotalDuration()));
			tags.get(i).setTotalDuration(0.0);
			tags.get(i).setStudent("");
			return true;
		} else {
			tags.add(tt);
		}
		updateTags(tt);
		return false; // added a new tag

	}

	public List<TogglTag> getTags() {
		return tags;
	}

	public void setTags(List<TogglTag> tags) {
		this.tags = tags;
	}

}
