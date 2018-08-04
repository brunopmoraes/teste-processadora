FROM maven:3.5.2-jdk-8-alpine

WORKDIR /app/teste-processadora

COPY . /app/teste-processadora

EXPOSE 8080

EXPOSE 9999

CMD [ "mvn", "spring-boot:run"]