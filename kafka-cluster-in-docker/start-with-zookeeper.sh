#!/bin/bash


RED='\033[0;31m'


# expected values:   listeners=PLAINTEXT://:9092
if [ ! -z "$BROKER_ID" ]; then
    echo "broker.id: ${BROKER_ID}"
    if grep -r -q "^#\?broker.id=" config/server.properties; then
        # use | as a delimiter to make sure // does not confuse sed
        sed -r -i "s|^#?(broker.id)=(.*)|\1=${BROKER_ID}|g" config/server.properties
    else
        echo "broker.id=${BROKER_ID}" >> config/server.properties
    fi
fi


# Configure the default number of log partitions per topic
if [ ! -z "$NUM_PARTITIONS" ]; then
    echo "default number of partition: $NUM_PARTITIONS"
    sed -r -i "s/#?(num.partitions)=(.*)/\1=$NUM_PARTITIONS/g" config/server.properties
fi


# Enable/disable auto creation of topics
if [ ! -z "$AUTO_CREATE_TOPICS" ]; then
    echo "auto.create.topics.enable: $AUTO_CREATE_TOPICS"
    if grep -r -q "^#\?auto.create.topics.enable" $KAFKA_HOME/config/server.properties; then
        sed -r -i "s/#?(auto.create.topics.enable)=(.*)/\1=$AUTO_CREATE_TOPICS/g" $KAFKA_HOME/config/server.properties
    else
        echo "auto.create.topics.enable=$AUTO_CREATE_TOPICS" >> $KAFKA_HOME/config/server.properties
    fi
else
    echo "auto.create.topics.enable=true" >> $KAFKA_HOME/config/server.properties
fi


# expected values:   listeners=PLAINTEXT://:9092
if [ ! -z "$LISTENERS" ]; then
    echo "listeners: ${LISTENERS}"
    if grep -r -q "^#\?listeners=" config/server.properties; then
        # use | as a delimiter to make sure // does not confuse sed
        sed -r -i "s|^#?(listeners)=(.*)|\1=${LISTENERS}|g" config/server.properties
    else
        echo "listeners=${LISTENERS}" >> config/server.properties
    fi
fi


# expected values:   advertised.listeners=PLAINTEXT://your.host.name:9092
if [ ! -z "$ADVERTISED_LISTENERS" ]; then
    echo "advertised.listeners: ${ADVERTISED_LISTENERS}"
    if grep -r -q "^#\?advertised.listeners=" config/server.properties; then
        # use | as a delimiter to make sure // does not confuse sed
        sed -r -i "s|^#?(advertised.listeners)=(.*)|\1=${ADVERTISED_LISTENERS}|g" config/server.properties
    else
        echo "advertised.listeners=${ADVERTISED_LISTENERS}" >> config/server.properties
    fi
else
    echo -e "${red} not found required env ADVERTISED_LISTENERS variable ${reset}"
    echo ""
    echo "use 'PLAINTEXT://localhost:9092' value if consumers in localhost,  or  'PLAINTEXT://your.host.name:9092', for example"
    echo ""
    exit 1
    return 0
fi


# expected values:   zookeeper.connect=localhost:2181
if [ ! -z "$ZOOKEEPER" ]; then
    echo "zookeeper.connect: ${ZOOKEEPER}"
    if grep -r -q "^#\?zookeeper.connect=" config/server.properties; then
        # use | as a delimiter to make sure // does not confuse sed
        sed -r -i "s|^#?(zookeeper.connect)=(.*)|\1=${ZOOKEEPER}|g" config/server.properties
    else
        echo "zookeeper.connect=${ZOOKEEPER}" >> config/server.properties
    fi
else
    echo -e "${red} not found required env ZOOKEEPER variable ${reset}"
    echo ""
    echo "use this kafka image was a service and define de zoo image "
    echo ""
   # exit 1
   # return 0
fi


bin/zookeeper-server-start.sh config/zookeeper.properties &
sleep 5
bin/kafka-server-start.sh config/server.properties
