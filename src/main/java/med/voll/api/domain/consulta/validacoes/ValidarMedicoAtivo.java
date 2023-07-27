package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoAtivo implements ValidadorAgendamentoDeConsultas  {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = repository.findStatusById(dados.idMedico());
        if(!medicoEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluido");
        }
    }
}
