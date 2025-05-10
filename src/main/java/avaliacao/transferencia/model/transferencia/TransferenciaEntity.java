package avaliacao.transferencia.model.transferencia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entidade que representa uma transferência financeira.
 * Mapeia as informações das transferências armazenadas na tabela tb_transferencia.
 */
@Entity
@Table(name = "tb_transferencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaEntity {

    /**
     * Identificador único da transferência.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransferencia;

    /**
     * Conta de origem da transferência.
     */
    private Long contaOrigem;

    /**
     * Conta destino da transferência.
     */
    private Long contaDestino;

    /**
     * Taxa percentual aplicada à transferência.
     */
    private BigDecimal taxa = BigDecimal.ZERO;

    /**
     * Taxa fixa adicional aplicada à transferência.
     */
    private BigDecimal taxaExtra = BigDecimal.ZERO;

    /**
     * Valor bruto da transferência.
     */
    private BigDecimal valor;

    /**
     * Valor final da transferência após a aplicação das taxas.
     */
    private BigDecimal valorFinal;

    /**
     * Data agendada ou realizada da transferência.
     */
    private Date dataTransferencia;
}
