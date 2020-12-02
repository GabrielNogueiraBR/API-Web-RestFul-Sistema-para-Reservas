package web.api.sistemareservas.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Cliente {
    private int codigo;
    private String nome;
    private String endereco;
    private String cpf;
    
    @JsonIgnore
    private List<Reserva> reservas = new ArrayList<Reserva>();
    
    public Cliente() {

    }
    
    public Cliente(int codigo, String nome, String endereco, String cpf) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
