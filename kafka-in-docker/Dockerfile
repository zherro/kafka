# WARNING: This Dockerfile is only for testing purpose.

# reference: https://github.com/zherro/kafka
#
# Ports:
# - description: Major ports are exposed to host computer
# - zookeeper: 2181
#      kafka1: 9092

# Docker commands (stop all container and remove all containers)
# $ docker stop  $(docker container ls -aq) -t 0 
# $ docker container prune ( ALERT: this remove al container )


# BUILD KAFKA DOCKER IMAGE WITH NAME 'kafka-zherro:2.6'

# $ docker build . -t kafka-zherro:2.6
# $ docker run -p 9092:9092 --name kafka-in-docker kafka-zherro:2.6


FROM openjdk:11
RUN cd /opt && curl -OL https://downloads.apache.org/kafka/2.6.0/kafka_2.12-2.6.0.tgz && tar -zxvf kafka_2.12-2.6.0.tgz && rm kafka_2.12-2.6.0.tgz

WORKDIR /opt/kafka_2.12-2.6.0/

COPY start.sh /opt/kafka_2.12-2.6.0
COPY server.properties /opt/kafka_2.12-2.6.0/config
    
CMD ["./start.sh"]

EXPOSE 9092
EXPOSE 2181
