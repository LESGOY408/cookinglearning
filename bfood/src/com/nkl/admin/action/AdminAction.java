package com.nkl.admin.action;

import java.util.List;

import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.Param;
import com.nkl.page.domain.Food;
import com.nkl.page.domain.FoodType;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.User;

public class AdminAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	AdminManager adminManager = new AdminManager();

	//抓取页面参数
	Food paramsFood;
	FoodType paramsFoodType;
	Sblog paramsSblog;
	User paramsUser;
	String tip;
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveAdmin(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.getUser(admin);
			Param.setSession("admin", admin);

			setSuccessTip("编辑成功", "modifyInfo.jsp");
		} catch (Exception e) {
			setErrorTip("编辑异常", "modifyInfo.jsp");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveAdminPass(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = (User)Param.getSession("admin");
			if (admin!=null) {
				admin.setLogin_pass(paramsUser.getLogin_pass());
				Param.setSession("admin", admin);
			}

			setSuccessTip("修改成功", "modifyPwd.jsp");
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listUsers
	 * @Description: 查询注册用户
	 * @return String
	 */
	public String listUsers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查询注册用户
			paramsUser.setUser_type(1);
			//设置分页信息
			setPagination(paramsUser);
			int[] sum={0};
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询注册用户异常", "main.jsp");
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加注册用户页面
	 * @return String
	 */
	public String addUserShow(){
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加注册用户
	 * @return String
	 */
	public String addUser(){
		try {
			//检查登录名是否存在
			User user = new User();
			user.setLogin_name(paramsUser.getLogin_name());
			user = adminManager.getUser(user);
			if (user!=null) {
				tip="失败，该用户名已经存在！";
				Param.setAttribute("user", paramsUser);
				return "senderEdit";
			}
			
			 //添加用户
			paramsUser.setUser_type(1);
			adminManager.addUser(paramsUser);

			setSuccessTip("添加用户信息成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("添加用户信息异常", "Admin_listUsers.action");
		}
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑注册用户
	 * @return String
	 */
	public String editUser(){
		try {
			 //得到用户
			User user = adminManager.getUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询用户信息异常", "Admin_listUsers.action");
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑用户信息
	 * @return String
	 */
	public String saveUser(){
		try {
			 //保存编辑用户
			adminManager.updateUser(paramsUser);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		setSuccessTip("编辑用户信息成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @return String
	 */
	public String delUsers(){
		try {
			 //删除用户
			adminManager.delUsers(paramsUser);

			setSuccessTip("删除用户信息成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("删除用户信息异常", "Admin_listUsers.action");
		}
		return "infoTip";
	}
	/**
	 * @Title: listFoodTypes
	 * @Description: 查询菜谱类型
	 * @return String
	 */
	public String listFoodTypes(){
		try {
			if (paramsFoodType==null) {
				paramsFoodType = new FoodType();
			}
			
			//设置分页信息
			setPagination(paramsFoodType);
			//总的条数
			int[] sum={0};
			//查询菜谱类型列表
			List<FoodType> foodTypes = adminManager.listFoodTypes(paramsFoodType,sum); 
			
			Param.setAttribute("foodTypes", foodTypes);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询菜谱类型异常", "main.jsp");
			return "infoTip";
		}
		
		return "foodTypeShow";
	}
	
	/**
	 * @Title: addFoodTypeShow
	 * @Description: 显示添加菜谱类型页面
	 * @return String
	 */
	public String addFoodTypeShow(){
		return "foodTypeEdit";
	}
	
	/**
	 * @Title: addFoodType
	 * @Description: 添加菜谱类型
	 * @return String
	 */
	public String addFoodType(){
		try {
			//检查菜谱类型是否存在
			FoodType foodType = new FoodType();
			foodType.setFood_type_name(paramsFoodType.getFood_type_name());
			foodType = adminManager.queryFoodType(foodType);
			if (foodType!=null) {
				tip="失败，该类型已经存在！";
				Param.setAttribute("foodType", paramsFoodType);
				return "foodTypeEdit";
			}
			
			 //添加菜谱类型
			adminManager.addFoodType(paramsFoodType);
			
			setSuccessTip("添加成功", "Admin_listFoodTypes.action");
		} catch (Exception e) {
			setErrorTip("添加菜谱类型异常", "Admin_listFoodTypes.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editFoodType
	 * @Description: 编辑菜谱类型
	 * @return String
	 */
	public String editFoodType(){
		try {
			 //得到菜谱类型
			FoodType foodType = adminManager.queryFoodType(paramsFoodType);
			Param.setAttribute("foodType", foodType);
			
		} catch (Exception e) {
			setErrorTip("查询菜谱类型异常", "Admin_listFoodTypes.action");
			return "infoTip";
		}
		
		return "foodTypeEdit";
	}
	
	/**
	 * @Title: saveFoodType
	 * @Description: 保存编辑菜谱类型
	 * @return String
	 */
	public String saveFoodType(){
		try {
			//检查菜谱类型是否存在
			FoodType foodType = new FoodType();
			foodType.setFood_type_name(paramsFoodType.getFood_type_name());
			foodType = adminManager.queryFoodType(foodType);
			if (foodType!=null&&foodType.getFood_type_id()!=paramsFoodType.getFood_type_id()) {
				tip="失败，该类型已经存在！";
				Param.setAttribute("foodType", paramsFoodType);
				return "foodTypeEdit";
			}
			
			 //保存编辑菜谱类型
			adminManager.updateFoodType(paramsFoodType);
			
			setSuccessTip("编辑成功", "Admin_listFoodTypes.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("foodType", paramsFoodType);
			return "foodTypeEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delFoodTypes
	 * @Description: 删除菜谱类型
	 * @return String
	 */
	public String delFoodTypes(){
		try {
			 //删除菜谱类型
			adminManager.delFoodTypes(paramsFoodType);
			
			setSuccessTip("删除菜谱类型成功", "Admin_listFoodTypes.action");
		} catch (Exception e) {
			setErrorTip("删除菜谱类型异常", "Admin_listFoodTypes.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listFoods
	 * @Description: 查询菜谱
	 * @return String
	 */
	public String listFoods(){
		try {
			if (paramsFood==null) {
				paramsFood = new Food();
			}
			//设置分页信息
			setPagination(paramsFood);
			int[] sum={0};
			List<Food> foods = adminManager.listFoods(paramsFood,sum); 
			Param.setAttribute("foods", foods);
			setTotalCount(sum[0]);
			
			//查询菜谱类型
			FoodType foodType = new FoodType();
			foodType.setStart(-1);
			List<FoodType> foodTypes = adminManager.listFoodTypes(foodType, null);
			Param.setAttribute("foodTypes", foodTypes);
			
		} catch (Exception e) {
			setErrorTip("查询菜谱异常", "main.jsp");
			return "infoTip";
		}
		
		return "foodShow";
	}
	
	/**
	 * @Title: queryFood
	 * @Description: 查看菜谱
	 * @return String
	 */
	public String queryFood(){
		try {
			 //得到菜谱
			Food food = adminManager.queryFood(paramsFood);
			Param.setAttribute("food", food);
			
		} catch (Exception e) {
			setErrorTip("查询菜谱异常", "Admin_listFoods.action");
			return "infoTip";
		}
		
		return "foodDetail";
	}
	
	/**
	 * @Title: addFoodShow
	 * @Description: 显示添加菜谱页面
	 * @return String
	 */
	public String addFoodShow(){
		//查询菜谱类型
		FoodType foodType = new FoodType();
		foodType.setStart(-1);
		List<FoodType> foodTypes = adminManager.listFoodTypes(foodType, null);
		Param.setAttribute("foodTypes", foodTypes);
		
		return "foodEdit";
	}
	
	/**
	 * @Title: addFood
	 * @Description: 添加菜谱
	 * @return String
	 */
	public String addFood(){
		try {
			 //添加菜谱
			adminManager.addFood(paramsFood);

			setSuccessTip("添加菜谱成功", "Admin_listFoods.action");
		} catch (Exception e) {
			setErrorTip("添加菜谱异常", "Admin_listFoods.action");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: editFood
	 * @Description: 编辑菜谱
	 * @return String
	 */
	public String editFood(){
		try {
			 //得到菜谱
			Food food = adminManager.queryFood(paramsFood);
			Param.setAttribute("food", food);
			
			//查询菜谱类型
			FoodType foodType = new FoodType();
			foodType.setStart(-1);
			List<FoodType> foodTypes = adminManager.listFoodTypes(foodType, null);
			Param.setAttribute("foodTypes", foodTypes);
			
		} catch (Exception e) {
			setErrorTip("查询菜谱异常", "Admin_listFoods.action");
			return "infoTip";
		}
		
		return "foodEdit";
	}
	
	/**
	 * @Title: saveFood
	 * @Description: 保存编辑菜谱
	 * @return String
	 */
	public String saveFood(){
		try {
			 //保存编辑菜谱
			adminManager.updateFood(paramsFood);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("food", paramsFood);
			
			//查询菜谱类型
			FoodType foodType = new FoodType();
			foodType.setStart(-1);
			List<FoodType> foodTypes = adminManager.listFoodTypes(foodType, null);
			Param.setAttribute("foodTypes", foodTypes);
			
			return "foodEdit";
		}
		setSuccessTip("编辑菜谱成功", "Admin_listFoods.action");
		return "infoTip";
	}
	
	
	/**
	 * @Title: delFoods
	 * @Description: 删除菜谱
	 * @return String
	 */
	public String delFoods(){
		try {
			 //删除菜谱
			adminManager.delFoods(paramsFood);

			setSuccessTip("删除菜谱成功", "Admin_listFoods.action");
		} catch (Exception e) {
			setErrorTip("删除菜谱异常", "Admin_listFoods.action");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 查询菜谱评论
	 * @return String
	 */
	public String listSblogs(){
		try {
			//查询菜谱评论
			if (paramsSblog==null) {
				paramsSblog = new Sblog();
			}
			//分页设置
			setPagination(paramsSblog);
			paramsSblog.setSblog_id2(0);
			int[] sum={0};
			List<Sblog> sblogs = adminManager.listSblogs(paramsSblog,sum); 
			
			Param.setAttribute("sblogs", sblogs);
			setTotalCount(sum[0]);
			
			 //得到菜谱
			Food food = new Food();
			food.setFood_id(paramsSblog.getFood_id());
			food = adminManager.queryFood(food);
			Param.setAttribute("food", food);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("查询异常", "main.jsp");
			return "infoTip";
		}
		
		return "sblogShow";
	}
	
	/**
	 * @Title: delSblogs
	 * @Description: 删除菜谱评论
	 * @return String
	 */
	public String delSblogs(){
		try {
			 //删除菜谱评论
			adminManager.delSblogs(paramsSblog);

			setSuccessTip("删除留言成功", "Admin_listSblogs.action?paramsSblog.food_id="+paramsSblog.getFood_id());
		} catch (Exception e) {
			setErrorTip("删除留言异常", "Admin_listSblogs.action?paramsSblog.food_id="+paramsSblog.getFood_id());
		}
		return "infoTip";
	}
	
	private boolean validateAdmin(){
		User admin = (User)Param.getSession("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url){
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url){
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}

	public FoodType getParamsFoodType() {
		return paramsFoodType;
	}

	public void setParamsFoodType(FoodType paramsFoodType) {
		this.paramsFoodType = paramsFoodType;
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Food getParamsFood() {
		return paramsFood;
	}

	public void setParamsFood(Food paramsFood) {
		this.paramsFood = paramsFood;
	}

	public Sblog getParamsSblog() {
		return paramsSblog;
	}

	public void setParamsSblog(Sblog paramsSblog) {
		this.paramsSblog = paramsSblog;
	}

}
