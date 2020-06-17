package employeeAPI;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/locations/*")

public class LocationsServlet extends HttpServlet{
	LocationsController locController = new LocationsController();
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		String info = req.getPathInfo();
		if(info == null)
			res.getWriter().print(locController.getLocations());
		else {
			String[] param = info.split("/");
			res.getWriter().print(locController.getLocById(Integer.parseInt(param[1])));
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse resp) {
		String[] params = req.getPathInfo().split("/");
		Location location = new Location();
		location.setLocation(params[1]);
		try {
			locController.addLocation(location);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
