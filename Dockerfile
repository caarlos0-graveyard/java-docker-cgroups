FROM store/oracle/serverjre:8
COPY Main.class /
COPY entrypoint.sh /
ENTRYPOINT [ "/entrypoint.sh" ]
