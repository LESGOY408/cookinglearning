package com.nkl.page.domain;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Sblog extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -5153140362380984117L;
	private int sblog_id; // 
	private int food_id; // 
	private int user_id; // 
	private String nick_name; // 
	private String nick_name2; //
	private int sblog_id2; // 0表示评论
	private String user_pic; // 
	private String sblog_content; // 
	private String sblog_date; // 

	List<Sblog> replys = new ArrayList<Sblog>();
	private String user_pic2; // 
	private String food_name; // 
	private String ids;
	private String random;
	
	public String getSblog_contentShow(){
		if (!StringUtil.isEmptyString(sblog_content)) {
			return Transcode.htmlDiscode(sblog_content);
		}
		return sblog_content;
	}

	public void setSblog_id(int sblog_id){
		this.sblog_id=sblog_id;
	}

	public int getSblog_id(){
		return sblog_id;
	}

	public void setFood_id(int food_id){
		this.food_id=food_id;
	}

	public int getFood_id(){
		return food_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setNick_name(String nick_name){
		this.nick_name=nick_name;
	}

	public String getNick_name(){
		return nick_name;
	}

	public void setUser_pic(String user_pic){
		this.user_pic=user_pic;
	}

	public String getUser_pic(){
		if (!StringUtil.isEmptyString(user_pic2)) {
			return user_pic2;
		}
		return user_pic;
	}

	public void setSblog_content(String sblog_content){
		this.sblog_content=sblog_content;
	}

	public String getSblog_content(){
		return sblog_content;
	}

	public void setSblog_date(String sblog_date){
		this.sblog_date=sblog_date;
	}

	public String getSblog_date(){
		return sblog_date;
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

	public String getNick_name2() {
		return nick_name2;
	}

	public void setNick_name2(String nick_name2) {
		this.nick_name2 = nick_name2;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public int getSblog_id2() {
		return sblog_id2;
	}

	public void setSblog_id2(int sblog_id2) {
		this.sblog_id2 = sblog_id2;
	}

	public List<Sblog> getReplys() {
		return replys;
	}

	public void setReplys(List<Sblog> replys) {
		this.replys = replys;
	}

	public String getUser_pic2() {
		return user_pic2;
	}

	public void setUser_pic2(String user_pic2) {
		this.user_pic2 = user_pic2;
	}

}
