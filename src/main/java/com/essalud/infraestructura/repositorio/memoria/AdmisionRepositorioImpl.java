package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.admision.modelo.Admision;
import com.essalud.dominio.admision.repositorio.IAdmisionRepositorio;
import com.essalud.infraestructura.repositorio.jpa.AdmisionJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdmisionRepositorioImpl implements IAdmisionRepositorio {

    private final AdmisionJpaRepository jpaRepository;

    public AdmisionRepositorioImpl(AdmisionJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Admision guardar(Admision admision) {
        return jpaRepository.save(admision);
    }

    @Override
    public Optional<Admision> buscarPorId(Integer id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Admision> buscarPorAsegurado(Integer aseguradoId) {
        return jpaRepository.findByAseguradoId(aseguradoId);
    }

    @Override
    public List<Admision> listarTodos() {
        return jpaRepository.findAll();
    }
}