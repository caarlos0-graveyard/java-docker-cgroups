# java-docker-mem

Playing around with the new cgroup-related JVM option.

```console
# builds a program that eats all memory (1mb at time)
$ javac MemoryEater.java

# builds a docker image with that container
$ docker build -t caarlos0/java-docker-mem .

# run the container limiting its memory via cgroups
$ docker run --memory=10m caarlos0/java-docker-mem
max memory: 361
total memory: 23
free memory: 21
free memory: 20
free memory: 19
free memory: 18
free memory: 17
free memory: 16
/entrypoint.sh: line 6:     5 Killed                  java $JAVA_OPTS MemoryEater

# run the container limiting its memory via cgroups and enabling the
# experimental features
$ docker run --memory=10m caarlos0/java-docker-mem -x
Enable experimental vm options
max memory: 6
total memory: 6
free memory: 4
free memory: 3
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at MemoryEater.main(MemoryEater.java:10)
```
