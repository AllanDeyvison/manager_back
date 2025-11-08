 # Importando o JDK e copiando os arquivos necessários
FROM openjdk:21-jdk-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src src

# Copiar wrapper Maven
COPY mvnw .
COPY .mvn .mvn

# Definir permissão de execução para o wrapper Maven
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Etapa 2: Criar a imagem final do Docker usando o OpenJDK 19
 FROM openjdk:21-jdk-slim
VOLUME /tmp

# Copiar o JAR da etapa de compilação
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT [ "java" , "-jar" , "/app.jar" ]
EXPOSE 8090