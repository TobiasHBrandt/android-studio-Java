package dk.tec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBTools {
	String url = "jdbc:sqlserver://DESKTOP-JBE33R4\\BACON; databaseName=MyApiDB";
	Connection conn;
	Statement statement;
	
	private void connect() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url, "sa", "passw0rd");
			
			System.out.println("Connected");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public Person getPersonById(int id) {
		
		Person p = new Person();
		connect();
		
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery("Select * from Person where id = " + id);
			
			if (result.next()) {
				p.setId(result.getInt("id"));
				p.setName(result.getNString("name"));
				p.setJob(result.getNString("job"));
				p.setAge(result.getInt("age"));
				p.setGender(result.getNString("gender"));
				p.setHaircolor(result.getNString("haircolor"));
				p.setStudent(result.getBoolean("student"));
				
			}
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		closeConnection();
		return p;
	}
	
	public ArrayList<Person> getAllPerson(){
		ArrayList<Person> personlist = new ArrayList<Person>();
		connect();
		
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery("Select * from Person");
			
			while (result.next()) {
				Person p2 = new Person();
				p2.setId(result.getInt("id"));
				p2.setName(result.getNString("name"));
				p2.setJob(result.getNString("job"));
				p2.setAge(result.getInt("age"));
				p2.setGender(result.getNString("gender"));
				p2.setHaircolor(result.getNString("haircolor"));
				p2.setStudent(result.getBoolean("student"));
				personlist.add(p2);
			}
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		closeConnection();
		return personlist ;
	}
	
	public void addPerson(Person p) {
		connect();
		
		//String insertStr = "Insert into Person values('" + p.getName() + "')";
		
		String insertStr = "Insert into Person values(?, ?, ?, ?, ?, ?)";
		
		
		
		try {
			PreparedStatement prep = conn.prepareStatement(insertStr);
			
			prep.setString(1, p.getName());
			prep.setString(2, p.getJob());
			prep.setInt(3, p.getAge());
			prep.setString(4, p.getHaircolor());
			prep.setBoolean(5, p.isStudent());
			prep.setString(6, p.getGender());
			
			prep.execute();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		closeConnection();
		
	}
	
	public void updatePerson(Person p) {
		int status = 0;
		
		connect();
		
		String insertStr = "update Person set name=?,job=?,age=?,haircolor=?, student=?,gender=? where id=?";
		
		
		
		try {
			PreparedStatement prep = conn.prepareStatement(insertStr);
			
			prep.setString(1, p.getName());
			prep.setString(2, p.getJob());
			prep.setInt(3, p.getAge());
			prep.setString(4, p.getHaircolor());
			prep.setBoolean(5, p.isStudent());
			prep.setString(6, p.getGender());
			prep.setInt(7, p.getId());
			
			status = prep.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		closeConnection();
		
		
	}
	
	public void deletePerson(int id) {
		// doDelete Person/1003 eller {id: 1003} eller Person.json {id:1003, =} "jan" osv
		
		
		//String insertStr = "Delete from Person where id = ?";
		int status = 0;
		String insertStr = "Delete from Person where id = ?";
		connect();
		
		
		try {
			PreparedStatement prep = conn.prepareStatement(insertStr);
			
//			prep.setString(1, p.getName());
//			prep.setString(2, p.getJob());
//			prep.setInt(3, p.getAge());
//			prep.setString(4, p.getHaircolor());
//			prep.setBoolean(5, p.isStudent());
//			prep.setString(6, p.getGender());
			
			prep.setInt(1, id);
			
			status = prep.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		closeConnection();
	}

}
