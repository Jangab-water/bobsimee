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
			System.out.println("����̹��� ã�� �� �����ϴ�");
		} catch (SQLException e) {
			System.out.println("DBMS�� ������ �� �����ϴ�:URL, ID, PWȮ�ιٶ��ϴ�");
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
	
	// ȸ������ ���
	public int userJoin(UserDTO dto) {
		String sql="insert into users values(?,?,?)";
		try {
			conn=conn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			ps.setString(3, dto.getName());
			check=ps.executeUpdate();
		} catch (SQLException e) { // SQLException: ORD ����
			System.out.println("DB�� ���� ������ �� �����ϴ�");
			return -1;
		} catch(Exception e) {
			e.printStackTrace();
			return -2;
		} finally {
			close();
			
		}
		return check;
	}
	
	// �α��� ���
	public UserDTO userLogin(String id, String pw) {
		String sql="select * from users where id=? and pw=?";
		
		// �α��� id �� pw ����
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
				System.out.println("��ġ�ϴ� ������ �����ϴ�");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�α��� ����");
			return null;
		} finally {
			close();
			
		}
		return dto;
	}

}
