# ✨🚑 𝗦𝗶𝘀𝘁𝗲𝗺𝗮 𝗛𝗼𝘀𝗽𝗶𝘁𝗮𝗹𝗮𝗿𝗶𝗼 - 𝗝𝗮𝘃𝗮𝗙𝗫 & 𝗦𝗽𝗿𝗶𝗻𝗴 𝗕𝗼𝗼𝘁 🚑✨

## 📌 Descripción
Este es un sistema hospitalario desarrollado con **JavaFX** y **Spring Boot**, con una base de datos en **PostgreSQL**. Permite la gestión de citas médicas, pacientes y médicos de manera eficiente.

---

## 🚀 Características

### 🔹 Login
- **Médicos** pueden iniciar sesión y gestionar su información.  
- **Pacientes** pueden acceder a su historial de citas.  

### 🔹 Funcionalidades para Médicos
- Ver sus **citas** y **pacientes**.  
- **Añadir** y **eliminar** citas.  
- **Añadir**, **eliminar** y **modificar** pacientes.  
- Ver su **perfil** y posibilidad de **modificarlo**.  

### 🔹 Funcionalidades para Pacientes
- Ver sus **citas pendientes** con fecha y médico asignado.  

---

## 🗄️ Modelo de Base de Datos

📌 **Entidades principales**:
1. **Hospital** (id, nombre, localidad) 🏥  
2. **Médico** (id, nombre, apellidos, id_hospital) 👨‍⚕️  
3. **Paciente** (id, nombre, apellidos, pueblo_residencia) 🧑‍⚕️  
4. **Visita** (id, id_paciente, id_medico, fecha) 📅  

---

## 🛠️ Tecnologías Utilizadas

- **JavaFX** (Interfaz gráfica) 🎨  
- **Spring Boot** (Back-end) ⚙️  
- **PostgreSQL** (Base de datos) 🗄️  

---

## 🏆 Autores

👨‍💻 **[Nacho](https://github.com/NachoPen0804)**  
👨‍💻 **[Sergi](https://github.com/sergiEscriva)**  
