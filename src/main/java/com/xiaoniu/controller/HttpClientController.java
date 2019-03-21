package com.xiaoniu.controller;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xiaoniu.httpclient.HttpClientOperate;
import com.xiaoniu.httpclient.HttpResult;



@Controller
public class HttpClientController {

	@Autowired
	private HttpClientOperate httpClientOperate;
	
//	@Autowired
//	private HttpClientService http;
	
	@RequestMapping("/HttpClient")
	public String HttpTest(Model model){
		String response=null;
		try {
			HttpResult result=httpClientOperate.doGet("https://www.xiaoniu88.com");
			response=result.getContent();
			model.addAttribute("message",response);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "err";
	}
	
//	@RequestMapping("/Http")
//	public String Http(Model model){
//		String URL="https://www.xiaoniu88.com";
//		String response=null;
//			HttpResult result=http.doSend("18环境", "get", URL, null);
//			response=result.getContent();
//			model.addAttribute("message",response);
//		
//		return "err";
//	}
}
