# EsSalud – Servicios Web REST

Implementación de los servicios web REST que soportan las tareas automáticas de los procesos de negocio de **EsSalud (Seguro Social de Salud del Perú)**, dentro de una Aplicación BPM distribuida y guiada por eventos.

> 🔗 Repositorio hermano (procesos BPMN en Bonitasoft): `<https://github.com/saph1r0/Essalud-BonitaSoft/tree/master>`
> 🔗 Tablero de gestión (GitHub Projects / Trello): `<https://github.com/users/hellen228/projects/3>'

---

## 👥 Equipo de Trabajo

**Curso:** Desarrollo de Software Empresarial — Universidad Nacional de San Agustín
**Docente:** Edgar Sarmiento Calisaya
**Grupo A**

| Integrante | Proceso de negocio a cargo | Servicio (subsistema) |
|---|---|---|
| Diaz Vasquez, Esdras Amado | Afiliación de asegurados | `AseguradoService` |
| Juan de Dios Delgado, Hellen Grace | Validación de cobertura | `AcreditacionService` |
| Ticona Pereyra, Erika Daysi | Programación de citas médicas | `CitaService` |
| Cornejo Alvarez, Mauricio Andres | Admisión del paciente | `AdmisionService` + `HistoriaClinicaService` |
| Carpio Paiva, Cesar Gonzalo | Atención médica general | `AtencionMedicaService` |
| Torres Ara, Alberto Gabriel | Diagnóstico clínico | `DiagnosticoService` |
| Alfaro Buiza, Jesús Alberto | Tratamiento médico | `TratamientoService` |
| Esteba Feria, Sophia Alejandra | Farmacia y dispensación | `FarmaciaService` |

Cada integrante es dueño de su módulo de dominio completo (entidad, repositorio, servicio de aplicación, controlador REST y pruebas), y participa además en la integración de su proceso vía eventos (RabbitMQ) con el repositorio BPMN.

---

## 🎯 Propósito del Proyecto

Brindar el soporte transaccional (servicios REST) a la Aplicación BPM de EsSalud, permitiendo automatizar y trazar de punta a punta el recorrido de un asegurado: desde su afiliación hasta la dispensación de medicamentos, pasando por validación de cobertura, programación de citas, admisión, atención médica, diagnóstico y tratamiento. El diseño prioriza bajo acoplamiento y alta cohesión entre dominios, siguiendo **Domain-Driven Design (DDD)** y una **Arquitectura en Capas**, de modo que cada subsistema pueda evolucionar, probarse y desplegarse de forma independiente.

---

## 🏗️ Visión General de Arquitectura

La arquitectura combina **DDD** con **Arquitectura en Capas**, y cada dominio se comunica con los demás únicamente a través de interfaces (repositorios) o eventos — nunca invocando directamente las clases internas de otro compañero.


<img width="1202" height="1468" alt="imagen" src="https://github.com/user-attachments/assets/4f58be5c-08fb-4c37-97cf-d4bee33b6f84" />


### Elementos DDD aplicados por módulo

| Bloque DDD | Dónde vive | Ejemplo |
|---|---|---|
| **Entidad** | `dominio/<contexto>/modelo` | `Asegurado`, `Cita`, `Diagnostico` |
| **Objeto de Valor** | `dominio/<contexto>/modelo/vo` | `Dni`, `RangoHorario`, `CodigoCie10` |
| **Servicio de Dominio** | `dominio/<contexto>/servicio` | reglas que no pertenecen a una sola entidad (ej. validar compatibilidad horario-especialidad) |
| **Agregado** | Entidad raíz + entidades internas | `Admision` como raíz agrega `Triaje`, `AsignacionCama` |
| **Repositorio (interfaz)** | `dominio/<contexto>/repositorio` | `IAseguradoRepositorio` |
| **Módulo** | Paquete por contexto delimitado (`asegurado`, `cita`, `farmacia`, ...) | Bounded Context = paquete Java |



### Subsistemas identificados

| Subsistema | Descripción |
|---|---|
| **AseguradoService** | Gestión de afiliación, validación de identidad y perfil de asegurados. |
| **AcreditacionService** | Auditoría de cobertura, vigencia de aportes y autorización de atención. |
| **CitaService** | Programación de citas, gestión de agendas y reservas. |
| **AdmisionService** | Admisión de pacientes (hospitalización y emergencia), asignación de camas. |
| **HistoriaClinicaService** | Apertura y gestión del historial clínico electrónico. |
| **AtencionMedicaService** | Registro de la consulta médica, signos vitales y anamnesis. |
| **DiagnosticoService** | Gestión de exámenes auxiliares y diagnóstico clínico. |
| **TratamientoService** | Plan terapéutico, prescripciones y órdenes electrónicas. |
| **FarmaciaService** | Dispensación de medicamentos y control de inventario. |

---

## 🔌 Diagrama de Composición de Servicios mediados por Procesos de Negocio



---

## 🛠️ Tecnologías Utilizadas

- **Backend:** Java 17 + Spring Boot 3 (Spring MVC, Spring Data JPA, Spring Security)
- **Base de Datos:** PostgreSQL 15
- **ORM:** Hibernate
- **Mensajería / Broker de Eventos:** RabbitMQ (AMQP)
- **Pruebas:** JUnit 5, Mockito (dobles de prueba / mocks para repositorios y adaptadores externos), pruebas BDD (Given/When/Then) en Postman
- **Documentación de API:** OpenAPI 3 + Swagger UI (`springdoc-openapi`)
- **Gestión de Dependencias:** Maven
- **Contenerización:** Docker / Docker Compose (Postgres + RabbitMQ)
- **Control de Versiones:** Git / GitHub (branches `master`, `desarrollo`, `feature/*`)
- **Gestión de Tareas:** GitHub Projects (Kanban)

---

## 📦 Portafolio de Servicios (OpenAPI: Recurso · Operaciones · Modelos)

> Documentación completa y probable en vivo vía Swagger UI: `http://localhost:8080/swagger-ui.html`

<details>
<summary><strong>AseguradoService</strong> — Gestión de afiliación y perfil del asegurado</summary>

| Operación | Método | URL | Parámetros |
|---|---|---|---|
| RegistrarSolicitudAfiliacion | `POST` | `/api/v1/asegurados/solicitudes` | `dni: String` |
| ValidarIdentidadReniec | `GET` | `/api/v1/asegurados/{dni}/validacion-reniec` | `dni: String` |
| VerificarRequisitosLegales | `GET` | `/api/v1/asegurados/{dni}/requisitos` | `dni: String` |
| CrearAsegurado | `POST` | `/api/v1/asegurados` | `dni, nombres, apellidos, fechaNacimiento, sexo` |
| ObtenerDatosAsegurado | `GET` | `/api/v1/asegurados/{dni}` | `dni: String` |
| ActualizarEstadoAfiliacion | `PATCH` | `/api/v1/asegurados/{idAsegurado}/estado` | `idAsegurado: Integer, estado: Boolean` |

**Modelos:** Entidad `Asegurado` (agregado raíz) · VO `Dni` · Repositorio `IAseguradoRepositorio`
</details>

<details>
<summary><strong>AcreditacionService</strong> — Auditoría de cobertura y autorización de atención</summary>

| Operación | Método | URL | Parámetros |
|---|---|---|---|
| ConsultarVigenciaSunat | `GET` | `/api/v1/acreditaciones/sunat/{dni}` | `dni: String` |
| VerificarHistorialAportes | `GET` | `/api/v1/acreditaciones/{idAsegurado}/aportes` | `idAsegurado: Integer` |
| AuditarCartaGarantia | `GET` | `/api/v1/acreditaciones/{idAsegurado}/carta-garantia` | `idAsegurado: Integer` |
| AprobarCoberturaPaciente | `POST` | `/api/v1/acreditaciones/{idAsegurado}/aprobar` | `idAsegurado: Integer` |
| RechazarCoberturaPaciente | `POST` | `/api/v1/acreditaciones/{idAsegurado}/rechazar` | `idAsegurado: Integer, motivo: String` |
| NotificarEstadoAcreditacion | `POST` | `/api/v1/acreditaciones/{idAsegurado}/notificar` | `idAsegurado: Integer` |

**Modelos:** Entidad `Cobertura` · Repositorio `ICoberturaRepositorio` · Adaptador `SunatServiceAdapter`
</details>

<details>
<summary><strong>CitaService</strong> — Agenda médica y reservas</summary>

| Operación | Método | URL | Parámetros |
|---|---|---|---|
| ConsultarDisponibilidadMedico | `GET` | `/api/v1/citas/disponibilidad` | `idMedico, fecha` (query) |
| ConsultarHorariosPorEspecialidad | `GET` | `/api/v1/citas/horarios` | `idEspecialidad, fecha` (query) |
| BloquearHorarioReserva | `POST` | `/api/v1/citas/bloqueo` | `idMedico, fechaHora` |
| ReservarTurnoMedico | `POST` | `/api/v1/citas` | `idAsegurado, idMedico, fechaHora` |
| GenerarConstanciaCita | `GET` | `/api/v1/citas/{idCita}/constancia` | `idCita: Integer` |
| CancelarCitaProgramada | `DELETE` | `/api/v1/citas/{idCita}` | `idCita: Integer` |
| ReprogramarCitaMedica | `PUT` | `/api/v1/citas/{idCita}/reprogramacion` | `idCita, nuevaFechaHora` |

**Modelos:** Entidad `Cita` · VO `RangoHorario` · Repositorio `ICitaRepositorio` · DTO `CitaRequestDTO`
</details>

<details>
<summary><strong>AdmisionService</strong> — Ingreso hospitalario, emergencia y triaje</summary>

| Operación | Método | URL | Parámetros |
|---|---|---|---|
| RegistrarIngresoPaciente | `POST` | `/api/v1/admisiones` | `dni, idCita` |
| VerificarCoberturaSistemaHis | `GET` | `/api/v1/admisiones/cobertura/{idAsegurado}` | `idAsegurado: Integer` |
| RegistrarIngresoEmergencia | `POST` | `/api/v1/admisiones/emergencia` | `idAsegurado: Integer` |
| ClasificarPrioridadTriaje | `PATCH` | `/api/v1/admisiones/{idAdmision}/triaje` | `idAdmision, prioridad` |
| ActivarHistoriaClinicaAtencion | `POST` | `/api/v1/admisiones/{idAdmision}/historia-clinica` | `idAsegurado: Integer` |
| GenerarDatosPulseraIdentificacion | `GET` | `/api/v1/admisiones/{idAdmision}/pulsera` | `idAdmision: Integer` |
| AsignarCamaOSala | `PATCH` | `/api/v1/admisiones/{idAdmision}/cama` | `idSala, idCama` |

**Modelos:** Agregado `Admision` (raíz) con `Triaje` y `AsignacionCama` como entidades internas · Repositorio `IAdmisionRepositorio`
</details>

<details>
<summary><strong>HistoriaClinicaService</strong> — Expediente médico electrónico</summary>

| Operación | Método | URL | Parámetros |
|---|---|---|---|
| AperturarHistoriaClinica | `POST` | `/api/v1/historias-clinicas` | `idAsegurado, grupoSanguineo` |
| ObtenerHistorialPorPaciente | `GET` | `/api/v1/historias-clinicas/paciente/{idAsegurado}` | `idAsegurado: Integer` |
| ConsultarAntecedentesFamiliares | `GET` | `/api/v1/historias-clinicas/{idHistoria}/antecedentes` | `idHistoria: Integer` |
| ConsultarAlergiasPaciente | `GET` | `/api/v1/historias-clinicas/{idHistoria}/alergias` | `idHistoria: Integer` |

**Modelos:** Entidad `HistoriaClinica` · Repositorio `IHistoriaClinicaRepositorio`
</details>

<details>
<summary><strong>AtencionMedicaService</strong> — Consulta médica actual</summary>

| Operación | Método | URL | Parámetros |
|---|---|---|---|
| IniciarAtencionMedica | `POST` | `/api/v1/atenciones` | `idAdmision, idMedico` |
| RegistrarSignosVitales | `POST` | `/api/v1/atenciones/{idAtencion}/signos-vitales` | `signosVitales: String` |
| RegistrarAnamnesisSintomas | `POST` | `/api/v1/atenciones/{idAtencion}/anamnesis` | `sintomas: String` |
| RegistrarExamenFisico | `POST` | `/api/v1/atenciones/{idAtencion}/examen-fisico` | `examen: String` |
| CerrarAtencionMedica | `PATCH` | `/api/v1/atenciones/{idAtencion}/cierre` | `idAtencion: Integer` |

**Modelos:** Entidad `AtencionMedica` · Repositorio `IAtencionMedicaRepositorio`
</details>

<details>
<summary><strong>DiagnosticoService</strong> — Exámenes auxiliares y diagnóstico</summary>

| Operación | Método | URL | Parámetros |
|---|---|---|---|
| GenerarOrdenLaboratorio | `POST` | `/api/v1/diagnosticos/ordenes/laboratorio` | `idAtencion, tipoExamen` |
| GenerarOrdenRayosX | `POST` | `/api/v1/diagnosticos/ordenes/rayos-x` | `idAtencion, zonaCuerpo` |
| VisualizarResultadosExamen | `GET` | `/api/v1/diagnosticos/ordenes/{idOrden}/resultados` | `idOrden: Integer` |
| AsignarDiagnosticoCIE10 | `POST` | `/api/v1/diagnosticos` | `idAtencion, codigoCie10, descripcion` |
| ActualizarEstadoEnfermedad | `PATCH` | `/api/v1/diagnosticos/{idDiagnostico}/estado` | `estado: String` |

**Modelos:** Entidad `Diagnostico` · VO `CodigoCie10` · Repositorio `IDiagnosticoRepositorio`
</details>

<details>
<summary><strong>TratamientoService</strong> — Plan terapéutico y órdenes</summary>

| Operación | Método | URL | Parámetros |
|---|---|---|---|
| CrearPlanTerapeutico | `POST` | `/api/v1/tratamientos/planes` | `idDiagnostico, indicaciones` |
| RegistrarPrescripcionMedica | `POST` | `/api/v1/tratamientos/planes/{idPlan}/prescripciones` | `idMedicamento, dosis, frecuencia, duracion` |
| GenerarOrdenElectronica | `POST` | `/api/v1/tratamientos/planes/{idPlan}/orden-electronica` | `idPlan: Integer` |
| DerivarOrdenEspecialidad | `POST` | `/api/v1/tratamientos/{idAtencion}/derivacion` | `idEspecialidad: Integer` |

**Modelos:** Agregado `PlanTerapeutico` (raíz) con `Prescripcion` como entidad interna · Repositorio `ITratamientoRepositorio`
</details>

<details>
<summary><strong>FarmaciaService</strong> — Dispensación e inventario</summary>

| Operación | Método | URL | Parámetros |
|---|---|---|---|
| ObtenerRecetaElectronica | `GET` | `/api/v1/farmacia/recetas/{idPrescripcion}` | `idPrescripcion: Integer` |
| ValidarVigenciaReceta | `GET` | `/api/v1/farmacia/recetas/{idPrescripcion}/vigencia` | `idPrescripcion: Integer` |
| AuditarFirmaFarmacoControlado | `GET` | `/api/v1/farmacia/medicamentos/{idMedicamento}/firma` | `idMedico` (query) |
| ConsultarStockKardex | `GET` | `/api/v1/farmacia/medicamentos/{idMedicamento}/stock` | `idMedicamento: Integer` |
| RegistrarDispensacionMedicamento | `POST` | `/api/v1/farmacia/dispensaciones` | `idPrescripcion: Integer` |
| ActualizarInventarioAutomatico | `PATCH` | `/api/v1/farmacia/medicamentos/{idMedicamento}/inventario` | `cantidadRetirada: Integer` |

**Modelos:** Entidad `Medicamento` · Repositorio `IMedicamentoRepositorio`
</details>

---

## 📂 Estructura del Proyecto
<img width="426" height="751" alt="imagen" src="https://github.com/user-attachments/assets/56ba5d38-1ee2-4259-8669-bfd2e691bf0a" />
<img width="546" height="741" alt="imagen" src="https://github.com/user-attachments/assets/1b583a17-b198-4635-9c12-f0cffe239109" />


Cada subsistema sigue exactamente este mismo patrón de carpetas dentro de `dominio/<contexto>`.

---

## 🧪 Testing y Calidad

- **Pruebas unitarias:** JUnit 5 + Mockito. Cada servicio de aplicación se prueba con **mocks** de sus repositorios y de los repositorios de otros dominios de los que depende (para no acoplar el desarrollo entre integrantes mientras cada uno avanza en paralelo).
- **Pruebas de aceptación (BDD):** una colección Postman por feature en `/Pruebas de API`, con estructura `Given` (pre-request) → `When` (request al endpoint) → `Then` (asserts post-response).
  <img width="1283" height="933" alt="imagen" src="https://github.com/user-attachments/assets/d8ae0cdb-2c73-4264-81e1-fd86bcbd4d44" />

- **Calidad de código:** se recomienda correr un análisis estático (SonarLint/SonarQube) antes de cada PR a `desarrollo`, manteniendo severidad máxima *Minor/Info* para el puntaje máximo del rubro.

---

## 🚀 Instrucciones para Levantar el Proyecto Localmente

### Requisitos Previos

- **Java 17** o superior ([Descargar](https://adoptium.net/))
- **Maven 3.8+** ([Descargar](https://maven.apache.org/download.cgi))
- **Docker** y **Docker Compose** ([Descargar](https://www.docker.com/products/docker-desktop))
- **Git** ([Descargar](https://git-scm.com/downloads))

### Paso 1: Clonar el Repositorio

```bash
git clone https://github.com/<tu-organizacion>/essalud-services.git
cd essalud-services
```

### Paso 2: Levantar PostgreSQL y RabbitMQ con Docker Compose

```bash
docker-compose up -d
```

Esto levanta:
- **PostgreSQL** en `localhost:5432` (crear BD `essalud_db`)
- **RabbitMQ** en `localhost:5672` (panel de administración en `localhost:15672`, usuario/clave por defecto `guest`/`guest`)

### Paso 3: Configurar variables de entorno / `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/essalud_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update

spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
```

### Paso 4: Compilar y ejecutar

```bash
mvn clean install
mvn spring-boot:run
```

La API quedará disponible en `http://localhost:8080` y la documentación interactiva en `http://localhost:8080/swagger-ui.html`.

### Paso 5: Ejecutar las pruebas

```bash
mvn test
```

### Paso 6: Ejecutar la colección Postman

Importar `Pruebas de API/*.json` en Postman (o ejecutar vía Newman CLI) contra el entorno local.

---

## 🌿 Flujo de Trabajo con Git

- `master`: código estable, listo para entrega.
- `desarrollo`: integración continua de features.
- `feature/<servicio>-<nombre>`: una rama por integrante/servicio (ver tabla de equipo).

```
feature/xxx → desarrollo → master   (merge, vía Pull Request)
master → desarrollo → feature/xxx   (sincronización, vía rebase/merge)
```

**Buenas prácticas de commits:** mensajes descriptivos en modo imperativo (`feat: agrega endpoint de reserva de cita`, `fix: corrige validación de dni nulo`, `test: agrega pruebas unitarias de AcreditacionService`).

---

## 📋 Gestión de Tareas

El seguimiento de historias de usuario, mejoras y correcciones se gestiona en **GitHub Projects** (tablero Kanban: `Backlog → To Do → In Progress → In Review → Done`), organizado por Sprints/Milestones. Ver tablero: `<URL del board aquí>`

---

## 📄 Licencia

Proyecto académico desarrollado para el curso de Desarrollo de Software Empresarial, UNSA — sin fines comerciales. Datos de EsSalud usados con fines educativos, referenciados de [essalud.gob.pe](https://www.essalud.gob.pe) y [gob.pe/essalud](https://www.gob.pe/essalud).
