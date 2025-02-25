# ✨🚑 𝗦𝗶𝘀𝘁𝗲𝗺𝗮 𝗚𝗲𝘀𝘁𝗶𝗼́𝗻 𝗠𝗲́𝗱𝗶𝗰𝗮 - 𝗝𝗮𝘃𝗮𝗙𝗫 & 𝗦𝗽𝗿𝗶𝗻𝗴 𝗕𝗼𝗼𝘁 🚑✨

[![Typing SVG](https://readme-typing-svg.demolab.com?font=Sigmar&pause=1000&color=FF0000&background=004FFF00&width=435&lines=By.+Nacho+and+Sergi)](https://git.io/typing-svg)

- [📌 Descripción](#-descripción)
- [🚀 Características](#-Características)
    - [🔹 Login](#-Login)
    - [🔹 Funcionalidades para Médicos](#-Funcionalidades-para-Médicos)
    - [🔹 Funcionalidades para Pacientes](#-Funcionalidades-para-Pacientes)
- [🗄️ Modelo de Base de Datos](#-Modelo-de-Base-de-Datos)
- [🛠️ Tecnologías Utilizadas](#-Tecnologías-Utilizadas)
- [🏆 Autores](#-Autores)

## 📌 Descripción
Este es un sistema hospitalario desarrollado con **JavaFX** y **Spring Boot**, con una base de datos en **PostgreSQL**. Permite la gestión de citas médicas, pacientes y médicos de manera eficiente.

---

## 🚀 Características

### 🔹 Login
- **Médicos** pueden iniciar sesión y gestionar su información.  
- **Pacientes** pueden acceder a su historial de citas.  

### 🔹 Funcionalidades para Médicos
- Ver sus **citas** y **pacientes** y tener la posibilidad de **imprimirlas**.  
- **Añadir** y **eliminar** citas.  
- **Añadir**, **eliminar** y **modificar** pacientes.  
- Ver su **perfil** y posibilidad de **modificarlo**.  

### 🔹 Funcionalidades para Pacientes
- Ver sus **citas pendientes** con fecha y médico asignado.  

---

## 🗄️ Modelo de Base de Datos

📌 **Entidades principales**:
1. **Hospital** (id, nombre, localidad) 🏥  
2. **Médico** (id, nombre, apellidos, id_hospital, contrasenya) 👨‍⚕️  
3. **Paciente** (id, nombre, apellidos, pueblo_residencia, telefono, contrasenya) 🧑‍⚕️  
4. **Visita** (id, id_paciente, id_medico, fecha, hora) 📅  

---

## 🛠️ Tecnologías Utilizadas

- **JavaFX** (Interfaz gráfica) 🎨  
- **Spring Boot** (Back-end) ⚙️  
- **PostgreSQL** (Base de datos) 🗄️  

---

## 🏗️ Instalación y Ejecución

1️⃣ **Extrae el proyecto**
- Extrae el archivo `Proyecto DI AD - Nacho y Sergi.zip`

2️⃣ **Configura la base de datos PostgreSQL**
- Crea la base de datos y configura las credenciales en [application.properties](src/main/resources/application.properties).

3️⃣ **Ejecuta la aplicación**
- Ejecuta la aplicación con la clase [AdSanidadDiApplication](src/main/java/es/cheste/ad_sanidad_di/AdSanidadDiApplication.java).

---

## 🏆 Autores

👨‍💻 **[Nacho](https://github.com/NachoPen0804)**  
👨‍💻 **[Sergi](https://github.com/sergiEscriva)**  
