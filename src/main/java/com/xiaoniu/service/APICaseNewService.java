package com.xiaoniu.service;

import java.util.List;
import com.xiaoniu.model.APICaseNew;

public interface APICaseNewService {

	//获取待运行用例
	List<APICaseNew> getALLAPICaseNew(APICaseNew APIcaseNew);
	//获取单个测试用例
	APICaseNew getAPICaseNew(Integer id);
	//添加运行测试用例
	Integer addAPICaseNew(Integer dependCaseId,String caseName,String remark);
	//获取单个测试用例
	Integer getMaxCaseId();
	//通过ID删除运行测试用例
	Integer delAPICaseNew(Integer id);
	//更新用例运行状态
//	Integer updateAPICaseIsRun(Integer id,Integer isRun);
	//更新用例
	Integer updateAPICaseNew(Integer id,Integer dependCaseId,String remark);
}
