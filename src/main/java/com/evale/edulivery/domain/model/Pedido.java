package com.evale.edulivery.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Pedido {

    public static final String RECEBIDO = "RECEBIDO";
    public static final String CONFIRMADO = "CONFIRMADO";

    private Long id;
    private String cliente;
    private String email;
    private String telefone;
    private BigDecimal valor;
    private String status;
    private LocalDateTime dataConfirmacao;
    private TipoNotificacao preferenciaNotificacao;

    public enum TipoNotificacao {
        EMAIL, SMS, AMBOS
    }

    public Pedido(String cliente, String email, String telefone, BigDecimal valor, TipoNotificacao preferenciaNotificacao) {
        this.cliente = cliente;
        this.email = email;
        this.telefone = telefone;
        this.valor = valor;
        this.preferenciaNotificacao = preferenciaNotificacao;
        this.status = RECEBIDO;
    }

    public void confirmar() {
        if (!"RECEBIDO".equals(this.status)) {
            throw new IllegalStateException("Pedido só pode ser confirmado no status RECEBIDO");
        }
        this.status = CONFIRMADO;
        this.dataConfirmacao = LocalDateTime.now();
    }

}
