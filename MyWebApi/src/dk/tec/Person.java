package dk.tec;

public class Person {
	
	
	
	int id;
	String name;
	String job;
	int age;
	String haircolor;
	boolean student;
	String gender;
	
	
	
	
	

	public Person(int id, String name, String job, int age, String haircolor, boolean student, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.job = job;
		this.age = age;
		this.haircolor = haircolor;
		this.student = student;
		this.gender = gender;
	}
	
	
	public Person() {}
	
	

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
	
	public String getJob() {
		return job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getHaircolor() {
		return haircolor;
	}
	
	public void setHaircolor(String haircolor) {
		this.haircolor = haircolor;
	}
	
	public boolean isStudent() {
		return student;
	}
	
	public void setStudent(boolean student) {
		this.student = student;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	

}


//id int identity(1000,1) primary key,
//[name] nvarchar(25),
//job nvarchar(20),
//age int,
//haircolor nvarchar(20),
//student bit,
//gender nchar(1)
