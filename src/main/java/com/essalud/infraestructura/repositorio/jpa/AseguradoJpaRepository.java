package com.essalud.infraestructura.repositorio.jpa;

import com.essalud.dominio.asegurado.modelo.Asegurado;
import com.essalud.dominio.asegurado.repositorio.IAseguradoRepositorio;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class AseguradoJpaRepository implements IAseguradoRepositorio {

    private final AseguradoSpringJpaRepository springRepo;

    public AseguradoJpaRepository(AseguradoSpringJpaRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public Asegurado save(Asegurado asegurado) {
        AseguradoEntity entity = AseguradoEntity.fromDomain(asegurado);
        return springRepo.save(entity).toDomain();
    }

    @Override
    public Optional<Asegurado> findById(String id) {
        return springRepo.findById(id).map(AseguradoEntity::toDomain);
    }

    @Override
    public Optional<Asegurado> findByDni(String dni) {
        return springRepo.findByDni(dni).map(AseguradoEntity::toDomain);
    }

    @Override
    public List<Asegurado> findAll() {
        return StreamSupport.stream(springRepo.findAll().spliterator(), false)
                .map(AseguradoEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        springRepo.deleteById(id);
    }
}
