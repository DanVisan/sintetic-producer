#Sintetic Producer

Produces data that is registered in a kafka topic


####How to
Start zookeeper and kafka containers:

`docker-compose up -d`


Check kafka topic for registered messages:

`bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic events --from-beginning`

