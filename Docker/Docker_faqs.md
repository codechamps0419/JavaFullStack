FAQs
__________________________________

1. What is Docker and why do we use it?
Answer: Docker is a containerization platform that isolates applications in lightweight environments called containers. Each container includes all dependencies, ensuring consistent behavior across development, testing, and production environments—eliminating “works on my machine” issues.

2. How is Docker different from a Virtual Machine (VM)?
Answer: VMs virtualize hardware and require full guest OS, leading to high resource usage. Docker containers share the host OS kernel, making them more lightweight, faster to start, and efficient.

3. What is a Docker image versus a container?
Answer: An image is a static, read-only template. A container is a runtime instance—an image with a writable layer to handle processes and data.

4. Explain a Dockerfile and its basic structure.
Answer: A Dockerfile is a text script with instructions to build an image. Example:

FROM openjdk:17-slim-alpine
WORKDIR /app
COPY target/myapp.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
This sets a base image, working directory, copies your JAR, and sets up the startup command.

5. What’s the difference between CMD and ENTRYPOINT?
Answer:

ENTRYPOINT defines the main executable command in the container.

CMD provides default arguments for it—override-able at runtime. If both exist, CMD acts as default parameters to ENTRYPOINT.

6. How does EXPOSE differ from port mapping (-p)?
Answer: EXPOSE declares the port the container listens on (documentation only). -p hostPort:containerPort maps that container port to the host for external usage.

7. Explain the importance of .dockerignore.
Answer: It excludes files/directories from the build context—speeds builds and ensures sensitive data isn’t unintentionally added to images.

8. What is multi-stage build and why use it?
Answer: Multi-stage builds use multiple FROM statements—one for building (e.g., compiling) and another for runtime—minimizing image size and enhancing security.

9. How do you build and run an image?
Answer:

docker build -t my-java-app .
docker run -d -p 8080:8080 --name java-app my-java-app
-d runs it in detached mode, -p maps ports, and --name labels the container.

10. How do you list and inspect containers?
Answer:

docker ps         # Running only
docker ps -a      # All containers (running and stopped)
docker inspect <container_id>

11. How can you stop, start, restart, and remove a container?
Answer:

docker stop <container_id>
docker start <container_id>
docker restart <container_id>
docker rm <container_id>
Use -f with rm to force removal of running containers.

12. What is docker logs for?
Answer: Shows container logs:

docker logs <container_id>
docker logs -f <container_id>  # To Follows logs live

13. What is a volume vs. bind mount?
Answer:
Volume: Managed by Docker—persistent and decoupled from the host.

Bind mount: Directly uses host files—flexible but less portable.

14. How do you mount a volume?
Answer:
docker run -v myvolume:/app/data my-image

or for bind mounts:
docker run -v /host/path:/container/path my-image

15. What is Docker networking?
Answer: Allows inter-container communication. Default networks include bridge, host, and none. Custom networks (e.g., docker network create appnet) isolate traffic among services.

16. Explain Docker Compose.
Answer: Defines multi-container applications using docker-compose.yml. Example:
services:
  app:
    build: .
    ports: ["8080:8080"]
    depends_on: ["db"]
  db:
    image: postgres:14
    environment:
      POSTGRES_PASSWORD: example
      
Use docker-compose up to launch both together.

17. Can you scale services using Compose?
Answer:

docker-compose up --scale app=3
This launches multiple replicas of the app service.

18. How do you view network topology in Docker Compose?
Answer:

docker network ls
docker network inspect <network_name>

19. Explain container process isolation.
Answer: Containers use namespaces and cgroups to isolate processes (PID, network, mount, IPC) and manage resource usage.

20. What are images' layers and caching benefits?
Answer: Docker builds images in layers—each instruction creates a layer. Docker caches layers to avoid rebuilding unchanged parts—speeds up build time significantly.

21. How to reduce Docker image size?
Answer: Use slim base images, multi-stage builds, clean up unnecessary artifacts, combine RUN statements, and use .dockerignore.

22. Explain docker pull, docker push, and Docker registries.
Answer:

docker pull: Download images.

docker push: Upload to registry (like Docker Hub, ECR).

Registries are repositories for container images.

23. How do you tag images?
Answer:

docker build -t my-app:latest .
docker tag my-app:latest myrepo/my-app:v1.0
docker push myrepo/my-app:v1.0

24. What are labels?
Answer: Metadata in images/containers for filtering, organizing, or documentation:

LABEL maintainer="name@example.com"

25. Can a container run multiple processes? Should it?
Answer: You can via supervisord or custom scripts, but best practice recommends one process per container—for clarity, resilience, and scaling.

26. What is an ENTRYPOINT script?
Answer: Using an entrypoint shell script (e.g., entrypoint.sh) allows setup tasks (e.g. DB migration) before starting the main process.

27. Explain docker exec.
Answer: Execute commands inside running containers:

docker exec -it <container_id> /bin/bash

28. How do you commit container changes?
Answer:

docker commit <container_id> my-new-image
Not ideal for production—better to update Dockerfile.

29. What is the default base image for minimal Java runtime?
Answer: openjdk:17-jdk for full JDK or openjdk:17-slim, alpine variants for smaller footprints.

30. Explain CPU and memory limits.
Answer:

docker run -m 512m --cpus="1.0" my-app
Limits memory to 512 MB, CPU to 1 core—essential for resource control.

31. How can logging be centralized?
Answer: Use drivers like json-file, syslog, or forward logs to ELK, Fluentd, or another system-wide aggregator via Docker configuration.

32. What are health checks?
Answer: Defines container health:

HEALTHCHECK --interval=30s CMD curl -f http://localhost:8080/actuator/health || exit 1

Docker marks containers as unhealthy if checks fail.

33. Explain the “scratch” image.
Answer: A totally empty base image, used for highly minimal containers—commonly used with statically compiled Go binaries, less common for Java.

34. How to handle secrets?
Answer: Avoid hardcoding. Options: env vars, Docker Swarm secrets, --env-file, external secret managers (e.g., Vault).

35. What is docker system prune for?
Answer: Cleans up unused containers, volumes, networks, and images:

docker system prune

Optionally add -a to include unused images.

36. What are node selectors and affinity in Docker Swarm?
Answer: In Swarm, you can pin services to nodes by labels:

placement:
  constraints:
    - node.role == manager
    
Not common in beginner-to-intermediate level but good to know.

37. What is Phoenix server? (trick question)
Answer: Not related to Docker—often confuses candidates. Docker doesn’t have—Phoenix server is Elixir/Phoenix framework, unrelated here.

38. How to debug failed Docker builds?
Answer: Use:
docker build --progress=plain

or split Dockerfile into steps and inspect intermediate containers.

39. Explain --network host.
Answer: Shares host’s network namespace—container uses host’s interfaces/ports. Use with caution due to security implications.

40. How to optimize dependencies in Java Docker builds?
Answer: Use layer caching:

COPY pom.xml /app/
RUN mvn dependency:go-offline
COPY . /app
RUN mvn package -DskipTests

This avoids reinstalling dependencies between code changes.

41. What is OCI format?
Answer: Open Container Initiative—standardizes container formats and runtimes. Docker images follow OCI specifications.

42. Explain local builds vs build cache.
Answer: Docker caches layers, so rebuilding the same instructions is fast. Modifying earlier steps invalidates later cache. Understanding this speeds up CI/CD workflows.

43. How to build a native image with GraalVM and Docker?
Answer: Use a multi-stage build:

Build native binary using GraalVM image.

COPY the native binary into a minimal base (e.g., scratch or alpine) for smaller image.

44. What is Kubernetes’ relationship with Docker?
Answer: Kubernetes orchestrates containerized applications (Docker, containerd, CRI). Docker is the container runtime—K8s schedules and manages clusters.

45. What is Docker's role in CI/CD pipelines?
Answer: Builds, tests, and packages app into container images, which are then deployed consistently across stages (e.g., via Jenkins, GitHub Actions, GitLab CI).

46. How do you track image versions?
Answer:
Tagging convention:
:latest (use with caution)

Semantic versions (e.g., :v1.0.0)
Git SHA or build number for traceability.

47. What are intermediate containers?
Answer: Temporary containers created during build steps—they can be referenced with docker build --target, helpful in multi-stage builds.

48. Why isn’t RUN mkdir /app && WORKDIR /app cached separately?
Answer: Docker caching is per-layer: combining commands in one RUN helps reduce image layers and context overhead. If files change, only affected layers rebuild.

49. How to avoid build failures due to DNS issues?
Answer:
In docker run, add:
--dns 8.8.8.8

Or configure Docker daemon daemon.json with a reliable DNS.

50. What does docker inspect return?
Answer: Low-level JSON metadata of containers/images—including network settings, volumes, env vars, ports, mount points—useful for debugging.



