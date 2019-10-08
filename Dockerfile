FROM openjdk:8-jdk
COPY Main.java /
RUN javac Main.java
COPY entrypoint.sh /
ENTRYPOINT [ "/entrypoint.sh" ]
