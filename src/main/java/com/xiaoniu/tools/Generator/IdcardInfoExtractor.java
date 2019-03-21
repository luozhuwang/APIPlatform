package com.xiaoniu.tools.Generator;

import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Calendar;  
import java.util.Date;  
import java.util.GregorianCalendar;  
import java.util.HashMap;  
import java.util.Map;  
import java.util.Set;  
  
/**
 * 通过证件号码获取省份、性别、出生日期、年龄
 * */ 
public class IdcardInfoExtractor {  
     // 省份    
    private String province;    
    // 年份    
    private int year;    
    // 月份    
    private int month;    
    // 日期    
    private int day;    
    //出生年月日
    private  String BirthDate;
	// 性别    
    private String gender;//男、女   
    private int Sex;//0、1
    // 出生日期    
    private Date birthday;   
    //年龄  
    private int age;  
    
    private Map<String, String> cityCodeMap = new HashMap<String, String>() {    

	private static final long serialVersionUID = 85407761701568908L;

		{    
            this.put("11", "北京");    
            this.put("12", "天津");    
            this.put("13", "河北");    
            this.put("14", "山西");    
            this.put("15", "内蒙古");    
            this.put("21", "辽宁");    
            this.put("22", "吉林");    
            this.put("23", "黑龙江");    
            this.put("31", "上海");    
            this.put("32", "江苏");    
            this.put("33", "浙江");    
            this.put("34", "安徽");    
            this.put("35", "福建");    
            this.put("36", "江西");    
            this.put("37", "山东");    
            this.put("41", "河南");    
            this.put("42", "湖北");    
            this.put("43", "湖南");    
            this.put("44", "广东");    
            this.put("45", "广西");    
            this.put("46", "海南");    
            this.put("50", "重庆");    
            this.put("51", "四川");    
            this.put("52", "贵州");    
            this.put("53", "云南");    
            this.put("54", "西藏");    
            this.put("61", "陕西");    
            this.put("62", "甘肃");    
            this.put("63", "青海");    
            this.put("64", "宁夏");    
            this.put("65", "新疆");    
            this.put("71", "台湾");    
            this.put("81", "香港");    
            this.put("82", "澳门");    
            this.put("91", "国外");    
        }    
    };    
        
     
        
    /**  
     * 通过构造方法初始化各个成员属性  
     */    
    public void IdCardresolve(String idcard) {    	
		try {
			// 获取省份    
	        String provinceId = idcard.substring(0, 2);    
	        Set<String> key = this.cityCodeMap.keySet();    
	        for (String id : key) {    
	            if (id.equals(provinceId)) {    
	                this.province = this.cityCodeMap.get(id);    
	                break;    
	            }    
	        }    

	        // 获取性别    
	        String id17 = idcard.substring(16, 17);   
	        Sex=Integer.valueOf(id17)% 2;
	        if (Sex != 0) {    
	            this.gender = "男";    
	        } else {    
	            this.gender = "女";    
	        }    

	        // 获取出生日期    
	        String birthday = idcard.substring(6, 14);    
	        String yy1 = idcard.substring(6,10);          //出生的年份
	        String mm1 = idcard.substring(10,12);       //出生的月份
	        String dd1 = idcard.substring(12,14);         //出生的日期

	        BirthDate = yy1.concat("-").concat(mm1).concat("-").concat(dd1);
           
	        
			Date birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);
			 this.birthday = birthdate;    
		        GregorianCalendar currentDay = new GregorianCalendar();    
		        currentDay.setTime(birthdate);    
		        this.year = currentDay.get(Calendar.YEAR);    
		        this.month = currentDay.get(Calendar.MONTH) + 1;    
		        this.day = currentDay.get(Calendar.DAY_OF_MONTH);    
		          
		        //获取年龄  
		        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");  
		        String year=simpleDateFormat.format(new Date());  
		        this.age=Integer.parseInt(year)-this.year;  
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
       
    }    
    
    /**  
     * @return the province  
     */    
    public String getProvince() {    
        return province;    
    }    
    /**  
     * @return the year  
     */    
    public int getYear() {    
        return year;    
    }    
    
    /**  
     * @return the month  
     */    
    public int getMonth() {    
        return month;    
    }    
    
    /**  
     * @return the day  
     */    
    public int getDay() {    
        return day;    
    }    
    
    /**  
     * @return the gender  
     */    
    public String getGender() {    
        return gender;    
    }    
    
    public String getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(String birthDate) {
		BirthDate = birthDate;
	}
	
    /**  
     * @return the birthday  
     */    
    public Date getBirthday() {    
        return birthday;    
    }    
    
    @Override    
    public String toString() {    
        return "省份：" + this.province + ",性别：" + this.gender + ",出生日期："    
                + this.birthday;    
    }    
    
 
    public int getAge() {  
        return age;  
    }  
  
    public void setAge(int age) {  
        this.age = age;  
    } 
    public int getSex() {
		return Sex;
	}

	public void setSex(int sex) {
		Sex = sex;
	}
      
    public static void main(String[] args) {  
    	String IdCard="610927196110118916";
        
        IdcardInfoExtractor idcardInfo=new IdcardInfoExtractor();
        idcardInfo.IdCardresolve(IdCard);
        System.out.println("证件号码："+IdCard);
        System.out.println("出生日期:"+idcardInfo.getBirthDate());  
        System.out.println("性别:"+idcardInfo.getGender());  
        System.out.println("年龄:"+idcardInfo.getAge());  
        System.out.println("省份:"+idcardInfo.getProvince());  
        System.out.println("性别："+idcardInfo.getGender());
          
    }  
    
}  
