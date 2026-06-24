# Pets Service 🐾

API REST desarrollada con Spring Boot como prueba técnica.  
Este servicio se integra con una API externa (Petstore) y expone endpoints para consultar y crear mascotas.

---

## 🚀 Funcionalidades

- ✅ GET /api/pet/{petId}
- ✅ POST /api/pet
- ✅ Consumo de API externa (Petstore)
- ✅ Uso de RestClient (cliente HTTP moderno)
- ✅ Logging con Lombok (@Slf4j)
- ✅ Arquitectura por capas (Controller, Service, Client, Model)
- ✅ Pruebas unitarias (Service y Controller)

---

## 🏗️ Estructura del Proyecto

com.interview.pets  
├── controller  
│   └── PetController.java  
├── service  
│   └── PetService.java  
├── client  
│   └── PetClient.java  
├── config  
│   └── RestClientConfig.java  
├── model  
│   ├── Pet.java  
│   └── PostPetResponse.java  

---

## 🔧 Tecnologías

- Java 17  
- Spring Boot 3.2.7  
- Spring Web  
- Lombok  
- JUnit 5  
- Mockito  

---

## ⚙️ Cómo ejecutar

1. Clonar el repositorio:

git clone https://github.com/tu-usuario/pets-service.git

2. Entrar al proyecto:

cd pets-service

3. Ejecutar la aplicación:

./gradlew bootRun

---

## 🧪 Ejecutar pruebas

./gradlew test

---

## 📡 Endpoints

### ✅ Obtener mascota

GET /api/pet/{petId}

Ejemplo:
GET http://localhost:8080/api/pet/1

Respuesta:
{
  "id": 1,
  "name": "doggie",
  "status": "available"
}

---

### ✅ Crear mascota

POST /api/pet

Body:
{
  "id": 12345,
  "name": "miPet",
  "status": "available"
}

Respuesta:
{
  "transactionId": "uuid",
  "dateCreated": "2026-06-24T10:30:00",
  "name": "miPet",
  "status": "available"
}

---

## 🔄 API externa

Este servicio consume la API pública de Petstore:

https://petstore.swagger.io

---


