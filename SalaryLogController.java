package employeeAPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalaryLogController {
	
	public void creditBonus(int empId, float salary) throws SQLException {
		PreparedStatement ps = null;
		try {
			Connection con = DbController.getConnection();
			ps = con.prepareStatement("update salary_log set salary = salary + ? where emp_id = ?");
			ps.setFloat(1, salary);
			ps.setInt(2, empId);
			ps.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(ps != null)
				ps.close();
		}
	}
	
}
