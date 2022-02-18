package dk.tec;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeRequest {
	
	MatchPerson level;
	int id;
	
	
	public MatchPerson getLevel() {
		return level;
	}


	public int getId() {
		return id;
	}


	public AnalyzeRequest(String pathInfo) {
		Matcher match = Pattern.compile("/Person/([0-9]+)").matcher(pathInfo);
		
		if (match.find()) {
			level = MatchPerson.MatchPersonId;
			id = Integer.parseInt(match.group(1));
		}
		else {
			match = Pattern.compile("^/Person$").matcher(pathInfo);
			if (match.find()) {
				level = MatchPerson.MatchPerson;
			}
			else {
				level = MatchPerson.NoMatch;
			}
		}
	}
}
