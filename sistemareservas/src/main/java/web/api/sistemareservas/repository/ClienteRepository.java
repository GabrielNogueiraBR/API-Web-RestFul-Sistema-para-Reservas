package web.api.sistemareservas.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import web.api.sistemareservas.model.Cliente;

@Component
public class ClienteRepository {
    private List<Cliente> clientes = new ArrayList<Cliente>();
    private int nextCodigo = 1;

    public Cliente adicionaCliente(Cliente cliente){
        cliente.setCodigo(nextCodigo++);
        clientes.add(cliente);
        return cliente;
    }

    public List<Cliente> getAllClientes(){
        return clientes;
    }

    public Optional<Cliente> getClienteByCodigo(int codigo){
        for (Cliente cliente : clientes) {
            if(cliente.getCodigo()==codigo)
                return Optional.of(cliente);
        }
        return Optional.empty();
    }

    public Boolean removeCliente(Cliente cliente){
        return clientes.remove(cliente);
    }

    public Cliente updateCliente(Cliente cliente){
        Cliente nCliente = getClienteByCodigo(cliente.getCodigo()).get();
        
        if(nCliente != null){
            int posicaoNaLista = clientes.indexOf(nCliente);
            nCliente.setNome(cliente.getNome());
            nCliente.setEndereco(cliente.getEndereco());
            clientes.set(posicaoNaLista, nCliente);
        }
        return nCliente;
    }

}
