import java.sql.*;

public class Main {

	public static void main(String[] args) {
		Connection con = null;
		
		try {
			// SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");
			
			// SQLite 데이터베이스 파일에 연결
			String dbFile = "myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			System.out.println("\n*** 데이터 조회 ***");
			Statement stat1 = con.createStatement();
			String sql1 = "select * from g_artists;"; 
			ResultSet rs1 = stat1.executeQuery(sql1);
			while(rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				System.out.println(id + " " + name);
			}
			stat1.close();
			
			// 데이터 추가
			System.out.println("\n*** 새 데이터 추가 ***");
			Statement stat2 = con.createStatement();
			String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate)" 
			+ " values ('아이유','여성','2000년대','2008',datetime('now','localtime'));";
			int cnt2 = stat2.executeUpdate(sql2);
			if(cnt2>0)
				System.out.println("새로운 데이터 추가됨!");
			else 
				System.out.println("[Error] 데이터 추가 오류!");
			stat2.close();
			
			// 데이터 수정
			System.out.println("\n*** 데이터 수정 ***");
			Statement stat3 = con.createStatement();
			String sql3 = "update g_artists set a_year = '2000s, 2010s, 2020s' " 
			+"where id = 1;";
			int cnt3 = stat3.executeUpdate(sql3);
			if(cnt3>0)
				System.out.println("데이터 수정됨!");
			else 
				System.out.println("[Error] 데이터 수정 오류!");
			stat3.close();
			
			// 데이터 삭제
			System.out.println("\n*** 데이터 삭제 ***");
			Statement stat4 = con.createStatement();
			String sql4 = "delete from g_artists where id = 6;";
			int cnt4 = stat4.executeUpdate(sql4);
			if(cnt4>0)
				System.out.println("데이터 삭제됨!");
			else 
				System.out.println("[Error] 데이터 삭제 오류!");
			stat4.close();
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {}
			}
		}
		
		
		
	}

}
