FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp

WORKDIR /app/food-app-spring-boot

ADD ./food-app-spring-boot/mvnw /app/food-app-spring-boot/mvnw

COPY ./food-app-spring-boot /app/food-app-spring-boot/

RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

RUN ./mvnw clean package -DskipTests

ENTRYPOINT java -jar target/*.jar