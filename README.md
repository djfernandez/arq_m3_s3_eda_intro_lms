# Implementación de EDA

## 1. Diagrama de clases
<img src="images/EDA.png"/>

## 2. Pruebas

### 2.1. Crear un curso
POST : http://localhost:8080/api/courses

```
{
  "title": "Spring Boot Masterclass - version 2",
  "description": "Learn Spring Boot from scratch",
  "instructor": "John Doe"
}

```

### 2.2. Publicar un curso

PUT : http://localhost:8080/api/courses/1/publish

Donde el ID del curso es 1

## 3. Ejercicios

### Crear Eventos y Handlers para los siguientes casos:

#### StudentEnrolledEvent
- Handler: Enviar email de bienvenida
- Handler: Actualizar estadísticas del curso
- Handler: Crear acceso al material

#### LessonCompletedEvent
- Handler: Actualizar progreso
- Handler: Enviar notificación de logro
- Handler: Verificar si completó el curso


## 4. Implementando Retry

### 4.1. Agregar la dependencia
```
     <!-- Spring Retry -->
     <dependency>
         <groupId>org.springframework.retry</groupId>
         <artifactId>spring-retry</artifactId>
         <version>2.0.4</version>  <!-- La versión es importante considerarla -->
     </dependency>
     <dependency>
         <groupId>org.springframework</groupId>
         <artifactId>spring-aspects</artifactId>
     </dependency>
     
```
