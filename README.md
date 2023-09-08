# 一、SpringCloud Eureka 服务注册与发现
## 1.服务段模块添加相关依赖
![img.png](img.png)
## 2.服务端在启动类上添加@EnableEurekaServer注解
![img_1.png](img_1.png)
## 3.服务端在配置文件中配置eureka相关信息
![img_2.png](img_2.png)
## 4.客户端模块添加相关依赖
![img_3.png](img_3.png)
## 5.客户端在启动类上添加@EnableEurekaClient注解
![img_4.png](img_4.png)
## 6.客户端在配置文件中配置eureka相关信息
![img_5.png](img_5.png)
## 7.启动服务端和客户端，访问服务端端的服务地址，可以看到客户端被注册
![img_6.png](img_6.png)
## 8.使用三个不同配置文件运行实例，分别指定hostname和port，分别启动
![img_7.png](img_7.png)
## 9.访问服务端的服务地址，可以看到三个客户端被注册
![img_8.png](img_8.png)