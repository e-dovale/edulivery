package com.evale.edulivery.infra.persistence;

import com.evale.edulivery.domain.model.Pedido;
import com.evale.edulivery.infra.persistence.entities.PedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public PedidoEntity toEntity(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity();
        entity.setId(pedido.getId());
        entity.setCliente(pedido.getCliente());
        entity.setEmail(pedido.getEmail());
        entity.setTelefone(pedido.getTelefone());
        entity.setValor(pedido.getValor());
        entity.setStatus(pedido.getStatus());
        entity.setDataConfirmacao(pedido.getDataConfirmacao());
        entity.setPreferenciaNotificacao(pedido.getPreferenciaNotificacao());
        return entity;
    }

    public Pedido toDomain(PedidoEntity entity) {
        Pedido pedido = new Pedido(
                entity.getCliente(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getValor(),
                entity.getPreferenciaNotificacao()
        );

        pedido.setId(entity.getId());
        pedido.setStatus(entity.getStatus());
        pedido.setDataConfirmacao(entity.getDataConfirmacao());
        return pedido;

    }
}
