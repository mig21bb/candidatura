# candidatura
Este es el resultado de la prueba técnica requerida para mi candidatura laboral en esPublico.

  La arquitectura de la aplicación está basada en spring 3.0, empleando las herramientas de spring boot para que se ejecute la aplicación en
un servidor ligero y poder disponer también de la posibilidad de servir contenido html a través del framework MVC de thymeleaf y por último
la capa de persistencia de spring 3.0 basada en Hibernate para conectar con la BBDD.

  Se ha empleado una BBDD MariaDB alojada en un servicio de Amazon Web Services para poder acceder a ella por mí o por cualquier otra persona para su evaluación.
    
  Comando para crear el .jar : MVNW clean -Dbbddusername=esuser -Dbbddpassword=esPublico clean install
    
  Comando ejecutar el .jar y arrancar la aplicación :   java -Dbbddusername=esuser -Dbbddpassword=esPublico -jar espublico-0.0.1-SNAPSHOT.jar
  
  Una vez arrancada la aplicación se podrá acceder a ella en el navegador en la ruta http://localhost:8080/
  
  El esquema de la BBDD de starwars sería este:
