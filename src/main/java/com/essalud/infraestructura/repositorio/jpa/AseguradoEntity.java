package com.essalud.infraestructura.repositorio.jpa;

import com.essalud.dominio.asegurado.modelo.Asegurado;
import com.essalud.dominio.asegurado.modelo.EstadoAfiliacion;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asegurados")
public class AseguradoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false, length = 1)
    private String sexo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_afiliacion", nullable = false)
    private EstadoAfiliacion estadoAfiliacion;

    @Column(name = "fecha_afiliacion", nullable = false)
    private LocalDateTime fechaAfiliacion;

    public AseguradoEntity() {
    }

    public static AseguradoEntity fromDomain(Asegurado asegurado) {
        AseguradoEntity entity = new AseguradoEntity();
        entity.setId(asegurado.getId());
        entity.setDni(asegurado.getDni());
        entity.setNombres(asegurado.getNombres());
        entity.setApellidos(asegurado.getApellidos());
        entity.setFechaNacimiento(asegurado.getFechaNacimiento());
        entity.setSexo(asegurado.getSexo());
        entity.setEstadoAfiliacion(asegurado.getEstadoAfiliacion());
        entity.setFechaAfiliacion(asegurado.getFechaAfiliacion());
        return entity;
    }

    public Asegurado toDomain() {
        Asegurado asegurado = new Asegurado();
        asegurado.setId(this.id);
        asegurado.setDni(this.dni);
        asegurado.setNombres(this.nombres);
        asegurado.setApellidos(this.apellidos);
        asegurado.setFechaNacimiento(this.fechaNacimiento);
        asegurado.setSexo(this.sexo);
        asegurado.setEstadoAfiliacion(this.estadoAfiliacion);
        asegurado.setFechaAfiliacion(this.fechaAfiliacion);
        return asegurado;
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
