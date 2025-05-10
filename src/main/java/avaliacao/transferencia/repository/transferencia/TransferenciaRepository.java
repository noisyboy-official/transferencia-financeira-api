package avaliacao.transferencia.repository.transferencia;

import avaliacao.transferencia.model.transferencia.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Interface repositório para entidades de transferência financeira.
 * Fornece métodos de acesso a dados relacionados às transferências.
 */
@Repository
public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Integer> {

    /**
     * Retorna todas as transferências financeiras cadastradas.
     *
     * @return Lista de todas as transferências.
     */
    List<TransferenciaEntity> findAll();

    /**
     * Retorna transferências realizadas antes da data especificada.
     *
     * @param dataTransferencia Data limite para busca de transferências.
     * @return Lista de transferências antes da data especificada.
     */
    List<TransferenciaEntity> findByDataTransferenciaBefore(Date dataTransferencia);

    /**
     * Retorna transferências agendadas após a data especificada.
     *
     * @param dataTransferencia Data inicial para busca de transferências.
     * @return Lista de transferências após a data especificada.
     */
    List<TransferenciaEntity> findByDataTransferenciaAfter(Date dataTransferencia);
}
