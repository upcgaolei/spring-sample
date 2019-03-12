# spring-sample

#### Environment
```
Apache Maven 3.5.0
JDK 1.8
Spring FrameWork 4.0.6.RELEASE
Spring Kafka 2.1.0.RELEASE

apache-tomcat-7.0.78
kafka_2.11-0.11.0.0
```

#### Prepare MySQL
- [ ] localhost start MySQL Server, set username `root`, set password `123456`

#### Prepare Kafka
- [ ] Download the 2.1.0 release and un-tar it, execute `tar -xzf kafka_2.11-2.1.0.tgz`
- [ ] start zookeeper server, `bin/zookeeper-server-start.sh config/zookeeper.properties`
- [ ] start kafka server, `bin/kafka-server-start.sh config/server.properties`
- [ ] create topic, `bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic_test`
- [ ] send messages, `bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topic_test`


#### How to start this project

- [ ] execute `mvn clean install -DskipTests`
- [ ] copy `target/spring-sample-1.0-SNAPSHOT.jar` to `${home}/apache-tomcat-7.0.78/webapps`
- [ ] `cd ${home}/apache-tomcat-7.0.78/bin` then execute `sh start.sh`
- [ ] `cd ${home}/apache-tomcat-7.0.78/logs` then `tail -200f catelina.out`

#### Produce more messages

- [ ] `cd spring-boot-sample`, run com.github.xiaozhong.SpringBootSampleApplication#main

#### Stop Consumer, Restore problem scenario.

- [ ] `cd ${home}/apache-tomcat-7.0.78/bin` then execute `sh shutdown.sh`, then we will find the following stack trace

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