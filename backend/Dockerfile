#FROM openjdk:17
#VOLUME /tmp
#ARG JAR_FILE=target/library-cosmart-project.jar
#WORKDIR /opt/app
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","${JAVA_OPTS}","-jar","/app.jar"]

FROM maven:3.8.5-openjdk-17

WORKDIR /app
COPY .. .
RUN mvn clean package -DskipTests -q

CMD mvn spring-boot:run

# First stage: build the application
#FROM eclipse-temurin:17-jdk AS build
#COPY . /app
#WORKDIR /app
#RUN mvn clean package -DskipTests
#
## Second stage: create a slim image
#FROM openjdk:17
#COPY --from=build /app/target/library-cosmart-project.jar /app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]

## don't forget to set final name project on pom.xml
#ARG project_name=library-cosmart-project
#ARG application_name=$project_name.jar
#
#USER root
##-------------------------------------------------------------
## Prepare file and application
##-------------------------------------------------------------
#RUN mkdir -p /var/log/$project_name/
#

#ADD target/$application_name /apps/
#COPY docker-target/apps/ /apps/
#
##-------------------------------------------------------------
## Set Permission in OS
##-------------------------------------------------------------
#RUN chown -R 3000:3000 /apps
#USER 3000
#
##-------------------------------------------------------------
## Set default working dir
##-------------------------------------------------------------
#WORKDIR  /apps/
#EXPOSE 8080
#
