package com.evale.edulivery.application;

import com.evale.edulivery.application.dto.PedidoRequest;
import com.evale.edulivery.domain.model.Pedido;
import com.evale.edulivery.domain.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoApplicationService {

    private final PedidoService pedidoService;


    public PedidoApplicationService(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public Pedido criarPedido(PedidoRequest request) {
        Pedido pedido = request.toDomain();
        return pedidoService.criarPedido(pedido);
    }

    public Pedido confirmarPedido(Long id) {
        return pedidoService.confirmarPedido(id);
    }

}
