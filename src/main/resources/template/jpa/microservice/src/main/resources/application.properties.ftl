## 显示Sql
hibernate.show_sql=true
## 自动建表方式
#hibernate.hbm2ddl.auto= update
## 数据库连接
spring.datasource.url=jdbc:mysql://${dbHost}:${dbPort}/${dbSchema}?useUnicode=true&characterEncoding=${dbEncode}&useSSL=true&autoReconnect=true
## 用户名
spring.datasource.username=${dbUserName}
## 密码
spring.datasource.password=${dbPassword}
## 数据库驱动
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
## 建表方式
spring.jpa.properties.hibernate.hbm2ddl.auto=update
# 方言
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
spring.jpa.show-sql=true