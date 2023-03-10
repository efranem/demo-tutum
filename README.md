# Aplicacion DEMO TUTUM

## Control Escolar

----

### Configuracion de la The BD Postgres

Se adjunta el archivo docker-compose.yml que contiene la configuracion para levatar un PostgresSQL con docker (docker-compose)

#### USO

1. Levantar el contenedor ejecutando la siguiente instruccion

  ```batch
    docker-compose up -d
  ```

2. La BD se expone por default en el puerto ***5432*** y la contraseña ***password****, en caso de contar ya con una BD favor de ajustar la contraseña en el Back en el archivo **application.properties**

3. Se adjunta el archivo **db.sql** con las querys modificadas para ejecutarse de manera correcta en la BD


----

### Levantar el Back


Creado con **Java 1.8**, **SpringBoot** y **maven**

Puede levantarse modo standalone para evitar el uso de un servidor de aplicaiones


#### USO

1. Entrar a la carpeta **Back**

2. Compilar la aplicacion con la siguiente instruccion

  ```batch
    mvn clean package
  ```

3. Iniciar la aplicacion

  ```batch
    java -jar target/TutumBack-1.0.0.jar
  ```
3. Se expondra por default la **API** en la direccion http://localhost:8080/controlEscolar

4. Ver postman adjunto ***tutum.postman_collection.json*** para ver los metodos dispobibles


----

### Levantar el Front


Creado con **React** y **PrimeReact** como framework de estilos y utilerias

#### USO

1. Entrar a la carpeta **Front**

2. Instalar las dependencias necesarias

  ```batch
    npm install
  ```

3. Iniciar el componente

  ``` batch
    npm start
  ```
3. Se expondra por default en la direccion web http://localhost:3000/

4. Esta configurado para buscar el API anteriormente configurado
