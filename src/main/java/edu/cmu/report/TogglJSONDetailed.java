package main.java.edu.cmu.report;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;

/**
 * 
 * @author dianawesteves Parses a detailed report.
 * 
 */
public class TogglJSONDetailed {
	static final String weekNumber = "01";
	static final int PROJECT_ID = 4788327; // 17673 Software Development Studio III

	public static void main(String[] args) throws IOException {
		JsonReader reader = Json.createReader(new FileReader(
				"C:\\cygwin64\\home\\Linne\\" + weekNumber + ".txt"));
		JsonStructure jsonStructure = reader.read();
		JsonObject jsonObject = (JsonObject) jsonStructure;
		JsonArray courses = jsonObject.getJsonArray("data");
		TogglTags togglTags = new TogglTags();
		TogglTag tt;
		String curr_tag = "";
		Double curr_dur = 0.0;
		for (int j = 0; j < courses.size(); ++j) {
			JsonObject json_course = courses.getJsonObject(j);
			curr_tag = "";
			if (json_course.getInt("pid", 0) != PROJECT_ID) {
				continue;
			}

			tt = new TogglTag();
			JsonArray json_tags = json_course.getJsonArray("tags");
			if (json_tags.isEmpty())
				tt.setTogglTag("NullTag");
			else {
				// using the first tag, ignoring all others
				curr_tag = json_tags.getString(0, "NullTag");
				tt.setTogglTag(curr_tag);
			}
			curr_dur = ((double) json_course.getInt("dur", 0))
					/ (60000.0 * 60.0);
			tt.setTotalDuration(curr_dur.doubleValue());
			tt.setStudent(json_course.getString("user", "NullUser"));
			togglTags.updateTags(tt);
		}

		FileWriter fw = new FileWriter(
				"C:\\googleDrive\\STUDIO\\Planning\\RawTimeSheets_Summer2014\\"
						+ weekNumber + ".txt");
		for (TogglTag t : togglTags.getTags()) {
			fw.write(t.toString() + "\n");
			fw.flush();
			System.out.println(t.toString());
		}
		fw.close();
	} // end main
}
