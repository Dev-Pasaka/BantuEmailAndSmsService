FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

FROM openjdk:11
EXPOSE 8080:8080

#COPY --from=build /home/gradle/src/build/libs/*.jar /app/online.pasaka.bantuemailverificationapi-all.jar/
CMD ["java","-jar","online.pasaka.bantuemailverificationapi-all.jar"]