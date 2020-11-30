package web.api.sistemareservas.model;

import java.util.List;

public class Cliente {
    private int codigo;
    private String nome;
    private String endereco;
    private String cpf;
    private List<Reserva> reservas;
    
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
