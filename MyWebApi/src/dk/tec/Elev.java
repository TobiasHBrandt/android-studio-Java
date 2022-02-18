package dk.tec;

import java.util.ArrayList;

public class Elev {
	
	int id;
	String name;
	ArrayList<String> skills = new ArrayList<String>();
	
	public Elev() {}
	
	public Elev(int id, String name, String skill) {
		this.id = id;
		this.name = name;
		skills.add(skill);
	}
	
	public void addSkill(String skill) {
		skills.add(skill);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<String> skills) {
		this.skills = skills;
	}
	
	

}
