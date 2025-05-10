package avaliacao.transferencia.service.transferencia;

import avaliacao.transferencia.model.transferencia.TransferenciaEntity;
import avaliacao.transferencia.repository.transferencia.TransferenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * Classe de testes unitários para o serviço de transferência financeira.
 * Valida funcionalidades como transferência, consulta de transferências realizadas e agendadas.
 */
class TransferenciaServiceTest {

    @InjectMocks
    private TransferenciaService transferenciaService;

    @Mock
    private TransferenciaRepository transferenciaRepository;

    private TransferenciaEntity transferencia;

    /**
     * Configura o ambiente de testes com inicialização dos mocks e entidade padrão.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transferencia = new TransferenciaEntity();
        transferencia.setContaOrigem(1234567890L);
        transferencia.setContaDestino(9876543210L);
        transferencia.setValor(BigDecimal.valueOf(1000));
        transferencia.setDataTransferencia(new Date());
    }

    /**
     * Testa o método de transferência financeira e valida o retorno correto da entidade salva.
     */
    @Test
    void testTransferir() {
        when(transferenciaRepository.save(any(TransferenciaEntity.class))).thenReturn(transferencia);

        TransferenciaEntity result = transferenciaService.transferir(transferencia);

        assertNotNull(result);
        assertEquals(transferencia.getContaOrigem(), result.getContaOrigem());
        assertEquals(transferencia.getContaDestino(), result.getContaDestino());
        verify(transferenciaRepository, times(1)).save(transferencia);
    }

    /**
     * Testa o método que retorna transferências realizadas até a data atual.
     */
    @Test
    void testGetTransferencias() {
        when(transferenciaRepository.findByDataTransferenciaBefore(any(Date.class))).thenReturn(List.of(transferencia));

        List<TransferenciaEntity> result = transferenciaService.getTransferencias();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(transferenciaRepository, times(1)).findByDataTransferenciaBefore(any(Date.class));
    }

    /**
     * Testa o método que retorna transferências agendadas após a data atual.
     */
    @Test
    void testGetAgendamentos() {
        when(transferenciaRepository.findByDataTransferenciaAfter(any(Date.class))).thenReturn(List.of(transferencia));

        List<TransferenciaEntity> result = transferenciaService.getAgendamentos();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(transferenciaRepository, times(1)).findByDataTransferenciaAfter(any(Date.class));
    }
}