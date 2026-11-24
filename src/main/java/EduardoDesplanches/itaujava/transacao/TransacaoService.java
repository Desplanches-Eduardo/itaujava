package EduardoDesplanches.itaujava.transacao;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class TransacaoService {

    private final TransacaoRepository repository;
    private final TransacaoMapper mapper;

    public TransacaoService(TransacaoRepository repository, TransacaoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TransacaoDTO registrar(TransacaoDTO dto) {
        validarTransacao(dto);
        Transacao model = mapper.toModel(dto);
        repository.salvarDados(model);
        return mapper.toDto(model);
    }

    private void validarTransacao(TransacaoDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Transação não pode ser nula");
        }
        validarValor(dto);
        validarDataHora(dto);
    }

    private void validarValor(TransacaoDTO dto) {
        if (dto.getValor() == null) {
            throw new IllegalArgumentException("Valor é obrigatório");
        }
        if (dto.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }
    }

    private void validarDataHora(TransacaoDTO dto) {
        if (dto.getDataHora() == null) {
            throw new IllegalArgumentException("Data/hora é obrigatória");
        }
        if (dto.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Data/hora não pode estar no futuro");
        }
    }

    public void deletar() {
        repository.deletarDados();
    }


}
