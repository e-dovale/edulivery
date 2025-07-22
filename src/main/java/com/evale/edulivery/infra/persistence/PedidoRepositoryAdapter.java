package com.evale.edulivery.infra.persistence;

import com.evale.edulivery.domain.model.Pedido;
import com.evale.edulivery.infra.persistence.entities.PedidoEntity;
import com.evale.edulivery.infra.persistence.jpa.PedidoJpaRepository;
import org.springframework.stereotype.Component;

import com.evale.edulivery.domain.ports.PedidoRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final PedidoJpaRepository jpaRepository;
    private final PedidoMapper mapper;

    public PedidoRepositoryAdapter(PedidoJpaRepository jpaRepository, PedidoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Pedido save(Pedido pedido) {

        PedidoEntity entity = mapper.toEntity(pedido);

        PedidoEntity savedEntity = jpaRepository.save(entity);

        System.out.println("Id gerado: " + entity.getId());

        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {

        System.out.println("Buscando no banco o ID: " + id);
        Optional<PedidoEntity> entity = jpaRepository.findById(id);
        entity.ifPresentOrElse(
                e -> System.out.println("Entidade encontrada: " + e),
                () -> System.out.println("Nenhuma entidade encontrada para ID: " + id)
        );
        return entity.map(mapper::toDomain);
    }
}
