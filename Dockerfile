# Definindo base da imagem
FROM openjdk:21-jdk-slim-bullseye

# Definindo area de trabalho
WORKDIR /app

# Copiando codigo
COPY . /app/consult-credit

# Compilando a API
RUN cd consult-credit && \
    ./gradlew clean build
# Extraindo o JAR da aplicação
RUN cd /app && \
    rm -f ./consult-credit/consult-credit-api/build/libs/*-plain.jar && \
    cp ./consult-credit/consult-credit-api/build/libs/*.jar ./app.jar
# Removendo código fonte
RUN rm -rf consult-credit 

# Expondo porta da aplicação
EXPOSE 8080

# Executando a api
ENTRYPOINT ["java", "-jar", "app.jar"]