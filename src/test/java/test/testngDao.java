package test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoniu.mapper.APICaseNewMapper;
import com.xiaoniu.mapper.APICaseRelationMapper;
import com.xiaoniu.mapper.APIMapper;
import com.xiaoniu.mapper.APIResultMapper;
import com.xiaoniu.model.APICaseNew;
import com.xiaoniu.model.APICaseRelation;
import com.xiaoniu.model.APIModel;
import com.xiaoniu.model.AssertModel;
import com.xiaoniu.model.CaseResultModel;
import com.xiaoniu.model.Pie_Data;
import com.xiaoniu.tools.HttpsUtils;
import com.xiaoniu.tools.RegexUtils;



@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml" })
public class testngDao  extends AbstractTestNGSpringContextTests{

	@Autowired
	private APIMapper APImapper;
	
	@Autowired
	private APICaseNewMapper APIcaseNewMapper;
	
	@Autowired
	private APICaseRelationMapper APIcaseRelationMapper;
	
	@Autowired
	private APIResultMapper APIresultMapper;
	
	private Vector<Integer> stack = new Vector<Integer>();
	
	private Vector<Integer>  vecotr(Integer caseId){
		
		APICaseNew APICase=APIcaseNewMapper.getAPICaseNew(caseId);
		Integer isRun=APICase.getIsRun();
		Integer dependId=APICase.getDependCaseId();
//		Integer APIcaseId=APICase.getId();
		
		if(isRun==1){
			logger.info("用例<"+caseId+">已运行");
		}else{
			//堆栈进行存储,直接返回
			stack.add(caseId);
			if(dependId!=0 ){		
				//递归验证
				vecotr(dependId);
			}

		}
		
		return stack;
		
	}

	
	@Test
	public void testVector(){
		Vector<Integer> stackInt=vecotr(5);
		Integer aa;
		try {
			while ((aa = stackInt.lastElement()) != null) {
				System.out.println("执行用例---" + aa);
				stackInt.removeElement(aa);
				// break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testaa(){
//		APIcaseNewMapper.addAPICaseNew(1, "测试用例3", "用例依赖3");
//		System.out.println(APIcaseNewMapper.getMaxCaseId());
//		APICaseNew aa=new APICaseNew();
//		aa.setCaseName("测试用例2");
//		APICaseNew wbc=APIcaseNewMapper.getAPICaseNew(2);
//		System.out.println(wbc);
//		APIcaseNewMapper.delAPICaseNew(8);
//		APIcaseRelationMapper.delAPICaseRelation(8);
//		List<APICaseNew> casenewList=APIcaseNewMapper.getALLAPICaseNew(aa);
//		for(APICaseNew casess:casenewList){
//			System.out.println(casess);
////		}
//		APIcaseRelationMapper.addAPICaseRelation(3, 4, "casedata1", "caseAssert1", "caseParam1");
//		APIcaseRelationMapper.addAPICaseRelation(3, 5, "casedata2", "caseAssert2", "caseParam2");
//		APIcaseRelationMapper.addAPICaseRelation(3, 6, "casedata3", "caseAssert3", "caseParam3");
		
		
//		List<APICaseRelation> caseRelationsList=APIcaseRelationMapper.getAPICaseRelations(3);
//		for(APICaseRelation relation:caseRelationsList){
//			System.out.println(relation.getAPIId());
//			System.out.println(relation.getApiName());
//			System.out.println(relation.getApiUrl());
//			System.out.println(relation);
//		}
		
		APICaseRelation APIRelation=APIcaseRelationMapper.getAPIRelation(1,2, 3);
		System.out.println(APIRelation);
	}
	
	@Test
	public void testa(){
//		APImapper.addAPI("带参数get请求", "http://172.20.20.160:8030", "/testproject/publish/collection?", "get", null, "{\"online_date\":\"2018-10-18\"}", 1, "无");
//		List<APIModel>  apilist=APImapper.getALLAPI(null);
		APIModel api=new APIModel();
		api.setApiStatus(0);	
		List<APIModel>  apilist=APImapper.getALLAPI(api);
//		APImapper.getAPI(1);
//		APImapper.updateAPI(1, "带参数get请求", "http://172.20.20.160:8030", "/testproject/publish/collection?", "get", null, "{\"online_date\":\"2018-10-18\"}", 0, "无");
		System.out.println("1");
	}
	
	
	@Test
	public void testb(){
		int a=1;
		int b=1;
		
		String a1="1";
		String b1="b1";
		
		System.out.println(Integer.valueOf(a1));
//		Assert.assertNotEquals(a, b);
//		Assert.assertNotEquals(a1, b1);
//		Assert.assertNotSame(a1, b);
	}
	
	
	@Test
	public void testc(){
//		String json="{\"partnerId\":\"10018\",\"partnerAssetId\":\"25247112121\",\"category\":\"b6\",\"name\":\"分红的说法萨芬会大厦\",\"signTime\":1542159456000,\"signArea\":1,\"signAddr\":\"298\",\"contractId\":\"FP26h12018111400000003\",\"assetUserType\":0,\"assetUserId\":\"8b26be36-ac51-49fd-9306-0a183d72ff06\",\"loanType\":1,\"loanWay\":0,\"idCardType\":1,\"idCard\":\"450981199107208242\",\"amount\":7800,\"lendAmount\":7800,\"receiverName\":\"分红的说法萨芬会大厦\",\"receiverBankCardNo\":\"6228481778304422876\",\"receiverBankType\":\"abc\",\"receiverProvince\":\"浙江省\",\"receiverCity\":\"金华市\",\"receiverBranch\":\"台州银行股份有限公司金华分行\",\"periodType\":\"M\",\"period\":12,\"repayType\":1,\"purpose\":\"个人消费\",\"rate\":0.07716,\"intermediaryFee\":0,\"futuresFee\":0.00167,\"singleFee\":0,\"auditOpinion\":\"同意\",\"description\":\"旅游\",\"collectDuration\":1,\"receiverIdCardType\":3,\"receiverIdCardNo\":\"9133070009609571XC\",\"receiverMobile\":\"13575911187\",\"repaySource\":\"工资收入\",\"repaySafeguards\":\"该笔借款由深圳市小牛普惠投资管理有限公司提供担保。（其中小牛普惠与小牛在线为同一控制人控制下的关联方）\",\"undueLoanInfo\":[{\"fundUse\":\"无变化\",\"financialCondition\":\"无变化\",\"repayAbilityChange\":\"无变化\",\"overdue\":\"无变化\",\"litigation\":\"无\",\"punishment\":\"无\"},{\"fundUse\":\"变化1\",\"financialCondition\":\"变化2\",\"repayAbilityChange\":\"变化3\",\"overdue\":\"变化4\",\"litigation\":\"\",\"punishment\":\"无\"}],\"orgAssetsLevel\":\"D\",\"callbackUrl\":\"http://10.10.47.11:7001/XiaoNiu/CallBackService\"}";
//		System.out.println(json);
//		
//		 JSONObject jsonObject = JSONObject.parseObject(json);
//		 
//		 System.out.println(jsonObject.get("partnerId"));
//		 
//		 String zz= jsonObject.get("undueLoanInfo").toString();
//		 System.out.println(zz);
//		 //第二轮
//		 JSONArray jsonObject2 = JSONObject.parseArray(zz);
//		 String cc= jsonObject2.get(0).toString();
//		 
//		 
//		 JSONObject jsonObject3 = JSONObject.parseObject(cc);
//		 System.out.println(jsonObject3.get("financialCondition"));
		 
		 
		 String abc="{\"transId\":\"1542252603384\",\"contractId\":\"1542252603384\",\"records\":{\"financialCondition\":\"无变化\",\"punishment\":\"无\",\"overdue\":\"无变化\",\"litigation\":\"无\",\"fundUse\":\"无变化\",\"repayAbilityChange\":\"无变化\"}}";
		 System.out.println(abc);
		 JSONObject jsonObject4 = JSONObject.parseObject(abc);
		 String records=jsonObject4.get("records").toString();
		 System.out.println(records);
		 JSONObject jsonObject5 = JSONObject.parseObject(records);
		 System.out.println(jsonObject5.get("repayAbilityChange"));
	}
	
	
	@Test
	public void testd(){
//		String mess="[{\"param\":\"certNo\",\"method\":\"Regular\",\"rule\":\"(\\\\d{18}|\\\\d{15})\"}]";
//		
//		String ss="{\"custName\":\"陈一中\",\"certNo\":\"220183198208079824\",\"unClearedAmt\":20000.00}";
//		ArrayList<ParamModel> params = JSON.parseObject(mess, new TypeReference<ArrayList<ParamModel>>() {});
//		System.out.println(mess);
//		for(ParamModel param:params){
//			String reg=param.getRule();
//			System.out.println("正则："+reg);
//			RegexUtils.resolve_Reg(ss, reg);
//			
//			
//		}
		String mess="{\"RequestIp\":\"##{env_ip}##\",\"transId\":\"%{transId2}%\",\"merchantCode\":\"NEO200000\",\"certNo\":\"%{transId2}%\",\"status\":\"1\"}";				
		System.out.println(mess);
		RegexUtils.resolve_Reg(mess, "##\\{.+?\\}##");
	}
	
	
	@Test
	public void testjson(){
		String result="[{\"assertItem\":\"状态码\",\"compare\":\"等于\",\"expect\":\"200\"},{\"assertItem\":\"响应报文\",\"compare\":\"包含\",\"expect\":\"{\\\"page\\\":\\\"1\\\",\\\"rows\\\":\\\"20\\\"}\"}]";
		System.out.println(result);

//		System.out.println(RegexUtils.escapeExprSpecialWord(result));

		
		List<AssertModel> asserts = JSONObject.parseArray(JSON.toJSON(result).toString(), AssertModel.class);
		for(AssertModel assertM:asserts){
			String expect=assertM.getExpect();
			
			String escapeExpr=RegexUtils.escapeExpr(expect);
			assertM.setExpect(escapeExpr);
			System.out.println(escapeExpr);
			
		}
		String str = JSON.toJSONString(asserts); // List转json
		System.out.println("替换后："+str);
	}
	
	@Test
	public void teste(){
//		String a="13,14,16,17,32,33,34";
//		String [] ss=a.split(",");
//		int in[] = new int[ss.length];
//		for (int i = 0; i < ss.length; i++) {
//			in[i] = Integer.parseInt(ss[i]);
//			System.out.println(in[i]);
//		}
		String[] arr = { "0", "1", "2", "3", "4", "5" };
		for(String aa:arr){
			System.out.println(aa);
		}
	}
	
	@Test
	public void testf(){
//		APIresultMapper.addAPIResult(1, 2, 0, "2018-12-28 11:16:00", "2018-12-28 11:16:01", 300, "222", "OK");
		List<CaseResultModel> APIResults=APIresultMapper.getAPIResultBySetId(2, 1);
		for(CaseResultModel APIResult:APIResults){
			System.out.println(APIResult);
		}
	}
	
}
