FROM openjdk:11
RUN cd /opt && curl -OL https://downloads.apache.org/kafka/2.6.0/kafka_2.12-2.6.0.tgz && tar -zxvf kafka_2.12-2.6.0.tgz && rm kafka_2.12-2.6.0.tgz

WORKDIR /opt/kafka_2.12-2.6.0/

COPY start.sh /opt/kafka_2.12-2.6.0
COPY configurations.sh /opt/kafka_2.12-2.6.0
# COPY server.properties /opt/kafka_2.12-2.6.0/config

ENTRYPOINT ["./start.sh", "./configurations.sh"]

EXPOSE 9092
EXPOSE 2181
