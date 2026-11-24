package EduardoDesplanches.itaujava.Estatistica;

import EduardoDesplanches.itaujava.Docs.EstatisticaControllerDoc;
import EduardoDesplanches.itaujava.transacao.TransacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@Slf4j
@RestController
@RequestMapping("/estatistica")
public class EstatisticaController implements EstatisticaControllerDoc {
    private final EstatisticasProperties properties;
    private final TransacaoRepository transacaoRepository;

    public EstatisticaController(EstatisticasProperties properties, TransacaoRepository transacaoRepository) {
        this.properties = properties;
        this.transacaoRepository = transacaoRepository;
    }

    @GetMapping
    public ResponseEntity<EstatisticaModel> estatistica(){

        log.info("Calculando estatística dos {} últimos segundos" , properties.segundos());
        final var horaInicial = OffsetDateTime
                .now()
                .minusSeconds(properties.segundos());
        return ResponseEntity.ok(transacaoRepository.estatistica(horaInicial));
    }
}
