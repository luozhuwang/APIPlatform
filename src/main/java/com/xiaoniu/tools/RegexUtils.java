package com.xiaoniu.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RegexUtils {
	private static Logger logger = LoggerFactory.getLogger(RegexUtils.class);
	
	//身份证：(^\d{18}$)|(^\d{15}$)
	/**
	 * 通过正则表达式获取对应的数据
	 * @param	mess	//信息
	 * @param	reg		//正则表达式
	 * @return	String
	 * */
	 public static String resolve_Reg(String mess, String reg){
		logger.info("正则表达式："+reg);
	    String Reg_String = null;

	    Pattern pattern = Pattern.compile(reg);
	    Matcher m = pattern.matcher(mess);
	    while (m.find()) {
	      Reg_String = m.group();
	    }
	    System.out.println(Reg_String);
	    return Reg_String;
	  }
	 /**
		 * 通过正则表达式获取对应的数据
		 * @param	mess	//信息
		 * @param	reg		//正则表达式
		 * @param	i		//获取出现的位置
		 * @return	String
		 * */
	public static String resolve_Reg_index(String mess, String reg,int index){
			List<String> AllContent=new ArrayList<String>();						    
		    Pattern pattern = Pattern.compile(reg);
		    Matcher m = pattern.matcher(mess);
		    while (m.find()) {
		    	AllContent.add(m.group());
		    }
		    return AllContent.get(index);		    
	}
	
	public static List<String> resolve_Reg_Array(String mess, String reg){
		List<String> AllContent=new ArrayList<String>();				
			Pattern pattern = Pattern.compile(reg);
			Matcher m = pattern.matcher(mess);
			while (m.find()) {
				AllContent.add(m.group());
			}
	    return AllContent;		    
	}
		
	/**
	 * 转义正则特殊字符 （$()*+.[]?\^{},|）
	 * 
	 * @param keyword
	 * @return
	 */
	public static String escapeExprSpecialWord(String keyword) {
		if (StringUtils.isNotBlank(keyword)) {
			String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" ,"\""};
			for (String key : fbsArr) {
				if (keyword.contains(key)) {
					keyword = keyword.replace(key, "\\" + key);
				}
			}
		}
		return keyword;
	}

	/**
	 * 转义正则特殊字符 （$()*+.[]?\^{},|）
	 * 
	 * @param keyword
	 * @return
	 */
	public static String escapeExpr(String keyword) {
		if (StringUtils.isNotBlank(keyword)) {
			String[] fbsArr = { "\\" ,"\""};
			for (String key : fbsArr) {
				if (keyword.contains(key)) {
					keyword = keyword.replace(key, "\\" + key);
				}
			}
		}
		return keyword;
	}
	/** * 判断是否带IP请求 * */  
    public static boolean isboolIp(String URL) {  
        String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";   
        Pattern pattern = Pattern.compile(ip);
	    Matcher m = pattern.matcher(URL);
	    while (m.find()) {
	    	return true;
	    }
	    return false;
    }  
    
	public static void main(String args[]){
		String URL="http://172.20.20.160:8030/testproject/loan/auditAction";
		
		System.out.println(isboolIp(URL));
	}
}
