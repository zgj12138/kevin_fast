FROM java:8
EXPOSE 80

VOLUME /tmp
ADD kevin-fast-1.2.0.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
