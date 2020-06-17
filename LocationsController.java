package employeeAPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LocationsController {
	public Boolean addLocation(Location location) throws ClassNotFoundException {
		PreparedStatement ps = null;
		try {
			Connection conn = DbController.getConnection();
			ps = conn.prepareStatement("insert into locations(location) values(?)");
			ps.setString(1, location.getLocation());
			return ps.execute();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			if(ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return false;
	}

	public JSONArray getLocations() {
		JSONArray Locations = new JSONArray();
		try {
			Connection conn = DbController.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from locations");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				JSONObject location = new JSONObject();
				location.put("locId", rs.getInt("loc_id"));
				location.put("location", rs.getString("location"));
				
				Locations.add(location);
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return Locations;
	}
	
	public JSONObject getLocById(int id) {
		JSONObject locObj = new JSONObject();
		try {
			Connection conn = DbController.getConnection();
			PreparedStatement ps = conn.prepareStatement("select location from locations where loc_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				locObj.put("locId",id);
				locObj.put("location",rs.getString(1));
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return locObj;
	}
}
