package com.essalud.infraestructura.repositorio.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AseguradoSpringJpaRepository extends CrudRepository<AseguradoEntity, Long> {
    Optional<AseguradoEntity> findByDni(String dni);
}
