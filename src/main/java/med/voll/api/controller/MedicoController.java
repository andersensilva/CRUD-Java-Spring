package med.voll.api.controller;

import med.voll.api.endereco.Endereco;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @GetMapping
    public Page<DadosListagemMedicos> listar(@PageableDefault(sort ={"nome"}) Pageable paginacao){
        return repository.findAllByStatusTrue(paginacao).map(DadosListagemMedicos::new);
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroMedicos dados){
        repository.save(new Medico(dados));
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody AtualizarDadosMedicos dados){
        var medicos = repository.getReferenceById(dados.id());
        medicos.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medicos = repository.getReferenceById(id);
        medicos.excluir();
    }
}
