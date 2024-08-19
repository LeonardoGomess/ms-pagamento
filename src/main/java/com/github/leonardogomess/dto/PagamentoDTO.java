package com.github.leonardogomess.dto;



import com.github.leonardogomess.model.Pagamento;
import com.github.leonardogomess.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PagamentoDTO {

    private Long id;
    @NotNull(message = "Campo obrigatorio")
    @Positive(message = "O valor deve ser positivo")
    private BigDecimal valor;
    @Size(max = 100 , message = "Maximo de 100 caracteres")
    private String nome;
    @Size(max = 19, message = "Numero do cartao deve ter no maximo 19 caracteres")
    private String numeroDoCartao;
    @Size(min = 5 , max=5 , message = "Validade do cartao deve ter 5 caracteres")
    private String validade;
    @Size(min = 3 , max = 3 , message = "Codigo de segurança deve ter 3 caracteres")
    private String codigoDeSegurança;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @NotNull(message = "Pedido ID é obrigatorio")
    @Positive(message = "O ID pedido deve ser um valor positivo")
    private Long pedidoId;
    @NotNull(message = "Forma de pagamento ID é obrigatorio")
    @Positive(message = "A forma de pagamento deve ser um numero positivo")
    private  Long formaDePagamentoId;

    public PagamentoDTO(Pagamento entity){
        id = entity.getId();
        valor = entity.getValor();
        nome = entity.getNome();
        numeroDoCartao = entity.getNumeroDoCartao();
        validade = entity.getValidade();
        codigoDeSegurança = entity.getCodigoDeSeguranca();
        status = entity.getStatus();
        pedidoId = entity.getPedidoId();
        formaDePagamentoId = entity.getFormaDePagamentoId();
    }

}
