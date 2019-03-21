package com.xiaoniu.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.xiaoniu.model.EnvVarModel;
import com.xiaoniu.service.EnvVarService;

@Controller
public class EnvVarController {
	private Logger logger=LoggerFactory.getLogger(EnvVarController.class); 

	@Autowired
	private EnvVarService envVarService;
	
	
	@RequestMapping("/Var/list")
	public String var_list(EnvVarModel envVarModel,@RequestParam(value="pagenum",defaultValue="1") Integer pagenum,Model model){
		logger.info("进入变量列表页面");
		PageHelper.startPage(pagenum, 15);
		envVarModel.setVarType(1);
		List<EnvVarModel> envVarList=envVarService.getAllVar(envVarModel);
		PageInfo<EnvVarModel> pageinfo=new PageInfo<EnvVarModel>(envVarList);	
		model.addAttribute("VarList",pageinfo);
		model.addAttribute("VarName",envVarModel.getVarName());
		return "/Env/list";
	}
	
	@RequestMapping("/Var/addVar")
	public String var_add(){
		logger.info("进入添加变量页面");
		return "/Env/addVar";
	}
	
	@RequestMapping("/Var/addAction")
	public String var_addAction(@Valid @ModelAttribute("envVarModel") EnvVarModel envVarModel, BindingResult result,Model model){
		logger.info("添加变量Action");
		if(!result.hasErrors()){
			int count=envVarService.addVar(envVarModel.getVarName(),  envVarModel.getVarValue(), 1);
			if(count>0){
				return "redirect:list";
			}
		}
		model.addAttribute("envVarModel", envVarModel);
		return "/Env/addVar";
	}
	
	@RequestMapping("/Var/editVar/{id}")
	public String var_edit(Model model, @PathVariable Integer id){
		logger.info("编辑变量<"+id+">");
		EnvVarModel envVarModel=envVarService.getVar(id);
		model.addAttribute("envVarModel",envVarModel);
		return "/Env/editVar";
	}
	
	@RequestMapping("/Var/editAction")
	public String var_editAction(@Valid @ModelAttribute("envVarModel")EnvVarModel envVarModel, BindingResult result,Model model){
		logger.info("编辑变量Action");
		if(!result.hasErrors()){			
			int count=envVarService.updateVar(envVarModel.getId(), envVarModel.getVarName(), envVarModel.getVarValue());
			if(count>0){
				return "redirect:list";
			}
		}
		model.addAttribute("envVarModel",envVarModel);
		return "/Env/editVar";
	}
	
	@RequestMapping("/Var/del/{id}")
	public String var_del(Model model, @PathVariable Integer id){
		logger.info("删除变量<"+id+">操作");
		int count=envVarService.delVar(id);
		if(count<=0){
			logger.info("没有可删除变量");
		}
		return "redirect:/Var/list"; 
	}
}
