package com.management.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.StringJoiner;
import com.management.dbconfig.DbConfig;
import com.management.model.Leave;
import com.management.repo.Repository;

public class LeaveReportDao {
	private DbConfig config = DbConfig.getInstance();
	private String querry = null;
	private Leave leave = null;
	private Repository repo = new Repository(config);

	public HashMap<Integer, Leave> getLeavesDao(int emp_id) {
		HashMap<Integer, Leave> leaves = null;
		ResultSet rs = null;
		try {
			String psmtquerry = "select * from leaves l INNER join employees e on l.Leave_Employee_Id=e.Employee_Id where l.Leave_Employee_Id=?";

//			repo.setValues();
//			repo.getConfig().prepareStatement(psmtquerry);
			repo.SetQuerry(psmtquerry);
			repo.getPsmt().setInt(1, emp_id);
			rs = repo.executePreparedStatement();

			System.out.println("Reached leaves dao with Employee id" + emp_id);
			int id = 0;
			/*
			 * querry =
			 * "select * from leaves l INNER join employees e on l.Leave_Employee_Id=e.Employee_Id where l.Leave_Employee_Id="
			 * + emp_id; System.out.println(querry);
			 *
			 * rs = repo.executeQuerryRep(querry);
			 */
			if (rs.isBeforeFirst()) {
				leaves = new HashMap<>();
				this.leave = new Leave();
			} else {
				this.leave = null;
				System.out.println("Exiting GET Leaves Dao as no leaves found ");
				return null;
			}
			while (rs.next()) {

				this.leave.setLeave_emp_id(rs.getInt(1));
				id = this.leave.setLeave_id(rs.getInt(2));
				this.leave.setLeave_type(rs.getString(3));
				this.leave.setLeave_from(rs.getDate(4).toLocalDate());
				this.leave.setLeave_till(rs.getDate(5).toLocalDate());
				this.leave.setLeave_total(rs.getInt(6));
				this.leave.setLeave_taken(rs.getInt(7));
				this.leave.setLeave_left(rs.getInt(8));

				leaves.put(rs.getInt(2), this.leave);
				System.out.println("Found Leave id : " + leaves.get(id).getLeave_emp_id());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("Exiting GET Leaves Dao safely");
		return leaves;

	}

	public Leave getLeaveDao(int emp_id, int leave_id) {
		ResultSet rs = null;

		querry = "select * from leaves l INNER join employees e on l.Leave_Employee_Id=e.Employee_Id where l.Leave_Id="
				+ String.valueOf(leave_id) + " and l.Leave_Employee_Id=" + String.valueOf(emp_id);
//				"select * from leaves where Leave_Id=" + String.valueOf(id);
//		System.out.println(querry);
		try {
			ResultSet rs_id = repo.executeQuerryRep("select Employee_Id from employees where Employee_Id=" + emp_id);
			if (!rs_id.isBeforeFirst()) {
				System.out.println("Exiting GET Leave Dao as no Employee found with Employee id : " + emp_id);
				return null;
			}
			rs = repo.executeQuerryRep(querry);
			if (rs.isBeforeFirst()) {
				this.leave = new Leave();
			} else {
				this.leave = null;
				System.out.println("Exiting GET Leave Dao as no leaves found for Employee id : " + emp_id);
				return null;
			}

			while (rs.next()) {
				this.leave.setLeave_emp_id(rs.getInt(1));
				this.leave.setLeave_id(rs.getInt(2));
				this.leave.setLeave_type(rs.getString(3));
				this.leave.setLeave_from(rs.getDate(4).toLocalDate());
				this.leave.setLeave_till(rs.getDate(5).toLocalDate());
				this.leave.setLeave_total(rs.getInt(6));
				this.leave.setLeave_taken(rs.getInt(7));
				this.leave.setLeave_left(rs.getInt(8));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exiting GET Leave Dao safely");
		return this.leave;
	}

	public Leave postLeaveDao(int emp_id, Leave leave) {
		ResultSet rs = null;
		StringBuffer sbquerry = new StringBuffer();
		StringBuffer sbquerryup1 = new StringBuffer();
		StringBuffer sbquerryup2 = new StringBuffer();
		StringBuffer sbquerryup3 = new StringBuffer();
		StringJoiner sjquerry = new StringJoiner(" ");

		try {
			int id = 0;

			ResultSet rs_id = repo.executeQuerryRep("select Employee_Id from employees where Employee_Id=" + emp_id);
			if (!rs_id.isBeforeFirst()) {
				System.out.println("Exiting POST Leave Dao as no Employee found with Employee id : " + emp_id);
				return null;
			} else {
				id = rs_id.getInt(1);
				sjquerry.add("INSERT INTO leaves (Leave_Employee_Id, Leave_Type, Leave_From, Leave_Till)")
						.add("VALUES(" + String.valueOf(emp_id) + ",")
						.add("'" + String.valueOf(leave.getLeave_type()) + "',")
						.add("'" + java.sql.Date.valueOf(leave.getLeave_from()).toString() + "',")
						.add("'" + java.sql.Date.valueOf(leave.getLeave_till()).toString() + "')");
				// querry.add("INSERT INTO leaves(Leave_Employee_Id, Leave_Type, Leave_From,
				// Leave_Till, Leaves_Total) VALUES(");
				//
				// querry. + String.valueOf(leave.getLeave_emp_id()) + ", '" +
				// String.valueOf(leave.getLeave_type()) + "', '"
				// + java.sql.Date.valueOf(leave.getLeave_from()) + "', '"
				// + java.sql.Date.valueOf(leave.getLeave_till()).toString() + "', "
				// + String.valueOf(leave.getLeave_total()) + ")";
				// System.out.println(sjquerry.toString());

				repo.executeUpdateRep(sjquerry.toString());
				System.out.println("Getting track of leaves");
				ResultSet tTrackLeavesRs = repo.executeQuerryRep("select * from track_leaves where Emp_Id=" + id);
				if (!tTrackLeavesRs.next()) {
					System.out.println("No track of leaves Found. Inserting new record");
					repo.executeUpdateRep("INSERT INTO track_leaves(Emp_Id, Total_Leaves, Track_Left) VALUES(" + emp_id
							+ ", " + leave.getLeave_total() + ", " + leave.getLeave_total() + ")");
				}

				// Getting Leave_Id to update Tracked Leaves
				System.out.println("Getting leave id of the leave posted");
				sbquerryup1.append(
						"select Leave_Id from leaves l INNER join employees e on l.Leave_Employee_Id=e.Employee_Id where Leave_Employee_Id=")
						.append(emp_id).append(" and Leave_From='").append(java.sql.Date.valueOf(leave.getLeave_from()))
						.append("' and Leave_Till='").append(java.sql.Date.valueOf(leave.getLeave_till())).append("'");

				// querry = "select * from leaves where Leave_Employee_Id=" +
				// String.valueOf(leave.getLeave_emp_id())
				// + "and Leave_From='" +
				// java.sql.Date.valueOf(leave.getLeave_from()).toString() + "' and
				// Leave_Till='"
				// + java.sql.Date.valueOf(leave.getLeave_till()).toString()+"'";
				// System.out.println(sbquerryup1.toString());

				ResultSet trs = repo.executeQuerryRep(sbquerryup1.toString());
				int TLeave_id = 0;
				while (trs.next()) {
					TLeave_id = trs.getInt(1);
				}

				// Updating Tracked Leaves of Leaves table from Track_Leaves table
				sbquerryup2.append(
						"update leaves t1 join track_leaves t2 on t2.Emp_Id = t1.Leave_Employee_Id set t1.Tracked_Leaves = t2.Track_Left where t1.Leave_Id =")
						.append(TLeave_id);
				System.out.println("Updating track of leaves in Leaves table ");
				repo.executeUpdateRep(sbquerryup2.toString());

				// Updating Track_Left of table Track_Leaves from leaves table
				sbquerryup3.append(
						"update track_leaves t1 join leaves t2 on t1.Emp_Id = t2.Leave_Employee_Id set t1.Track_Left = t2.Leaves_Left where t2.Leave_Id=")
						.append(TLeave_id);
				System.out.println("Updating Track_left  in track_leaves table from leaves_left in Leaves table");
				repo.executeUpdateRep(sbquerryup3.toString());

				sbquerry.append(
						"select * from leaves l INNER join employees e on l.Leave_Employee_Id=e.Employee_Id where Leave_Employee_Id=")
						.append(emp_id).append(" and Leave_From='").append(java.sql.Date.valueOf(leave.getLeave_from()))
						.append("' and Leave_Till='").append(java.sql.Date.valueOf(leave.getLeave_till())).append("'");

				// querry = "select * from leaves where Leave_Employee_Id=" +
				// String.valueOf(leave.getLeave_emp_id())
				// + "and Leave_From='" +
				// java.sql.Date.valueOf(leave.getLeave_from()).toString() + "' and
				// Leave_Till='"
				// + java.sql.Date.valueOf(leave.getLeave_till()).toString()+"'";
				// System.out.println(sbquerry.toString());

				rs = repo.executeQuerryRep(sbquerry.toString());
				if (rs.isBeforeFirst()) {
					this.leave = new Leave();
				} else {
					System.out.println("Some error while inserting leave");
					this.leave = null;
				}

				while (rs.next()) {
					this.leave.setLeave_emp_id(rs.getInt(1));
					this.leave.setLeave_id(rs.getInt(2));
					this.leave.setLeave_type(rs.getString(3));
					this.leave.setLeave_from(rs.getDate(4).toLocalDate());
					this.leave.setLeave_till(rs.getDate(5).toLocalDate());
					this.leave.setLeave_total(rs.getInt(6));
					this.leave.setLeave_taken(rs.getInt(7));
					this.leave.setLeave_left(rs.getInt(8));
				}
				rs.close();
				trs.close();
				rs_id.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exiting POST leave Dao safely");
		return this.leave;
	}

	public Leave putLeaveDao(int emp_id, int leave_id, Leave leave) {
		ResultSet rs = null;
		ResultSet rid = null;
		try {
			int lID = 0;
			querry = "select Leave_Employee_Id from leaves l INNER join employees e on l.Leave_Employee_Id=e.Employee_Id where l.Leave_Id="
					+ String.valueOf(leave_id) + " and Leave_Employee_Id=" + String.valueOf(emp_id);
			rid = repo.executeQuerryRep(querry);
			if (rid.next()) {
				lID = rid.getInt(1);
				System.out.println("Found leave id : " + lID);
			}
			rs = repo.executeQuerryRep(
					"select Leave_Employee_Id, Leave_Id, Leave_Type, Leave_From, Leave_Till from leaves where Leave_Id="
							+ leave_id + " and Leave_Employee_Id=" + lID);
			if (rs.next()) {
				this.leave = new Leave();
			} else {
				this.leave = null;
				System.out.println("Error Fetching leave details of Employee id " + lID + " with leave id " + leave_id);
			}
			if (leave.getLeave_emp_id() != 0) {
				rs.updateInt("Leave_Employee_Id", leave.getLeave_emp_id());
			}
			if (leave.getLeave_id() != 0) {
				rs.updateInt("Leave_Id", leave.getLeave_id());
			}
			if (leave.getLeave_type() != null) {
				rs.updateString("Leave_Type", leave.getLeave_type());
			}
			if (leave.getLeave_from() != null) {
				rs.updateDate("Leave_From", java.sql.Date.valueOf(leave.getLeave_from()));
			}
			if (leave.getLeave_till() != null) {
				rs.updateDate("Leave_Till", java.sql.Date.valueOf(leave.getLeave_till()));
			}
			rs.updateRow();
			ResultSet ret = repo.executeQuerryRep(
					"Select * from leaves where Leave_Employee_Id=" + emp_id + " and Leave_Id=" + leave_id);
			while (ret.next()) {

				this.leave.setLeave_emp_id(ret.getInt(1));
				this.leave.setLeave_id(ret.getInt(2));
				this.leave.setLeave_type(ret.getString(3));
				this.leave.setLeave_from(ret.getDate(4).toLocalDate());
				this.leave.setLeave_till(ret.getDate(5).toLocalDate());
				this.leave.setLeave_taken(ret.getInt(7));
				this.leave.setLeave_left(ret.getInt(8));
			}
			System.out.println("Updating Track_left  in track_leaves table from leaves_left in Leaves table");
			repo.executeUpdateRep(
					"update track_leaves t1 join leaves t2 on t1.Emp_Id = t2.Leave_Employee_Id set t1.Track_Left = t2.Leaves_Left where t1.Emp_Id="
							+ emp_id + " and t2.Leave_Id=" + leave_id);
			ret.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				rid.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("Exiting Put Dao Safely");
		return this.leave;
	}

	public boolean deleteLeaveDao(int emp_id, int leave_id) {
		ResultSet rs = null;
		System.out
				.println("Reached within Delete Leave Dao with Employee id :" + emp_id + " and leave id :" + leave_id);
		boolean rid = false;
		querry = "select * from leaves where Leave_Employee_Id=" + String.valueOf(emp_id) + " and Leave_Id="
				+ String.valueOf(leave_id);

		try {

			rs = repo.executeQuerryRep(querry);
			if (rs.isBeforeFirst()) {
				this.leave = new Leave();
			} else {
				System.out.println("Exiting: No leaves Found");
				this.leave = null;
				return rid;
			}
			while (rs.next()) {
				this.leave.setLeave_emp_id(rs.getInt(1));
				this.leave.setLeave_id(rs.getInt(2));
				this.leave.setLeave_type(rs.getString(3));
				this.leave.setLeave_from(rs.getDate(4).toLocalDate());
				this.leave.setLeave_till(rs.getDate(5).toLocalDate());
				this.leave.setLeave_total(rs.getInt(6));
				this.leave.setLeave_taken(rs.getInt(7));
				this.leave.setLeave_left(rs.getInt(8));
			}
			rs.absolute(1);
			rs.deleteRow();
			System.out.println("Updating Track_left  in track_leaves table from Tracked_Leaves in Leaves table");
			int dRa = repo.executeUpdateRep(
					"update track_leaves l1 inner join leaves l2 set l1.Track_Left=l2.Tracked_Leaves where l1.Emp_Id="
							+ emp_id);
			if (dRa == 0) {
				System.out.println(
						"Deleting record of Track_left in track_leaves table since no record found in leaves table");
				repo.executeUpdateRep("delete from track_leaves where Emp_Id=" + emp_id);
			}
			rs.beforeFirst();
			rs.next();
			rid = !rs.first();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		querry = "DELETE l from leaves l INNER join employees e on l.Leave_Employee_Id=e.Employee_Id where l.Leave_Employee_Id="
//				+ String.valueOf(emp_id) + " and l.Leave_Id=" + String.valueOf(leave_id);
//		System.out.println("\n \n"+this.leave.toString());

//		ra = repo.executeUpdateRep(querry);
//		if (ra == 0) {
//			return 0;
//		} else
		System.out.println("exiting dao " + ((rid) ? "successfully" : "with errors"));
		return rid;

	}

}
