package com.nkl.page.action;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.action.BaseAction;
import com.nkl.common.util.Param;
import com.nkl.page.domain.Food;
import com.nkl.page.domain.FoodType;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.User;
import com.nkl.page.manager.IndexManager;

public class IndexAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	IndexManager indexManager = new IndexManager();

	//抓取页面参数
	Food paramsFood;
	FoodType paramsFoodType;
	Sblog paramsSblog;
	User paramsUser;
	String tip;
	
	/**
	 * @Title: index
	 * @Description: 展示菜谱列表
	 * @return String
	 */
	public String index(){
		try {
			//查询最新菜谱
			Food food = new Food();
			food.setStart(0);
			food.setLimit(8);
			List<Food> foods = indexManager.listFoods(food,null);
			Param.setAttribute("foods", foods);
			
			//查询热门菜谱
			food.setTop_flag(1);
			food.setLimit(5);
			List<Food> foods2 = indexManager.listFoods(food,null);
			Param.setAttribute("foods2", foods2);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "index";
	}
	
	/**
	 * @Title: aboutUs
	 * @Description: 关于我们
	 * @return String
	 */
	public String aboutUs(){
		try {
			//查询热门菜谱
			Food food = new Food();
			food.setStart(0);
			food.setTop_flag(1);
			food.setLimit(5);
			List<Food> foods2 = indexManager.listFoods(food,null);
			Param.setAttribute("foods2", foods2);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "aboutUs";
	}
	
	/**
	 * @Title: contactUs
	 * @Description: 联系我们
	 * @return String
	 */
	public String contactUs(){
		try {
			//查询热门菜谱
			Food food = new Food();
			food.setStart(0);
			food.setTop_flag(1);
			food.setLimit(5);
			List<Food> foods2 = indexManager.listFoods(food,null);
			Param.setAttribute("foods2", foods2);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "contactUs";
	}
	
	/**
	 * @Title: listFoods
	 * @Description: 展示菜谱列表
	 * @return String
	 */
	public String listFoods(){
		try {
			//查询菜谱信息集合
			if (paramsFood==null) {
				paramsFood = new Food();
			}
			//分页信息设置
			setPagination(paramsFood);
			int[] sum={0};
			List<Food> foods = indexManager.listFoods(paramsFood,sum); 
			Param.setAttribute("foods", foods);
			setTotalCount(sum[0]);
			
			//查询菜谱类型
			FoodType foodType = new FoodType();
			foodType.setStart(-1);
			List<FoodType> foodTypes = indexManager.listFoodTypes(foodType, null);
			if (foodTypes==null) {
				foodTypes = new ArrayList<FoodType>();
			}
			Param.setAttribute("foodTypes", foodTypes);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "food";
	}
	
	/**
	 * @Title: listFoodsTop
	 * @Description: 展示推荐菜谱列表
	 * @return String
	 */
	public String listFoodsTop(){
		try {
			//查询菜谱信息集合
			if (paramsFood==null) {
				paramsFood = new Food();
			}
			//分页信息设置
			setPagination(paramsFood);
			int[] sum={0};
			paramsFood.setTop_flag(1);
			List<Food> foods = indexManager.listFoods(paramsFood,sum); 
			Param.setAttribute("foods", foods);
			setTotalCount(sum[0]);
			
			//查询菜谱类型
			FoodType foodType = new FoodType();
			foodType.setStart(-1);
			List<FoodType> foodTypes = indexManager.listFoodTypes(foodType, null);
			if (foodTypes==null) {
				foodTypes = new ArrayList<FoodType>();
			}
			Param.setAttribute("foodTypes", foodTypes);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "foodTop";
	}
	
	/**
	 * @Title: queryFood
	 * @Description: 查询菜谱详情
	 * @return String
	 */
	public String queryFood(){
		try {
			 //得到菜谱
			Food food = indexManager.queryFood(paramsFood);
			Param.setAttribute("food", food);
			indexManager.updateFoodClick(food);
			
			//查询评论信息集合
			if (paramsSblog==null) {
				paramsSblog = new Sblog();
			}
			paramsSblog.setFood_id(paramsFood.getFood_id());
			paramsSblog.setSblog_id2(0);
			//分页信息设置
			paramsSblog.setStart(-1);
			List<Sblog> sblogs = indexManager.listSblogs(paramsSblog,null); 
			Param.setAttribute("sblogs", sblogs);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "foodDetail";
	}
	
	/**
	 * @Title: addSblog
	 * @Description: 新增评论
	 * @return String
	 */
	public String addSblog(){
		try {
			//验证码验证
			String random = (String)Param.getSession("random");
			if (!random.equals(paramsSblog.getRandom())) {
				setErrorReason("验证码错误！");
				return "error2";
			}
			
			//新增评论
			indexManager.addSblog(paramsSblog);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}
	
	/**
	 * @Title: saveUserFront
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveUserFront(){
		try {
			 //保存修改个人信息
			indexManager.updateUser(paramsUser);
			//更新session
			User userFront = new User();
			userFront.setUser_id(paramsUser.getUser_id());
			userFront = indexManager.getUser(userFront);
			Param.setSession("userFront", userFront);
			
		} catch (Exception e) {
			e.printStackTrace();
			tip = "修改失败";
			return "userInfo";
		}
		tip = "修改成功";
		return "userInfo";
	}
	
	/**
	 * @Title: saveUserFrontPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveUserFrontPass(){
		try {
			 //保存修改个人密码
			indexManager.updateUser(paramsUser);
			//更新session
			User userFront = (User)Param.getSession("userFront");
			if (userFront!=null) {
				userFront.setLogin_pass(paramsUser.getLogin_pass());
				Param.setSession("userFront", userFront);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tip = "修改失败";
			return "userPwd";
		}
		tip = "修改成功";
		return "userPwd";
	}
	
	/**
	 * @Title: listMyFoods
	 * @Description: 查询我的菜谱
	 * @return String
	 */
	public String listMyFoods(){
		try {
			if (paramsFood==null) {
				paramsFood = new Food();
			}
			//获取用户,用户只能查询自己的菜谱
			User userFront = (User)Param.getSession("userFront");
			if (userFront.getUser_type()==1) {
				paramsFood.setUser_id(userFront.getUser_id());
			}
			//设置分页信息
			setPagination(paramsFood);
			//总的条数
			int[] sum={0};
			//查询菜谱列表
			List<Food> foods = indexManager.listFoods(paramsFood,sum); 
			Param.setAttribute("foods", foods);
			setTotalCount(sum[0]);
			
			//查询菜谱类型
			FoodType foodType = new FoodType();
			foodType.setStart(-1);
			List<FoodType> foodTypes = indexManager.listFoodTypes(foodType, null);
			if (foodTypes==null) {
				foodTypes = new ArrayList<FoodType>();
			}
			Param.setAttribute("foodTypes", foodTypes);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "foodShow";
	}
	
	/**
	 * @Title: queryMyFood
	 * @Description: 编辑菜谱界面
	 * @return String
	 */
	public String queryMyFood(){
		try {
			//查询菜谱
			Food food = indexManager.queryFood(paramsFood);
			Param.setAttribute("food",food);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "foodMyDetail";
	}
	
	/**
	 * @Title: addFoodShow
	 * @Description: 新增菜谱界面
	 * @return String
	 */
	public String addFoodShow(){
		//查询菜谱类型
		FoodType foodType = new FoodType();
		foodType.setStart(-1);
		List<FoodType> foodTypes = indexManager.listFoodTypes(foodType, null);
		if (foodTypes==null) {
			foodTypes = new ArrayList<FoodType>();
		}
		Param.setAttribute("foodTypes", foodTypes);
		
		return "foodEdit";
	}
	
	/**
	 * @Title: addFood
	 * @Description: 新增菜谱
	 * @return String
	 */
	public String addFood(){
		try {
			//新增菜谱
			indexManager.addFood(paramsFood);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}
	
	/**
	 * @Title: editFood
	 * @Description: 编辑菜谱界面
	 * @return String
	 */
	public String editFood(){
		try {
			//查询菜谱
			Food food = indexManager.queryFood(paramsFood);
			Param.setAttribute("food",food);
			
			//查询菜谱类型
			FoodType foodType = new FoodType();
			foodType.setStart(-1);
			List<FoodType> foodTypes = indexManager.listFoodTypes(foodType, null);
			if (foodTypes==null) {
				foodTypes = new ArrayList<FoodType>();
			}
			Param.setAttribute("foodTypes", foodTypes);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
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
			//编辑菜谱
			indexManager.updateFood(paramsFood);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}
	
	/**
	 * @Title: delFood
	 * @Description: 撤销菜谱
	 * @return String
	 */
	public String delFood(){
		try {
			//撤销菜谱
			indexManager.delFoods(paramsFood);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	/**
	 * @Title: reg
	 * @Description: 跳转注册页面
	 * @return String
	 */
	public String reg(){
		return "reg";
	}
	
	/**
	 * @Title: myInfo
	 * @Description: 跳转个人信息页面
	 * @return String
	 */
	public String myInfo(){
		return "userInfo";
	}
	
	/**
	 * @Title: myPwd
	 * @Description: 跳转个人密码页面
	 * @return String
	 */
	public String myPwd(){
		return "userPwd";
	}
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public Food getParamsFood() {
		return paramsFood;
	}

	public FoodType getParamsFoodType() {
		return paramsFoodType;
	}

	public void setParamsFood(Food paramsFood) {
		this.paramsFood = paramsFood;
	}

	public void setParamsFoodType(FoodType paramsFoodType) {
		this.paramsFoodType = paramsFoodType;
	}

	public Sblog getParamsSblog() {
		return paramsSblog;
	}

	public void setParamsSblog(Sblog paramsSblog) {
		this.paramsSblog = paramsSblog;
	}

}
