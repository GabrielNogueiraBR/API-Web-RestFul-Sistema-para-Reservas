package web.api.sistemareservas.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class VeiculoDTO {
    
    @NotBlank(message = "O modelo do carro é obrigatório!")
    @Length(min = 4, max = 60, message = "O modelo deve ter no mínimo 4 até 60 caracteres.")
    private String modelo;

    @NotBlank(message = "O valor da diária é obrigatório!")
    @DecimalMin()
    private double valorDiaria;

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
}
