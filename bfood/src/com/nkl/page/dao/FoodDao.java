package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Food;

public class FoodDao {

	public int addFood(Food food, Connection conn){
		String sql = "INSERT INTO food(food_id,food_type_id,food_name,food_pic,food_author,food_desc," +
				"food_date,food_click,user_id) values(null,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			food.getFood_type_id(),
			food.getFood_name(),
			food.getFood_pic(),
			food.getFood_author(),
			food.getFood_desc(),
			food.getFood_date(),
			food.getFood_click(),
			food.getUser_id()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delFood(String food_id, Connection conn){
		String sql = "DELETE FROM food WHERE food_id=?";

		Object[] params = new Object[] { new Integer(food_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delFoods(String[] food_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <food_ids.length; i++) {
			sBuilder.append("?");
			if (i !=food_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM food WHERE food_id IN(" +sBuilder.toString()+")";

		Object[] params = food_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateFood(Food food, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE food SET food_id = " + food.getFood_id() +" ");
		if (food.getFood_type_id()!=0) {
			sBuilder.append(" ,food_type_id = " + food.getFood_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(food.getFood_name())) {
			sBuilder.append(" ,food_name = '" + food.getFood_name() +"' ");
		}
		if (!StringUtil.isEmptyString(food.getFood_pic())) {
			sBuilder.append(" ,food_pic = '" + food.getFood_pic() +"' ");
		}
		if (!StringUtil.isEmptyString(food.getFood_isbn())) {
			sBuilder.append(" ,food_isbn = '" + food.getFood_isbn() +"' ");
		}
		if (!StringUtil.isEmptyString(food.getFood_author())) {
			sBuilder.append(" ,food_author = '" + food.getFood_author() +"' ");
		}
		if (!StringUtil.isEmptyString(food.getFood_desc())) {
			sBuilder.append(" ,food_desc = '" + food.getFood_desc() +"' ");
		}
		
		sBuilder.append(" where food_id = " + food.getFood_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}
	
	public int updateFoodClick(Food food, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE food SET food_id = " + food.getFood_id() +" ");
		if (food.getFood_click()!=-1) {
			sBuilder.append(" ,food_click = " + food.getFood_click() +" ");
		}
		sBuilder.append(" where food_id = " + food.getFood_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public Food getFood(Food food, Connection conn){
		Food _food=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT b.*,bt.food_type_name,u.real_name FROM food b " +
		         " join user u on b.user_id=u.user_id " +
		         " left join food_type bt on b.food_type_id=bt.food_type_id WHERE 1=1 ");
		if (food.getFood_id()!=0) {
			sBuilder.append(" and food_id = " + food.getFood_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Food.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _food = (Food)list.get(0);
		}
		return _food;
	}

	public List<Food>  listFoods(Food food, Connection conn){
		List<Food> foods = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT b.*,bt.food_type_name,u.real_name FROM food b " +
				         " join user u on b.user_id=u.user_id " +
				         " left join food_type bt on b.food_type_id=bt.food_type_id WHERE 1=1 ");

		if (food.getFood_id()!=0) {
			sBuilder.append(" and food_id = " + food.getFood_id() +" ");
		}
		if (!StringUtil.isEmptyString(food.getFood_name())) {
			sBuilder.append(" and food_name like '%" + food.getFood_name() +"%' ");
		}
		if (food.getUser_id()!=0) {
			sBuilder.append(" and b.user_id = " + food.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(food.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + food.getReal_name() +"%' ");
		}
		if (food.getFood_type_id()!=0) {
			sBuilder.append(" and b.food_type_id = " + food.getFood_type_id() +" ");
		}
		if (food.getTop_flag()==1) {
			sBuilder.append(" order by food_click desc,food_id asc) t");
		}else {
			sBuilder.append(" order by food_date desc,food_id asc) t");
		}
		

		if (food.getStart() != -1) {
			sBuilder.append(" limit " + food.getStart() + "," + food.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Food.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			foods = new ArrayList<Food>();
			for (Object object : list) {
				foods.add((Food)object);
			}
		}
		return foods;
	}

	public int  listFoodsCount(Food food, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM food b " +
		         " join user u on b.user_id=u.user_id " +
		         " left join food_type bt on b.food_type_id=bt.food_type_id WHERE 1=1 ");

		if (food.getFood_id()!=0) {
			sBuilder.append(" and food_id = " + food.getFood_id() +" ");
		}
		if (!StringUtil.isEmptyString(food.getFood_name())) {
			sBuilder.append(" and food_name like '%" + food.getFood_name() +"%' ");
		}
		if (food.getUser_id()!=0) {
			sBuilder.append(" and b.user_id = " + food.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(food.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + food.getReal_name() +"%' ");
		}
		if (food.getFood_type_id()!=0) {
			sBuilder.append(" and b.food_type_id = " + food.getFood_type_id() +" ");
		}
		
		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}
	
}
