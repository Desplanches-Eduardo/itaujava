package EduardoDesplanches.itaujava.transacao;

import EduardoDesplanches.itaujava.estatistica.EstatisticaModel;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Repository
public class TransacaoRepository {

    private final Queue<Transacao> listaDeTransacoes = new ConcurrentLinkedQueue<>();

    public void salvarDados(Transacao model) {
        listaDeTransacoes.add(model);
    }

    public void deletarDados() {
        listaDeTransacoes.clear();
    }

    public List<Transacao> listar() {
        return List.copyOf(listaDeTransacoes);
    }

    public EstatisticaModel estatistica(OffsetDateTime horaInicial) {
        final var sumary = listaDeTransacoes.stream()
                .filter(t -> !t.getDataHora().isBefore(horaInicial))
                .mapToDouble(t -> t.getValor().doubleValue())
                .summaryStatistics();
        if (sumary.getCount() == 0) {
            return new EstatisticaModel(0, 0, 0, 0, 0);
        }
        return new EstatisticaModel(sumary.getCount(), sumary.getAverage(),
                sumary.getMax(), sumary.getMin(), sumary.getSum());
    }
}
