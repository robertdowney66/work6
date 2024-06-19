FROM openjdk:11
WORKDIR /root/work6/sentinel
COPY ./sentinel-dashboard.jar sentinel-dashboard.jar
EXPOSE 8718
ENTRYPOINT ["java", "-jar", "sentinel-dashboard.jar"]