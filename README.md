[TOC]

# springboot整合dubbo #
_说明：在mac系统下进行整合_
## 1. 安装zookeeper ##
   ```
    //查看zookeeper信息
    brew info zookeeper
    //安装
    brew install zookeeper
    //配置文件
    /usr/local/etc/zookeeper/
    //启动
    zkServer start
    //停止
    zkServer stop
    //查看状态
    zkServer status
   ```
   ![zookeeper](https://github.com/willpower88/spbd/blob/master/doc/image/zookeeper-info.png)
## 2. 搭建dubbo-admin
   ```
    //具体启动查看项目readme
    //https://github.com/apache/incubator-dubbo-admin
    git clone https://github.com/apache/incubator-dubbo-admin
   ```
## 3. 新建idea工程
1. 新建spring工程 spbd: File -> new -> project -> Sprint Initializr next ...
    ![new-project](https://github.com/willpower88/spbd/blob/master/doc/image/new-project.png)
1. 删除src目录
1. 新建module spbd-api 右击工程spbd: new -> module -> Sprint Initializr  next ...
    ![new-module](https://github.com/willpower88/spbd/blob/master/doc/image/new-module.png)
1. 新建module spbd-consumer 右击工程spbd: 同上
1. 新建module spbd-provider 右击工程spbd: 同上
1. spbd pom.xml增加moddle和spring-boot-maven-plugin增加configuration
    ```
	    <?xml version="1.0" encoding="UTF-8"?>
        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        	<modelVersion>4.0.0</modelVersion>
        	<parent>
        		<groupId>org.springframework.boot</groupId>
        		<artifactId>spring-boot-starter-parent</artifactId>
        		<version>2.1.4.RELEASE</version>
        		<relativePath/> <!-- lookup parent from repository -->
        	</parent>
        	<groupId>com.willpower.spbd</groupId>
        	<artifactId>spbd</artifactId>
        	<version>0.0.1-SNAPSHOT</version>
        	<packaging>pom</packaging>
        	<name>spbd</name>
        	<description>Demo project for Spring Boot</description>

        	<modules>
        		<module>spbd-api</module>
        		<module>spbd-consumer</module>
        		<module>spbd-provider</module>
        	</modules>

        	<properties>
        		<java.version>1.8</java.version>
        	</properties>

        	<dependencies>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter</artifactId>
        		</dependency>

        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-test</artifactId>
        			<scope>test</scope>
        		</dependency>
        		<dependency>
        			<groupId>org.springframework.boot</groupId>
        			<artifactId>spring-boot-starter-web</artifactId>
        		</dependency>
        		<!--引入dubbo的场景启动器-->
        		<dependency>
        			<groupId>com.alibaba.boot</groupId>
        			<artifactId>dubbo-spring-boot-starter</artifactId>
        			<version>0.1.0</version>
        		</dependency>
        		<!-- 引入zookeeper的客户端 -->
        		<dependency>
        			<groupId>com.github.sgroschupf</groupId>
        			<artifactId>zkclient</artifactId>
        			<version>0.1</version>
        		</dependency>
        	</dependencies>

        	<build>
        		<plugins>
        			<plugin>
        				<groupId>org.springframework.boot</groupId>
        				<artifactId>spring-boot-maven-plugin</artifactId>
        				<configuration>
        					<classifier>exec</classifier>
        				</configuration>
        			</plugin>
        		</plugins>
        	</build>

        </project>

    ```
1. spbd-api
    + 更新pom.xml
       ```
        <?xml version="1.0" encoding="UTF-8"?>
        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        	<modelVersion>4.0.0</modelVersion>
        	<parent>
        		<groupId>com.willpower.spbd</groupId>
        		<artifactId>spbd</artifactId>
        		<version>0.0.1-SNAPSHOT</version>
        	</parent>

        	<groupId>com.willpower.spbd.api</groupId>
        	<artifactId>spbd-api</artifactId>
        	<version>0.0.1-SNAPSHOT</version>
        	<name>spbd-api</name>
        	<description>Demo project for Spring Boot</description>



        </project>

       ```
    + 新建Service
        ```
            package com.willpower.spbd.api.service;

            /**
             * Created by Powersoft on 2019/4/25.
             */
            public interface DemoService {

                String sayHi(String name);
            }

        ```
1. spbd-provider
    + 更新pom.xml
        ```
           <?xml version="1.0" encoding="UTF-8"?>
           <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
               <modelVersion>4.0.0</modelVersion>
               <parent>
                   <groupId>com.willpower.spbd</groupId>
                   <artifactId>spbd</artifactId>
                   <version>0.0.1-SNAPSHOT</version>
               </parent>
               <groupId>com.willpower.spbd.provider</groupId>
               <artifactId>spbd-provider</artifactId>
               <version>0.0.1-SNAPSHOT</version>
               <name>spbd-provider</name>
               <description>Demo project for Spring Boot</description>
               <properties>
                   <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                   <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
                   <java.version>1.8</java.version>
               </properties>

               <dependencies>
                   <dependency>
                       <groupId>com.willpower.spbd.api</groupId>
                       <artifactId>spbd-api</artifactId>
                       <version>0.0.1-SNAPSHOT</version>
                   </dependency>
               </dependencies>

           </project>

         ```
    + 创建Service实现类
        ```
        package com.willpower.spbd.provider.service.impl;

        import com.alibaba.dubbo.config.annotation.Service;
        import com.willpower.spbd.api.service.DemoService;
        import org.springframework.stereotype.Component;

        /**
         * Created by Powersoft on 2019/4/25.
         */
        @Component
        @Service
        class DemoServiceImpl implements DemoService {
            @Override
            public String sayHi(String name) {
                return "Hi " + name + "!";
            }
        }

        ```
    + application.properties 添加配置
        ```

            server.port=8081


            #解决 defined in null, could not be registered.
            #A bean with that name has already been defined in file XXX and overriding is disabled.
            spring.main.allow-bean-definition-overriding=true

        ```
    + 新建 resources/provider.xml
       ```
        <?xml version="1.0" encoding="UTF-8"?>
        <beans xmlns="http://www.springframework.org/schema/beans"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
               xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://code.alibabatech.com/schema/dubbo
            http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

            <!-- 提供方应用信息，用于计算依赖关系 -->
            <dubbo:application name="spbd-provider" />

            <!-- 注册中心暴露服务地址
                <dubbo:registry protocol="zookeeper" address="10.170.219.98:2181,10.173.55.173:2181" />
            -->
            <dubbo:registry protocol="zookeeper" address="127.0.0.1:2181" />

            <!-- 暴露服务 -->
            <dubbo:protocol name="dubbo" port="20880" />

            <!-- 切记 ref="xxxImpl" -->
            <dubbo:service interface="com.willpower.spbd.api.service.DemoService"
                           ref="demoServiceImpl" retries="0" timeout="6000" />
        </beans>

       ```
    + 启动类SpbdProviderApplication增加annotation
         ```
          @ImportResource("classpath:provider.xml")
         ```
1. spbd-consumer
    + 更新pom.xml
        ```
        <?xml version="1.0" encoding="UTF-8"?>
        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
            <modelVersion>4.0.0</modelVersion>
            <parent>
                <groupId>com.willpower.spbd</groupId>
                <artifactId>spbd</artifactId>
                <version>0.0.1-SNAPSHOT</version>
            </parent>
            <groupId>com.willpower.spbd.consumer</groupId>
            <artifactId>spbd-consumer</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <name>spbd-consumer</name>

            <description>Demo project for Spring Boot</description>

            <properties>
                <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
                <java.version>1.8</java.version>
            </properties>

            <dependencies>
                <dependency>
                    <groupId>com.willpower.spbd.api</groupId>
                    <artifactId>spbd-api</artifactId>
                    <version>0.0.1-SNAPSHOT</version>
                </dependency>
            </dependencies>

        </project>

        ```
    + 创建controller
        ```
        package com.willpower.spbd.consumer.controller;

        import com.alibaba.dubbo.config.annotation.Reference;
        import com.willpower.spbd.api.service.DemoService;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        /**
         * Created by Powersoft on 2019/4/25.
         */
        @RestController
        public class DemoController {
            @Reference
            private DemoService demoService;

            @RequestMapping("/")
            public String index() {
                return demoService.sayHi("sam");
            }
        }


        ```

    + application.properties 添加配置
        ```

        server.port=8082

        dubbo.application.name=spbd-customer
        dubbo.registry.address=zookeeper://127.0.0.1:2181
        dubbo.registry.protocol=zookeeper

        ```
    + 新建 resources/consumer.xml
        ```
            <?xml version="1.0" encoding="UTF-8"?>
            <beans xmlns="http://www.springframework.org/schema/beans"
                   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
                   xsi:schemaLocation="http://www.springframework.org/schema/beans
                http://www.springframework.org/schema/beans/spring-beans.xsd
                http://code.alibabatech.com/schema/dubbo
                http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

                <!-- 提供方应用信息，用于计算依赖关系 -->
                <dubbo:application name="${dubbo.application.name}" />

                <!-- 注册中心暴露服务地址 -->
                <dubbo:registry protocol="${dubbo.registry.protocol}" address="${dubbo.registry.address}" />

                <dubbo:reference id="demoService" interface="com.willpower.spbd.api.service.DemoService" />
            </beans>

       ```
     + **启动类SpbdConsumerApplication增加annotation,consumer加了@ImportResource("classpath:consumer.xml")会报错，待研究，不加正常，但consumer.xml必须存在**

## 4. 启动provider
   ```
    Idea启动即可，要先启动provider
   ```
## 5. 启动consumer
   ```
    Idea启动即可，要先启动provider
   ```
## 6. 启动dubbo-admin,进入incubator-dubbo-admin
   + dubbo-admin
        ```
        //可选
        mvn clean package
        cd dubbo-admin-distribution/target; java -jar dubbo-admin-0.1.jar
        ```

   ![dubbo-admin](https://github.com/willpower88/spbd/blob/master/doc/image/dubbo-admin.png)

   + show success

   ![project-success](https://github.com/willpower88/spbd/blob/master/doc/image/project-success.png)
