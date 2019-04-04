一、代码结构规范化  
1、数据库处理层，entity、dao
2、业务处理层，service、service.dto
3、控制层，web
4、配置类，config

二、支持durid 链接池
       默认启用hikari数据库链接池，内置了Druid链接池，若想启用durid链接，按如下步骤
1、pom.xml 放开hikari数据库注释，引入druid链接池依赖
（<artifactId>druid</artifactId>）
2、com.cplh.config.DruidConfig.java_invalid  ,去掉“_invalid”后缀，使其生效

3、application.properties，注释掉hikari连接池配置，放开对应的Druid链接池配置
（com.alibaba.druid.pool.DruidDataSource）
4、http://localhost:port/druid，admin/123456 就可以进行一系列监控了

三、集成swagger
1、pom引入包依赖
2、配置文件com.epipe.boot.config.Swagger2
3、使用注解，参考AccountController
4、http://localhost:port//swagger-ui.html 查看注解

四、集成restTemplate 
1、pom引入依赖包
2、配置文件FastjsonConfiguration、RestTemplateConfiguration，参考：https://blog.csdn.net/clj198606061111/article/details/70227571
3、使用参考 AccountService callRemote方法

五、集成mybatis反向工程
1、pom引入构建插件mybatis-generator-maven-plugin
2、引入配置文件generatorConfig.xml
3、根据需要修改配置文件中反向生成的表名tableName 以及配置
4、使用MavenProjects视图中的 mybatis-generator:generate 功能即可反向生成mpper配置文件、实体类、和dao类

六、集成redis
1、pom引入依赖包
2、application.yml设置redis相关参数，widows本地安装redis攻略:http://www.runoob.com/redis/redis-install.html
3、创建接口ReidsService、实现接口ReidsServiceImpl
4、创建RedisController 实现可以通过接口操作redis
   1.存入redis:调用set接口参数为key、value,可以直接对redis进行存入操作;
     URL示例：http://localhost:8080/redis/set?key=cplh&value=123456
   2.读取redis:调用getkey接口参数为key，可以根据redis中key值获取对应value值
     URL示例：http://localhost:8080/redis/getkey?key=cplh
5、修改redis地址可以连接产品redis测试环境的单体、集群部署方式
   单体部署:192.168.100.134:6379
   集群部署:cluster.nodes=192.168.100.225:7000,192.168.100.226:7000,192.168.100.227:7000

七、集成kafka
1、pom引入依赖包
2、application.yml设置kafka相关参数，widows本地安装kafka相关资料:
   1.kafka依赖于zookeeper,zookeeper安装攻略: https://blog.csdn.net/isharry/article/details/81778193
   2.kafka安装攻略：https://blog.csdn.net/u010283894/article/details/77106159
   3.本次集成了产品kafka测试环境：192.168.100.225:9092
3、创建Message，封装消息的model
4、创建kafka生产者KafkaProducerService、kafka消费者KafkaConsumerService
5、创建对外接口kafkaController，URL示例：http://localhost:8080/send?msg=kafka学习 向指定topic进行消息发送
   解析:kafka生产者进行消息发送后，kafka消费者监听指定topic内容，发生变化会触发KafkaListener方法