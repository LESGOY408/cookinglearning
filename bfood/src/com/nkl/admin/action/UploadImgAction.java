package com.nkl.admin.action;

import java.util.Date;

import com.nkl.common.action.BaseUploadAction;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.FindProjectPath;
import com.nkl.common.util.Param;
import com.nkl.common.util.Print;
import com.nkl.common.util.UploadFile;

@SuppressWarnings("serial")
public class UploadImgAction extends BaseUploadAction {
	/**
	 * 文件上传
	 */
	public String execute() {
		Print.println("进入execute方法");
		//重命名该图片
		String old_name=getUploadFileName();
		String file_name=DateUtil.dateToDateString(new Date(),"yyyyMMddHHmmssSSS")+old_name.substring(old_name.indexOf("."));
		//设置保存文件位置
		String savePath = super.getSavePath();
		if ("1".equals(getNum())) {
			savePath = getSavePath1();
		}else if ("2".equals(getNum())) {
			savePath = getSavePath2();
		}else if ("3".equals(getNum())) {
			savePath = getSavePath3();
		}
		String saveFile=FindProjectPath.getRootPath(savePath+"\\"+file_name);
		//文件类型限制
		String allowedTypes = super.getAllowedTypes();
		if ("1".equals(getNum())) {
			allowedTypes = getAllowedTypes1();
		}else if ("2".equals(getNum())) {
			allowedTypes = getAllowedTypes2();
		} else if ("3".equals(getNum())) {
			allowedTypes = getAllowedTypes3();
		}
		//上传文件
		String errorString=UploadFile.upload(getUpload(), saveFile, getUploadContentType(), getUpload().length(), allowedTypes, getMaximunSize());
		//判断上传结果
		if(!"".equals(errorString))
		{
			Print.println(errorString);
			Param.setAttribute("tip", "no");
			Param.setAttribute("errorString", errorString);
			return INPUT;
		}
		Param.setAttribute("tip", "ok");
		Param.setAttribute("filename",file_name);
		Param.setAttribute("filenameGBK",old_name);
		Param.setAttribute("filelength",Math.round(getUpload().length()/1024.0));
		return SUCCESS;
	}
	
	private String num;
	private String savePath1;
	private String allowedTypes1;
	private String savePath2;
	private String allowedTypes2;
	private String savePath3;
	private String allowedTypes3;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getSavePath2() {
		return savePath2;
	}

	public void setSavePath2(String savePath2) {
		this.savePath2 = savePath2;
	}

	public String getAllowedTypes2() {
		return allowedTypes2;
	}

	public void setAllowedTypes2(String allowedTypes2) {
		this.allowedTypes2 = allowedTypes2;
	}

	public String getSavePath3() {
		return savePath3;
	}

	public String getAllowedTypes3() {
		return allowedTypes3;
	}

	public void setSavePath3(String savePath3) {
		this.savePath3 = savePath3;
	}

	public void setAllowedTypes3(String allowedTypes3) {
		this.allowedTypes3 = allowedTypes3;
	}

	public String getSavePath1() {
		return savePath1;
	}

	public String getAllowedTypes1() {
		return allowedTypes1;
	}

	public void setSavePath1(String savePath1) {
		this.savePath1 = savePath1;
	}

	public void setAllowedTypes1(String allowedTypes1) {
		this.allowedTypes1 = allowedTypes1;
	}

}
