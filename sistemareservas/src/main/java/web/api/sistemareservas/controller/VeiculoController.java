package web.api.sistemareservas.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import web.api.sistemareservas.dto.VeiculoDTO;
import web.api.sistemareservas.model.Veiculo;
import web.api.sistemareservas.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    
    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public List<Veiculo> getVeiculos(){
        return veiculoService.getAllVeiculos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Veiculo> getVeiculoByCodigo(@PathVariable int codigo){
        Veiculo veiculo = veiculoService.getVeiculoByCodigo(codigo);
        return ResponseEntity.ok(veiculo);
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@Valid @RequestBody VeiculoDTO dto, HttpServletRequest request, UriComponentsBuilder builder ){

        Veiculo veiculo = veiculoService.fromDTO(dto);
        veiculo = veiculoService.adicionaVeiculo(veiculo);
        
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + veiculo.getCodigo()).build();
        URI uri = uriComponents.toUri();

        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping("{codigo}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable int codigo, @Valid @RequestBody VeiculoDTO dto){
        Veiculo veiculo = veiculoService.atualizaVeiculo(codigo,dto);
        return ResponseEntity.ok(veiculo);
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<String> remover(@PathVariable int codigo)
    {
        var veiculo = veiculoService.removeVeiculoByCodigo(codigo);
        
        if(!veiculo){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: Esse Veiculo esta vinculado a uma reserva ativa!");
        }
        
        return ResponseEntity.noContent().build();
    }

}
