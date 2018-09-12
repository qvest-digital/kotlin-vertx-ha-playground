# kotlin-vertx-ha-playground
Test project for testing of HA feature of vert.x in kotlin.

This features an vert.x hazelcast cluster with fatjars in docker-compose with 2 containers with one vertx node each.


To start the test cluster run
```
 docker-compose up -d
```

To rebuild and start the test cluster run
```
 docker-compose up -d --build
``` 

To show the log output run

```
 docker-compose log -f vertx1
```
or
```
 docker-compose log -f vertx2
```

