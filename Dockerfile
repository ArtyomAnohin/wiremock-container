FROM java:8
WORKDIR /
ADD wiremock-1.0-SNAPSHOT.jar wiremock-1.0-SNAPSHOT.jar
ADD ./mappings ./mappings
ADD ./__files ./__files
VOLUME ["/mappings"]
EXPOSE 8080
CMD ["java", "-jar", "wiremock-1.0-SNAPSHOT.jar"]
