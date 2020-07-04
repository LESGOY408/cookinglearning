package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class User extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 935450826788987376L;
	private int user_id; // 
	private String login_name; // 
	private String login_pass; // 
	private String real_name; // 
	private String nick_name; // 
	private String user_lxdz; // 
	private String user_mail; // 
	private String user_mobile; // 
	private double user_money; // 
	private String user_pic; // 
	private String add_date; // 
	private int user_type; // 1：注册用户 2：系统管理员 
	
	private String ids; //  
	private String random;
	private String user_types;
	
	public String getUser_typeDesc() {
		switch (user_type) {
		case 1:
			return "注册用户"; 
		case 2:
			return "系统管理员";
		default:
			return "";
		}
	}
	
	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setLogin_name(String login_name){
		this.login_name=login_name;
	}

	public String getLogin_name(){
		return login_name;
	}

	public void setLogin_pass(String login_pass){
		this.login_pass=login_pass;
	}

	public String getLogin_pass(){
		return login_pass;
	}

	public void setUser_mail(String user_mail){
		this.user_mail=user_mail;
	}

	public String getUser_mail(){
		return user_mail;
	}

	public void setReal_name(String real_name){
		this.real_name=real_name;
	}

	public String getReal_name(){
		return real_name;
	}

	public void setAdd_date(String add_date){
		this.add_date=add_date;
	}

	public String getAdd_date(){
		return add_date;
	}

	public void setUser_type(int user_type){
		this.user_type=user_type;
	}

	public int getUser_type(){
		return user_type;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getUser_types() {
		return user_types;
	}

	public void setUser_types(String user_types) {
		this.user_types = user_types;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getUser_lxdz() {
		return user_lxdz;
	}

	public void setUser_lxdz(String user_lxdz) {
		this.user_lxdz = user_lxdz;
	}

	public String getUser_mobile() {
		return user_mobile;
	}

	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}

	public double getUser_money() {
		return user_money;
	}

	public String getUser_pic() {
		return user_pic;
	}

	public void setUser_money(double user_money) {
		this.user_money = user_money;
	}

	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}


}
