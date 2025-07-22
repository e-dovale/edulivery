package com.evale.edulivery.infra.notifications;

import com.evale.edulivery.domain.model.Pedido;
import com.evale.edulivery.domain.ports.NotificadorPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class SmsNotificationAdapter implements NotificadorPort {

    private final RestTemplate restTemplate;
    private final String SMS_API_URL = "https://amock.io/api/sms-notification";

    public SmsNotificationAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean suporta(Pedido.TipoNotificacao tipoNotificacao) {
        return tipoNotificacao == Pedido.TipoNotificacao.SMS || tipoNotificacao == Pedido.TipoNotificacao.AMBOS;
    }

    @Override
    public void notificar(Pedido pedido) {
        SmsRequest request = new SmsRequest(
                pedido.getCliente(),
                pedido.getId().toString(),
                pedido.getValor(),
                "sms",
                pedido.getTelefone()
        );

        ResponseEntity<NotificationResponse> response = restTemplate.postForEntity(
                SMS_API_URL, request, NotificationResponse.class
        );

        if (!response.getStatusCode().is2xxSuccessful() ||
                !"sucesso".equalsIgnoreCase(response.getBody().status())) {
            throw new RuntimeException("Falha ao enviar SMS: +" +
                    (response.getBody() != null ? response.getBody().mensagem() : ""));
        }
    }

    private record SmsRequest(String cliente, String pedidoId, BigDecimal valor, String canal, String telefone) {
    }

}
