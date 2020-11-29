package web.api.sistemareservas.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonGetter;


public class ReservaDTO {

    @NotNull
    @FutureOrPresent(message = "A data de início para a reserva deve ser a atual ou uma data futura.")
    private LocalDate dataInicio;
    
    @NotNull
    @Future()
    private LocalDate dataFinal;

    private VeiculoDTO veiculoDTO;
    private ClienteDTO clienteDTO;

    /**
     * Validação para verificar se o intervalo entre as datas é negativo ou não. Caso a data final seja antes da data de início, o valor retornado será negativo.
     * @return
     */
    @Positive(message = "A data final deve ser maior que a data de início. Digite uma data válida.")
    public Long diferencaDatas(){
        long dias = ChronoUnit.DAYS.between(dataFinal, dataInicio);
        return dias;
    }

    /**
     * Validação para verificar se a data de inicio da reserva não é em um domingo.
     * @return
     */
    @AssertFalse(message = "Erro! A data de inicio informada é em um domingo, por favor, informe outra data para realizar a reserva.")
    public Boolean isDataInicioDomingo(){
        
        if(dataFinal.getDayOfWeek().getValue() == 7){
            return true;
        }
        
        return false;
    }

    @AssertFalse(message = "Erro! A data informada para realizar a entrega do veiculo é em um domingo, por favor informe outra data.")
    public Boolean isDataFinalDomingo(){
        
        if(dataFinal.getDayOfWeek().getValue() == 7){
            return true;
        }
        
        return false;
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
        long dias = ChronoUnit.DAYS.between(dataFinal, dataInicio);
        Double total = veiculoDTO.getValorDiaria() * dias;
        return total;
    }

}
