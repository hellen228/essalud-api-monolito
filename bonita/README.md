# Sistema de Gestión de Atención Médica - EsSalud

## Descripción

Este proyecto implementa un flujo de atención médica utilizando **Bonita BPM** para la automatización de procesos de negocio y **RabbitMQ** como mecanismo de mensajería entre dominios independientes.

El objetivo es desacoplar los diferentes procesos del sistema (Atención Médica, Farmacia, Laboratorio, etc.) permitiendo el intercambio de información mediante eventos en formato JSON.

---

# Arquitectura General

```
                +-----------------------+
                |       Usuario         |
                +-----------+-----------+
                            |
                            v
                +-----------------------+
                |      Bonita BPM       |
                | Formularios y BPMN    |
                +-----------+-----------+
                            |
            ----------------+----------------
            |                               |
            v                               v
   Atención Médica                  Farmacia
            |                               ^
            |                               |
            +----------- RabbitMQ ----------+
                     (Mensajería)

```

Bonita actúa como la capa de presentación y orquestación de procesos, mientras que RabbitMQ permite la comunicación asíncrona entre procesos independientes.

---

# Función de Bonita BPM

Bonita BPM es responsable de:

- Gestionar el flujo BPMN de cada proceso.
- Presentar formularios al usuario.
- Validar la información ingresada.
- Ejecutar tareas humanas y automáticas.
- Publicar y consumir eventos mediante conectores RabbitMQ.
- Coordinar la ejecución del proceso de negocio.

Cada proceso se implementa como un flujo independiente dentro de Bonita.

Ejemplo:

```
Paciente

↓

Registrar consulta

↓

Registrar receta

↓

Enviar receta mediante RabbitMQ
```

---

# Función de RabbitMQ

RabbitMQ funciona como un middleware de mensajería desacoplada.

Su función consiste en:

- Recibir eventos enviados desde Bonita.
- Almacenar temporalmente los mensajes.
- Permitir que otros procesos los consuman cuando estén disponibles.
- Evitar el acoplamiento directo entre procesos.

Ejemplo del flujo implementado:

```
Proceso Atención Médica

↓

Publica receta

↓

recetas.queue

↓

Proceso Farmacia

↓

Consume receta
```

Ejemplo de mensaje:

```json
{
    "dniPaciente":"12345678",
    "medicamentos":"Amoxicilina",
    "duracionTratamiento":"7 días",
    "medicoEncargado":"Dr. Pérez"
}
```

---

# Arquitectura DDD

El sistema se organiza en dominios independientes, donde cada uno representa un contexto específico del negocio.

| Dominio | Responsabilidad |
|----------|-----------------|
| Atención Médica | Registro de consultas y emisión de recetas. |
| Farmacia | Recepción de recetas y dispensación de medicamentos. |
| Laboratorio | Gestión de resultados clínicos. |
| Acreditación | Validación del asegurado. |

Cada dominio puede evolucionar de forma independiente y comunicarse mediante eventos publicados en RabbitMQ.

---

# Recursos OpenAPI / Swagger

Los servicios REST del sistema son documentados mediante OpenAPI 3 y Swagger UI.

## Atención Médica

| Método | Endpoint | Descripción |
|---------|----------|-------------|
| POST | /api/consultas | Registrar consulta médica. |
| POST | /api/recetas | Registrar una receta médica. |
| GET | /api/pacientes/{id} | Consultar información del paciente. |

---

## Farmacia

| Método | Endpoint | Descripción |
|---------|----------|-------------|
| POST | /api/dispensacion | Registrar la entrega de medicamentos. |
| GET | /api/stock | Consultar disponibilidad de medicamentos. |
| GET | /api/recetas | Consultar recetas recibidas. |

---

## Laboratorio

| Método | Endpoint | Descripción |
|---------|----------|-------------|
| POST | /api/resultados | Registrar resultados clínicos. |
| GET | /api/resultados/{id} | Consultar resultados de laboratorio. |

---

# Flujo de Integración

```
Paciente

↓

Formulario Bonita

↓

Proceso Atención Médica

↓

RabbitMQ (recetas.queue)

↓

Proceso Farmacia

↓

Dispensación

↓

Actualización del inventario
```

---

# Tecnologías

- Bonita BPM Community
- RabbitMQ
- Java 17
- Maven
- OpenAPI 3
- Swagger UI
- JSON
- REST API

---

# Objetivo de la Integración

La integración entre Bonita BPM y RabbitMQ permite desacoplar los procesos del sistema de salud, facilitando el intercambio de información mediante eventos. De esta forma, cada dominio puede ejecutarse de manera independiente, manteniendo una arquitectura modular, escalable y alineada con los principios de Domain Driven Design.
