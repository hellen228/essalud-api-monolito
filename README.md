# **EsSalud (Seguro Social de Salud del Perú)**.  
---

##  Arquitectura del Sistema

La arquitectura propuesta se basa en **Domain‑Driven Design (DDD)** combinado con una **Arquitectura en Capas** tradicional. Esto permite:

- **Modelar el dominio** de forma rica y cercana al negocio.
- **Separar preocupaciones** (presentación, lógica de negocio, acceso a datos).
- **Facilitar la evolución** del sistema ante cambios en los requisitos.

### Diagrama de Capas

<img width="601" height="734" alt="imagen" src="https://github.com/user-attachments/assets/5393456e-17b7-46cc-8005-31ac452a39a9" />


### Subsistemas Identificados

A partir del análisis de los procesos de negocio, se definieron los siguientes subsistemas/servicios principales:

| Subsistema          | Descripción                                                                 |
|---------------------|-----------------------------------------------------------------------------|
| **AseguradoService**   | Gestión de afiliación, validación de identidad y perfil de asegurados.      |
| **AcreditacionService**| Auditoría de cobertura, vigencia de aportes y autorización de atención.     |
| **CitaService**        | Programación de citas, gestión de agendas y reservas.                       |
| **AdmisionService**    | Admisión de pacientes (hospitalización y emergencia), asignación de camas.  |
| **HistoriaClinicaService**| Apertura y gestión del historial clínico electrónico.                   |
| **AtencionMedicaService**| Registro de la consulta médica, signos vitales y anamnesis.               |
| **DiagnosticoService** | Gestión de exámenes auxiliares y diagnóstico clínico.                       |
| **TratamientoService** | Plan terapéutico, prescripciones y órdenes electrónicas.                    |
| **FarmaciaService**    | Dispensación de medicamentos y control de inventario.                       |

Cada subsistema expone un conjunto de **funcionalidades** y **operaciones** (detalladas en el [Portafolio de Servicios](#portafolio-de-servicios)).

---

##  Tecnologías Utilizadas (Propuesta de Implementación)

Para una futura implementación, se sugiere el siguiente stack tecnológico:

- **Backend:** Java 17 + Spring Boot 3 (Spring MVC, Spring Data JPA, Spring Security)
- **Base de Datos:** PostgreSQL 15
- **ORM:** Hibernate
- **Pruebas:** JUnit 5, Mockito
- **Gestión de Dependencias:** Maven

---

##  Portafolio de Servicios

A continuación se listan los subsistemas con sus funcionalidades y operaciones principales (basadas en el modelo de datos propuesto).

<details>
<summary><strong>AseguradoService</strong></summary>

- **Funcionalidad:** Gestionar Solicitud de Afiliación
  - `RegistrarSolicitudAfiliacion(dni: String)`
  - `ValidarIdentidadReniec(dni: String): Boolean`
  - `VerificarRequisitosLegales(dni: String): Boolean`
- **Funcionalidad:** Gestionar Perfil de Asegurado
  - `CrearAsegurado(dni, nombres, apellidos, fechaNacimiento, sexo)`
  - `ObtenerDatosAsegurado(dni: String): Asegurado`
  - `ActualizarEstadoAfiliacion(idAsegurado: Integer, estado: Boolean)`
</details>

<details>
<summary><strong>AcreditacionService</strong></summary>

- **Funcionalidad:** Auditar Acreditación
  - `ConsultarVigenciaSunat(dni: String): Boolean`
  - `VerificarHistorialAportes(idAsegurado: Integer): Boolean`
  - `AuditarCartaGarantia(idAsegurado: Integer): Boolean`
- **Funcionalidad:** Gestionar Autorización de Atención
  - `AprobarCoberturaPaciente(idAsegurado: Integer)`
  - `RechazarCoberturaPaciente(idAsegurado: Integer, motivo: String)`
  - `NotificarEstadoAcreditacion(idAsegurado: Integer)`
</details>

<details>
<summary><strong>CitaService</strong></summary>

- **Funcionalidad:** Gestionar Agenda Médica
  - `ConsultarDisponibilidadMedico(idMedico, fecha): List<Horario>`
  - `ConsultarHorariosPorEspecialidad(idEspecialidad, fecha): List<Horario>`
  - `BloquearHorarioReserva(idMedico, fechaHora)`
- **Funcionalidad:** Gestionar Reservas de Pacientes
  - `ReservarTurnoMedico(idAsegurado, idMedico, fechaHora): Cita`
  - `GenerarConstanciaCita(idCita): Constancia`
  - `CancelarCitaProgramada(idCita)`
  - `ReprogramarCitaMedica(idCita, nuevaFechaHora)`
</details>

<details>
<summary><strong>AdmisionService</strong></summary>

- **Funcionalidad:** Gestionar Ingreso Hospitalario
  - `RegistrarIngresoPaciente(dni, idCita)`
  - `VerificarCoberturaSistemaHis(idAsegurado): Boolean`
- **Funcionalidad:** Gestionar Emergencia y Triaje
  - `RegistrarIngresoEmergencia(idAsegurado)`
  - `ClasificarPrioridadTriaje(idAdmision, prioridad)`
- **Funcionalidad:** Gestionar Identidad en Centro
  - `ActivarHistoriaClinicaAtencion(idAsegurado): Integer`
  - `GenerarDatosPulseraIdentificacion(idAdmision): String`
  - `AsignarCamaOSala(idAdmision, idSala, idCama)`
</details>

<details>
<summary><strong>HistoriaClinicaService</strong></summary>

- **Funcionalidad:** Gestionar Expediente Médico
  - `AperturarHistoriaClinica(idAsegurado, grupoSanguineo): HistoriaClinica`
  - `ObtenerHistorialPorPaciente(idAsegurado): HistoriaClinica`
  - `ConsultarAntecedentesFamiliares(idHistoria): List<Antecedente>`
  - `ConsultarAlergiasPaciente(idHistoria): List<Alergia>`
</details>

<details>
<summary><strong>AtencionMedicaService</strong></summary>

- **Funcionalidad:** Gestionar Consulta Actual
  - `IniciarAtencionMedica(idAdmision, idMedico): Atencion`
  - `RegistrarSignosVitales(idAtencion, signosVitales)`
  - `RegistrarAnamnesisSintomas(idAtencion, sintomas)`
  - `RegistrarExamenFisico(idAtencion, examen)`
  - `CerrarAtencionMedica(idAtencion)`
</details>

<details>
<summary><strong>DiagnosticoService</strong></summary>

- **Funcionalidad:** Gestionar Pruebas y Evidencia
  - `GenerarOrdenLaboratorio(idAtencion, tipoExamen)`
  - `GenerarOrdenRayosX(idAtencion, zonaCuerpo)`
  - `VisualizarResultadosExamen(idOrden): String`
- **Funcionalidad:** Gestionar Cuadro Clínico
  - `AsignarDiagnosticoCIE10(idAtencion, codigoCie10, descripcion)`
  - `ActualizarEstadoEnfermedad(idDiagnostico, estado)`
</details>

<details>
<summary><strong>TratamientoService</strong></summary>

- **Funcionalidad:** Gestionar Plan Terapéutico
  - `CrearPlanTerapeutico(idDiagnostico, indicaciones): PlanTerapeutico`
  - `RegistrarPrescripcionMedica(idPlan, idMedicamento, dosis, frecuencia, duracion)`
  - `GenerarOrdenElectronica(idPlan)`
  - `DerivarOrdenEspecialidad(idAtencion, idEspecialidad)`
</details>

<details>
<summary><strong>FarmaciaService</strong></summary>

- **Funcionalidad:** Gestionar Recetas
  - `ObtenerRecetaElectronica(idPrescripcion): Prescripcion`
  - `ValidarVigenciaReceta(idPrescripcion): Boolean`
  - `AuditarFirmaFarmacoControlado(idMedicamento, idMedico): Boolean`
- **Funcionalidad:** Gestionar Dispensación e Inventario
  - `ConsultarStockKardex(idMedicamento): Integer`
  - `RegistrarDispensacionMedicamento(idPrescripcion)`
  - `ActualizarInventarioAutomatico(idMedicamento, cantidadRetirada)`
</details>

---

##  Instrucciones para Levantar el Proyecto Localmente

Siga los pasos a continuación para configurar y ejecutar el entorno de desarrollo en su máquina local.

### Requisitos Previos

Asegúrese de tener instalado:

- **Java 17** o superior ([Descargar](https://adoptium.net/))
- **Maven 3.8+** ([Descargar](https://maven.apache.org/download.cgi))
- **Docker** y **Docker Compose** ([Descargar](https://www.docker.com/products/docker-desktop))
- **Git** ([Descargar](https://git-scm.com/downloads))

### Paso 1: Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/essalud-services.git
cd essalud-services
