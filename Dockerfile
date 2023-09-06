FROM eclipse-temurin:17-jre-jammy
COPY target/guest-registration-1.0.jar app.jar
EXPOSE 8080

# script waits for services to be up before starting
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.2/wait /wait
RUN chmod +x /wait

CMD ["java","-jar","/app.jar"]