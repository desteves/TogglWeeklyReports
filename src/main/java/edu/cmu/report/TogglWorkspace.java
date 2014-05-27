package main.java.edu.cmu.report;
import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;
/**
 * 
 * @author dianawesteves
 * Toggl Workspace
 * Represents MSE, MSIT, or ESE Team
 *
 */
public class TogglWorkspace {

	private List<TogglProject> courses;

	public TogglWorkspace() {
		name = "";
		courses = new ArrayList<TogglProject>();
	}

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TogglProject> getCourses() {
		return courses;
	}

	public boolean updateCourse(TogglProject tc) {
		if (courses.contains(tc)) {
			int i = courses.indexOf(tc);
			courses.get(i).setTime(tc.getTime() + courses.get(i).getTime());
			courses.get(i).addTogglStudents(tc.getTogglStudents());
			return true; // updated existing course;
		}
		courses.add(tc);
		return false; // added a new course
	}

}
