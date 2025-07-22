package com.evale.edulivery.domain.service;

import com.evale.edulivery.domain.model.Pedido;
import com.evale.edulivery.domain.ports.NotificadorPort;
import com.evale.edulivery.domain.ports.PedidoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepositoryPort repository;
    private final List<NotificadorPort> notificadores;

    public PedidoService(PedidoRepositoryPort repository, List<NotificadorPort> notificadores) {
        this.repository = repository;
        this.notificadores = notificadores;
    }

    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus("RECEBIDO");
        Pedido pedidoSalvo = repository.save(pedido);
        notificarTodos(pedidoSalvo, "Pedido criado com status: " + pedidoSalvo.getStatus());
        return pedidoSalvo;
    }

    public Pedido confirmarPedido(Long id) {
        System.out.println("Buscando pedido ID: " + id);

        Pedido pedido = repository.buscarPorId(id)
                .orElseThrow(() -> {
                    System.err.println("Pedido não encontrado! ID: " + id);
                    return new RuntimeException("Pedido não encontrado");
                });

        System.out.println("Pedido encontrado: " + pedido);
        pedido.confirmar();

        Pedido confirmado = repository.save(pedido);
        System.out.println("Pedido confirmado: " + confirmado);

        return confirmado;

    }

    private void notificarConfirmacao(Pedido pedido) {
        notificadores.stream()
                .filter(n -> n.suporta(pedido.getPreferenciaNotificacao()))
                .forEach(n -> n.notificar(pedido));
    }

    private void notificarTodos(Pedido pedido, String mensagem) {
        //
    }
}
