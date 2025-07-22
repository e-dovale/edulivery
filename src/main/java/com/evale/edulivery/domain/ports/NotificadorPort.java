package com.evale.edulivery.domain.ports;

import com.evale.edulivery.domain.model.Pedido;

public interface NotificadorPort {

    boolean suporta(Pedido.TipoNotificacao tipoNotificacao);
    void notificar(Pedido pedido);
}
