package web.api.sistemareservas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import web.api.sistemareservas.dto.ClienteDTO;
import web.api.sistemareservas.model.Cliente;
import web.api.sistemareservas.repository.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;

    public List<Cliente> getAllClientes(){
        return repository.getAllClientes();
    }

    public Cliente getClienteByCodigo(int codigo){
        Optional<Cliente> optional = repository.getClienteByCodigo(codigo);

        return optional.orElseThrow( 
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao cadastrado.") );
    }

    public Cliente adicionaCliente(Cliente cliente){
        return repository.adicionaCliente(cliente);
    }

    public Cliente fromDTO(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEndereco(dto.getEndereco());
        cliente.setCpf(dto.getCpf());
        return cliente;
    }

    public Cliente atualizaCliente(int codigo, ClienteDTO dto){
        
        //Tem um cliente? Caso contrario lança o 404
        getClienteByCodigo(codigo);
        
        Cliente cliente = fromDTO(dto);
        cliente.setCodigo(codigo);
        return repository.updateCliente(cliente);
    }

    public Boolean removeClienteByCodigo(int codigo) {
        Cliente cliente = getClienteByCodigo(codigo);
        return repository.removeCliente(cliente);
    }



}
