FROM openjdk:8-alpine as builder

COPY . /src
WORKDIR /src
RUN ./gradlew --no-daemon shadowJar

FROM openjdk:8-jre-alpine

COPY --from=builder /src/build/libs/*.jar /bin/runner/run.jar
#COPY conf/example_dm-config.json /bin/runner/conf/example.json
WORKDIR /bin/runner

CMD ["java","-Dhazelcast.max.no.heartbeat.seconds=5", "-jar","run.jar"]