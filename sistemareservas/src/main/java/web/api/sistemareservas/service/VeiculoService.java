package web.api.sistemareservas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import web.api.sistemareservas.dto.VeiculoDTO;
import web.api.sistemareservas.model.Veiculo;
import web.api.sistemareservas.repository.VeiculoRepository;

@Service
public class VeiculoService {
    
    @Autowired
    private VeiculoRepository repository;

    public List<Veiculo> getAllVeiculos(){
        return repository.getAllVeiculos();
    }

    public Veiculo getVeiculoByCodigo(int codigo){
        Optional<Veiculo> optional = repository.getVeiculoByCodigo(codigo);

        return optional.orElseThrow( 
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo nao cadastrado.") );

    }

    public Veiculo fromDTO(VeiculoDTO dto){
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(dto.getModelo());
        veiculo.setValorDiaria(dto.getValorDiaria());
        return veiculo;
    }

    public VeiculoDTO toDTO(Veiculo veiculo){
        VeiculoDTO dto = new VeiculoDTO();
        dto.setModelo(veiculo.getModelo());
        dto.setValorDiaria(veiculo.getValorDiaria());
        return dto;
    }

    public Boolean adicionaVeiculo(VeiculoDTO dto){
        Veiculo veiculo = fromDTO(dto);
        return repository.adicionaVeiculo(veiculo);
    }

    public Boolean removeVeiculoByCodigo(int codigo){
        Veiculo veiculo = getVeiculoByCodigo(codigo);
        return repository.removeVeiculo(veiculo);
    }

    public Veiculo atualizaVeiculo(int codigo, VeiculoDTO dto){
        Veiculo veiculo = fromDTO(dto);
        veiculo.setCodigo(codigo);
        return repository.updateVeiculo(veiculo);
    }

}
