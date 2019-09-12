在当前pom配置下，依赖分离出来，不打入jar包，减少jar包的大小
使用时，将jar包复制到lib下，
打包命令：mvn clean package -DskipTests=true
启动命令：参数可以自己调整
以factory-service举例
d:
cd D:\stsworkspace2\factory\factory-service\target\lib
java -Xms256M -Xmx512M -Xss2M -XX:+AggressiveOpts -XX:+UseParallelGC -XX:NewSize=64M -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails -classpath "./*" com.cdf.factory.eureka.FactoryEurekaServerApplication