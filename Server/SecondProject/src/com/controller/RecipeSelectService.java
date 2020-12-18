package com.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.front.FrontInterface;
import com.model.RecipeDAO;
import com.model.RecipeDTO;
import com.model.UserDAO;

public class RecipeSelectService implements FrontInterface{
	
	private UserDAO dao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private PrintWriter out;
	

	public RecipeSelectService(UserDAO dao, HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		this.dao=dao;
		this.request=request;
		this.response=response;
		this.out=out;
	}

	@Override
	public void execute() {
		String ingredient1=request.getParameter("ingredient1");
		System.out.println("ingredient1="+ingredient1);
		String ingredient2=request.getParameter("ingredient2");
		System.out.println("ingredient2="+ingredient2);
		
		RecipeDAO dao=new RecipeDAO();
		ArrayList<RecipeDTO> arr=dao.selectFood(ingredient1,ingredient2);
		
		JSONObject jobj=new JSONObject();
		for(int i=0;i<arr.size();i++) {
			out.print(arr.get(i).getR_name());
			out.print(arr.get(i).getR_url());
			out.print(arr.get(i).getR_ingredient());
			out.print(arr.get(i).getR_recipe());
		}
	}

}
