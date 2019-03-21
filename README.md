访问URL:http://localhost:8080/APIPlatform/home
<br>

文档介绍<br>
---------
测试的项目使用内部加密的算法，请求方式中多一种post加密，同时数据比较多结构复杂，采用json数据串格式输入，如果是普通的get或post的请求，将表单形式的数据转换成json格式，数据都是String类型，针对于普通get/post的http接口自动化的友好性和体验不是特别好，待后续优化的，可以满足接口的自动化测试


实现技术：<br>
---------
项目使用主流SSM框架整合HttpClient、TestNG等开源框架提高开发效率进行后台开发，前端使用JSP结合BootStrap、jQuery、echarts操作和动态展示页面，采用Maven对工程进行管理和打包，使用Tomcat部署在Linux机器。


登陆主界面<br>
---------
显示最近七次执行记录，点击不同的柱子，刷新右侧饼图和下面的表格数据
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/1.png)

一、接口管理
---------

  1.1接口列表页面<br>
    可以通过接口名称/请求方式/状态 查询对应的数据
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/2.png)
  1.2新增接口<br>
    在接口列表页面，点击新增按钮，出现此界面<br>
    请求环境：域名或ip+端口，例如： http://www.abc.com 或 http:// www.abc.com:8080<br>
    请求地址：具体的请求地址，例如：/user/login<br>
    状态:启用/暂停(新增的时候会过滤此状态的数据)
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/3.png)
  1.3编辑接口<br>
    同新增接口<br>

  1.4删除接口<br>
    1.被用例引用的接口不可删除<br>
    2.数据库删除数据，不可恢复
<br>

二、用例管理
---------

2.1用例列表页面<br>
可以通过接口名称/用例名称 查询对应的数据<br>
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/4.png)
2.2新增用例<br>
1.选择接口数据来源于接口列表中启用的接口，选择成功后，默认的数据会带过来，并且可以自行改动保存<br>
2.每个tab页都会有个帮助，鼠标放上去即可显示
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/5.png)
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/6.png)
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/7.png)
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/8.png)

2.3执行用例<br>
在用例列表页面，点击“执行“按钮会进行灰化，文字变成“执行中”，用例执行成功会提示：用例执行完成，用例执行失败会提示：用例执行异常，点击确定后，恢复正常，可以查看结果
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/9.png)

2.4编辑用例<br>
同新增功能
<br>
2.5查看结果<br>
1.显示用例开始时间和结束时间,耗时多长，如果用例执行成功显示” SUCCESS”,用例执行失败显示” FAILURE “并且标红，每个用例请求的参数和响应都可展示出来，如果响应内容为html或xml格式，会影响数据的展示<br>
2.显示最新一轮执行结果，每次执行的用例结果会保存，方便后续做统计/展示<br>
成功样例：

![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/10.png)<br>
失败样例(待优化显示失败接口行)：

![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/11.png)

2.6删除用例<br>
1.被用例集引用的用例不可删除<br>
2.数据库删除数据，不可恢复<br>

三、用例集管理
---------
3.1用例集列表页面<br>
1.可以通过用例集名称 查询对应的数据，展示每个用例集下面包含多少个用例数<br>
2.点击全部执行，可以执行所有用例集

![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/12.png)

3.2新增用例集<br>
关联用例中的数据来源于用例列表,已选中的用例会显示在关联用例中
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/13.png)

3.3执行用例集<br>
与2.3功能类似
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/14.png)

3.4编辑用例集<br>
与新增功能类似，用例集名称不能编辑，如果需要编辑用例集，不如新增一个
<br>
3.5查看结果<br>
1.显示总用例数、成功用例、失败用例、用例集开始时间、结束时间、用例集耗时多长，并且把每个用例的详情展示出来，如果用例执行成功显示” SUCCESS”,用例执行失败显示” FAILURE “并且标红，每个用例请求的参数和响应都可展示出来，如果响应内容为html或xml格式，会影响数据的展示<br>
2.显示最新一轮执行结果，每次执行的结果会保存，方便后续做统计/展示<br>
3.点击查看接口结果，将每个用例请求的接口显示出来
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/15.png)

3.6删除用例集<br>
数据库删除数据，不可恢复
<br>

四、基础信息管理
---------
4.1初始化变量管理页面<br>
1.可以通过变量名称 查询对应的数据<br>
2.变量名不可重复<br>
3.变量方便在用例参数进行引用
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/16.png)

4.1.1新增变量
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/17.png)

4.1.2编辑变量:同新增功能
<br>
4.1.3删除变量<br>
数据库删除数据，不可恢复
<br>
4.2用户管理页面<br>
可以通过用户名查询对应的数据,用户只能通过注册
<br>
4.2.1用户注册<br>
登陆名不允许重复添加，密码/用户名/邮箱都为必填项
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/18.png)

4.2.2用户登陆
<br>
4.2.3用户编辑<br>
登陆名不允许修改，其它项不允许为空<br>
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/19.png)

4.2.4用户删除<br>
数据库删除数据，不可恢复
<br>

五、运行环境
---------
数据库初始化运行环境数据<br>
![image](https://github.com/luozhuwang/APIPlatform/blob/master/src/main/webapp/image/platform/20.png)
