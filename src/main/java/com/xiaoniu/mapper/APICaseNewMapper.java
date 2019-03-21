package com.xiaoniu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoniu.model.APICaseNew;

public interface APICaseNewMapper {
	//获取待运行用例
	List<APICaseNew> getALLAPICaseNew(APICaseNew APIcaseNew);
	//获取单个测试用例
	APICaseNew getAPICaseNew(Integer id);
	
	//获取单个测试用例
	Integer getMaxCaseId();
	//添加运行测试用例
	Integer addAPICaseNew(@Param("dependCaseId")Integer dependCaseId,@Param("caseName")String caseName,@Param("remark")String remark);
	//通过ID删除运行测试用例
	Integer delAPICaseNew(Integer id);
	//更新用例运行状态
//	Integer updateAPICaseIsRun(@Param("id")Integer id,@Param("isRun")Integer isRun);
	//更新用例
	Integer updateAPICaseNew(@Param("id")Integer id,@Param("dependCaseId")Integer dependCaseId,@Param("remark")String remark);
}
