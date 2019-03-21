package com.xiaoniu.serviceImp;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiaoniu.service.TelnetService;
import com.xiaoniu.tools.Constant;


@Service
public class TelnetServiceImp implements TelnetService{
	private Logger log = LoggerFactory.getLogger(TelnetServiceImp.class);
	
	 /** 系统标示符号 */
    private final String osTag = "\r\n";

    /** get Value 系统标示符号 */
    private final String getValOsTag = "END\r\n";

    /** 结束符  */
    private char promptChar = '>'; 
    /** 输入流，接收返回信息 */
    private InputStream in;

    private String response=null;
    /** 向 服务器写入 命令 */
    private PrintStream out;
    

	private TelnetClient telnetClient =new TelnetClient();
	
	@Override
	public boolean connect(String ip, Integer port) {
		 try {
			telnetClient.connect(ip, port);		
			in = telnetClient.getInputStream();
		    out = new PrintStream(telnetClient.getOutputStream());
		    log.info("Telnet连接成功，IP:"+ip+"和端口:"+port);
		    return true;
		}  catch (IOException e) {			
			log.error("Telnet连接失败，请检查IP:"+ip+"和端口:"+port+"是否正确");
			return false;
		}	
	}

	@Override
	public String execute(String command) {
	       write(command);
	       StringBuffer sb = new StringBuffer();
	       String osTagX = osTag;
	       if (command.startsWith("get")) {
	           osTagX = getValOsTag;
	       }       
	       try {
	           char ch = (char) in.read();	           
	           int isEnd = 0;
	           while (true) {
	              sb.append(ch);
	              if (ch == promptChar){
	                  isEnd++;
	                  if (sb.toString().endsWith(osTagX) && isEnd == osTagX.length());  
	                  String ss=new String(sb.toString().getBytes(Constant.ISO_8859_1), Constant.UTF_8); // 编码转换，解决中文乱码问题
	                  if(ss.contains("elapsed:")){
	                	  int lastIndex = ss.lastIndexOf("elapsed:");  
	                	  response=ss.substring(0, lastIndex).trim();
	                  }else{
	                	  response=ss.replace("dubbo>", "").trim(); 
	                  }
	                  log.info("响应结果："+response);
	                  return response;
	              } else {
	                  isEnd = 0;
	              }
	              ch = (char) in.read();
	           }
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	       return "执行异常error! when the program execute";

	    
	}

	@Override
	public void disconnect() {
	       try {
	           Thread.sleep(100);
	           if(telnetClient!=null&&!telnetClient.isConnected())  
	    		   telnetClient.disconnect();
	           log.info("关闭连接成功");
	       } catch (InterruptedException e1) {
	           e1.printStackTrace();
	       } catch (IOException e2) {
	           log.error("关闭连接异常");
	       }
	}

	 private void write(String command) {
	       try {
	           out.println(command);
	           out.flush();
	           log.info("[telnet] 打印本次执行的telnet命令:" + command);
	       } catch (Exception e) {
	          log.error("执行Telent命令失败");
	       }

	    }
	 
	 public static void main(String args []){
		 TelnetServiceImp ts = new TelnetServiceImp();
		 ts.connect("172.20.20.175", 6041);
		 ts.execute("invoke com.neo.xnol.pcts.credit.facade.LoanFacade.selectByPrimaryKey(\"1\")");
		 ts.disconnect();
	 }
	
}
