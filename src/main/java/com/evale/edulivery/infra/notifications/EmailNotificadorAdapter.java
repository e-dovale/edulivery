package com.evale.edulivery.infra.notifications;

import com.evale.edulivery.domain.model.Pedido;
import com.evale.edulivery.domain.ports.NotificadorPort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class EmailNotificadorAdapter implements NotificadorPort {

    private final RestTemplate restTemplate;
    private final String EMAIL_API_URL = "https://amock.io/api/email-notification";

    public EmailNotificadorAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean suporta(Pedido.TipoNotificacao tipoNotificacao) {

        return tipoNotificacao == Pedido.TipoNotificacao.EMAIL || tipoNotificacao == Pedido.TipoNotificacao.AMBOS;
    }

    @Override
    public void notificar(Pedido pedido) {

        EmailRequest request = new EmailRequest(
                pedido.getCliente(),
                pedido.getId().toString(),
                pedido.getValor(),
                "email",
                pedido.getEmail()
        );

        ResponseEntity<NotificationResponse> response = restTemplate.postForEntity(
                EMAIL_API_URL,
                request,
                NotificationResponse.class
        );

        if (!response.getStatusCode().is2xxSuccessful() ||
        !"sucesso".equalsIgnoreCase(response.getBody().status())) {
            throw new RuntimeException("Falha ao enviar email: " +
                    (response.getBody() != null ? response.getBody().mensagem() : ""));
        }
    }

    private record EmailRequest(String cliente, String pedidoId, BigDecimal valor, String canal, String email) {}
}
