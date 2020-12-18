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
		
		// DTO ��ü�� ��Ű¡
		UserDTO dto = new UserDTO(id, pw, name);
		
		//�α��� �õ�
		check = dao.userJoin(dto);
		
		//return�� check ���� ���� Exception �Ǻ�
		if (check > 0) {
			System.out.println("ȸ������ ����");
		} else if (check == -1) {
			System.out.println("ȸ������ ����: DB�� ���� �Ұ���");
		} else if (check == -2) {
			System.out.println("ȸ������ ����: �߸��� ����");
		}
		out.print(check);
	}
	
	// request ���ڵ�
	private void encodingChar() {
		try {
			request.setCharacterEncoding("euc-kr");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("���ڵ� ȣȯ �Ұ���");
		}
	}

}
