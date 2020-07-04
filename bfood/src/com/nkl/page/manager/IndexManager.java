package com.nkl.page.manager;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;
import com.nkl.page.dao.FoodDao;
import com.nkl.page.dao.FoodTypeDao;
import com.nkl.page.dao.SblogDao;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.Food;
import com.nkl.page.domain.FoodType;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.User;

public class IndexManager {

	UserDao userDao = new UserDao();
	FoodTypeDao foodTypeDao = new FoodTypeDao();
	FoodDao foodDao = new FoodDao();
	SblogDao sblogDao = new SblogDao();
	
	/**
	 * @Title: getUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User  getUser(User user){
		Connection conn = BaseDao.getConnection();
		User _user = userDao.getUser(user, conn);
		BaseDao.closeDB(null, null, conn);
		return _user;
	}
	 
	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void  updateUser(User user){
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getLogin_pass())) {
			user.setLogin_pass(Md5.makeMd5(user.getLogin_pass()));
		}
		userDao.updateUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listFoodTypes
	 * @Description: 菜谱类型查询
	 * @param foodType
	 * @return List<FoodType>
	 */
	public List<FoodType> listFoodTypes(FoodType foodType, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = foodTypeDao.listFoodTypesCount(foodType, conn);
		}
		List<FoodType> foodTypes = foodTypeDao.listFoodTypes(foodType, conn);

		BaseDao.closeDB(null, null, conn);
		return foodTypes;
	}

	/**
	 * @Title: listFoods
	 * @Description: 菜谱查询
	 * @param food
	 * @return List<Food>
	 */
	public List<Food> listFoods(Food food, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = foodDao.listFoodsCount(food, conn);
		}
		List<Food> foods = foodDao.listFoods(food, conn);

		BaseDao.closeDB(null, null, conn);
		return foods;
	}

	/**
	 * @Title: queryFood
	 * @Description: 菜谱查询
	 * @param food
	 * @return Food
	 */
	public Food queryFood(Food food) {
		Connection conn = BaseDao.getConnection();
		Food _food = foodDao.getFood(food, conn);
		BaseDao.closeDB(null, null, conn);
		return _food;
	}

	/**
	 * @Title: addFood
	 * @Description: 添加菜谱
	 * @param food
	 * @return void
	 */
	public void addFood(Food food) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(food.getFood_desc())) {
			food.setFood_desc(Transcode.htmlEncode(food.getFood_desc()));
		}
		food.setFood_date(DateUtil.dateToDateString(new Date()));
		foodDao.addFood(food, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateFood
	 * @Description: 更新菜谱信息
	 * @param food
	 * @return void
	 */
	public void updateFood(Food food) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(food.getFood_desc())) {
			food.setFood_desc(Transcode.htmlEncode(food.getFood_desc()));
		}
		foodDao.updateFood(food, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	public void updateFoodClick(Food food) {
		Connection conn = BaseDao.getConnection();
		food.setFood_click(food.getFood_click()+1);
		foodDao.updateFoodClick(food, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delFood
	 * @Description: 删除菜谱信息
	 * @param food
	 * @return void
	 */
	public void delFoods(Food food) {
		Connection conn = BaseDao.getConnection();
		foodDao.delFoods(food.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 评论信息查询
	 * @param sblog
	 * @return List<Sblog>
	 */
	public List<Sblog> listSblogs(Sblog sblog, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = sblogDao.listSblogsCount(sblog, conn);
		}
		List<Sblog> sblogs = sblogDao.listSblogs(sblog, conn);
		if (sblogs!=null && sblogs.size()>0) {
			for (Sblog sblog2 : sblogs) {
				Sblog _sblog = new Sblog();
				_sblog.setSblog_id2(sblog2.getSblog_id());
				_sblog.setStart(-1);
				List<Sblog> replys = sblogDao.listSblogs(_sblog, conn);
				if (replys!=null) {
					sblog2.setReplys(replys);
				}
			}
		}

		BaseDao.closeDB(null, null, conn);
		return sblogs;
	}

	/**
	 * @Title: addSblog
	 * @Description: 新增评论信息
	 * @param sblog
	 * @return void
	 */
	public void addSblog(Sblog sblog) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(sblog.getSblog_content())) {
			//内容编码
			sblog.setSblog_content(Transcode.htmlEncode(sblog.getSblog_content()));
		}
		//评论时间
		sblog.setSblog_date(DateUtil.dateToDateString(new Date()));
		sblogDao.addSblog(sblog, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delSblogs
	 * @Description: 删除评论信息
	 * @param sblog
	 * @return void
	 */
	public void  delSblogs(Sblog sblog){
		Connection conn = BaseDao.getConnection();
		sblogDao.delSblogs(sblog.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
}
