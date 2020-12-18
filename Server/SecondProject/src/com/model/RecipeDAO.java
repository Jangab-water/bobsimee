package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeDAO {
	private final String URL = "jdbc:oracle:thin:@smhrdai.cwnxiom872oa.ap-northeast-2.rds.amazonaws.com:1521/ORCL";
	private final String USER = "smhrd";
	private final String PASSWORD = "hkread132";

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	
	RecipeDTO dto=null;

	private Connection conn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다");
		} catch (SQLException e) {
			System.out.println("DBMS에 접속할 수 없습니다:URL, ID, PW확인바랍니다");
		}
		return conn;
	}

	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public ArrayList<RecipeDTO> selectFood(String ingredient1, String ingredient2){
		ArrayList<RecipeDTO> arr=new ArrayList<>();
//		select * from users where id like '%a%' and id like '%m%';
		String sql="select * from recipe where f_ingredient='%?%' and f_ingredient='%?%'";

		try {
			conn=conn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, ingredient1);
			ps.setString(2, ingredient2);
			rs=ps.executeQuery();
			while(rs.next()) {
				String recipeName=rs.getString(0);
				String recipeUrl=rs.getString(1);
				String recipeIngredient=rs.getString(2);
				String recipeRecipe=rs.getString(3);
				dto=new RecipeDTO(recipeName,recipeUrl,recipeIngredient,recipeRecipe);
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}

}