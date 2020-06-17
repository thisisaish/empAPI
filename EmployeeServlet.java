package employeeAPI;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/employee/*")

public class EmployeeServlet extends HttpServlet {
	
	EmployeeController empController = new EmployeeController();
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {

		String path = req.getPathInfo();
		res.setContentType("application/json");
		if(path == null) {
			res.getWriter().print(empController.getEmployees());
		}else {
			String param = req.getParameter("param");
			String[] params = path.split("/");
			switch(param) {
			
			case "emp":
				res.getWriter().print(empController.getEmpById(Integer.parseInt(params[1])));
				break;
			case "loc":
				res.getWriter().print(empController.getEmpByLoc(Integer.parseInt(params[1])));
				break;
			case "desig":
				res.getWriter().print(empController.getEmpByDesig(Integer.parseInt(params[1])));
				break;
				
			}
		}
		
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = null;
		Date joiningDate = null;
		try {
			birthDate = (Date) formatter.parse(req.getParameter("birthDate"));
			joiningDate = (Date) formatter.parse(req.getParameter("joiningDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlDateOfBirth = new java.sql.Date(birthDate.getTime());
		java.sql.Date sqlDateOfJoining = new java.sql.Date(joiningDate.getTime());
		
		Employee emp = new Employee();
		emp.setFirstName(req.getParameter("firstName"));
		emp.setLastName(req.getParameter("lastName"));
		emp.setDesigId(Integer.parseInt(req.getParameter("desigId")));
		emp.setDob(sqlDateOfBirth);
		emp.setDoj(sqlDateOfJoining);
		emp.setGender(Gender.valueOf(req.getParameter("gender")));
		emp.setLocId(Integer.parseInt(req.getParameter("locId")));
		
		empController.addEmployee(emp);
	}
	
}
