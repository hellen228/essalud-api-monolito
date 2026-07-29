package com.essalud.infraestructura.repositorio.jpa;

import com.essalud.dominio.asegurado.modelo.Asegurado;
import com.essalud.dominio.asegurado.modelo.EstadoAfiliacion;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.LocalDate;

@Entity
@Table(name = "asegurados")
public class AseguradoEntity {

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "dni", nullable = false, length = 8)
    private String dni;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "seguro_activo", nullable = false)
    private Boolean seguroActivo;

    @Column(name = "apellidos", length = 100)
    private String apellidos;

    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "sexo", length = 1)
    private String sexo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estadoAfiliacion", length = 50)
    private EstadoAfiliacion estadoAfiliacion;

    @Column(name = "fechaAfiliacion")
    private LocalDate fechaAfiliacion;

    public AseguradoEntity() {
    }

    public static AseguradoEntity fromDomain(Asegurado asegurado) {
        AseguradoEntity entity = new AseguradoEntity();
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
        asegurado.setDni(this.dni);
        asegurado.setNombres(this.nombres);
        asegurado.setApellidos(this.apellidos);
        asegurado.setFechaNacimiento(this.fechaNacimiento);
        asegurado.setSexo(this.sexo);
        asegurado.setEstadoAfiliacion(this.estadoAfiliacion);
        asegurado.setFechaAfiliacion(this.fechaAfiliacion);
        return asegurado;
    }

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

    public LocalDate getFechaAfiliacion() { return fechaAfiliacion; }
    public void setFechaAfiliacion(LocalDate fechaAfiliacion) { this.fechaAfiliacion = fechaAfiliacion; }
}
