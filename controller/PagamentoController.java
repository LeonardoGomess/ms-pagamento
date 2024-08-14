package br.com.fiap.ms_pagamento.controller;

import br.com.fiap.ms_pagamento.dto.PagamentoDTO;
import br.com.fiap.ms_pagamento.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pagamentos")
@Tag(name = "Pagamento" ,description = "Controller para pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @Operation(description = "Listar Pagamentos" ,summary = "Retorna uma lista de pagamentos",responses = {
            @ApiResponse(description = "OK" , responseCode = "200")
    })

//    @GetMapping
//    public ResponseEntity<List<PagamentoDTO>> findAll(){
//        List<PagamentoDTO> dto = service.findAll();
//        return ResponseEntity.ok(dto);
//    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<PagamentoDTO>> findAll(
            @PageableDefault(size = 10) Pageable pageable) {

        Page<PagamentoDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }


    @Operation(description = "Retorna um pagamento a partir do indentificador (id)" ,summary = "Consulta pagamento por id",responses = {
            @ApiResponse(description = "OK" , responseCode = "200"),
            @ApiResponse(description = "Not Found" , responseCode = "404")
    })

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagamentoDTO> findById(@PathVariable Long id) {
        PagamentoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }



    @Operation(description = "Cria um novo pagamento" ,summary = "Salva um novo pagamento",responses = {
            @ApiResponse(description = "Created" , responseCode = "201"),
            @ApiResponse(description = "Bad Request" , responseCode = "400"),
            //@ApiResponse(description = "Unauthorized" , responseCode = "401"),
            //@ApiResponse(description = "Forbidden" , responseCode = "403"),
            //@ApiResponse(description = "Unprocessable Entity" , responseCode = "422")
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<PagamentoDTO> insert(@RequestBody @Valid PagamentoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto)
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }


    @Operation(description = "Exclui um pagamento a partir  do identificador (id)" ,summary = "Exclui um pagamento",responses = {
            @ApiResponse(description = "Sucesso" , responseCode = "204"),
            @ApiResponse(description = "Bad Request" , responseCode = "400"),
            @ApiResponse(description = "Unauthorized" , responseCode = "401"),
            @ApiResponse(description = "Forbidden" , responseCode = "403"),
            @ApiResponse(description = "Unprocessable Entity" , responseCode = "422"),
            @ApiResponse(description = "Not Found" , responseCode = "404"),

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
