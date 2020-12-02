package web.api.sistemareservas.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class ReservaDTO {

    private int codigo;
    
    @NotNull
    @FutureOrPresent(message = "A data de início para a reserva deve ser a atual ou uma data futura.")
    private LocalDate dataInicio;
    
    @NotNull
    @Future(message = "A data final para a reserva deve ser uma data futura.")
    private LocalDate dataFinal;

    private VeiculoDTO veiculoDTO;
    private ClienteDTO clienteDTO;

    /**
     * Validação para verificar se o intervalo entre as datas é negativo ou não. Caso a data final seja antes da data de início, o valor retornado será negativo.
     * @return
     */
    @JsonIgnore
    @AssertFalse(message = "A data final deve ser maior que a data de início. Digite uma data válida.")
    public boolean isDiferencaDatasNegativo(){
        var dias = Period.between(dataInicio, dataFinal).getDays();
        if(dias < 0)
            return true;
        return false;
    }

    /**
     * Validação para verificar se a data de inicio da reserva não é em um domingo.
     * @return
     */
    @JsonIgnore
    @AssertFalse(message = "Erro! A data de inicio informada é em um domingo, por favor, informe outra data para realizar a reserva.")
    public boolean isDataInicioDomingo(){
        return(dataInicio.getDayOfWeek().equals(DayOfWeek.SUNDAY));
    }

    @JsonIgnore
    @AssertFalse(message = "Erro! A data informada para realizar a entrega do veiculo é em um domingo, por favor informe outra data.")
    public boolean isDataFinalDomingo(){    
        return(dataFinal.getDayOfWeek().equals(DayOfWeek.SUNDAY));
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

    public VeiculoDTO getVeiculoDTO() {
        return veiculoDTO;
    }

    public void setVeiculoDTO(VeiculoDTO veiculoDTO) {
        this.veiculoDTO = veiculoDTO;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }

    @JsonGetter
    public Double totalReserva(){
        long dias = ChronoUnit.DAYS.between(dataInicio, dataFinal);
        Double total = veiculoDTO.getValorDiaria() * dias;
        return total;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}
