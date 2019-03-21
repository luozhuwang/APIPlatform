package com.xiaoniu.controller;

import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class FileController {
	
	@RequestMapping("/upload")
	public String upload(){
		return "/upload";
	}

	//单文件上传
    @RequestMapping(value = "/upload.do")
    public String queryFileData(@RequestParam("uploadfile") CommonsMultipartFile file,HttpServletRequest request) throws IOException {
        // MultipartFile是对当前上传的文件的封装，当要同时上传多个文件时，可以给定多个MultipartFile参数(数组)
        if (!file.isEmpty()) {
        	String filename = file.getOriginalFilename();
            
            String savedDir = request.getSession().getServletContext().getRealPath("upload"); //获取服务器指定文件存取路径
//            File outFile = new File(savedDir);
//            if(!outFile.exists()){
//            	System.err.println("文件夹不存在");
//            	outFile.mkdirs();
//            }else{
//            	System.out.println("文件夹已存在");
//            }
			File savedFile = new File(savedDir,filename );
			File pf=savedFile.getParentFile();
			System.out.println("文件存储路径："+savedDir);
	          if(!pf.exists()){
	        	pf.mkdirs();
	        	System.err.println("文件夹不存在,创建成功");
	        }else{
	        	System.out.println("文件夹已存在");
	        }
			if(savedFile.exists()){
				savedFile.delete();
				System.out.println("删除文件");
			}
				savedFile.createNewFile();				
				file.transferTo(savedFile);  //转存文件

            return "redirect:/home";
        } else {
            return "redirect:err";
        }
    }

    //多文件上传
    @RequestMapping(value = "/uploads.do")
    public String queryFileDatas(@RequestParam("uploadfile") CommonsMultipartFile[] files,HttpServletRequest request) throws IOException {
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
            	String filename = files[i].getOriginalFilename();
            	String savedDir = request.getSession().getServletContext().getRealPath("upload"); //获取服务器指定文件存取路径
                
    			File savedFile = new File(savedDir,filename );
    			File pf=savedFile.getParentFile();
    			System.out.println("文件存储路径："+savedDir);
    	        if(!pf.exists()){
    	        	pf.mkdirs();
    	        	System.err.println("文件夹不存在,创建成功");
    	        }else{
    	        	System.out.println("文件夹已存在");
    	        }
    			if(savedFile.exists()){
    				savedFile.delete();
    				System.out.println("删除文件");
    			}
    			savedFile.createNewFile();				
    			files[i].transferTo(savedFile);  //转存文件
            	
            	
//            	File outFile = new File(savedDir);
//                if(!outFile.exists()){
//                	System.err.println("文件夹不存在");
//                	outFile.mkdirs();
//                }else{
//                	System.out.println("文件夹已存在");
//                }
//                System.out.println("文件存储路径："+savedDir);
//    			File savedFile = new File(savedDir,filename );
//    			boolean isCreateSuccess = savedFile.createNewFile();
//    			if(isCreateSuccess){					
//    				files[i].transferTo(savedFile);  //转存文件
//    			}

            }
            return "redirect:/home";
        } else {
        	return "redirect:err";
        }

    }
}
