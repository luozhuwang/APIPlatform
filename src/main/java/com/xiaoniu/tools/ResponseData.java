package com.xiaoniu.tools;

import java.util.HashMap;
import java.util.Map;
import com.xiaoniu.model.CaseResultModel;


public class ResponseData {
	public static Map<Integer, CaseResultModel> mpalist=new HashMap<Integer, CaseResultModel>();

	public static Map<Integer ,CaseResultModel> set(Integer caseId, CaseResultModel caseResultModel){
		mpalist.put(caseId, caseResultModel);
		return mpalist;		
	}
	
	public static Map<Integer ,CaseResultModel> getmap(){
		return mpalist;
	}

}
