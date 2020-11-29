package web.api.sistemareservas.controller;

import java.net.URI;
import java.util.List;

import javax.print.DocFlavor.READER;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import web.api.sistemareservas.dto.ClienteDTO;
import web.api.sistemareservas.dto.ReservaDTO;
import web.api.sistemareservas.model.Cliente;
import web.api.sistemareservas.model.Reserva;
import web.api.sistemareservas.service.ClienteService;
import web.api.sistemareservas.service.ReservaService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Cliente> getClientes(){
        return clienteService.getAllClientes();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> getClienteByCodigo(@PathVariable int codigo){
        Cliente cliente = clienteService.getClienteByCodigo(codigo);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@Valid @RequestBody ClienteDTO dto, HttpServletRequest request, UriComponentsBuilder builder ){

        Cliente cliente = clienteService.fromDTO(dto);
        cliente = clienteService.adicionaCliente(cliente);
        
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + cliente.getCodigo()).build();
        URI uri = uriComponents.toUri();

        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping("/{codigo}")
    public ResponseEntity<Cliente> atualizar(@PathVariable int codigo, @Valid @RequestBody ClienteDTO dto){
        Cliente cliente;
        cliente = clienteService.atualizaCliente(codigo, dto);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable int codigo)
    {
        clienteService.removeClienteByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{codigoCliente}/veiculos/{codigoVeiculo}")
    public ResponseEntity<Void> salvarReserva(@Valid @RequestBody ReservaDTO dto, HttpServletRequest request, UriComponentsBuilder builder, @PathVariable int codigoCliente, @PathVariable int codigoVeiculo){

        Reserva reserva = reservaService.fromDTO(dto, codigoCliente, codigoVeiculo);

    }

}
