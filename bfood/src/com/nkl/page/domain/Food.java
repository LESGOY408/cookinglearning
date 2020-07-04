package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Food extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 8782325810716469437L;
	private int food_id; // 
	private int user_id; // 
	private int food_type_id; // 
	private String food_name; // 
	private String food_pic; // 
	private String food_isbn; // 
	private String food_author; // 
	private String food_desc; // 
	private String food_date; // 
	private int food_click; // 

	private String real_name; // 
	private String food_type_name; // 
	private int top_flag; 
	private String ids;
	private String random;
	
	public void setFood_id(int food_id){
		this.food_id=food_id;
	}

	public int getFood_id(){
		return food_id;
	}

	public void setFood_type_id(int food_type_id){
		this.food_type_id=food_type_id;
	}

	public int getFood_type_id(){
		return food_type_id;
	}

	public void setFood_name(String food_name){
		this.food_name=food_name;
	}

	public String getFood_name(){
		return food_name;
	}

	public void setFood_pic(String food_pic){
		this.food_pic=food_pic;
	}

	public String getFood_pic(){
		return food_pic;
	}

	public void setFood_isbn(String food_isbn){
		this.food_isbn=food_isbn;
	}

	public String getFood_isbn(){
		return food_isbn;
	}

	public void setFood_author(String food_author){
		this.food_author=food_author;
	}

	public String getFood_author(){
		return food_author;
	}

	public void setFood_desc(String food_desc){
		this.food_desc=food_desc;
	}

	public String getFood_desc(){
		return food_desc;
	}
	
	public String getFood_descShow(){
		if (!StringUtil.isEmptyString(food_desc)) {
			return Transcode.htmlDiscode(food_desc);
		}
		return food_desc;
	}

	public void setFood_date(String food_date){
		this.food_date=food_date;
	}

	public String getFood_date(){
		return food_date;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

	public String getFood_type_name() {
		return food_type_name;
	}

	public void setFood_type_name(String food_type_name) {
		this.food_type_name = food_type_name;
	}

	public int getUser_id() {
		return user_id;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public int getFood_click() {
		return food_click;
	}

	public void setFood_click(int food_click) {
		this.food_click = food_click;
	}

	public int getTop_flag() {
		return top_flag;
	}

	public void setTop_flag(int top_flag) {
		this.top_flag = top_flag;
	}

}
