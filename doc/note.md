##Spring Cloud 五大组件
- 服务注册与发现——Netflix Eureka
- 负载均衡：
  - 客户端负载均衡——Netflix Ribbon
  - 服务端负载均衡——Feign(其也是依赖于Ribbon，只是将调用方式RestTemplate更改成Service接口)
- 断路器——Netflix Hystrix
- 服务网关——Netflix Zuul
- 分布式配置——Spring Cloud Config

##微服务技术栈
| **微服务技术条目**          | 落地技术                                             |
| -------------------- | ------------------------------------------------ |
| 服务开发                 | SpringBoot、Spring、SpringMVC等                     |
| 服务配置与管理              | Netfix公司的Archaius、阿里的Diamond等                    |
| 服务注册与发现              | Eureka、Consul、Zookeeper等                         |
| 服务调用                 | Rest、PRC、gRPC                                    |
| 服务熔断器                | Hystrix、Envoy等                                   |
| 负载均衡                 | Ribbon、Nginx等                                    |
| 服务接口调用(客户端调用服务的简化工具) | Fegin等                                           |
| 消息队列                 | Kafka、RabbitMQ、ActiveMQ等                         |
| 服务配置中心管理             | SpringCloudConfig、Chef等                          |
| 服务路由(API网关)          | Zuul等                                            |
| 服务监控                 | Zabbix、Nagios、Metrics、Specatator等                |
| 全链路追踪                | Zipkin、Brave、Dapper等                             |
| 数据流操作开发包             | SpringCloud Stream(封装与Redis，Rabbit，Kafka等发送接收消息) |
| 时间消息总栈               | SpringCloud Bus                                  |
| 服务部署                 | Docker、OpenStack、Kubernetes等                     |

##各微服务框架对比
| 功能点/服务框架      | Netflix/SpringCloud | Motan | gRPC | Thri t | Dubbo/DubboX |
| ----------------- | -------------- | ------------- | -------- | ------- | ------- |
| 功能定位 | 完整的微服务框架 | RPC框架，但整合了ZK或Consul，实现集群环境的基本服务注册发现 | RPC框架 | RPC框架 | 服务框架 |
| 支持Rest | 是，Ribbon支持多种可拔插的序列号选择 | 否 | 否 | 否 | 否 |
| 支持RPC | 否 | 是(Hession2) | 是 | 是 | 是 |
| 支持多语言 | 是(Rest形式) | 否 | 是 | 是 | 否 |
| 负载均衡 | 是(服务端zuul+客户端Ribbon)，zuul-服务，动态路由，云端负载均衡Eureka（针对中间层服务器） | 是(客户端) | 否 | 否 | 是(客户端) |
| 配置服务 | Netfix Archaius，Spring Cloud Config Server 集中配置 | 是(Zookeeper提供) | 否 | 否 | 否 |
| 服务调用链监控 | 是(zuul)，zuul提供边缘服务，API网关 | 否 | 否 | 否 | 否 |
| 高可用/容错 | 是(服务端Hystrix+客户端Ribbon) | 是(客户端) | 否 | 否 | 是(客户端) |
| 典型应用案例 | Netflix | Sina | Google | Facebook | |
| 社区活跃程度 | 高 | 一般 | 高 | 一般 | 2017年后重新开始维护，之前中断了5年 |
| 学习难度 | 中等 | 低 | 高 | 高 | 低 |
| 文档丰富程度 | 高 | 一般 | 一般 | 一般 | 高 |
| 其他 | Spring Cloud Bus为我们的应用程序带来了更多管理端点 | 支持降级 | Netflix内部在开发集成gRPC | IDL定义 | 实践的公司比较多 |

##Eureka对比Zookeeper
###1. 回顾CAP原则  
   RDBMS (MySQL\Oracle\sqlServer) ===> ACID  
   NoSQL (Redis\MongoDB) ===> CAP

###2. ACID是什么  
   - A (Atomicity) 原子性  
   - C (Consistency) 一致性  
   - I (Isolation) 隔离性  
   - D (Durability) 持久性  
###3. CAP是什么  
   - C (Consistency) 强一致性  
   - A (Availability) 可用性  
   - P (Partition tolerance) 分区容错性  

   CAP的三进二：CA、AP、CP
###4. CAP理论的核心
   一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求  
   根据CAP原理，将NoSQL数据库分成了满足CA原则，满足CP原则和满足AP原则三大类
   - CA：单点集群，满足一致性，可用性的系统，通常可扩展性较差
   - CP：满足一致性，分区容错的系统，通常性能不是特别高
   - AP：满足可用性，分区容错的系统，通常可能对一致性要求低一些
###5. 作为分布式服务注册中心，Eureka比Zookeeper好在哪里？
   著名的CAP理论指出，一个分布式系统不可能同时满足C (一致性) 、A (可用性) 、P (容错性)，由于分区容错性P再分布式系统中是必须要保证的，因此我们只能再A和C之间进行权衡。

   - Zookeeper 保证的是 CP —> 满足一致性，分区容错的系统，通常性能不是特别高
   - Eureka 保证的是 AP —> 满足可用性，分区容错的系统，通常可能对一致性要求低一些

####Zookeeper保证的是CP
当向注册中心查询服务列表时，我们可以容忍注册中心返回的是几分钟以前的注册信息，但不能接收服务直接down掉不可用。也就是说，服务注册功能对可用性的要求要高于一致性。  
但zookeeper会出现这样一种情况，当master节点因为网络故障与其他节点失去联系时，剩余节点会重新进行leader选举。问题在于，选举leader的时间太长，30-120s，且选举期间整个zookeeper集群是不可用的，这就导致在选举期间注册服务瘫痪。  
在云部署的环境下，因为网络问题使得zookeeper集群失去master节点是较大概率发生的事件，虽然服务最终能够恢复，但是，漫长的选举时间导致注册长期不可用，是不可容忍的。

####Eureka保证的是AP
Eureka看明白了这一点，因此在设计时就优先保证可用性。  
Eureka各个节点都是平等的，几个节点挂掉不会影响正常节点的工作，剩余的节点依然可以提供注册和查询服务。  
而Eureka的客户端在向某个Eureka注册时，如果发现连接失败，则会自动切换至其他节点，只要有一台Eureka还在，就能保住注册服务的可用性，只不过查到的信息可能不是最新的，  
除此之外，Eureka还有之中自我保护机制，如果在15分钟内超过85%的节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，此时会出现以下几种情况：

- Eureka不在从注册列表中移除因为长时间没收到心跳而应该过期的服务
- Eureka仍然能够接受新服务的注册和查询请求，但是不会被同步到其他节点上 (即保证当前节点依然可用)
- 当网络稳定时，当前实例新的注册信息会被同步到其他节点中  

因此，Eureka可以很好的应对因网络故障导致部分节点失去联系的情况，而不会像zookeeper那样使整个注册服务瘫痪

##Ribbon
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```
Eureka中已集成了Ribbon

负载均衡算法：(也可能不对)
```java
package org.springframework.cloud.loadbalancer.core;
public interface ReactorServiceInstanceLoadBalancer extends ReactorLoadBalancer<ServiceInstance> {}
```
两个实现：
- `org.springframework.cloud.loadbalancer.core.RandomLoadBalancer`
- `org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer`

##服务熔断和降级的区别
- 服务熔断—>服务端：某个服务超时或异常，引起熔断~，类似于保险丝(自我熔断)
- 服务降级—>客户端：从整体网站请求负载考虑，当某个服务熔断或者关闭之后，服务将不再被调用，此时在客户端，我们可以准备一个 FallBackFactory ，返回一个默认的值(缺省值)。会导致整体的服务下降，但是好歹能用，比直接挂掉强。
- 触发原因不太一样，服务熔断一般是某个服务（下游服务）故障引起，而服务降级一般是从整体负荷考虑；管理目标的层次不太一样，熔断其实是一个框架级的处理，每个微服务都需要（无层级之分），而降级一般需要对业务有层级之分（比如降级一般是从最外围服务开始）
- 实现方式不太一样，服务降级具有代码侵入性(由控制器完成/或自动降级)，熔断一般称为自我熔断。

##Config
git上新建文件application.yml
```yml
spring:
  profiles:
    active: dev
---
spring:
  config:
    activate:
      on-profile: dev
  application:
    name: springcloud-config-dev
---
spring:
  config:
    activate:
      on-profile: prod
  application:
    name: springcloud-config-prod
```
启动后访问：
- http://localhost:8245/application/test/master
- http://localhost:8245/application-dev.yml
- http://localhost:8245/application-prod.yml

config-client.yml
```yml
spring:
  profiles:
    active: dev
---
server:
  port: 8255
spring:
  config:
    activate:
      on-profile: dev
  application:
    name: springcloud-provider-dev
eureka:
  client:
    service-url:
      defaultZone: http://eureka8213:8213/eureka,http://eureka8214:8214/eureka,http://eureka8215:8215/eureka

---
server:
  port: 8266
spring:
  config:
    activate:
      on-profile: prod
  application:
    name: springcloud-provider-prod
eureka:
  client:
    service-url:
      defaultZone: http://eureka8213:8213/eureka,http://eureka8214:8214/eureka,http://eureka8215:8215/eureka

```