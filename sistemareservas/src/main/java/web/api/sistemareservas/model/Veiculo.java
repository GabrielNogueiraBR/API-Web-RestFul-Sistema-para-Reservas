package web.api.sistemareservas.model;

import java.util.List;

public class Veiculo {
    private int codigo;
    private String modelo;
    private double valorDiaria;
    private List<Reserva> reservas;
    
    public Veiculo() {
    }
    
    public Veiculo(int codigo, String modelo, double valorDiaria) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Boolean adicionaReserva(Reserva reserva){
        return this.reservas.add(reserva);
    }
}
