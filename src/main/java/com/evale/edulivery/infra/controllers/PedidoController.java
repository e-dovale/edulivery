package com.evale.edulivery.infra.controllers;

import com.evale.edulivery.application.PedidoApplicationService;
import com.evale.edulivery.application.dto.PedidoRequest;
import com.evale.edulivery.domain.model.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoApplicationService applicationService;

    public PedidoController(PedidoApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public Pedido criarPedido(@RequestBody PedidoRequest request) {
        return applicationService.criarPedido(request);
    }

    @PostMapping("/{id}/confirmar")
    public Pedido confirmarPedido(@PathVariable Long id) {
        return applicationService.confirmarPedido(id);
    }

}
