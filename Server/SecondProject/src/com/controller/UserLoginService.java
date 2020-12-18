package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.front.FrontInterface;
import com.model.UserDAO;
import com.model.UserDTO;

public class UserLoginService implements FrontInterface{
	
	private UserDAO dao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	PrintWriter out;
	UserDTO dto=null;
	
	public UserLoginService(UserDAO dao, HttpServletRequest request,HttpServletResponse response,PrintWriter out) {
		super();
		this.dao = dao;
		this.request = request;
		this.response=response;
		this.out=out;
		
	}

	@Override
	public void execute() {
		String id=request.getParameter("id");
		System.out.println("id in login: "+id);
		String pw=request.getParameter("pw");
		System.out.println("pw in login: "+pw);
		
		// 로그인 시도
		dto=dao.userLogin(id, pw);
		String name=dto.getName();
		System.out.println(name);
		response.setCharacterEncoding("euc-kr");
		out.print(name); // android로 name 리턴해주기
	}

}
