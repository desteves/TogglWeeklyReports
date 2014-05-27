package main.java.edu.cmu.report;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
/**
 * 
 * @author dianawesteves
 * A tag on a time entry.
 *
 */
public class TogglTag {
	private double totalDuration;
	private String student;
	private String togglTag;
	private Map<String, Double> studentTimes;

	public TogglTag() {
		studentTimes = new TreeMap<String, Double>();
		totalDuration = 0.0;
		student = "";
	}

	@Override
	public String toString() {
		return togglTag + "\t" + sumDurations(studentTimes.values()) + "\t" + studentTimes.toString();
	}

	private double sumDurations(Collection<Double> values) {
		Double sum_d = 0.0;
		for (Double d : values) {
			sum_d += d;
		}
		return sum_d.doubleValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TogglTag other = (TogglTag) obj;
		if ((this.togglTag == null) ? (other.togglTag != null) : !this.togglTag
				.equals(other.togglTag)) {
			return false;
		}
		return true;
	}

	public String getTogglTag() {
		return togglTag;
	}

	public void setTogglTag(String togglTag) {
		this.togglTag = togglTag;
	}

	public Map<String, Double> getStudentTimes() {
		return studentTimes;
	}

	public void setStudentTimes(Map<String, Double> studentTimes) {
		this.studentTimes = studentTimes;
	}

	public double getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(double totalDuration) {
		this.totalDuration = totalDuration;
	}

	public boolean updateStudentTimes(String student, Double duration) {
		Double temp = studentTimes.remove(student);
		if (temp == null)
			temp = 0.0;
		studentTimes.put(student, duration + temp.doubleValue());
		return true; // added a new student
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

}
