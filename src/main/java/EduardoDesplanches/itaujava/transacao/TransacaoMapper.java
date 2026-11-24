package EduardoDesplanches.itaujava.transacao;

import org.springframework.stereotype.Component;

@Component
public class TransacaoMapper {

    public Transacao toModel(TransacaoDTO dto){
        Transacao model = new Transacao();
        model.setValor(dto.getValor());
        model.setDataHora(dto.getDataHora());
        return model;
    }

    public TransacaoDTO toDto(Transacao model){
        TransacaoDTO dto = new TransacaoDTO();
        dto.setValor(model.getValor());
        dto.setDataHora(model.getDataHora());
        return dto;
    }
}
