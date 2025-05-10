package avaliacao.transferencia.service.transferencia;

import avaliacao.transferencia.model.transferencia.TransferenciaEntity;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import avaliacao.transferencia.repository.transferencia.TransferenciaRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
 * Serviço responsável pelas operações de transferência financeira, incluindo cálculo de taxas
 * e gerenciamento das transferências realizadas e agendadas.
 *
 * @author Gabriel Aguiar
 * @version 1.0
 */
@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    private final Logger logger = LoggerFactory.logger(TransferenciaService.class);

    /**
     * Realiza a transferência financeira, incluindo o cálculo das taxas aplicáveis.
     *
     * @param transferencia objeto contendo os detalhes da transferência.
     * @return TransferenciaEntity contendo informações atualizadas após persistência.
     */
    public TransferenciaEntity transferir(TransferenciaEntity transferencia) {
        calcularTaxa(transferencia);
        transferenciaRepository.save(transferencia);
        return transferencia;
    }

    /**
     * Calcula as taxas aplicáveis com base nos dias restantes até a data da transferência.
     *
     * @param transferencia objeto contendo informações da transferência.
     * @throws IllegalArgumentException caso o período esteja fora do intervalo permitido.
     */
    private void calcularTaxa(TransferenciaEntity transferencia) {
        long dias = calcularDiasAteTransferencia(transferencia);
        BigDecimal taxaExtra = BigDecimal.ZERO;
        BigDecimal taxaPorcentagem = BigDecimal.ZERO;

        if (dias == 0) {
            taxaPorcentagem = BigDecimal.valueOf(2.5);
            taxaExtra = BigDecimal.valueOf(3.00);
        } else if (dias <= 10) {
            taxaExtra = BigDecimal.valueOf(12.00);
        } else if (dias <= 20) {
            taxaPorcentagem = BigDecimal.valueOf(8.2);
        } else if (dias <= 30) {
            taxaPorcentagem = BigDecimal.valueOf(6.9);
        } else if (dias <= 40) {
            taxaPorcentagem = BigDecimal.valueOf(4.7);
        } else if (dias <= 50) {
            taxaPorcentagem = BigDecimal.valueOf(1.7);
        } else {
            throw new IllegalArgumentException("Dias de transferência fora do intervalo permitido.");
        }

        BigDecimal taxaCalculada = transferencia.getValor()
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
                .multiply(taxaPorcentagem);

        transferencia.setTaxaExtra(taxaExtra);
        transferencia.setTaxa(taxaPorcentagem);
        transferencia.setValorFinal(transferencia.getValor().add(taxaCalculada).add(taxaExtra));
    }

    /**
     * Calcula a quantidade de dias restantes até a data da transferência.
     *
     * @param transferencia objeto contendo a data da transferência.
     * @return número de dias até a transferência.
     */
    private long calcularDiasAteTransferencia(TransferenciaEntity transferencia) {
        LocalDate hoje = LocalDate.now(ZoneId.systemDefault());
        LocalDate dataTransferencia = transferencia.getDataTransferencia().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(hoje, dataTransferencia);
    }

    /**
     * Retorna uma lista de transferências financeiras realizadas até a data atual.
     *
     * @return lista de transferências passadas.
     */
    public List<TransferenciaEntity> getTransferencias() {
        return transferenciaRepository.findByDataTransferenciaBefore(new Date());
    }

    /**
     * Retorna uma lista de transferências financeiras agendadas para datas futuras.
     *
     * @return lista de transferências futuras.
     */
    public List<TransferenciaEntity> getAgendamentos() {
        return transferenciaRepository.findByDataTransferenciaAfter(new Date());
    }
}