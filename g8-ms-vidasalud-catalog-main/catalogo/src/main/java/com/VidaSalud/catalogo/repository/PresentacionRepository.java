package com.VidaSalud.catalogo.repository;

import com.VidaSalud.catalogo.entity.Presentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentacionRepository extends JpaRepository<Presentacion, Long> {
}

