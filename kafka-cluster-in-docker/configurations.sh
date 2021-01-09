#!/bin/bash

sleep 30

echo "##########################################################"

# Run KafkaTopics to create the topics if they don't exist yet
if [[ -n $KAFKA_CREATE_TOPICS ]]; then
  for TOPIC in ${KAFKA_CREATE_TOPICS//,/ }; do
    echo "creating topic: $TOPIC"
    TOPIC_CONFIG=(${TOPIC//:/ })
    if [ ${TOPIC_CONFIG[3]} ]; then
      bin/kafka-topics.sh --create --if-not-exists --bootstrap-server ${HOSTNAME}:${HOST_PORT} --replication-factor ${TOPIC_CONFIG[2]} --partitions ${TOPIC_CONFIG[1]} --topic "${TOPIC_CONFIG[0]}" --config cleanup.policy="${TOPIC_CONFIG[3]}"
    else
      bin/kafka-topics.sh --create --if-not-exists --bootstrap-server ${HOSTNAME}:${HOST_PORT} --replication-factor ${TOPIC_CONFIG[2]} --partitions ${TOPIC_CONFIG[1]} --topic "${TOPIC_CONFIG[0]}"
    fi
  done
fi
