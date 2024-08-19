package com.github.leonardogomess.service;


import com.github.leonardogomess.dto.PagamentoDTO;
import com.github.leonardogomess.model.Pagamento;
import com.github.leonardogomess.model.Status;
import com.github.leonardogomess.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Transactional(readOnly = true)
    public List<PagamentoDTO> findAll(){
        List<Pagamento> list = repository.findAll();
        return  list.stream().map(PagamentoDTO::new).collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public  PagamentoDTO findById(Long id){
        Pagamento entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso nao encontrado! id: " + id)
        );
        return new PagamentoDTO(entity);
    }

    @Transactional
    public PagamentoDTO insert(PagamentoDTO dto){
        Pagamento entity = new Pagamento();
        copyDtoToEntity(dto,entity);
        entity = repository.save(entity);
        return new PagamentoDTO(entity);
    }

    @Transactional
    public  void  delete(Long id){
        if (!repository.existsById(id)){
            throw  new ResourceNotFoundException("Recurso nao encontrado! Id:" + id);
        }
        try {
            repository.deleteById(id);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso nao encontrado! Id:" + id);
        }
    }

    @Transactional
    public PagamentoDTO update(Long id,PagamentoDTO dto){
        try {
            Pagamento entity = repository.getReferenceById(id);
            copyDtoToEntity(dto,entity);
            entity = repository.save(entity);
            return  new PagamentoDTO(entity);
        }catch (EntityNotFoundException e){
            throw  new ResourceNotFoundException("Recurso nao encontrado! id: " + id);
        }
    }

    private void copyDtoToEntity(PagamentoDTO dto ,Pagamento entity){
        entity.setValor(dto.getValor());
        entity.setNome(dto.getNome());
        entity.setNumeroDoCartao(dto.getNumeroDoCartao());
        entity.setValidade(dto.getValidade());
        entity.setCodigoDeSeguranca(dto.getCodigoDeSegurança());
        entity.setStatus(Status.CRIADO);
        entity.setPedidoId(dto.getPedidoId());
        entity.setFormaDePagamentoId(dto.getFormaDePagamentoId());
    }
}
