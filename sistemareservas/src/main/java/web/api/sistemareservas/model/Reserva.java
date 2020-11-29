package web.api.sistemareservas.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Reserva {
    private int codigo;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private Cliente cliente;
    private Veiculo veiculo;
    
    public Reserva() {
    }
    
    public Reserva(int codigo, LocalDate dataInicio, LocalDate dataFinal, Cliente cliente, Veiculo veiculo) {
        this.codigo = codigo;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.cliente = cliente;
        this.veiculo = veiculo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }
    
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Veiculo getVeiculo() {
        return veiculo;
    }
    
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @JsonGetter
    public Double totalReserva(){
        long dias = ChronoUnit.DAYS.between(dataInicio, dataFinal);
        Double total = veiculo.getValorDiaria() * dias;
        return total;
    }

}
