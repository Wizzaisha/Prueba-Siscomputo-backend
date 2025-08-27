# Proxy App

Documentación para la ejecución y gestion de la aplicación.

### Ejecución del entorno local SIN DOCKER

1. Configurar las variables de entorno en un archivo .env
```
EXTERNAL_API_BASEURL=https://jsonplaceholder.typicode.com
```
2. Sincronizar la dependencias del proyecto con maven:
```
mvn clean package
```
3. Correr la aplicación por medio de la idle


### Ejecución del entorno local CON DOCKER sin usar el DOCKER-COMPOSE

1. Realizar la construicción de la imagen con:
```
docker build -t spring-proxy:latest .
```
2. Ejecutar el contenedor seteando las variables de entorno:
```
docker run --rm -e EXTERNAL_API_BASEURL="https://jsonplaceholder.typicode.com" -p 8080:8080 spring-proxy:latest 
```

### Ejecución del entorno local CON DOCKER y el DOCKER-COMPOSE

1. Configurar las variables de entorno en un archivo .env
```
EXTERNAL_API_BASEURL=VALORURLAPI
```
2. Ejecutar docker compose para construir la imagen y ejecutar el contenedor:
```
docker compose up -d --build
```

## Test de los endpoints
La aplicación cuenta con swagger para acceder y probar los endpoints de la aplicación:

http://localhost:8080/swagger-ui/index.html

Tambien se pueden ejecutar los siguientes comandos para probar la aplicacion por CURL:

- /api/external
```
curl -X 'GET' 'http://localhost:8080/api/external' -H 'accept: */*'
```
- /api/external/{id}
```
curl -X 'GET' 'http://localhost:8080/api/external/1' -H 'accept: */*'
```
- /actuator/health
```
curl -X 'GET' 'http://localhost:8080/actuator/health' -H 'accept: */*'
```

