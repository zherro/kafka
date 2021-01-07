# kafka in docker

This is an simple example of how create your docker image.

## Files

- **Dockerfile**:  docker configuratior to create image
- **server.properties**: kafka server configuration 
- **start**: zookeeper and kafka start server
- **docker-compose.yml**: docker compose example

### Dockerfile

Docker image start from `openjdk:11` image

```
FROM openjdk:11
```

Then, donload of kafka to `/opt` folder and unzip.

```
RUN cd /opt && curl -OL https://downloads.apache.org/kafka/2.6.0/kafka_2.12-2.6.0.tgz && tar -zxvf kafka_2.12-2.6.0.tgz && rm kafka_2.12-2.6.0.tgz
```


We defines the `workdir` folder, we copy the `start.sh` file to workdir and the `server.properties`  to config folder of kafka.

```
WORKDIR /opt/kafka_2.12-2.6.0/

COPY start.sh /opt/kafka_2.12-2.6.0
COPY server.properties /opt/kafka_2.12-2.6.0/config
```

Lastly, set de start.sh to execute when de container start and exposes zookeeper and kafka default ports.

```
CMD ["./start.sh"]
```


#### Build image

For build image docker run it in files folder:

```
$ docker build . -t kafka-zherro:2.6
$ docker run -p 9092:9092 --name kafka-in-docker kafka-zherro:2.6
```

> Note that it creates de docker image with name `kafka-zherro:2.6` and run container whith name `kafka-in-docker`, respectively.
