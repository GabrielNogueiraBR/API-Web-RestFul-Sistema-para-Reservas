package web.api.sistemareservas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.api.sistemareservas.model.Reserva;
import web.api.sistemareservas.service.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> getAllReservas(){
        return reservaService.getAllReservas();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable int codigo){
        Reserva reserva = reservaService.getReservaByCodigo(codigo);
        return ResponseEntity.ok(reserva);
    }

}
