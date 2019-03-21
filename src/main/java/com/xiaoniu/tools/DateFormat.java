package com.xiaoniu.tools;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormat {

	public static void main(String[] args) {
		
//		DecimalFormat df = new DecimalFormat("0.00");
//		double dd = 12397; 
//		System.out.println(df.format(dd/100));
		
//		DateFormat();
//		StringToDate("2016-5-10");
//		System.out.println(DateToString(new Date()));
//		gettime();
		DateToLong(new Date());
		LongToDate(DateToLong(new Date()));
//		datetoMillisecond(new Date());
//		DateUtil(DateToString(new Date()),"yyyy-MM-dd","day",-28);
//		DateUtil("2016-07-07","yyyy-MM-dd","day",-30);
//		daysBetween("2016-06-01 10:00:20", "2016-06-02 10:00:00");
//		daysBetween(StringToDate("2016-06-01"), StringToDate("2016-05-30"));
	}
	/**
	 * 将当前日期转换成"2014-06-04"
	 * 如果需要显示时、分、秒yyyy-MM-dd HH:mm:ss
	 */
	public static String DateFormat(){
		
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		Date date = new Date();		
		String str=bartDateFormat.format(date);
		System.out.println("当前日期(yyyy-MM-dd)："+str); 
		return str;
	}
	/**
	 * 将String类型转换成Date
	 * @throws ParseException 
	 */
	public static Date StringToDate(String StringDate) {
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		String str = "2014-06-02"; 		
		Date date = java.sql.Date.valueOf(StringDate);
		System.out.println("设置日期(yyyy-MM-dd)："+date);
		return date;

	}

	/**
	 * 将Date类型转换成String
	 */
	public static String DateToString(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");  
		String str=sdf.format(date); 
		
//		System.out.println("将Date类型转换成String:"+str);
//		String[] sArray=str.split("-");
//		for(String aa:sArray){
//			System.out.print(aa+" ");
//		}
		return str;
		
	}
	
	/**
	 * Date类型转换成long类型,设置的日期为：2014-06-01
	 * */
	public static Long DateToLong(Date date){
//		String str = "2014-05-23"; 		
//		Date date = java.sql.Date.valueOf(str);		
		Long dLong = date.getTime();		
		System.out.println("Date类型转换成long类型:"+dLong);
		return dLong;
	}
	
	public static long datetoMillisecond(Date date) {
		long	millionSeconds =0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
				millionSeconds = sdf.parse(sdf.format(date)).getTime();
			System.out.println(millionSeconds);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//毫秒
		return millionSeconds;
	}

	
	/**
	 * 由long类型转换成Date类型
	 * */
	public static String LongToDate(long lSysTime){		
		Date date = new Date(lSysTime);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDateTime =sd.format(date);
//        System.out.println("由long类型转换成Date类型:"+sDateTime);		
		return sDateTime;
		
	}
	/**
	 * 系统当前时间--//得到毫秒表示的系统当前时间
	 * /
	 */
	public static long gettime(){
		long lSysTime2 = System.currentTimeMillis();  
		System.out.println("得到毫秒表示的系统当前时间:"+lSysTime2);
		
		long lSysTime1 =lSysTime2+60000;
		System.out.println("1分钟以后的时间："+lSysTime1);
		return lSysTime1;
	}
	
	/**
	 * 对日期进行加减
	 * @param	Stringdate		//字符串日期:2016-06-01
	 * @param	DateFormat		//日期格式:yyyy-MM-dd
	 * @param	YEAR_MONTH_DAY	//值为:YEAR、MONTH、DAY、HOUR
	 * @param	amount			//变更的日期数，负数代表减，正数代表加	
	 * */
	public static String DateUtil(String Stringdate, String DateFormat,
			String YEAR_MONTH_DAY, int amount) {
		String reStr = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
			Date dt = sdf.parse(Stringdate);
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(dt);
			// 对年进行处理
			if (YEAR_MONTH_DAY.toUpperCase().equals("YEAR")) {
				// rightNow.add(Calendar.YEAR,-1);//日期减1年
				rightNow.add(Calendar.YEAR, amount);
			}
			// 对月进行处理
			else if (YEAR_MONTH_DAY.toUpperCase().equals("MONTH")) {
				// rightNow.add(Calendar.MONTH,3);//日期加3个月
				rightNow.add(Calendar.MONTH, amount);
			}
			// 对天进行处理
			else if (YEAR_MONTH_DAY.toUpperCase().equals("DAY")) {
				// rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天
				rightNow.add(Calendar.DAY_OF_YEAR, amount);
			}
			// 对小时进行处理
			else if (YEAR_MONTH_DAY.toUpperCase().equals("HOUR")) {
				// rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天
				rightNow.add(Calendar.HOUR, amount);
			}
			Date dt1 = rightNow.getTime();
			reStr = sdf.format(dt1);
//			System.out.println("变更前的日期：" + Stringdate + "-------->变更后的日期："+ reStr);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return reStr;
	}
	
	/**
	 * 计算两个日期之间相差的天数，会跟据具体的时、分、秒之间来比较，如果求精准的天和小时，日期格式建议使用：yyyy-MM-dd HH:mm:ss
	 * 例如：2016-06-01 14：00：00 与 2016-06-02 12：00：00
	 * @param smdate 较小的时间
	 * @param bdate 较大的时间
	 * @return 相差天数
	 */
	public static int daysBetween(Date smdate, Date bdate){
		int days=0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			
			 days=Integer.parseInt(String.valueOf(between_days));
			 System.out.println("相差天数："+days);
		} catch (ParseException e) {
			System.out.println("日期格式错误");			
		}
		return days;
	}

	/**
	 * 字符串的日期格式的计算:会跟据具体的时、分、秒之间来比较，如果只求大概的天数据，日期格式建议使用：yyyy-MM-dd HH:mm:ss
	 * 	 例如：2016-06-01 14：00：00 与 2016-06-02 12：00：00
	 * @param smdate 较小的时间
	 * @param bdate 较大的时间
	 * @return 相差天数
	 */
	public static int daysBetween(String smdate, String bdate){
		int days=0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			days= Integer.parseInt(String.valueOf(between_days));
			System.out.println("相差天数："+days);
		} catch (ParseException e) {
			System.out.println("日期格式错误");		
		}
		return days;
	}
}
