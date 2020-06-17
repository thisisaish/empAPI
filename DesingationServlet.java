package employeeAPI;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/designations/*")

public class DesingationServlet extends HttpServlet{
	DesignationController designationController = new DesignationController();
	public void doGet(HttpServletRequest req,HttpServletResponse resp)throws IOException {
		resp.setContentType("application/json");
		String info = req.getPathInfo();
		if(info == null)
			resp.getWriter().print(designationController.getDesignations());
		else {
			String[] param = info.split("/");
			resp.getWriter().print(designationController.getDesigById(Integer.parseInt(param[1])));
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse resp) {
		String[] params = req.getPathInfo().split("/");
		Designation designation = new Designation();
		designation.setDesignation(params[1]);
		try {
			designationController.addDesignation(designation);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
