FROM openjdk:17
WORKDIR /app
COPY build/libs/hakaton-0.3.0-beta.jar /app/hakaton-0.3.0-beta.jar
EXPOSE 8080
ENV JAVA_OPTS="-Xms1536m -Xmx2048m"
CMD ["sh", "-c", "java $JAVA_OPTS -jar /app/hakaton-0.3.0-beta.jar"]
#ENTRYPOINT ["java", "-jar", "hakaton-0.2.3-beta.jar"]