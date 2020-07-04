package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class FoodType extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1770185824735782580L;
	private int food_type_id; // 
	private String food_type_name; // 

	private String ids;

	public int getFood_type_id() {
		return food_type_id;
	}

	public void setFood_type_id(int food_type_id) {
		this.food_type_id = food_type_id;
	}

	public String getFood_type_name() {
		return food_type_name;
	}

	public void setFood_type_name(String food_type_name) {
		this.food_type_name = food_type_name;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
