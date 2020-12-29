------------------------

## 简介


> 这个项目的目的是什么？

现在有很多很多商城开源代码，有些已经十分成熟。但是却对刚入门接触的人来说，可能不太友善。许许多多的技术栈，导致学习的点太多。可能把整个项目搭建跑起来都比较吃力。
那么我希望通过这个项目结合一些知识点的讲解，让入门的小伙伴可以快速通过一个项目去了解掌握知识点。先能够在本地跑起一个完整的项目，然后通过相应的文档，掌握知识点。
所有知识点我会在我的博客 www.zjzjgxw.cn 中持续更新。


## 能学到

* 掌握Spring boot 基础，构建一个web服务。
* 使用Mybatis连接数据库
* 使用Spring boot连接redis
* 微信小程序的开发 包括微信授权登录，微信支付等
* 使用阿里antd pro 快速搭建一个后台管理前端项目。  


在博客 www.zjzjgxw.cn 中，我会尽量细致的讲解各个知识点包括java,spring boot,微信小程序等等。然后结合项目，使入门的小伙伴更加了解和掌握。


## 快速开始


```shell script
mkdir ~/BlackIron
cd ~/BlackIron
git clone  https://github.com/zjzjgxw/BlackIron.git
```

修改配置文件
```shell script
### 在~/BlackIron 项目目录下
cp src/main/resources/application.properties.tmpl src/main/resources/application.properties
```

修改相应的数据库配置信息
```md
#数据库配置
spring.datasource.url=jdbc:mysql://{localhost:3306}/{database_name}?useSSL=false&serverTimezone=GMT%2B8&useAffectedRows=true
spring.datasource.username=root
spring.datasource.password=
spring.datasource.tomcat.max-idle=5
```

使用black_iron_mysql.sql 初始化数据库结构。


使用marven 安装依赖。



## 相关项目

- 管理后台(采用antd pro) ：<https://github.com/zjzjgxw/BlackIron_Admin>  
- 微信小程序： <https://github.com/zjzjgxw/BlackIronApp.git>


## 计划安排

- 项目功能迭代 （持续更新）
- 项目文档 （持续更新）
- 结合项目输出知识点 （持续更新）


## 功能模块
有删除的线的表示已完成。


### 商户后台
> 具体功能点见 <https://github.com/zjzjgxw/BlackIron_Admin>  

- 登录：
    1. ~~商户后台商户员工登录 （账号密码）~~

- 商户信息：
   1. ~~基本信息编辑~~
   2. ~~首页banner~~
   3. ~~首页广告位~~
   4. ~~首页导航项目~~
   

- 员工管理：
    1.~~员工（增删改查）~~
    2.~~角色管理（增删改查）~~

- 商品管理：
    1.~~商品类目（增删改查)~~
    2.~~商品（增删改查）~~
    3.~~库存编辑~~
    4.~~支持上传视频~~
    
- 订单管理：
    1.~~订单列表~~
    2.~~订单详情~~
    3.~~发货~~
   

- 评论管理：
    1. 查看评论
    2. 删除评论

- 促销活动：
    1. ~~限时折扣（增删改查）~~
    2. ~~优惠券（增删改查）~~
    3. 优惠券发放
    
- 用户管理：
    1.~~用户列表~~
    2.用户分组

- 统计分析
    1.商品浏览统计分析
    2.订单统计分析
    3.用户图标统计


### 微信小程序  
> 具体功能点见 <https://github.com/zjzjgxw/BlackIronApp.git>

- ~~微信登陆~~

- ~~获取微信信息~~

- ~~获取微信地址信息~~

- 微信支付

- 首页搜索

- 微信分享

- 微信客服

### 管理员后台 （后台界面化还未开始）

- 权限管理：
    1.管理员后台编辑权限
    2.商户后台判断用户权限
