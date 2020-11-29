package web.api.sistemareservas.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import web.api.sistemareservas.model.Reserva;

@Component
public class ReservaRepository {
    private List<Reserva> reservas = new ArrayList<Reserva>();
    private int nextCodigo = 1;

    public Reserva adicionReserva(Reserva reserva){
        reserva.setCodigo(nextCodigo++);
        reservas.add(reserva);
        return reserva;
    }

    public List<Reserva> getAllReservas(){
        return reservas;
    }

    public Optional<Reserva> getReservaByCodigo(int codigo){
        
        for (Reserva reserva : reservas) {
            if(reserva.getCodigo() == codigo){
                return Optional.of(reserva);
            }
        }

        return Optional.empty();
    }

    public Boolean removeReserva(Reserva reserva){
        return reservas.remove(reserva);
    }
    
    public Reserva atualizaReserva(Reserva reserva){

        Reserva nReserva = getReservaByCodigo(reserva.getCodigo()).get();

        if(nReserva != null){
            nReserva.setDataInicio(reserva.getDataInicio());
            nReserva.setDataFinal(reserva.getDataFinal());
        }
        return nReserva;
    }
}
