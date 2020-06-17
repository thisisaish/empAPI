package employeeAPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DesignationController {
	public Boolean addDesignation(Designation designation) throws ClassNotFoundException {
		PreparedStatement ps = null;
		try {
			Connection conn = DbController.getConnection();
			ps = conn.prepareStatement("insert into desingations(designation) values(?)");
			ps.setString(1, designation.getDesignation());
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

	public JSONArray getDesignations() {
		JSONArray designations = new JSONArray();
		try {
			Connection conn = DbController.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from designations");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				JSONObject desig = new JSONObject();
				desig.put("desigId", rs.getInt("designation_id"));
				desig.put("designation", rs.getString("designation"));
				
				designations.add(desig);
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return designations;
	}
	
	public JSONObject getDesigById(int id) {
		JSONObject desigObj = new JSONObject();
		try {
			Connection conn = DbController.getConnection();
			PreparedStatement ps = conn.prepareStatement("select designation from designations where designation_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				desigObj.put("desigId",id);
				desigObj.put("designation",rs.getString(1));
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return desigObj;
	}
}
