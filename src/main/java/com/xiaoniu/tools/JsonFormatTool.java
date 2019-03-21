package com.xiaoniu.tools;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class JsonFormatTool {
	 /**
     * 单位缩进字符串。
     */
    private static String SPACE = "   ";

    /**
     * 返回格式化JSON字符串。
     * 
     * @param json 未格式化的JSON字符串。
     * @return 格式化的JSON字符串。
     */
    public static String formatJson(String json)
    {
        StringBuffer result = new StringBuffer();

        int length = json.length();
        int number = 0;
        char key = 0;
        //遍历输入字符串。
        for (int i = 0; i < length; i++)
        {
            //1、获取当前字符。
            key = json.charAt(i);

            //2、如果当前字符是前方括号、前花括号做如下处理：
            if((key == '[') || (key == '{') )
            {
                //（1）如果前面还有字符，并且字符为“：”，打印：换行和缩进字符字符串。
                if((i - 1 > 0) && (json.charAt(i - 1) == ':'))
                {
                    result.append('\n');
                    result.append(indent(number));
                }

                //（2）打印：当前字符。
                result.append(key);

                //（3）前方括号、前花括号，的后面必须换行。打印：换行。
                result.append('\n');

                //（4）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。
                number++;
                result.append(indent(number));

                //（5）进行下一次循环。
                continue;
            }

            //3、如果当前字符是后方括号、后花括号做如下处理：
            if((key == ']') || (key == '}') )
            {
                //（1）后方括号、后花括号，的前面必须换行。打印：换行。
                result.append('\n');

                //（2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。
                number--;
                result.append(indent(number));

                //（3）打印：当前字符。
                result.append(key);

                //（4）如果当前字符后面还有字符，并且字符不为“，”，打印：换行。
                if(((i + 1) < length) && (json.charAt(i + 1) != ','))
                {
                    result.append('\n');
                }

                //（5）继续下一次循环。
                continue;
            }

            //4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。
            if((key == ','))
            {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }

            //5、打印：当前字符。
            result.append(key);
        }

        return result.toString();
    }

    /**
     * 返回指定次数的缩进字符串。每一次缩进三个空格，即SPACE。
     * 
     * @param number 缩进次数。
     * @return 指定缩进次数的字符串。
     */
    private static String indent(int number)
    {
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < number; i++)
        {
            result.append(SPACE);
        }
        return result.toString();
    }

    public static void main(String[] args) {
//        JsonFormatTool json = new JsonFormatTool();
//        String str = "{'age':23,'aihao':['pashan','movies'],'name':{'firstName':'zhang','lastName':'san','aihao':['pashan','movies','name':{'firstName':'zhang','lastName':'san','aihao':['pashan','movies']}]}}";
//        String result = formatJson(str);
//        System.out.println(result);
        
        //JSON转换String
        String abc="{\"contractNo\":\"1523343809903\",\"loanCode\":\"NA0115acc6259815b7200382701d9\",\"idNo\":\"130301198805132616\",\"idType\":1,\"nameType\":\"1\",\"borrowerName\":\"陈国\",\"borrowAmount\":\"55555.55\",\"annualRate\":\"10.1400\",\"term\":3,\"termMode\":2,\"repaymentMode\":0,\"purpose\":5,\"auditOpinion\":\"SDAFSDF\",\"auditTime\":\"2018-04-10 15:19:41\",\"assetClass\":1,\"loanCategory\":\"a1\",\"loanCategoryDesc\":\"小微贷-A\",\"jjServiceCost\":\"10.1300\",\"zjServiceCost\":\"10.1100\",\"zmServiceCost\":\"10.1200\",\"monthAnnualRate\":\"0.85\",\"borrowFeeType\":\"借款费用类别1\",\"borrowType\":0,\"sourceInstitution\":\"a\",\"institutionType\":\"n\",\"payeeName\":\"陈国\",\"loanAmount\":\"4444.44\",\"contractAmount\":\"55555.55\",\"outBankAccount\":\"6222026263563341971\",\"outBankOpenName\":\"工商银行\",\"outBankOpenPrince\":\"广东省\",\"outBranchBankName\":\"中国工商银行南山支行\",\"outBankOpenCity\":\"深圳市\",\"inBankOpenName\":\"工商银行\",\"inBankOpenPrince\":\"广东省\",\"inBankOpenCity\":\"深圳市\",\"inBranchBankName\":\"中国工商银行南山支行\",\"inBankAccount\":\"6222026263563341971\",\"sourceZone\":0,\"signDate\":1487520000000,\"serialNum\":\"NA0115acc6259815b7200382701d9\",\"matchType\":4}";
    	System.out.println(abc);
    	List<String> ll2= new ArrayList<>();
    	ll2.add(abc);
    	String ss3=JSON.toJSONString(ll2);
    	System.out.println(ss3);

    }
}
