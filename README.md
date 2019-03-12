# spring-sample
* Producer: https://github.com/upcgaolei/spring-sample
* Consumer: https://github.com/upcgaolei/spring-boot-sample

#### 1、Environment
```
Apache Maven 3.5.0
JDK 1.8
Spring FrameWork 4.0.6.RELEASE
Spring Kafka 2.1.0.RELEASE

apache-tomcat-7.0.78
kafka_2.11-0.11.0.0
```

#### 2、Prepare MySQL
* 2.1 localhost start MySQL Server, set username `root`, set password `123456`
* 2.2 create two database `test` and `dimension`
* 2.3 create table `test_user_info` and `test_user_address` in `test` database
* 2.4 create table `test_order_info` in `dimension` database

schema
```sql
CREATE TABLE `test_user_info` (
  `id` int(11) DEFAULT NULL,
  `user_name` varchar(64) NOT NULL DEFAULT '' COMMENT 'user name',
  `user_phone` varchar(512) DEFAULT '' COMMENT 'user phone'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
```sql
CREATE TABLE `test_user_address` (
  `user_id` int(11) NOT NULL COMMENT 'user id',
  `user_address` varchar(64) NOT NULL DEFAULT '' COMMENT 'user address'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
```sql
CREATE TABLE `test_order_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `price` int(11) DEFAULT NULL COMMENT 'price',
  `order_desc` varchar(64) DEFAULT NULL COMMENT 'order desc',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

#### 3、Prepare Kafka
* 3.1 Download the 2.1.0 release and un-tar it, execute `tar -xzf kafka_2.11-2.1.0.tgz`
* 3.2 start zookeeper server, `bin/zookeeper-server-start.sh config/zookeeper.properties`
* 3.3 start kafka server, `bin/kafka-server-start.sh config/server.properties`
* 3.4 create topic, `bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic_test`
* 3.5 send messages, `bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topic_test`


#### 4、How to start this project
* 4.1 execute `mvn clean install -DskipTests`
* 4.2 copy `target/spring-sample-1.0-SNAPSHOT.jar` to `${home}/apache-tomcat-7.0.78/webapps`
* 4.3 `cd ${home}/apache-tomcat-7.0.78/bin` then execute `sh start.sh`
* 4.4 `cd ${home}/apache-tomcat-7.0.78/logs` then `tail -200f catelina.out`

#### 5、Produce more messages

* 5.1 `cd spring-boot-sample`, run com.github.xiaozhong.SpringBootSampleApplication#main

#### 6、Stop Consumer, Restore problem scenario.

* 6.1 `cd ${home}/apache-tomcat-7.0.78/bin` then execute `sh shutdown.sh`, then we will find the following stack trace

```
信息: received: ConsumerRecord(topic = topic_test, partition = 0, offset = 618243, CreateTime = 1552358060833, serialized key size = -1, serialized value size = 76, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = {"userAddress":"china, shanghai","userName":"my name","userPhone":"1234567"}), 3849
三月 12, 2019 10:34:35 上午 com.github.xiaozhong.component.LogComponent destroy
信息: com.github.xiaozhong.extend.ExtendDataSourceTransactionManager- - - destroying bean
三月 12, 2019 10:34:35 上午 com.github.xiaozhong.component.LogComponent destroy
信息: com.github.xiaozhong.extend.ExtendDataSourceTransactionManager- - - destroying bean
三月 12, 2019 10:34:35 上午 com.github.xiaozhong.msg.Consumer onMessage
信息: received: ConsumerRecord(topic = topic_test, partition = 0, offset = 618244, CreateTime = 1552358060834, serialized key size = -1, serialized value size = 76, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = {"userAddress":"china, shanghai","userName":"my name","userPhone":"1234567"}), 3850
三月 12, 2019 10:34:35 上午 com.github.xiaozhong.component.MyComponent dealMessage
严重: dealMessage error
org.springframework.beans.factory.BeanCreationNotAllowedException: Error creating bean with name 'userTransactionManager': Singleton bean creation not allowed while the singletons of this factory are in destruction (Do not request a bean from a BeanFactory in a destroy method implementation!)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:215)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:298)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:198)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeansOfType(DefaultListableBeanFactory.java:470)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeansOfType(DefaultListableBeanFactory.java:459)
	at org.springframework.beans.factory.BeanFactoryUtils.beansOfTypeIncludingAncestors(BeanFactoryUtils.java:228)
	at org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils.qualifiedBeanOfType(BeanFactoryAnnotationUtils.java:80)
	at org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils.qualifiedBeanOfType(BeanFactoryAnnotationUtils.java:56)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.determineTransactionManager(TransactionAspectSupport.java:331)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:252)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:95)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:644)
	at com.github.xiaozhong.manager.UserManager$$EnhancerBySpringCGLIB$$603786d4.createUserInfo(<generated>)
	at com.github.xiaozhong.component.MyComponent.dealMessage(MyComponent.java:32)
	at com.github.xiaozhong.msg.Consumer$1.onMessage(Consumer.java:45)
	at com.github.xiaozhong.msg.Consumer$1.onMessage(Consumer.java:41)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:978)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeWithRecords(KafkaMessageListenerContainer.java:943)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeRecordListener(KafkaMessageListenerContainer.java:894)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeListener(KafkaMessageListenerContainer.java:763)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:646)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.lang.Thread.run(Thread.java:748)

三月 12, 2019 10:34:35 上午 com.github.xiaozhong.msg.Consumer onMessage
信息: received: ConsumerRecord(topic = topic_test, partition = 0, offset = 618245, CreateTime = 1552358060835, serialized key size = -1, serialized value size = 76, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = {"userAddress":"china, shanghai","userName":"my name","userPhone":"1234567"}), 3851
三月 12, 2019 10:34:35 上午 com.github.xiaozhong.component.MyComponent dealMessage
```