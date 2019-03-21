package com.xiaoniu.tools.listener;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ResourceCDN;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.TestAttribute;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentTestNGIReporterListener implements IReporter {
    //生成的路径以及文件名   
    private static final String OUTPUT_FOLDER ="test-output/";
    private static final String FILE_NAME = "TestNGReport.html";

    private ExtentReports extent;

    @Override
    public void generateReport(List<XmlSuite>  xmlSuites, List<ISuite> suites, String outputDirectory) {
        init();
        boolean createSuiteNode = false;
        if(suites.size()>1){
            createSuiteNode=true;
        }
        for (ISuite suite : suites) {
            Map<String, ISuiteResult>  result = suite.getResults();
            //如果suite里面没有任何用例，直接跳过，不在报告里生成
            if(result.size()==0){
                continue;
            }
            //统计suite下的成功、失败、跳过的总用例数
            int suiteFailSize=0;
            int suitePassSize=0;
            int suiteSkipSize=0;
            ExtentTest suiteTest=null;
            //存在多个suite的情况下，在报告中将同一个一个suite的测试结果归为一类，创建一级节点。
            if(createSuiteNode){
                suiteTest = extent.createTest(suite.getName()).assignCategory(suite.getName());
            }
            boolean createSuiteResultNode = false;
            if(result.size()>1){
                createSuiteResultNode=true;
            }
            Date suiteStartTime = null,suiteEndTime=new Date();
            for (ISuiteResult r : result.values()) {
                ExtentTest resultNode;
                ITestContext context = r.getTestContext();
                if(createSuiteResultNode){
                    //没有创建suite的情况下，将在SuiteResult的创建为一级节点，否则创建为suite的一个子节点。
                    if( null == suiteTest){
                        resultNode = extent.createTest(context.getName());
                    }else{
                        resultNode = suiteTest.createNode(context.getName());
                    }
                }else{
                    resultNode = suiteTest;
                }
                if(resultNode != null){
                    resultNode.assignCategory(suite.getName(),context.getName());
                    if(suiteStartTime == null){
                        suiteStartTime = context.getStartDate();
                    }
                    suiteEndTime = context.getEndDate();
                    resultNode.getModel().setStartTime(context.getStartDate());
                    resultNode.getModel().setEndTime(context.getEndDate());
                    //统计SuiteResult下的数据
                    int passSize = context.getPassedTests().size();
                    int failSize = context.getFailedTests().size();
                    int skipSize = context.getSkippedTests().size();
                    suitePassSize += passSize;
                    suiteFailSize += failSize;
                    suiteSkipSize += skipSize;
                    if(failSize>0){
                        resultNode.getModel().setStatus(Status.FAIL);
                    }
                    resultNode.getModel().setDescription(String.format("Pass: %s ; Fail: %s ; Skip: %s ;",passSize,failSize,skipSize));
                }
                buildTestNodes(resultNode,context.getFailedTests(), Status.FAIL);
                buildTestNodes(resultNode,context.getSkippedTests(), Status.SKIP);
                buildTestNodes(resultNode,context.getPassedTests(), Status.PASS);
            }
            if(suiteTest!= null){
                suiteTest.getModel().setDescription(String.format("Pass: %s ; Fail: %s ; Skip: %s ;",suitePassSize,suiteFailSize,suiteSkipSize));
                suiteTest.getModel().setStartTime(suiteStartTime==null?new Date():suiteStartTime);
                suiteTest.getModel().setEndTime(suiteEndTime);
                if(suiteFailSize>0){
                    suiteTest.getModel().setStatus(Status.FAIL);
                }
            }

        }
        extent.flush();
    }

    private void init() {
    	System.out.println("OUTPUT_FOLDER:"+OUTPUT_FOLDER);
        //文件夹不存在的话进行创建
        File reportDir= new File(OUTPUT_FOLDER);
        if(!reportDir.exists()&& !reportDir .isDirectory()){
            reportDir.mkdir();
        }
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
        htmlReporter.config().setDocumentTitle(ReportUtil.getReportName());
        htmlReporter.config().setReportName(ReportUtil.getReportName());
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
      //设置报表中的时间格式
        htmlReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss"); 
        //设置编码格式:gbk
      htmlReporter.config().setEncoding("utf-8");        
        htmlReporter.config().setTheme(Theme.STANDARD);
        //设置点击效果：.node.level-1  ul{ display:none;} .node.level-1.active ul{display:block;}
        //设置系统信息样式：.card-panel.environment  th:first-child{ width:30%;}
        htmlReporter.config().setCSS(".node.level-1  ul{ display:none;} .node.level-1.active ul{display:block;}  .card-panel.environment  th:first-child{ width:30%;}");
        // 移除按键监听事件
        htmlReporter.config().setJS("$(window).off(\"keydown\");");
        //设置静态文件的DNS 如果cdn.rawgit.com访问不了，可以设置为：ResourceCDN.EXTENTREPORTS 或者ResourceCDN.GITHUB
        htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);
        // 设置系统信息
        Properties properties = System.getProperties();
        extent.setSystemInfo("os.name",properties.getProperty("os.name","未知"));
//        extent.setSystemInfo("os.arch",properties.getProperty("os.arch","未知"));
//        extent.setSystemInfo("os.version",properties.getProperty("os.version","未知"));
        extent.setSystemInfo("java.version",properties.getProperty("java.version","未知"));
//        extent.setSystemInfo("java.home",properties.getProperty("java.home","未知"));
        extent.setSystemInfo("user.name",properties.getProperty("user.name","未知"));
//        extent.setSystemInfo("user.dir",properties.getProperty("user.dir","未知"));
    }
    
    //增加用例描述和参数
    private void buildTestNodes(ExtentTest extenttest,IResultMap tests, Status status) {
    	int id=1;
        //存在父节点时，获取父节点的标签
        String[] categories=new String[0];
        if(extenttest != null ){
            List<TestAttribute> categoryList = extenttest.getModel().getCategoryContext().getAll();
            categories = new String[categoryList.size()];
            for(int index=0;index<categoryList.size();index++){
                categories[index] = categoryList.get(index).getName();
            }
        }

        ExtentTest test;

        if (tests.size() > 0) {
            //调整用例排序，按时间排序
            Set<ITestResult> treeSet = new TreeSet<ITestResult>(new Comparator<ITestResult>() {
                @Override
                public int compare(ITestResult o1, ITestResult o2) {
                    return o1.getStartMillis()<o2.getStartMillis()?-1:1;
                }
            });
            treeSet.addAll(tests.getAllResults());
            for (ITestResult result : treeSet) {
                Object[] parameters = result.getParameters();
                String name="";
                
                //用例描述
                String Description=result.getMethod().getDescription();               
                String method=result.getMethod().getMethodName();
                //如果有参数，则使用参数的toString组合代替报告中的name
                for(Object param:parameters){
                    name+=param.toString();
                }
                if(name.length()>0){
                    if(name.length()>50){
                        name= name.substring(0,49)+"...";
                    }
                }
                //测试方法和参数 拼接
                String TestName=method+name;
                if(extenttest==null){
                    test = extent.createTest(TestName);
                }else{
                    //作为子节点进行创建时，设置同父节点的标签一致，便于报告检索。
                    test = extenttest.createNode(TestName).assignCategory(categories);
                }
                //test = extent.createTest(result.getMethod().getMethodName());
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                List<String> outputList = Reporter.getOutput(result);
                for(String output:outputList){
                    //将用例的log输出报告中
                    test.debug(output.replaceAll("<","&lt;").replaceAll(">","&gt;"));
                }
                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());
                }
                else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }
                //添加用例描述
                test.getModel().setDescription("用例描述："+Description);
                //设置log的时间，根据ReportUtil.log()的特定格式进行处理获取数据log的时间
                Iterator logIterator = test.getModel().getLogContext().getIterator();
                while (logIterator.hasNext()){
                    Log log = (Log) logIterator.next();
                    String details = log.getDetails();
                    if(details.contains(ReportUtil.getSpiltTimeAndMsg())){
                        String time = details.split(ReportUtil.getSpiltTimeAndMsg())[0];
                        log.setTimestamp(getTime(Long.valueOf(time)));
                        log.setDetails(details.substring(time.length()+ReportUtil.getSpiltTimeAndMsg().length()));
                    }else{                    	
                        log.setTimestamp(getTime(result.getEndMillis()));
                    }
                }
                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
                
                
//                System.out.println("用例id:"+"U0"+id);
//                System.out.println("case_title:"+method);
//                System.out.println("test_description:"+Description);
//                System.out.println("executor："+"罗菊旺");
//                System.out.println("test_type:"+"api");
//                System.out.println("test_result:"+status.toString().toLowerCase());
//                System.out.println("test_start_time:"+FormatTime(result.getStartMillis()));
//                System.out.println("test_end_time："+FormatTime(result.getEndMillis()));
                id++;
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
    
    private String FormatTime(long millis) {
    	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
		//前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
		java.util.Date dt = new Date(millis);  
		String sDateTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
        return sDateTime;
    }    
}