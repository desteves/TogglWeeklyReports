package main.java.edu.cmu.report;
import java.util.Set;
import java.util.TreeSet;
/**
 * 
 * @author dianawesteves
 * Toggl Project
 * Represents a course.
 *
 */
public class TogglProject {
	private boolean isCore;
	private long time;
	private String togglStudent;
	private String name;
	private Set<String> togglStudents;
	
	public TogglProject() {
		togglStudents = new TreeSet<String>();
	}

	public Set<String> getTogglStudents() {
		return togglStudents;
	}

	public void setTogglStudents(Set<String> togglStudents) {
		this.togglStudents = togglStudents;
	}

	public void setTogglStudent(String togglStudent) {
		this.togglStudent = togglStudent;
	}

	public Set<String> getTogglStudent() {
		return togglStudents;
	}

	public boolean isCore() {
		return isCore;
	}

	public void setCore(boolean isCore) {
		this.isCore = isCore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TogglProject other = (TogglProject) obj;
		if ((this.name == null) ? (other.name != null) : !this.name
				.equals(other.name)) {
			return false;
		}
		return true;
	}

	public void addTogglStudent() {
		togglStudents.add(togglStudent);
	}

	public void addTogglStudents(Set<String> togglStudents2) {
		togglStudents.addAll(togglStudents2);
	}
}
