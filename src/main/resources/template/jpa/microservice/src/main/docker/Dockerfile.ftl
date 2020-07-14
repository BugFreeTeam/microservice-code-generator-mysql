FROM java:8
VOLUME /tmp
ADD ${artifactId}-0.0.1-SNAPSHOT.jar ${artifactId}.jar
RUN bash -c 'touch /${artifactId}.jar'
EXPOSE ${servicePort}
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/${artifactId}.jar"]