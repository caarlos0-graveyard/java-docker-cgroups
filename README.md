# java-docker-cgroups

Playing around with the new cgroup-related JVM option.

```console
# builds a program that eats all memory (1mb at time) and prints runtime info
$ javac MemoryEater.java

# builds a docker image with that container
$ docker build -t caarlos0/java-docker-cgroups .

# run the container limiting its memory and cpus via cgroups
$ docker run --memory=10m --cpuset-cpus 1 caarlos0/java-docker-cgroups
available processors: 1
max memory: 361
total memory: 23
free memory: 21
free memory: 20
free memory: 19
free memory: 18
free memory: 17
free memory: 16
/entrypoint.sh: line 6:     5 Killed                  java $JAVA_OPTS Main


# run the container limiting its memory and cpus via cgroups and enabling the
# experimental features
$ docker run --memory=10m --cpuset-cpus 1 caarlos0/java-docker-cgroups -x
Enable experimental vm options
available processors: 1
max memory: 6
total memory: 6
free memory: 4
free memory: 3
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at Main.main(Main.java:11)
```

Note that the first time the container was killed (OOMKill) and the second time
it uses at its available heat (~50% max mem) and died.
