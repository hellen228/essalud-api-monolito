package com.essalud.dominio.asegurado.modelo;

<<<<<<< HEAD
public class Asegurado {
    private Integer id;
    private String dni;
    private boolean seguroActivo;

    public Asegurado() {}

    public Asegurado(Integer id, String dni, boolean seguroActivo) {
        this.id = id;
        this.dni = dni;
        this.seguroActivo = seguroActivo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public boolean isSeguroActivo() { return seguroActivo; }
    public void setSeguroActivo(boolean seguroActivo) { this.seguroActivo = seguroActivo; }
}
=======
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Asegurado {
    private Long id;
    private String dni;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String sexo;
    private EstadoAfiliacion estadoAfiliacion;
    private LocalDateTime fechaAfiliacion;

    public Asegurado() {
    }

    public Asegurado(String dni, String nombres, String apellidos,
                     LocalDate fechaNacimiento, String sexo) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.estadoAfiliacion = EstadoAfiliacion.PENDIENTE;
        this.fechaAfiliacion = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public EstadoAfiliacion getEstadoAfiliacion() { return estadoAfiliacion; }
    public void setEstadoAfiliacion(EstadoAfiliacion estadoAfiliacion) { this.estadoAfiliacion = estadoAfiliacion; }

    public LocalDateTime getFechaAfiliacion() { return fechaAfiliacion; }
    public void setFechaAfiliacion(LocalDateTime fechaAfiliacion) { this.fechaAfiliacion = fechaAfiliacion; }
}
>>>>>>> feature/asegurado-service
