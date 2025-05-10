package avaliacao.transferencia.controller.transferencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import avaliacao.transferencia.model.transferencia.TransferenciaEntity;
import avaliacao.transferencia.service.transferencia.TransferenciaService;

import java.util.List;
import java.util.logging.Logger;

/**
 * Controller REST responsável por gerenciar as operações relacionadas às transferências financeiras.
 * Disponibiliza endpoints para realizar transferências, consultar agendamentos e transferências efetuadas.
 *
 * @author Gabriel Aguiar
 */
@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    @Autowired
    TransferenciaService transferenciaService;

    private final Logger logger = Logger.getLogger(TransferenciaController.class.getName());

    /**
     * Endpoint para realizar uma nova transferência financeira.
     *
     * @param transferenciaEntity Entidade contendo os dados da transferência a ser realizada.
     * @return ResponseEntity contendo a entidade da transferência realizada.
     */
    @PostMapping("/transferir")
    public ResponseEntity<TransferenciaEntity> transferir(@RequestBody TransferenciaEntity transferenciaEntity) {
        logger.info("Class: TransferenciaController - Method: transferir");
        return ResponseEntity.ok(transferenciaService.transferir(transferenciaEntity));
    }

    /**
     * Endpoint para obter a lista de transferências financeiras agendadas para datas futuras.
     *
     * @return ResponseEntity contendo uma lista das transferências agendadas.
     */
    @GetMapping("/agendamentos")
    public ResponseEntity<List<TransferenciaEntity>> agendamentos() {
        logger.info("Class: TransferenciaController - Method: agendamentos");
        return ResponseEntity.ok(transferenciaService.getAgendamentos());
    }

    /**
     * Endpoint para obter a lista de transferências financeiras já realizadas.
     *
     * @return ResponseEntity contendo uma lista das transferências realizadas até a data atual.
     */
    @GetMapping("/transferencias")
    public ResponseEntity<List<TransferenciaEntity>> transferencias() {
        logger.info("Class: TransferenciaController - Method: transferencias");
        return ResponseEntity.ok(transferenciaService.getTransferencias());
    }
}