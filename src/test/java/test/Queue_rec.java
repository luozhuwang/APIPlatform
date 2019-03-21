package test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.xiaoniu.model.accountBookModel;
import com.xiaoniu.model.certInfoModel;
import com.xiaoniu.model.mortgageModel;



 
public class Queue_rec {
	private Logger logger=LoggerFactory.getLogger(Queue_rec.class);
	//  队列
	private static void queue(){
			Queue<String> queue = new LinkedList<>();
			queue.offer("Hello");
			queue.offer("world");
			queue.offer("I m find");
			System.out.println("--------------------------");
			System.out.println(queue.size());
			String aa ;
			while((aa = queue.poll()) !=null){
				System.out.println("queue---"+aa);
			}
			System.out.println(queue.size());
			System.out.println("--------------------------");
	}

	
	// 堆栈
	private static void stack(){
		Vector<String> stack = new Vector<String>();
		stack.add("aaa");
		stack.add("bbb");
		stack.add("ccc");
		stack.add("ddd");
		stack.add("fff");
		System.out.println("---------------------------");
		System.out.println("size" + stack.size());

		String aa;
		try {
			while ((aa = stack.lastElement()) != null) {
				System.out.println("a---" + aa);
				stack.removeElement(aa);
				// break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("size" + stack.size());

	}

	
	public static void main(String[] args) {
		accountBookModel accountBookmodel=new accountBookModel();
		accountBookmodel.setAccountType("0");
		accountBookmodel.setAllFee(1.12);
		mortgageModel mortgage=new mortgageModel();
		mortgage.setCarNo("123ABC");
		accountBookmodel.setMortgage(mortgage);
		
		List<certInfoModel> certInfoList=new ArrayList<certInfoModel>();
		certInfoModel certinfo=new certInfoModel();
		certinfo.setCertType("A");
		certInfoList.add(certinfo);
		accountBookmodel.setCertInfoList(certInfoList);
		
		String abc=JSON.toJSONString(accountBookmodel);
		
		System.out.println(abc);
//		queue();
//		stack();
//		Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );
		
//		List<String> aa=new ArrayList<String>();
//		aa.add("a");
//		aa.add("d1");
//		aa.add("c");
//		aa.add("d2");
//		aa.add("e");
//		
//		
//		System.out.println(aa.stream().filter(e -> e.contains("d")).collect(Collectors.toList()));
		
	}
 
	//DNS配置，未测试通过
	public boolean addressCache(Map<String, String> addressMap)
			throws NoSuchMethodException, SecurityException {

		// 以下内容修改缓存失效时间,单位秒(-1,永久缓存;0,不缓存;其它>0的值为缓存的秒数)
		Class<?> iacp = sun.net.InetAddressCachePolicy.class;

		Field cachePolicyFiled = null;
		Field setEnable = null;
		try {
			cachePolicyFiled = iacp.getDeclaredField("cachePolicy");
			setEnable = iacp.getDeclaredField("set");
		} catch (NoSuchFieldException e) {
			logger.error("Get cachePolicyFiled or setEnable failed!", e);
		}
		cachePolicyFiled.setAccessible(true);
		setEnable.setAccessible(true);

		try {
			cachePolicyFiled.set(null, 0);
			setEnable.set(null, true);
		} catch (IllegalArgumentException e) {
			logger.error("Set cachePolicyFiled and setEnable failed!", e);
		} catch (IllegalAccessException e) {
			logger.error("Access cachePolicyFiled and setEnable failed!", e);
		}
		// 以下内容修改DNS的缓存数据，实现域名动态绑定
		Class<?> inetAddressClass = java.net.InetAddress.class;
		Field cacheField = null;
		Object addressCache = null;
		InetAddress ia = null;
		try {
			cacheField = inetAddressClass.getDeclaredField("addressCache");
		} catch (SecurityException e) {
			logger.error("Get addressCache security failed!", e);
		} catch (NoSuchFieldException e) {
			logger.error("Get addressCache field failed!", e);
		}

		cacheField.setAccessible(true);

		try {
			addressCache = cacheField.get(inetAddressClass);
		} catch (IllegalArgumentException e) {
			logger.error("Get cacheField IllegalArgument failed!", e);
		} catch (IllegalAccessException e) {
			logger.error("Get cacheField Access failed!", e);
		}
		Class<?> cacheClazz = addressCache.getClass();

		Method m_put = cacheClazz.getMethod("put", new Class<?>[] {
				String.class, Object.class });
		m_put.setAccessible(true);

		try {
			for (String domain : addressMap.keySet()) {
				ia = Inet4Address.getByName(addressMap.get(domain));
				m_put.invoke(addressCache, new Object[] { domain,
						new InetAddress[] { ia } });
			}

		} catch (IllegalArgumentException e) {
			logger.error("Put addressCache IllegalArgument failed!", e);
		} catch (IllegalAccessException e) {
			logger.error("Put addressCache IllegalAccess failed!", e);
		} catch (InvocationTargetException e) {
			logger.error("Put addressCache InvocationTarget failed!", e);
		} catch (UnknownHostException e) {
			logger.error("Put addressCache UnknownHost failed!", e);
		}
		// 修改缓存数据结束
		return true;
	}
	
}

