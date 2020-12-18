package com.model;

public class RecipeDTO {
	private String r_name;
	private String r_url;
	private String r_ingredient;
	private String r_recipe;
	
	public RecipeDTO() {
		super();
	}

	public RecipeDTO(String r_name, String r_url, String r_ingredient, String r_recipe) {
		super();
		this.r_name = r_name;
		this.r_url = r_url;
		this.r_ingredient = r_ingredient;
		this.r_recipe = r_recipe;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public String getR_url() {
		return r_url;
	}

	public void setR_url(String r_url) {
		this.r_url = r_url;
	}

	public String getR_ingredient() {
		return r_ingredient;
	}

	public void setR_ingredient(String r_ingredient) {
		this.r_ingredient = r_ingredient;
	}

	public String getR_recipe() {
		return r_recipe;
	}

	public void setR_recipe(String r_recipe) {
		this.r_recipe = r_recipe;
	}

	@Override
	public String toString() {
		return "RecipeDTO [r_name=" + r_name + ", r_url=" + r_url + ", r_ingredient=" + r_ingredient + ", r_recipe="
				+ r_recipe + "]";
	}
	
	

}
