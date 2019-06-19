### Eureka高可用注册中心
- registered-replicas和available-replicas就是没有分布式注册中心的地址
1. eureka.client.serviceUrl.defaultZone配置项的地址，不能使用localhost，要使用service-center-1之类的域名，通过host映射到127.0.0.1；
2. spring.application.name或eureka.instance.appname必须一致；
3. 相互注册要开启：
    1. eureka.client.register-with-eureka=true
    2. eureka.client.fetch-registry=true
！