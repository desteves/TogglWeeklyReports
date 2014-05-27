package main.java.edu.cmu.report;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;

/**
 * 
 * @author dianawesteves Parses a weekly report.
 * 
 */
public class TogglJSONWeekly {
	static final String coreCourses = "17677 MSIT Project I | 17657 Communication for Software Engineers II | 17654 A Analysis of Software Artifacts | 17655 Architectures for Software Systems | 17672 Software Development Studio II ";
	static final String electiveCourse = "Elective";
	static final String weekNumber = "07";

	public static void main(String[] args) throws IOException {
		JsonReader reader = Json.createReader(new FileReader(
				"C:\\cygwin64\\home\\Linne\\week" + weekNumber + ".txt"));
		JsonStructure jsonst = reader.read();
		JsonArray jsonArr = (JsonArray) jsonst;
		List<TogglWorkspace> projects = new ArrayList<TogglWorkspace>();

		// each jsonObj is a workspace (MSE or MSIT group project)
		for (int i = 0; i < jsonArr.size(); i++) {
			TogglWorkspace project = new TogglWorkspace();
			JsonObject workSpace = jsonArr.getJsonObject(i);
			// get "data" (all courses) for one workspace
			JsonArray courses = workSpace.getJsonArray("data");
			for (int j = 0; j < courses.size(); j++) {
				TogglProject toggl_course = new TogglProject();
				JsonObject json_course = courses.getJsonObject(j);
				JsonObject json_title = json_course.getJsonObject("title");

				if (project.name.isEmpty())
					project.name = json_title.getString("client", "");

				// get "title" (Course# & Name)
				// example, 17672 Software Development Studio II
				toggl_course.setName(json_title.getString("project", ""));
				toggl_course.setCore(coreCourses.contains(toggl_course
						.getName()));
				if (toggl_course.getName().isEmpty()
						|| !toggl_course.getName().matches("^\\d{5}.*$")) {
					continue;// its not a core nor elective, most likely
					// personal stuff so ignore it.
				} else if (!toggl_course.isCore()) {
					toggl_course.setName(electiveCourse);
				}
				toggl_course.setTime((long) Integer.valueOf(json_course
						.getJsonArray("totals").get(7).toString()));
				JsonArray json_details = json_course.getJsonArray("details");
				for (int k = 0; k < json_details.size(); k++) {
					JsonObject json_detail = json_details.getJsonObject(k);
					toggl_course.setTogglStudent(json_detail.getJsonObject(
							"title").getString("user"));
					toggl_course.addTogglStudent();
				}
				project.updateCourse(toggl_course);
			}
			projects.add(project);
		}

		List<String> output = new ArrayList<String>();
		String out = "";
		FileWriter fw = new FileWriter(
				"C:\\dropbox\\cmu\\StudioMgr\\2014-Fall\\RawData\\week"
						+ weekNumber + ".tab");
		for (TogglWorkspace t : projects) {
			out = "";
			for (TogglProject c : t.getCourses()) {
				double avg_time = c.getTime()
						/ (60000.0 * c.getTogglStudent().size() * 60.0);
				out = c.getName() + "\t" + t.name + "\t"
						+ String.format("%.1f", avg_time) + "\t"
						+ (c.getTime() / 3600000);
				output.add(out);
			}
		}
		Collections.sort(output, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});
		for (int i = 0; i < output.size(); ++i) {
			fw.write(output.get(i) + "\n");
			fw.flush();
			System.out.println(output.get(i));
		}
		fw.close();
	}
}
