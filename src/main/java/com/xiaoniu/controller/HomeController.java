package com.xiaoniu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoniu.model.EnvInfoModel;
import com.xiaoniu.service.EnvInfoService;
import com.xiaoniu.tools.RecordIP;

@Controller
public class HomeController {

	private Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private EnvInfoService envInfoService;
	
	@RequestMapping(value="/home")
	public String home(HttpServletRequest  request,Model model) throws ClientProtocolException, IOException{
		String ip=RecordIP.getIpAddress(request);
		log.info("访问用户 IP:"+ip);
		
		List<EnvInfoModel>	EnvList=  envInfoService.getEnvALLTitle();
		model.addAttribute("EnvList", EnvList);
		return "home";
	}
	
}
