package com.evale.edulivery.application.dto;

import com.evale.edulivery.domain.model.Pedido;
import java.math.BigDecimal;

public record PedidoResponse(Long id, String cliente, String status, BigDecimal valor,
                             Pedido.TipoNotificacao preferenciaNotificacao) {
    public static PedidoResponse fromDomain(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getCliente(),
                pedido.getStatus(),
                pedido.getValor(),
                pedido.getPreferenciaNotificacao()
        );
    }
}
