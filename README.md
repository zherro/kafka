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

By default:
- kafka is served in: `localhost:9092`
- zookeper is served in: `localhost:2181`

> If executing in Windows S.O., use files of `bin/windows/...`. This provide best execution.


- Stop the producer and consumer clients with Ctrl-C, if you haven't done so already.
- Stop the Kafka broker with Ctrl-C.
- Lastly, stop the ZooKeeper server with Ctrl-C.

If you also want to delete any data of your local Kafka environment including any events you have created along the way, run the command:

```
$ rm -rf /tmp/kafka-logs /tmp/zookeeper
```

> by default kafka events are stored in the `/tmp/kafka-logs` folder. This configuration can be modifyed in  `config/server.properties` and `config/zookeeper.properties`  respectively.


## Topics

Events are organized and durably stored in topics. Very simplified, a topic is similar to a folder in a filesystem, and the events are the files in that folder. An example topic name could be "payments". Topics in Kafka are always multi-producer and multi-subscriber: a topic can have zero, one, or many producers that write events to it, as well as zero, one, or many consumers that subscribe to these events. Events in a topic can be read as often as needed—unlike traditional messaging systems, events are not deleted after consumption. Instead, you define for how long Kafka should retain your events through a per-topic configuration setting, after which old events will be discarded. Kafka's performance is effectively constant with respect to data size, so storing data for a long time is perfectly fine.

So before you can write your first events, you must create a topic. Open another terminal session and run:

```
$ bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
```

> here create topic with name `quickstart-events`.

Note that we use file `bin/kafka-topics.sh`, for execute topic commands. And define the kafka server `--bootstrap-server localhost:9092`.


### Topic Utils

- `create`: Create a new topic.                    
- `delete`: Delete a topic    
- `alter`: Alter the number of partitions, replica assignment, and/or configuration for the topic.
- `if-not-exists`: if set when creating topics, the action will only execute if the topic does not already exist.
- `list`: List all available topics.             
- `describe`: List details for the given topics.
- `partitions <Integer: # of partitions>`: The number of partitions for the topic being created or altered (WARNING:   If partitions are increased for a    topic that has a key, the partition  logic or ordering of the messages    will be affected). If not supplied   for create, defaults to the cluster  default.
- `replication-factor <Integer:  The replication factor for each  replication factor>` : partition in the topic being  created. If not supplied, defaults to the cluster default.

**In action:**

```
----------------------
>>> CREATE <<<

$ bin/kafka-topics.sh --create --if-not-exists  --topic quickstart-events --bootstrap-server localhost:9092

# result:
Create topic if not exists and not throw an excepiion if exists.

----------------------
>>> LIST <<<

$ bin/kafka-topics.sh --list --bootstrap-server localhost:9092

# result:
quickstart-events

----------------------
>>> GET INFO <<<

$  bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092

# result:
Topic: quickstart-events	PartitionCount: 1	ReplicationFactor: 1	Configs: segment.bytes=1073741824
    Topic: quickstart-events	Partition: 0	Leader: 0	Replicas: 0	Isr: 0

----------------------
>>> CREATE IF NOT EXIST <<<

$ bin/kafka-topics.sh --create --if-not-exists  --topic quickstart-events2 --bootstrap-server localhost:9092 --partitions 3

# result:
Created topic quickstart-events2.



>>> OR ALTER TOPIC <<<

$ bin/kafka-topics.sh --bootstrap-server localhost:9092 --alter --topic quickstart-events   --partitions 5



>>> CHECK TOPIC INFO <<<

$  bin/kafka-topics.sh --describe --topic quickstart-events2 --bootstrap-server localhost:9092

# result:
Topic: quickstart-events2	PartitionCount: 3	ReplicationFactor: 1	Configs: segment.bytes=1073741824
	Topic: quickstart-events2	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
	Topic: quickstart-events2	Partition: 1	Leader: 0	Replicas: 0	Isr: 0
	Topic: quickstart-events2	Partition: 2	Leader: 0	Replicas: 0	Isr: 0

# Note that now we have three partitions.

----------------------
>>> DELETE <<<


$ bin/kafka-topics.sh --list --bootstrap-server localhost:9092

# result:
quickstart-events
quickstart-events2


$ bin/kafka-topics.sh --delete --topic quickstart-events2 --bootstrap-server localhost:9092

# result:


$ bin/kafka-topics.sh --list --bootstrap-server localhost:9092

# result:
quickstart-events


```


## Producer

A Kafka client communicates with the Kafka brokers via the network for writing (or reading) events. Once received, the brokers will store the events in a durable and fault-tolerant manner for as long as you need—even forever.

Run the console producer client to write a few events into your topic. By default, each line you enter will result in a separate event being written to the topic.

```
$ bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092
This is my first event
This is my second event
```

> You can stop the producer client with Ctrl-C at any time.


## Consumer

Open another terminal session and run the console consumer client to read the events you just created:

```
$ bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
This is my first event
This is my second event
```

> You can stop the consumer client with Ctrl-C at any time.

Feel free to experiment: for example, switch back to your producer terminal (previous step) to write additional events, and see how the events immediately show up in your consumer terminal.

Because events are durably stored in Kafka, they can be read as many times and by as many consumers as you want. You can easily verify this by opening yet another terminal session and re-running the previous command again.
