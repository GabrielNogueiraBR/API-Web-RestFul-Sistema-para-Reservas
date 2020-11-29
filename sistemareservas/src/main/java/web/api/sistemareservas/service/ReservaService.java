package web.api.sistemareservas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import web.api.sistemareservas.dto.ClienteDTO;
import web.api.sistemareservas.dto.ReservaDTO;
import web.api.sistemareservas.dto.VeiculoDTO;
import web.api.sistemareservas.model.Cliente;
import web.api.sistemareservas.model.Reserva;
import web.api.sistemareservas.model.Veiculo;
import web.api.sistemareservas.repository.ReservaRepository;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VeiculoService veiculoService;


    public List<Reserva> getAllReservas(){
        return reservaRepository.getAllReservas();
    }

    public Reserva getReservaByCodigo(int codigo){
        Optional<Reserva> optional = reservaRepository.getReservaByCodigo(codigo);

        return optional.orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não cadastrada.")
        );
    }

    public Reserva fromDTO(ReservaDTO dto, int codigoCliente, int codigoVeiculo){
        
        //Tem um cliente?
        Cliente cliente = clienteService.getClienteByCodigo(codigoCliente);

        //Tem um veiculo?
        Veiculo veiculo = veiculoService.getVeiculoByCodigo(codigoVeiculo);

        //O veiculo se encontra reservado para o período informado (entre datas)?

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setVeiculo(veiculo);
        
        reserva.setDataInicio(dto.getDataInicio());
        reserva.setDataFinal(dto.getDataFinal());
        
        return reserva;

    }

    public ReservaDTO toDTO(Reserva reserva){
        ReservaDTO dto = new ReservaDTO();
        dto.setDataFinal(reserva.getDataFinal());
        dto.setDataInicio(reserva.getDataInicio());
        
        //Transformando o Veiculo da reserva em DTO para ser exibido
        VeiculoDTO veiculoDTO = veiculoService.toDTO(reserva.getVeiculo());
        dto.setVeiculoDTO(veiculoDTO);

        //Transformando o Cliente da reserva em DTO para ser exibido
        ClienteDTO clienteDTO = clienteService.toDTO(reserva.getCliente());
        dto.setClienteDTO(clienteDTO);

        return dto;

    }

    public Reserva adicionReserva(Reserva reserva){
        return reservaRepository.adicionReserva(reserva);
    }

    public Reserva atualizaReserva(ReservaDTO dto, int codigoReserva, int codigoCliente, int codigoVeiculo){
        
        //Tem uma Reserva? Caso contrario lança o 404
        getReservaByCodigo(codigoReserva);

        Reserva reserva = fromDTO(dto, codigoCliente, codigoVeiculo);
        reserva.setCodigo(codigoReserva);
        return reservaRepository.adicionReserva(reserva);

    }

    public Boolean removeReservaByCodigo(int codigo){
        
        Reserva reserva = getReservaByCodigo(codigo);
        return reservaRepository.removeReserva(reserva);
        
    }

}
