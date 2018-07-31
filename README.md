## demo68

### 侧滑栏
<font face="宋体">
侧滑栏NavigationView布局的id为navigation_left_container1。<br/>
侧滑栏布局里包含一个线性布局，线性布局里包含两个NavigationView，第一个NavigationView内容为侧滑栏顶部人员信息和菜单项，第二个NavigationView内容为底部的登陆和注册按钮。  

* 第一个NavigationView  
  ID为navigation_left_container2，包含两个布局：顶部人员信息布局navigation_left_head.xml和底部菜单布局navigation_left_item。
   
    1.顶部人员信息  
    布局文件为navigation_left_head.xml，用户头像ImageView ID为navigation_left_image1，人员信息1TextView ID为navigation_left_text1，人员信息2TextView ID为navigation_left_text2。  
   
    2.中间菜单项  
    布局文件为navigation_left_item.xlm，共5个菜单项，ID依次为navigation_left_item_1~5。

* 第二个NavigationView  
  引入登陆和注册按钮的布局navigation_left_foot.xml，登陆按钮ID为navigation_left_button1，注册按钮ID为navigation_left_button2。  
</font>

### 主界面
<font face="宋体">
&emsp;&emsp;主界面布局在activity_main.xml布局文件中引入，主界面布局包括顶部toolbar，中间地图展示区域，底部BootNavigationView。  

* 顶部toolbar  
ID为toolbar_top_container1  
toolbar菜单项：  
切换地图：ID为tooBar_top_menu_item1  
GPS设置：ID为tooBar_top_menu_item2  
校准：ID为tooBar_top_menu_item3<br/><br/>
* 底部导航栏BottomNavigationView  
引入菜单文件navigation_bottom_item1.xml  
定位按钮：ID为bottom_dingwei  
添加事件：ID为bottom_tianjia  
搜索按钮：ID为bottom_sousuo
查看任务：ID为bottom_renwu
</font>

### 图标文件
<font face="宋体">
图标文件放到drawable文件夹下：<br/>
1.定位按钮用的图标为dingwei.svg；<br/>
2.添加事件用的图标为tianjia.svg；<br/>
3.搜索按钮用的图标为sousuo.svg；<br/>
4.任务查看用的图标为renwu.svg；
</font>