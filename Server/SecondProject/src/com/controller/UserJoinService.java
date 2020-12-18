package com.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.front.FrontInterface;
import com.model.UserDAO;
import com.model.UserDTO;

public class UserJoinService implements FrontInterface {

	private UserDAO dao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private PrintWriter out;
	
	private int check = 0;

	public UserJoinService(UserDAO dao, HttpServletRequest request,HttpServletResponse response,PrintWriter out) {
		super();
		this.dao = dao;
		this.request = request;
		this.response=response;
		this.out=out;
	}

	@Override
	public void execute() {

		encodingChar();

		String id = request.getParameter("id");
		System.out.println("id in con=" + id);
		String pw = request.getParameter("pw");
		System.out.println("pw in con=" + pw);
		String name = request.getParameter("name");
		System.out.println("name in con=" + name);
		
		// DTO 객체로 패키징
		UserDTO dto = new UserDTO(id, pw, name);
		
		//로그인 시도
		check = dao.userJoin(dto);
		
		//return된 check 값에 따라 Exception 판별
		if (check > 0) {
			System.out.println("회원가입 성공");
		} else if (check == -1) {
			System.out.println("회원가입 실패: DB에 세팅 불가능");
		} else if (check == -2) {
			System.out.println("회원가입 실패: 잘못된 접근");
		}
		out.print(check);
	}
	
	// request 인코딩
	private void encodingChar() {
		try {
			request.setCharacterEncoding("euc-kr");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("인코딩 호환 불가능");
		}
	}

}
