package avaliacao.transferencia.controller.transferencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import avaliacao.transferencia.model.transferencia.TransferenciaEntity;
import avaliacao.transferencia.service.transferencia.TransferenciaService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {
    @Autowired
    TransferenciaService transferenciaService;

    Logger logger = Logger.getLogger(TransferenciaController.class.getName());

    @PostMapping("/transferir")
    public ResponseEntity<TransferenciaEntity> transferir(@RequestBody TransferenciaEntity transferenciaEntity){
        logger.info("Class: TransferenciaController - Method: transferir");
        return ResponseEntity.ok(transferenciaService.transferir(transferenciaEntity));
    }

    @GetMapping("/agendamentos")
    public ResponseEntity<List<TransferenciaEntity>> agendamentos() {
        logger.info("Class: TransferenciaController - Method: transferir");
        return ResponseEntity.ok(transferenciaService.getAgendamentos());
    }

    @GetMapping("/transferencias")
    public ResponseEntity<List<TransferenciaEntity>> transferencias() {
        logger.info("Class: TransferenciaController - Method: transferir");
        return ResponseEntity.ok(transferenciaService.getTransferencias());
    }

}
