package com.model;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private final String URL="jdbc:oracle:thin:@smhrdai.cwnxiom872oa.ap-northeast-2.rds.amazonaws.com:1521/ORCL";
	private final String USER="smhrd";
	private final String PASSWORD="hkread132";
	
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	int check=0;
	UserDTO dto=null;
	
	private Connection conn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다");
		} catch (SQLException e) {
			System.out.println("DBMS에 접속할 수 없습니다:URL, ID, PW확인바랍니다");
		}
		return conn;
	}
	
	private void close() {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 회원가입 기능
	public int userJoin(UserDTO dto) {
		String sql="insert into users values(?,?,?)";
		try {
			conn=conn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			ps.setString(3, dto.getName());
			check=ps.executeUpdate();
		} catch (SQLException e) { // SQLException: ORD 에러
			System.out.println("DB에 값을 세팅할 수 없습니다");
			return -1;
		} catch(Exception e) {
			e.printStackTrace();
			return -2;
		} finally {
			close();
			
		}
		return check;
	}
	
	// 로그인 기능
	public UserDTO userLogin(String id, String pw) {
		String sql="select * from users where id=? and pw=?";
		
		// 로그인 id 및 pw 검증
		try {
			conn=conn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			rs=ps.executeQuery();
			if(rs.next()) {
				String rs_id=rs.getString(1);
				String rs_pw=rs.getString(2);
				String rs_name=rs.getString(3);
				dto=new UserDTO(rs_id,rs_pw,rs_name);
			} else {
				System.out.println("일치하는 계정이 없습니다");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("로그인 실패");
			return null;
		} finally {
			close();
			
		}
		return dto;
	}

}
