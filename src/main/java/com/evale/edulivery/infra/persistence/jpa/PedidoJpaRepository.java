package com.evale.edulivery.infra.persistence.jpa;

import com.evale.edulivery.infra.persistence.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoJpaRepository extends JpaRepository<PedidoEntity, Long> {
}
