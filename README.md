# kafka
> Apache Kafka is an open-source distributed event streaming platform used by thousands of companies for high-performance data pipelines, streaming analytics, data integration, and mission-critical applications

Read more in [https://kafka.apache.org/intro](https://kafka.apache.org/intro)

## How configure kafka

### Download stable version of kafka

Download an unzip kafka, 
[Donload kafka](https://kafka.apache.org/downloads)

> Your local environment must have Java 8+ installed.

### Start kafka 

Run the following commands in order to start all services in the correct order:

```
# Start the ZooKeeper service
# Note: Soon, ZooKeeper will no longer be required by Apache Kafka.
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```

Open another terminal session and run:

```
# Start the Kafka broker service
$ bin/kafka-server-start.sh config/server.properties
```

Once all services have successfully launched, you will have a basic Kafka environment running and ready to use

> If executing in Windows S.O., use files of `bin/windows/...`. This provide best execution.


