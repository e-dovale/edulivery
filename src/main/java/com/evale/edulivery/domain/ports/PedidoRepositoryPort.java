package com.evale.edulivery.domain.ports;

import com.evale.edulivery.domain.model.Pedido;

import java.util.Optional;

public interface PedidoRepositoryPort {

    Pedido save(Pedido pedido);
    Optional<Pedido> buscarPorId(Long id);
}
