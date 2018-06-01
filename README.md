# spring-boot-msa-rpc4j-starter
[![Build Status](https://travis-ci.org/microcmpt/spring-boot-msa-rpc4j-starter.svg?branch=master)](https://travis-ci.org/microcmpt/spring-boot-msa-rpc4j-starter) [![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
### Introduction
spring-boot-msa-rpc4j是对msa-rpc4j框架与spring boot整合，利用spring boot的自动配置特性自动配置msa-rpc4j。
### Quick Start
##### 服务端
##### step1:引入spring-boot-msa-rpc4j-starter
maven:
```$xslt
<dependency>
  <groupId>com.github.microcmpt</groupId>
  <artifactId>spring-boot-msa-rpc4j-starter</artifactId>
  <version>1.0.1</version>
</dependency>
```
gradle:
```$xslt
compile group: 'com.github.microcmpt', name: 'spring-boot-msa-rpc4j-starter', version: '1.0.1'
```
#####  step2:定义一个服务端接口HelloRpc4jService
```
public interface HelloRpc4jService {
    /**
     * Hello string.
     *
     * @param str the str
     * @return the string
     */
    String hello(String str);
}
```
##### step3:实现服务端业务逻辑HelloRpc4jServiceImpl类
```
@RpcService(value = HelloRpc4jService.class)
public class HelloRpc4jServiceImpl implements HelloRpc4jService {
    /**
     * Hello string.
     *
     * @param str the str
     * @return the string
     */
    public String hello(String str) {
        return "Hello, " + str;
    }
}
```
##### step4:在spring boot启动类加入注解@EnableRpc4jServer，实现rpc4j-server自动配置
```$xslt
@SpringBootApplication
@EnableRpc4jServer
public class Rpc4jServerSampleApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Rpc4jServerSampleApplication.class, args);
	}
}
```

##### 消费端
#####  step1:引入spring-boot-msa-rpc4j-starter
maven:
```$xslt
<dependency>
  <groupId>com.github.microcmpt</groupId>
  <artifactId>spring-boot-msa-rpc4j-starter</artifactId>
  <version>1.0.1</version>
</dependency>
```
gradle:
```$xslt
compile group: 'com.github.microcmpt', name: 'spring-boot-msa-rpc4j-starter', version: '1.0.1'
```
##### step2:定义一个消费端接口HelloRpc4jServiceRpcClient并添加注解@Rpc4jClient
```
@Rpc4jClient
public interface HelloRpc4jServiceRpcClient extends HelloRpc4jService {
}
```
##### step3:在控制类中调用该服务接口
```
@RestController
@RequestMapping("/api")
public class HelloRpc4jController {
    /**
     * The Hello rpc 4 j service.
     */
    @Resource
	private HelloRpc4jServiceRpcClient helloServiceRpcClient;

    /**
     * Hello string.
     *
     * @param name the name
     * @return the string
     */
    @RequestMapping(method = RequestMethod.GET, value = "/hello/{name}")
    public String hello(@PathVariable("name")String name) {
        return helloServiceRpcClient.hello(name);
    }
}
```
##### step4:在spring boot启动类加入注解@EnableRpc4jClients，实现rpc4j-client自动配置
```$xslt
@SpringBootApplication
@EnableRpc4jClients
public class Rpc4jClientSampleApplication {
	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Rpc4jClientSampleApplication.class, args);
	}
}
```

### Rpc4j Base On Spring Boot Config
#### 服务端配置
```$xslt
#服务端端口号，默认为8025，可修改
spring.rpc.server.port=8025
#服务注册中心地址，默认为localhost:2181，可修改，也可配置多个地址需以英文逗号隔开
spring.rpc.server.registry-address=localhost:2181
```
#### 客户端配置
```$xslt
#服务注册中心地址，默认为localhost:2181，可修改，也可配置多个地址需以英文逗号隔开
spring.rpc.client.registry-address=localhost:2181
```

### Annotation
#### @EnableRpc4jServer
@EnableRpc4jServer用于开启rpc服务提供端RpcServer以及注册中心和健康监测的自动配置
#### @EnableRpc4jClients
@EnableRpc4jClients用于开启服务消费端(客户端)RpcClient以及服务发现自动配置
#### @Rpc4jClient
@Rpc4jClient用于标记服务消费端调用的远程服务，将其注入到spring容器并做动态代理