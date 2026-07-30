# EsSalud -- Aplicación BPM (BonitaSoft)

Modelado e implementación de procesos de negocio para **EsSalud**
utilizando **Bonita BPM**, **Domain Driven Design (DDD)**, **RabbitMQ**
y **servicios REST documentados con OpenAPI/Swagger**.

El proyecto automatiza el flujo de atención de un paciente desde la
validación de cobertura hasta la dispensación de medicamentos,
integrando procesos BPMN con servicios web y mensajería asíncrona.

> **Repositorio de servicios REST:**
> https://github.com/hellen228/essalud-api-monolito

------------------------------------------------------------------------

# 1. Equipo de Trabajo

**Integrantes -- Grupo A**

-   Alfaro Buiza, Jesús Alberto
-   Carpio Paiva, Cesar Gonzalo
-   Cornejo Alvarez, Mauricio Andres
-   Diaz Vasquez, Esdras Amado
-   Esteba Feria, Sophia Alejandra
-   Juan de Dios Delgado, Hellen Grace
-   Ticona Pereyra, Erika Daysi
-   Torres Ara, Alberto Gabriel

**Docente:** Edgar Sarmiento Calisaya

------------------------------------------------------------------------

# 2. Descripción del Proyecto

La solución implementa una Living Application sobre Bonita BPM que
permite ejecutar procesos de negocio relacionados con la atención médica
de EsSalud.

Cada proceso está compuesto por formularios, tareas humanas, tareas
automáticas y reglas de negocio que coordinan la interacción entre los
diferentes actores del sistema.

La comunicación con aplicaciones externas se realiza mediante servicios
REST y, cuando es necesario desacoplar procesos, mediante RabbitMQ.

------------------------------------------------------------------------

# 3. Arquitectura de la Solución

``` text
                    Usuario
                       │
                       ▼
             Bonita BPM (Frontend)
      Formularios + BPMN + Living App
                       │
         ┌─────────────┴─────────────┐
         │                           │
         ▼                           ▼
     RabbitMQ                 Servicios REST
  (Mensajería)             (OpenAPI / Swagger)
         │                           │
         └─────────────┬─────────────┘
                       ▼
                 Dominios DDD
```

## Bonita BPM

Bonita constituye la capa de interacción con el usuario y la
orquestación de los procesos. Gestiona formularios, procesos BPMN,
actores, reglas de negocio, variables del proceso, el Business Data
Model (BDM) y la integración con servicios REST y RabbitMQ.

## RabbitMQ

RabbitMQ actúa como broker de mensajería entre procesos. Permite
publicar y consumir eventos sin que los dominios dependan directamente
unos de otros.

Ejemplo:

``` text
Tratamiento Médico
        │
Registrar Prescripción
        │
Publicar receta
        ▼
recetas.queue
        ▼
Farmacia y Dispensación
```

## Domain Driven Design

Los dominios implementados son:

-   Asegurado
-   Acreditación
-   Citas
-   Admisión
-   Historia Clínica
-   Atención Médica
-   Diagnóstico
-   Tratamiento
-   Farmacia

------------------------------------------------------------------------

# 4. Recursos OpenAPI / Swagger

Cada dominio expone servicios REST documentados mediante OpenAPI 3 y
Swagger.

-   AseguradoService
-   AcreditacionService
-   CitaService
-   AdmisionService
-   HistoriaClinicaService
-   AtencionMedicaService
-   DiagnosticoService
-   TratamientoService
-   FarmaciaService

La documentación describe endpoints, métodos HTTP, modelos de datos y
respuestas.

------------------------------------------------------------------------

# 5. Flujo General

``` text
Paciente
    │
Formulario Bonita
    │
Proceso BPMN
    │
Servicio REST
    │
RabbitMQ
    │
Proceso siguiente
    │
Actualización del BDM
```

------------------------------------------------------------------------

# 6. Tecnologías

-   Bonita BPM
-   Java 17
-   Maven
-   RabbitMQ
-   REST
-   OpenAPI / Swagger
-   PostgreSQL
-   Business Data Model (BDM)

------------------------------------------------------------------------

# 7. Procesos

-   Validación de Cobertura
-   Tratamiento Médico
-   Farmacia y Dispensación

Cada proceso integra formularios, tareas automáticas, servicios REST y
comunicación mediante RabbitMQ.

------------------------------------------------------------------------

# 8. Objetivo

La integración entre Bonita BPM, RabbitMQ y los servicios REST permite
construir una solución modular y desacoplada, donde cada dominio
mantiene su responsabilidad y se comunica mediante eventos y APIs
documentadas.

# 9. Code Smells

Se siguieron patrones de Clean Code para evitar los smells como:
``` text
Codigo duplicado
    │
Nombres de variables descriptivos
    │
Comentarios adecuados
    │
Vulnerabilidades 
```

# 10. Testing

Todos los endpoints fueron validados mediante PostMan


# 11. Ejemplo de Proceso: Proceso de Dispensación de Medicamentos – EsSalud

**Archivo:** `app/diagrams/FarmaciayDispensacion-1.0.proc`

**Descripción:**
Gestiona la entrega física de medicamentos al paciente desde la recepción de documentos en ventanilla hasta el cierre de la dispensación, actualizando el inventario en el sistema SIGES/SGH vía conector JDBC a PostgreSQL.

**Actores / Lanes:**
- **Paciente:** Entrega receta y DNI en ventanilla
- **Técnico de Farmacia:** Recepción, picking (recolección), etiquetado y entrega
- **Químico Farmacéutico:** Validación de acreditación, firma/sello, retención de receta
- **Actor Quimico:** Auditoría de fármacos controlados
- **Sistema SIGES / SGH:** Actualización automática del inventario

**Flujo de tareas:**
1. Entregar receta y DNI en ventanilla (`recepcionVentanilla`)
2. Identificar al paciente *[automático]*
3. Validar acreditación *[automático – conector JDBC PostgreSQL]* → conectorSeguro: verifica cobertura
4. **[Gateway]** ¿Acreditación válida?
   - **SÍ** → Consultar stock en kárdex electrónico *[automático – conectorStock]*
     - **[Gateway]** ¿Hay Stock?
       - **SÍ** → Validar firma, sello (`validacionConforme`) → Realizar picking (recolección) de medicamentos (`recoleccionMedicamentos`) → Etiquetar medicamentos (`etiquetarMedicamento`) → Dispensar medicamentos físicos → Registrar salida / Actualizar inventario (conectorUpdate + conectorSalida) → Recibir medicamentos (`entregaCierre`) → Dispensación exitosa / Proceso de atención finalizado
       - **NO** → Sellar receta como PENDIENTE / Medicamento pendiente (`sinStock`)
   - **NO** → Retener receta y notificar auditoría (`validacionDenegada`) → Informar motivo de rechazo (`atencionRechazada`) → Atención rechazada
5. FIN
