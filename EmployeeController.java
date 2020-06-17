package employeeAPI;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EmployeeController {
	
	public void addEmployee(Employee emp) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection con = DbController.getConnection();
			ps = con.prepareStatement("insert into employees(first_name,last_name,date_of_birth,date_of_joining,gender) values(?,?,?,?,?)");
			ps.setString(1, emp.getFirstName());
			ps.setString(2, emp.getLastName());
			ps.setDate(3, emp.getDob());
			ps.setDate(4, emp.getDoj());
			ps.setString(5,emp.getGender().name());
			if(ps.execute()) {
				ps = con.prepareStatement("select emp_id from employees where first_name = ? and last_name = ?");
				ps.setString(1, emp.getFirstName());
				ps.setString(2, emp.getLastName());
				rs = ps.executeQuery();
				while(rs.next()) {
					emp.setEmpId(rs.getInt(1));
				}
				ps = con.prepareStatement("insert into employee_designations values(?,?,?)");
				ps.setInt(1, emp.getEmpId());
				ps.setInt(2, emp.getDesigId());
				ps.setInt(3, emp.getLocId());
				ps.execute();
				
				ps = con.prepareStatement("insert into salary_log values(?,?)");
				ps.setInt(1, emp.getEmpId());
				ps.setInt(2, 0);
				ps.execute();
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
				try {
					if(ps!=null)
						ps.close();
					if(rs!=null)
						rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
		}
	}
	
	public JSONArray getEmployees() {
		JSONArray employees = new JSONArray();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			Connection con = DbController.getConnection();
			ps = con.prepareStatement("select employees.first_name,employees.last_name,employees.date_of_birth,employees.date_of_joining,designations.designation,locations.location from employee_designations"
					+ " inner join employees on employees.emp_id = employee_designations.emp_id"
					+ " inner join designations on designations.designation_id = employee_designations.designation_id"
					+ " inner join locations on locations.loc_id = employee_designations.loc_id order by employee_designations.emp_id ");
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				JSONObject emp = new JSONObject();
				emp.put("name", rs.getString(1)+" "+rs.getString(2));
				emp.put("dob", rs.getDate(3));
				emp.put("doj", rs.getDate(4));
				emp.put("designation", rs.getString(5));
				emp.put("location", rs.getString(6));
				
				employees.add(emp);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		return employees;
	}
	
	public JSONObject getEmpById(int id) {
		JSONObject employee = new JSONObject();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			Connection con = DbController.getConnection();
			ps = con.prepareStatement("select employees.first_name,employees.last_name,employees.date_of_birth,employees.date_of_joining,designations.designation,locations.location from employee_designations"
					+ " inner join employees on employees.emp_id = employee_designations.emp_id"
					+ " inner join designations on designations.designation_id = employee_designations.designation_id"
					+ " inner join locations on locations.loc_id = employee_designations.loc_id where employee_designations.emp_id = ? ");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				employee.put("name", rs.getString(1)+" "+rs.getString(2));
				employee.put("dob", rs.getDate(3));
				employee.put("doj", rs.getDate(4));
				employee.put("designation", rs.getString(5));
				employee.put("location", rs.getString(6));
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		return employee;
	}
	
	public JSONArray getEmpByLoc(int locId) {
		JSONArray employees = new JSONArray();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			Connection con = DbController.getConnection();
			ps = con.prepareStatement("select employees.first_name,employees.last_name,employees.date_of_birth,employees.date_of_joining,designations.designation,locations.location from employee_designations"
					+ " inner join employees on employees.emp_id = employee_designations.emp_id"
					+ " inner join designations on designations.designation_id = employee_designations.designation_id"
					+ " inner join locations on locations.loc_id = employee_designations.loc_id where employee_designations.loc_id = ? ");
			ps.setInt(1, locId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				JSONObject employee = new JSONObject();
				employee.put("name", rs.getString(1)+" "+rs.getString(2));
				employee.put("dob", rs.getDate(3));
				employee.put("doj", rs.getDate(4));
				employee.put("designation", rs.getString(5));
				employee.put("location", rs.getString(6));
				
				employees.add(employee);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		return employees;
	}
	
	public JSONArray getEmpByDesig(int desigId) {
		JSONArray employees = new JSONArray();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			Connection con = DbController.getConnection();
			ps = con.prepareStatement("select employees.first_name,employees.last_name,employees.date_of_birth,employees.date_of_joining,designations.designation,locations.location from employee_designations"
					+ " inner join employees on employees.emp_id = employee_designations.emp_id"
					+ " inner join designations on designations.designation_id = employee_designations.designation_id"
					+ " inner join locations on locations.loc_id = employee_designations.loc_id where employee_designations.desig_id = ? ");
			ps.setInt(1, desigId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				JSONObject employee = new JSONObject();
				employee.put("name", rs.getString(1)+" "+rs.getString(2));
				employee.put("dob", rs.getDate(3));
				employee.put("doj", rs.getDate(4));
				employee.put("designation", rs.getString(5));
				employee.put("location", rs.getString(6));
				
				employees.add(employee);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		return employees;
	}
}
