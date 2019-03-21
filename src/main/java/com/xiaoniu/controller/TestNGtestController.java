package com.xiaoniu.controller;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.testng.TestNG;



@Controller
public class TestNGtestController {
	
	
	@RequestMapping("/testng")
	public String testng(){
		return "testng";
	}

	@RequestMapping("/testng/run")
	public String runtestng(){
		String path=Thread.currentThread().getContextClassLoader().getResource("").toString();
    	System.out.println(path);
    	String tesngxml_path=path+"testng.xml";
	
		TestNG testng = new TestNG();
		List<String> testFilesList = new ArrayList<String>();
		 testFilesList.add(tesngxml_path);
		 testng.setTestSuites(testFilesList);
		 //设置output目录 
//		 String outputdir=System.getProperty("user.dir")+"/test-output" ;
//		 File outputdir=new File(".");
//		 testng.setOutputDirectory(outputdir.getAbsolutePath());
		 //设置监听
//		List<Class<? extends ITestNGListener>> classes=new ArrayList<Class<? extends ITestNGListener>>();
//		classes.add(ExtentTestNGIReporterListener.class);
//		 testng.setListenerClasses(classes);
		 testng.run();
		 return "redirect:/case/list";
	}	
}
