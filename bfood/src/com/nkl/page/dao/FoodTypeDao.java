package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.page.domain.FoodType;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class FoodTypeDao {

	public int addFoodType(FoodType foodType, Connection conn){
		String sql = "INSERT INTO food_type(food_type_id,food_type_name) values(null,?)";
		Object[] params = new Object[] {
			foodType.getFood_type_name()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delFoodType(String food_type_id, Connection conn){
		String sql = "DELETE FROM food_type WHERE food_type_id=?";

		Object[] params = new Object[] { new Integer(food_type_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delFoodTypes(String[] food_type_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <food_type_ids.length; i++) {
			sBuilder.append("?");
			if (i !=food_type_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM food_type WHERE food_type_id IN(" +sBuilder.toString()+")";

		Object[] params = food_type_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateFoodType(FoodType foodType, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE food_type SET food_type_id = " + foodType.getFood_type_id() +" ");
		if (!StringUtil.isEmptyString(foodType.getFood_type_name())) {
			sBuilder.append(" ,food_type_name = '" + foodType.getFood_type_name() +"' ");
		}
		sBuilder.append(" where food_type_id = " + foodType.getFood_type_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public FoodType getFoodType(FoodType foodType, Connection conn){
		FoodType _foodType=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM food_type WHERE 1=1");
		if (foodType.getFood_type_id()!=0) {
			sBuilder.append(" and food_type_id = " + foodType.getFood_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(foodType.getFood_type_name())) {
			sBuilder.append(" and food_type_name = '" + foodType.getFood_type_name() +"' ");
		}

		List<Object> list = BaseDao.executeQuery(FoodType.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _foodType = (FoodType)list.get(0);
		}
		return _foodType;
	}

	public List<FoodType>  listFoodTypes(FoodType foodType, Connection conn){
		List<FoodType> foodTypes = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM food_type WHERE 1=1");

		if (foodType.getFood_type_id()!=0) {
			sBuilder.append(" and food_type_id = " + foodType.getFood_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(foodType.getFood_type_name())) {
			sBuilder.append(" and food_type_name like '%" + foodType.getFood_type_name() +"%' ");
		}
		sBuilder.append(" order by food_type_id asc) t");

		if (foodType.getStart() != -1) {
			sBuilder.append(" limit " + foodType.getStart() + "," + foodType.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(FoodType.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			foodTypes = new ArrayList<FoodType>();
			for (Object object : list) {
				foodTypes.add((FoodType)object);
			}
		}
		return foodTypes;
	}

	public int  listFoodTypesCount(FoodType foodType, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM food_type WHERE 1=1");

		if (foodType.getFood_type_id()!=0) {
			sBuilder.append(" and food_type_id = " + foodType.getFood_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(foodType.getFood_type_name())) {
			sBuilder.append(" and food_type_name like '%" + foodType.getFood_type_name() +"%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
