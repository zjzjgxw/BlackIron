------------------------

## 简介

这个项目是用spring boot 搭建的商场服务端。希望它能给想学习的java,spring的同学一个完整的项目去参考。



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





## 相关项目

- 管理后台(采用antd pro) ：<https://github.com/zjzjgxw/BlackIron_Admin>  
- 微信小程序： <https://github.com/zjzjgxw/BlackIronApp.git>