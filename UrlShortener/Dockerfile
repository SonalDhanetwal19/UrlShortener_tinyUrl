#FROM openjdk:11
#FROM adoptopenjdk/openjdk11
#COPY src /usr/src/app/src
#COPY pom.xml /usr/src/app

#RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests=true
#RUN mkdir work
#WORKDIR /work

FROM adoptopenjdk/openjdk11
RUN pwd
COPY target/UrlShortener-0.0.1-SNAPSHOT.jar UrlShortener-0.0.1-SNAPSHOT.jar
RUN chmod 777 UrlShortener-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","UrlShortener-0.0.1-SNAPSHOT.jar"]