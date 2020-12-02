package web.api.sistemareservas.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class ClienteDTO {

    @NotBlank(message = "O 'nome' é obrigatório!")
    @Length(min = 3, max = 100, message = "O 'nome' deve ter no mínimo 3 até 100 caracteres.")
    private String nome;

    @NotBlank(message = "O 'endereco' é obrigatório!")
    @Length(max = 150, message = "O endereco excedeu o limite de caracteres de no maximo 150 caracteres.")
    private String endereco;

    @NotBlank(message = "O 'cpf' é obrigatório!")
    @Length(min = 11,max = 11, message = "O 'cpf' deve possuir 11 caracteres, sem pontuações.")
    private String cpf;


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
    

}
