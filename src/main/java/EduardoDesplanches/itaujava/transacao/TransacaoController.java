package EduardoDesplanches.itaujava.transacao;
import EduardoDesplanches.itaujava.docs.TransacaoControllerDoc;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transacao")
public class TransacaoController implements TransacaoControllerDoc {
    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @PostMapping

    public ResponseEntity<TransacaoDTO>adicionar(@Valid @RequestBody TransacaoDTO dto){
        log.info("adicionando a transação");
        TransacaoDTO transacao = service.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(transacao);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(){
        log.info("deletando todas transações");
        service.deletar();
        return ResponseEntity.ok().build();
    }

}
