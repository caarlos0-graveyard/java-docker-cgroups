# java-docker-cgroups

Playing around with the new cgroup-related JVM option.

## Prep

Build a program that eats all memory (1mb at time) and prints runtime info:

```console
$ javac Main.java
Note: Main.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
```

Build a docker image with that container:

```console
$ docker build -t caarlos0/java-docker-cgroups .
Sending build context to Docker daemon  83.46kB
Step 1/4 : FROM store/oracle/serverjre:8
 ---> f5a1afb45afe
Step 2/4 : COPY Main.class /
 ---> Using cache
 ---> bc43f3f5c123
Step 3/4 : COPY entrypoint.sh /
 ---> Using cache
 ---> 7673b0fe0969
Step 4/4 : ENTRYPOINT /entrypoint.sh
 ---> Using cache
 ---> 1aaf6193122a
Successfully built 1aaf6193122a
Successfully tagged caarlos0/java-docker-cgroups:latest
```

## Running

Run the container limiting its memory and cpus via cgroups:

```console
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
```

Run the container limiting its memory and cpus via cgroups and enabling the
experimental features:

```console
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
it uses at its available heap (~50% max mem) and died.
