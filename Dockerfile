FROM store/oracle/serverjre:8
COPY MemoryEater.class /
COPY entrypoint.sh /
ENTRYPOINT [ "/entrypoint.sh" ]
