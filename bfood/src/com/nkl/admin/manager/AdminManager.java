package com.nkl.admin.manager;

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

public class AdminManager {

	UserDao userDao = new UserDao();
	FoodTypeDao foodTypeDao = new FoodTypeDao();
	FoodDao foodDao = new FoodDao();
	SblogDao sblogDao = new SblogDao();
	
	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User>  listUsers(User user,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = userDao.listUsersCount(user, conn);
		}
		List<User> users = userDao.listUsers(user,conn);
		
		BaseDao.closeDB(null, null, conn);
		return users;
	}
	
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
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void  addUser(User user){
		Connection conn = BaseDao.getConnection();
		user.setAdd_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		if (!StringUtil.isEmptyString(user.getLogin_pass())) {
			user.setLogin_pass(Md5.makeMd5(user.getLogin_pass()));
		}
		//默认头像
		if (StringUtil.isEmptyString(user.getUser_pic())) {
			user.setUser_pic("default.jpg");
		}
		userDao.addUser(user, conn);
		BaseDao.closeDB(null, null, conn);
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
		userDao.updateUserMoney(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void  delUsers(User user){
		Connection conn = BaseDao.getConnection();
		userDao.delUsers(user.getIds().split(","), conn);
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
	 * @Title: queryFoodType
	 * @Description: 菜谱类型查询
	 * @param foodType
	 * @return FoodType
	 */
	public FoodType queryFoodType(FoodType foodType) {
		Connection conn = BaseDao.getConnection();
		FoodType _foodType = foodTypeDao.getFoodType(foodType, conn);
		BaseDao.closeDB(null, null, conn);
		return _foodType;
	}

	/**
	 * @Title: addFoodType
	 * @Description: 添加菜谱类型
	 * @param foodType
	 * @return void
	 */
	public void addFoodType(FoodType foodType) {
		Connection conn = BaseDao.getConnection();
		foodTypeDao.addFoodType(foodType, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateFoodType
	 * @Description: 更新菜谱类型信息
	 * @param foodType
	 * @return void
	 */
	public void updateFoodType(FoodType foodType) {
		Connection conn = BaseDao.getConnection();
		foodTypeDao.updateFoodType(foodType, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delFoodType
	 * @Description: 删除菜谱类型信息
	 * @param foodType
	 * @return void
	 */
	public void delFoodTypes(FoodType foodType) {
		Connection conn = BaseDao.getConnection();
		foodTypeDao.delFoodTypes(foodType.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
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

		BaseDao.closeDB(null, null, conn);
		return sblogs;
	}
	
	/**
	 * @Title: querySblog
	 * @Description: 评论信息查询
	 * @param sblog
	 * @return Sblog
	 */
	public Sblog querySblog(Sblog sblog) {
		Connection conn = BaseDao.getConnection();
		Sblog _sblog = sblogDao.getSblog(sblog, conn);
		BaseDao.closeDB(null, null, conn);
		return _sblog;
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
