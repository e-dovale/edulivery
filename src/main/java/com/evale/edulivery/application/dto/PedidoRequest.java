package com.evale.edulivery.application.dto;

import com.evale.edulivery.domain.model.Pedido;
import java.math.BigDecimal;

public record PedidoRequest (
    String cliente,
    String email,
    String telefone,
    BigDecimal valor,
    Pedido.TipoNotificacao preferenciaNotificacao
) {
    public Pedido toDomain() {
        return new Pedido(cliente, email, telefone, valor, preferenciaNotificacao);
    }
}
