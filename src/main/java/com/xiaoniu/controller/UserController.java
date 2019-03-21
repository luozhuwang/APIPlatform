package com.xiaoniu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoniu.model.UserModel;
import com.xiaoniu.service.UserService;
import com.xiaoniu.tools.RecordIP;

@Controller
public class UserController {
	private Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("/login")
	public String Login(){
		logger.info("进入登陆页面");
		return "/User/login";
	}
	
	@RequestMapping("/loginAction")
	public String LoginAction(UserModel user,Model model,HttpSession session){
		logger.info("登陆用户Action");
		UserModel loginUser=userService.findByUsername(user.getUser(), user.getUserPwd());
		//session保存登陆 用户
		if(loginUser!=null){
			session.setAttribute("LoginuserName", loginUser.getUserName());			
			return "redirect:/home";
		}else{		
			model.addAttribute("errorMsg", "用户名或密码错误，请重新输入");
			return "/User/login";
		}		
	}
	
	@RequestMapping("/register")
	public String register(){
		logger.info("进入注册页面");
		return "/User/register";
	}
	
	@RequestMapping("/registerAction")
	public String registerAction(UserModel user,HttpServletRequest request,Model model){
		logger.info("进入注册Action");
		String ip=RecordIP.getIpAddress(request);
		logger.info("Custom IP:"+ip);		
		if(StringUtils.isBlank(user.getUser()) || StringUtils.isBlank(user.getUserName())){
			model.addAttribute("errorMsg", "注册用户不能为空");
			return "/User/register";	
		}else{
			int count=userService.registerUser(user.getUser(),user.getUserPwd(),user.getUserName(), ip, user.getEmail());			
			if(count<=0){
				model.addAttribute("errorMsg", "用户已存在，请重新注册");
				return "/User/register";
			}else{
				return "/User/login";
			}	
		}
		
	}

	@RequestMapping("/outLogin")
	public String Longinout(HttpSession session){
		session.invalidate();
		return "/User/login";
	}
	
	
	@RequestMapping("/User/list")
	public String User_list(UserModel userModel,@RequestParam(value="pagenum",defaultValue="1") Integer pagenum,Model model ){
		logger.info("进入用户列表页面");
		PageHelper.startPage(pagenum, 15);
		List<UserModel> userList=userService.getAllUser(userModel);
		PageInfo<UserModel> pageinfo=new PageInfo<UserModel>(userList);
		model.addAttribute("userList", pageinfo);
		model.addAttribute("userName", userModel.getUserName());
		return "/User/list";
	}
	
	@RequestMapping("/User/editUser/{id}")
	public String User_edit(Model model, @PathVariable Integer id){
		logger.info("进入接口编辑页面");
		UserModel userModel  =userService.getUserById(id);
		model.addAttribute("userModel",userModel);
		return "/User/editUser";
	}
	
	@RequestMapping("/User/editAction")
	public String User_editAction(@Valid @ModelAttribute("userModel") UserModel userModel, BindingResult result,Model model){
		logger.info("编辑用户Action");
		if(!result.hasErrors()){
			int count=userService.UpdateUser(userModel.getId(), userModel.getUserPwd(), userModel.getUserName(), userModel.getEmail());
			if(count>0){			
				return "redirect:list";	
			}
		}
		model.addAttribute("userModel", userModel);
		return "/User/editUser";
	}
	
	
	@RequestMapping("/User/del/{id}")
	public String User_del(Model model, @PathVariable Integer id,HttpSession session) throws Exception{
		logger.info("删除用户");
		String LoginuserName=session.getAttribute("LoginuserName").toString();	
		UserModel userModel=userService.getUserById(id);
		if(!userModel.getUserName().equals(LoginuserName)){
			int count=userService.delUser(id);
			if(count<=0){
				logger.info("删除用户失败");			
				throw new Exception("删除用户失败"); 
			}
		}else{
			throw new Exception("当前用户为登陆用户，不可删除");
		}	
		return "/User/list";
	}
}
