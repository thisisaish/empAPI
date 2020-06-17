package employeeAPI;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/salary/*")

public class SalaryServlet extends HttpServlet{
	SalaryLogController sController = new SalaryLogController();
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		String[] params = req.getPathInfo().split("/");
		for(String param : params) {
			System.out.println(param);
		}
		try {
			sController.creditBonus(Integer.parseInt(params[1]), Float.parseFloat(params[2]));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
