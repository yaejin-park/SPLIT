package Split_Project0827;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.db.DbConnect;

public class SplitDBModel {
	DbConnect db = new DbConnect();

	public SplitDBModel(){
	}

	public void personInsert(SplitPerson_DTO dto) {
		Connection conn = db.getCloudOracle();
		PreparedStatement pstmt = null;

		String sql = "insert into Split_person values (?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getBank());
			pstmt.setString(3, dto.getCount());

			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
	}

	public void personUpdate(SplitPerson_DTO dto) {
		Connection conn = db.getCloudOracle();
		PreparedStatement pstmt = null;

		String sql = "update Split_person set bank = ?,count =? where name = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBank());
			pstmt.setString(2, dto.getCount());
			pstmt.setString(3, dto.getName());

			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
	}
	
	public void personDelte(SplitPerson_DTO dto) {
		Connection conn = db.getCloudOracle();
		PreparedStatement pstmt = null;

		String sql = "delete from Split_person where name = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());

			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
	}

	public ArrayList<SplitPerson_DTO> getAllPerson() {
		ArrayList<SplitPerson_DTO> list = new ArrayList<SplitPerson_DTO>();

		Connection conn = db.getCloudOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from Split_person";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				SplitPerson_DTO dto = new SplitPerson_DTO();

				dto.setName(rs.getString("name"));
				dto.setBank(rs.getString("bank"));
				dto.setCount(rs.getString("count"));

				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//consum¿¡ Ãß°¡
	public void consumInsert(SplitCon_DTO cdto) {
		Connection conn = db.getCloudOracle();
		PreparedStatement pstmt = null;
		
//		String sql = "insert into consum values(?, ?, ?, to_char(?,'L9,999,999'),?,to_char(sysdate,'MM/DD HH24:DD'),?)";
		String sql = "insert into consum values(?, ?, ?, ?, ?,to_char(sysdate,'MM/DD HH24:DD'),?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cdto.getName());
			pstmt.setString(2, cdto.getConName());
			pstmt.setString(3, cdto.getConType());
			pstmt.setString(4, cdto.getConPrice());
			pstmt.setString(5, cdto.getConExcept());
			pstmt.setString(6, cdto.getImageName());

			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
	}
		
	public ArrayList<SplitCon_DTO> getAllList() {
		ArrayList<SplitCon_DTO> list = new ArrayList<SplitCon_DTO>();

		Connection conn = db.getCloudOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from consum";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				SplitCon_DTO cdto = new SplitCon_DTO();

				cdto.setName(rs.getString("name"));
				cdto.setConName(rs.getString("conName"));
				cdto.setConType(rs.getString("conType"));
				cdto.setConPrice(rs.getString("conPrice"));
				cdto.setConExcept(rs.getString("conPerson"));
				cdto.setConTime(rs.getString("conTime"));
				cdto.setImageName(rs.getString("imageName"));

				list.add(cdto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return list;
	}
	
	public ArrayList<SplitCon_DTO> getNameList(String name) {
		ArrayList<SplitCon_DTO> list = new ArrayList<SplitCon_DTO>();

		Connection conn = db.getCloudOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from consum where name =?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SplitCon_DTO cdto = new SplitCon_DTO();

				cdto.setName(rs.getString("name"));
				cdto.setConName(rs.getString("conName"));
				cdto.setConType(rs.getString("conType"));
				cdto.setConPrice(rs.getString("conPrice"));
				cdto.setConExcept(rs.getString("conPerson"));
				cdto.setConTime(rs.getString("conTime"));
				cdto.setImageName(rs.getString("imageName"));

				list.add(cdto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return list;
	}
	
	public ArrayList<SplitCon_DTO> getTypeList(String type) {
		ArrayList<SplitCon_DTO> list = new ArrayList<SplitCon_DTO>();

		Connection conn = db.getCloudOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from consum where conType =?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SplitCon_DTO cdto = new SplitCon_DTO();

				cdto.setName(rs.getString("name"));
				cdto.setConName(rs.getString("conName"));
				cdto.setConType(rs.getString("conType"));
				cdto.setConPrice(rs.getString("conPrice"));
				cdto.setConExcept(rs.getString("conPerson"));
				cdto.setConTime(rs.getString("conTime"));
				cdto.setImageName(rs.getString("imageName"));

				list.add(cdto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return list;
	}
	
	public String getsumPrice() {
		Connection conn = db.getCloudOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select sum(conprice) totPay from consum";
		String sum = "0";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sum = rs.getString("totPay");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return sum;
	}
	
	public String getsumPriceN(String name) {
		Connection conn = db.getCloudOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select sum(conprice) totPay from consum where name = ?";
		String sum = "0";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sum = rs.getString("totPay");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return sum;
	}
	
	public String getsumPriceT(String type) {
		Connection conn = db.getCloudOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select sum(conprice) totPay from consum where contype = ?";
		String sum = "0";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sum = rs.getString("totPay");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return sum;
	}

	
	public String getImageName(String name, String conName, String conPrice) {
		Connection conn = db.getCloudOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select imageName from consum where Name = ? and conName = ? and conprice =?";
		String imageName = "";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, conName);
			pstmt.setString(3, conPrice);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				imageName = rs.getString("imageName");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return imageName;
	}
	
	
	public ArrayList<SplitCon_DTO> getPriceN(String name) {
		Connection conn = db.getCloudOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<SplitCon_DTO> list = new ArrayList<SplitCon_DTO>();
		String sql = "select conprice,conperson from consum where conperson not like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SplitCon_DTO cdto = new SplitCon_DTO();

				cdto.setConPrice(rs.getString("conPrice"));
				cdto.setConExcept(rs.getString("conPerson"));

				list.add(cdto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return list;
	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
