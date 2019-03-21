package com.xiaoniu.serviceImp;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoniu.mapper.APIMapper;
import com.xiaoniu.model.APIModel;
import com.xiaoniu.service.APIService;

@Service
public class APIServiceImp  implements APIService{
	private Logger log = LoggerFactory.getLogger(APIServiceImp.class); 

	@Autowired
	private APIMapper APImapper;
	
	
	@Override
	public List<APIModel> getALLAPI(APIModel APImodel) {
		log.info("获取所有接口");		
		List<APIModel> APIList=APImapper.getALLAPI(APImodel);
		if(APIList!=null){			
			return APIList;
		}else{
			log.info("获取测试用例为空");
			return null;
		}
	}

	@Override
	public APIModel getAPI(Integer id) {
		log.info("跟据id<"+id+">查询接口");		
		APIModel API= APImapper.getAPI(id);
		if(API!=null){			
			return API;
		}else{
			log.info("查询测试用例为空");
			return null;
		}
	}

	@Override
	public Integer delAPI(Integer id) {
		log.info("删除接口id<"+id+">");		
		int count=APImapper.delAPI(id);
		return count;
	}

	@Override
	public Integer addAPI(String apiName, String apiHost, String apiUrl,
			String apiMethod, String apiHeaders, String apiParams,
			Integer apiStatus, String remark) {
		
		log.info("新增接口");
		int count=APImapper.addAPI(apiName, apiHost, apiUrl, apiMethod, apiHeaders, apiParams, apiStatus, remark);
		return count;
	}

	@Override
	public Integer updateAPI(Integer id, String apiName, String apiHost,
			String apiUrl, String apiMethod, String apiHeaders,
			String apiParams, Integer apiStatus, String remark) {
		
		log.info("修改id<"+id+">接口");		
		int count=APImapper.updateAPI(id, apiName, apiHost, apiUrl, apiMethod, apiHeaders, apiParams, apiStatus, remark);
		return count;
	}

	
}
