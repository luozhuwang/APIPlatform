package com.xiaoniu.httpclient;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.util.FileCopyUtils;

import com.alibaba.dcm.DnsCacheManipulator;
import com.alibaba.fastjson.JSON;
import com.xiaoniu.tools.Constant;
import com.xiaoniu.tools.encryptAndDecrypt.ApiRequest;
import com.xiaoniu.tools.encryptAndDecrypt.ApiResponse;
import com.xiaoniu.tools.encryptAndDecrypt.RequestEncrypt;
import com.xiaoniu.tools.encryptAndDecrypt.ResponseDecrypt;

public class HttpClientOperate extends PoolingHttpClientConnectionManager implements BeanFactoryAware {
	private Logger logger =LoggerFactory.getLogger(HttpClientOperate.class);
	
    private BeanFactory beanFactory;
      

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * 将required设置为false:
     * 为了避免RequestConfig没被注进来的时候其他方法都不能用,报createbeanfailedexception
     *
     */
    private RequestConfig requestConfig;

    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }

    private CloseableHttpClient getHttpClient(){
        return this.beanFactory.getBean(CloseableHttpClient.class);
    }
    
    /**
     * 无参get请求
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public HttpResult doGet(String url) throws ClientProtocolException, IOException{
    	logger.info("doGet请求URL："+url);
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        
        httpGet.setConfig(requestConfig);//设置请求参数
        int statusCode=0;
        String content=null;
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = this.getHttpClient().execute(httpGet);
            statusCode=response.getStatusLine().getStatusCode();
            // 判断返回状态是否为200
            if (statusCode == 200) {
                content = EntityUtils.toString(response.getEntity(), Constant.UTF_8);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            //httpclient.close();
        }
        return  new HttpResult(statusCode, content);
    }

    /**
     * 有参get请求
     * @param url
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public HttpResult doGet(String url , Map<String, Object> params) throws URISyntaxException, ClientProtocolException, IOException{
        URIBuilder uriBuilder = new URIBuilder(url);
        if(params != null){
            for(String key : params.keySet()){
                uriBuilder.setParameter(key, (String) params.get(key));
            }
        }
        return this.doGet(uriBuilder.build().toString());
    }

    /**
     * 有参post请求
     * @param url
     * @param params
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public HttpResult doPost(String url , Map<String, Object> params) throws ClientProtocolException, IOException {
    	logger.info("doPost请求URL："+url);
    	HttpResult httpResult=new HttpResult();
    	String content=null;
        int statusCode=0;
        CloseableHttpResponse response = null;
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
		
        if(params != null){
        	logger.info("请求参数：");
            List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
            for(String key : params.keySet()){
            	String value = (String) params.get(key);
            	logger.info("\t" + key + "=" + value);
                parameters.add(new BasicNameValuePair(key, value));
            }
            // 构造一个form表单式的实体
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(formEntity);
        }      
        try {
            // 执行请求
            response = this.getHttpClient().execute(httpPost);
            content = EntityUtils.toString(response.getEntity(), Constant.UTF_8);
            statusCode=response.getStatusLine().getStatusCode();
            //如果是302，自动跳转到新URL
            if (statusCode == 302 || statusCode == 301) {		       
		        String locationUrl = response.getLastHeader("Location").getValue();
		        logger.info("跳转的URL：" + locationUrl);
		        httpResult= doGet(locationUrl);		        		        
		     }else{
		    	 httpResult.setStatusCode(statusCode);
		    	 httpResult.setContent(content);
		     }
        } finally {
            if (response != null) {
                response.close();
            }
            if(httpPost !=null){
            	httpPost.releaseConnection();
            }
            //httpclient.close();
        }
        return httpResult;     
    }
    
    
    /**
     * 有参post请求,json交互
     * @param url
     * @param json
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public HttpResult doPostJson(String url , String json) throws ClientProtocolException, IOException{
    	logger.info("doPostJson请求URL："+url);
    	logger.info("doPostJson请求json字符串："+json);
    	String content=null;
        int statusCode=0;
        CloseableHttpResponse response = null;
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
            //标识出传递的参数是 application/json
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
        
        try {
            // 执行请求
            response = this.getHttpClient().execute(httpPost);
            content = EntityUtils.toString(response.getEntity(), Constant.UTF_8);
            statusCode=response.getStatusLine().getStatusCode();
        } finally {
            if (response != null) {
                response.close();
            }           
            //httpclient.close();
        }
        return new HttpResult(statusCode,content);        
    }

    /**
     * 加密post请求
     * @param	url
     * @param	SoapReuqest
     * @return	String
     * @throws IOException 
     * @throws ClientProtocolException 
     * */
    public HttpResult doPostEncrypt(String url , String json) throws ClientProtocolException, IOException {
    	logger.info("加密doPostEncrypt请求URL："+url);
    	logger.info("加密doPostEncrypt请求json字符串："+json);
    	System.out.println("请求前DNS:"+DnsCacheManipulator.getWholeDnsCache());
    	String content=null;
        int statusCode=0;
        String Response_Body=null;
    	HttpPost	httppost = new HttpPost(url);
    	CloseableHttpResponse response=null;
    	ApiRequest request_text = RequestEncrypt.request(Constant.merChantCode,json,Constant.zxPublicKey, Constant.zxPrivateKey, Constant.otherPublicKey, Constant.otherPrivateKey);		        
        String jsonString = JSON.toJSONString(request_text);
    	try {
	    	// 创建 httppost 的实例
	    	//设置头信息
	    	httppost.setHeader("Content-Type","application/json;charset=UTF-8");
			httppost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36");
			httppost.setHeader("Accept", "text/html,application/xml,application/json");
			httppost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
	    	// 设置连接超时时间
	    	httppost.setConfig(requestConfig); 
	    	// 设置报文头以及参数的格式
    		byte[] b = jsonString.getBytes(Consts.UTF_8);
    		InputStream is = new ByteArrayInputStream(b, 0, b.length);
    		InputStreamEntity reqEntity = new InputStreamEntity(is, b.length);
    		httppost.setEntity(reqEntity);
    		// 执行post请求 ，返回Response，包含响应头和响度应体
    		response = this.getHttpClient().execute(httppost);	
    		statusCode=response.getStatusLine().getStatusCode();
			content = EntityUtils.toString(response.getEntity(), Constant.UTF_8);

			ApiResponse dencryptResponse=ResponseDecrypt.response(content, Constant.zxPublicKey, Constant.otherPrivateKey);
			logger.info("解密后响应报文:"+dencryptResponse);
			String errorMsg=dencryptResponse.getErrorMsg();
			
			if(errorMsg!=null){
				Response_Body=errorMsg;
			}else{				        	
				Response_Body=dencryptResponse.getData();
			}							
		}finally {								
			   if (response != null) {
					response.close();
			   }						
		}
    	 return new HttpResult(statusCode,Response_Body);       
    }
    
    
    /**
     * 无参post请求
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public HttpResult doPost(String url) throws ClientProtocolException, IOException{
        return this.doPost(url, null);
    }
    
    /**
     * post请求
     * @param url
     * @param	params
     * @param	FileName
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public void doPostDown(String url , Map<String, Object> params,String FileName) throws ClientProtocolException, IOException{
    	logger.info("下载文件"+url);    	
        CloseableHttpResponse response = null;
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        if(params != null){
        	logger.info("请求参数：");
        	List<NameValuePair> pairList = new ArrayList<>(params.size());
        	for (Map.Entry<String, Object> entry : params.entrySet()) {
				String key=entry.getKey();
				String value=entry.getValue().toString();
				NameValuePair pair = new BasicNameValuePair(key, value);
				logger.info("\t" + key + "=" + value);
				pairList.add(pair);
			}
            // 将请求实体设置到httpPost对象中
        	httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
        }      
     // 执行请求
        try {
			response = this.getHttpClient().execute(httpPost);
			HttpEntity entity = response.getEntity();
			
			//下载Excel：Content-Type: application/vnd.ms-excel;charset=UTF-8
			ByteToFile(entity, FileName);
		}finally{
			 if (response != null) {
	                response.close();
	         } 
			 if(httpPost !=null){
				 httpPost.releaseConnection();
			 }
		}
    }
    
    /**
	 * 下载HttpEntity文件
	 * */
	public  void ByteToFile(HttpEntity entity, String FileName){	
		try {
			File outfile = new File(FileName);
	        if(!outfile.exists()){
	        	logger.info("文件不存在，创建文件成功");
	        	outfile.createNewFile();
	        }	        
			OutputStream	outStream = new FileOutputStream(outfile);
			if (entity != null) {
				entity.writeTo(outStream);
			}
			outStream.close();
		} catch (FileNotFoundException e) {
			logger.info("文件没有找到！");
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("IO读写错误");
			e.printStackTrace();
		}
	}
	
	/**
	 * 直接从URL中下载图片
	 * */
	public  void DownPicture(String WebUrl, String file) throws IOException {
		File outfile = new File(file);
        if(!outfile.exists()){
        	outfile.createNewFile();
        	logger.info("文件不存在，创建文件成功");
        }else{
        	outfile.delete();
        	outfile.createNewFile();
        	logger.info("删除文件并创建文件成功");
        }
        
		try {
			URL	url = new URL(WebUrl);
			OutputStream os = new FileOutputStream(outfile);
			InputStream is = url.openStream();
			byte[] buff = new byte[1024];
			while (true) {
				int readed = is.read(buff);
				if (readed == -1) {
					break;
				}
				byte[] temp = new byte[readed];
				System.arraycopy(buff, 0, temp, 0, readed);
				os.write(temp);
			}
			is.close();
			os.close();
		} catch (MalformedURLException e) {
			logger.info("URL协议、格式或者路径错误");
		} catch (FileNotFoundException e) {
			logger.info("文件没找到");
		} catch (IOException e) {
			logger.info("IO输入异常");
		}
	}
	
	
	/**
	 * 上传文件
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * */
	public HttpResult httpFile(String url,Map<String, Object> paramsMap) throws ClientProtocolException, IOException{		
		logger.info("上传路径："+url);
		CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(url);
        String content=null;
        int statusCode=0;
        httpPost.setConfig(requestConfig);
         
        
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
		entityBuilder.setCharset(Charset.forName(Constant.UTF_8));
		entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		ContentType contentType = ContentType.create(Constant.PLAIN_TEXT, Constant.UTF_8);
		
		//填充参数
		if(paramsMap != null){
			logger.info("请求参数：");
			Set<String> keySet = paramsMap.keySet();
			for (String key : keySet) {
				Object obj = paramsMap.get(key);
				logger.info("\t" + key + "=" + obj);
				if (obj instanceof File) { // 参数是File 类型
					File file = (File) obj;
					entityBuilder.addPart(key, new FileBody(file));		
				} else if (obj instanceof String) { // 参数是String 类型
					String value = (String) obj;
					entityBuilder.addPart(key, new StringBody(value, contentType));
				} else {
					throw new IllegalArgumentException(key + "的类型是"
							+ obj.getClass() + "（允许的参数类型为File 或者 String）");
				}
			}
		}
		httpPost.setEntity(entityBuilder.build());
		try {
			response = this.getHttpClient().execute(httpPost);
			 statusCode=response.getStatusLine().getStatusCode();
//			if(statusCode == 200){
				HttpEntity entity = response.getEntity();
				content = EntityUtils.toString(entity);
//			}
		}finally{
			 if (response != null) {
	                response.close();
	         } 
			 if(httpPost !=null){
				 httpPost.releaseConnection();
			 }
		}
		return new HttpResult(statusCode,content);   
	}
}
