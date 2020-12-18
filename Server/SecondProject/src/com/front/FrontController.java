package com.front;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.RecipeSelectService;
import com.controller.UserJoinService;
import com.controller.UserLoginService;
import com.model.UserDAO;
import com.model.UserDTO;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/* http://220.95.45.219:8099/SecondProject/FrontController */
	// 클라이언트로부터 직접적으로 데이터를 받는 class
	
	UserDTO user=null;
	UserDAO dao=new UserDAO();
	FrontInterface fi=null;
	

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		// 서버 URI 받기
		String URI = request.getRequestURI();// 프로젝트 이름/이동한action의 이름
		System.out.println("URI="+URI);
		String path = request.getContextPath(); // 프로젝트 이름 출력
		System.out.println("path="+path);
		String resultURI = URI.substring(path.length() + 1);
		System.out.println("resultURI="+resultURI);
		
		// upcasting을 활용한 코딩 단순화
		if(resultURI.equals("join.do")) { // 회원가입: JoinActivity로부터
			System.out.println("redirect to UserJoinService");
			fi=new UserJoinService(dao,request,response,out);
		} else if(resultURI.equals("login.do")) { // 로그인: MainActivity로부터
			System.out.println("redirect to UserLoginService");
			fi=new UserLoginService(dao,request,response,out);
		} else if(resultURI.equals("ingredient.do")) {
			System.out.println("redirect to RecipeSelectService");
			fi=new RecipeSelectService(dao,request,response,out);
		}
		fi.execute();
	}
}
