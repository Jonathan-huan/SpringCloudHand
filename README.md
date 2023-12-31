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
```agsl
学习总结：在微服务架构中往往会有一个注册中心，每个微服务都会向注册中心去注册自己的地址及端口信息，
注册中心维护着服务名称与服务实例的对应关系。每个微服务都会定时从注册中心获取服务列表，同时汇报自己的运行情况，
这样当有的服务需要调用其他服务时，就可以从自己获取到的服务列表中获取实例地址进行调用，Eureka实现了这套服务注册与发现机制。
```
# 二、SpringCloud Ribbon 负载均衡
## 1.创建ribbon-service模块和user-service模块，均导入eureka-client依赖（由于新版本的eureka-client自带ribbon，所以不需要再导入ribbon依赖）
![img_9.png](img_9.png)
## 2.ribbon-service和user-service模块在启动类上添加@EnableDiscoveryClient注解
## 3.ribbon-service模块在配置文件中配置eureka相关信息,指定user-service的服务地址并启动user-service的负载均衡为随机
![img_10.png](img_10.png)
## 4.ribbon-service模块创建RestTemplate的bean，并添加@LoadBalanced注解
![img_11.png](img_11.png)
## 5.ribbon-service模块创建UserRibbonController类，通过RestTemplate调用user-service的接口
![img_12.png](img_12.png)
## 6.user-service模块创建UserController类，提供接口
![img_13.png](img_13.png)
## 7.启动eureka-server、ribbon-service，启动两个user-service实例，访问ribbon-service的接口，可以看到负载均衡效果是随机的
![img_14.png](img_14.png)
![img_15.png](img_15.png)

```agsl
学习总结：在微服务架构中，很多服务都会部署多个，其他服务去调用该服务的时候，
如何保证负载均衡是个不得不去考虑的问题。负载均衡可以增加系统的可用性和扩展性，
当我们使用RestTemplate来调用其他服务时，Ribbon可以很方便的实现负载均衡功能。
```
# 三、SpringCloud Feign 声明式服务调用以及服务降级
## 1.创建resilience4j-service模块，导入相关依赖
![img_17.png](img_17.png)
## 2.配置文件中配置resilience4j相关信息
![img_18.png](img_18.png)
## 3.创建UserClient接口用于声明式服务调用，并指定fallback类或方法
![img_19.png](img_19.png)
## 4.创建UserController类，使用UserClient接口进行服务调用
![img_20.png](img_20.png)
## 5.可以看到服务调用成功，并且打印了fallback的信息
![img_23.png](img_23.png)
## 6.在ribbon-service中使用loadbalancer进行服务调用，使用随机算法，调用10次，可以看到负载均衡效果
![img_21.png](img_21.png)
![img_22.png](img_22.png)

# 四、SpringCloud Config 配置中心和SpringCloud Gateway 网关
## 1.创建config-server模块，导入相关依赖
![img_24.png](img_24.png)
## 2.配置文件中配置git仓库地址
![img_25.png](img_25.png)
## 3.启动config-server,直接访问远程仓库文件，成功访问
![img_26.png](img_26.png)
## 4.创建config-client模块，导入相关依赖
![img_27.png](img_27.png)
## 5.配置文件中配置config-server地址
![img_28.png](img_28.png)
## 6.客户端编写接口，通过@Value注解获取配置文件中的值
![img_29.png](img_29.png)
## 7.启动config-server和config-client，访问客户端接口，可以看到获取到了配置文件中的值
![img_30.png](img_30.png)
## 8.创建gateway-service模块，导入相关依赖
![img_31.png](img_31.png)
## 9.配置文件中配置gateway相关信息和路由规则（注意要设置spring.main.web-application-type=reactive）
![img_32.png](img_32.png)
## 10.启动gateway-service和user-service，访问gateway-service的接口，可以看到路由规则生效
![img_33.png](img_33.png)

```agsl
学习总结：在微服务架构中，往往会有一个配置中心，每个微服务都会从配置中心获取自己的配置信息，
这样当配置信息发生变化时，只需要修改配置中心的配置文件，不需要重启服务，就可以实现配置的动态更新。
网关是微服务架构中的一个重要组件，它可以实现路由转发、权限校验、限流等功能，SpringCloud Gateway是SpringCloud提供的网关组件，
它基于异步非阻塞模型，性能更好，可以实现动态路由、限流、熔断等功能。
```


