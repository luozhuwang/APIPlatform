package com.xiaoniu.httpclient;

import org.apache.http.conn.HttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * 自定义定时清理无效链接(httpclient)
 */
public class ClearConnectionsHandler extends Thread{
	private Logger logger =LoggerFactory.getLogger(ClearConnectionsHandler.class);
	
    private final HttpClientConnectionManager connMgr;

    private volatile boolean shutdown;

    public ClearConnectionsHandler(HttpClientConnectionManager connMgr) {
        this.connMgr = connMgr;
        this.start();
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                	logger.info("清理无效链接");
                    wait(5000);
                    // 关闭失效的连接
                    connMgr.closeExpiredConnections();
                }
            }
        } catch (InterruptedException ex) {
            // 结束
        }
    }

    /**
     * CRON表达式                含义 
    "0/20 * * * * ?"			20秒执行一次	
    "0 0 0/1 * * ? *"			1小时执行一次      
    "0 0 12 * * ?"            每天中午十二点触发 
    "0 15 10 ? * *"            每天早上10：15触发 
    "0 15 10 * * ?"            每天早上10：15触发 
    "0 15 10 * * ? *"        每天早上10：15触发 
    "0 15 10 * * ? 2005"    2005年的每天早上10：15触发 
    "0 * 14 * * ?"            每天从下午2点开始到2点59分每分钟一次触发 
    "0 0/5 14 * * ?"        每天从下午2点开始到2：55分结束每5分钟一次触发 
    "0 0/5 14,18 * * ?"        每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发 
    "0 0-5 14 * * ?"        每天14:00至14:05每分钟一次触发 
    "0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发 
    "0 15 10 ? * MON-FRI"   每个周一、周二、周三、周四、周五的10：15触发
     */    
    @Scheduled(cron = "0/60 * * * * ?")
    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }

}
