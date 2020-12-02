package web.api.sistemareservas.service;

import java.time.LocalDate;
import java.util.ArrayList;
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
        
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setVeiculo(veiculo);
        
        reserva.setDataInicio(dto.getDataInicio());
        reserva.setDataFinal(dto.getDataFinal());

        //O veiculo se encontra reservado para o período informado (entre datas)?
        if(!verificaIntervaloDasDatas(reserva))
            return null;

        return reserva;
    }

    public Boolean verificaIntervaloDasDatas(Reserva reserva) {
        var reservas = getAllReservas();
        LocalDate dataInicialJaReservada;
        LocalDate dataFinalJaReservada;
        LocalDate dataInicialReservaAtual;
        LocalDate dataFinalReservaAtual;      

        for(Reserva reservaJaCadastrada : reservas){
            if(reservaJaCadastrada.getVeiculo() == reserva.getVeiculo() && reservaJaCadastrada.getCodigo() != reserva.getCodigo()){
                dataInicialJaReservada = reservaJaCadastrada.getDataInicio();
                dataFinalJaReservada = reservaJaCadastrada.getDataFinal();
                dataInicialReservaAtual = reserva.getDataInicio();
                dataFinalReservaAtual = reserva.getDataFinal();            

                if(dataInicialReservaAtual.isAfter(dataInicialJaReservada) 
                    && dataInicialReservaAtual.isBefore(dataFinalJaReservada) 
                    || dataInicialReservaAtual.isEqual(dataInicialJaReservada)
                    || dataInicialReservaAtual.isEqual(dataFinalJaReservada))
                {
                    return false;
                }

                if(dataFinalReservaAtual.isAfter(dataInicialJaReservada) 
                    && dataFinalReservaAtual.isBefore(dataFinalJaReservada) 
                    || dataFinalReservaAtual.isEqual(dataFinalJaReservada)
                    || dataFinalReservaAtual.isEqual(dataInicialJaReservada))
                {
                    return false;                    
                }
            }
        }
        return true;
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
        
        reserva = reservaRepository.adicionReserva(reserva);
        
        //Adicionando a reserva em Cliente
        if( !(clienteService.adicionaReservaOnCliente(reserva.getCliente().getCodigo(), reserva)))
            return null;

        //Adicionando a reserva em Veiculo
        if(!(veiculoService.adicionaReservaOnVeiculo(reserva.getVeiculo().getCodigo(),reserva)))
            return null;

        return reserva;
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

	public List<ReservaDTO> toListDTO(List<Reserva> reservas) {
        
        List<ReservaDTO> dtoList = new ArrayList<ReservaDTO>();
        
        for (Reserva reserva : reservas) {
            dtoList.add(toDTO(reserva));
        }

        return dtoList;
	}
}
