package web.api.sistemareservas.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import web.api.sistemareservas.model.Veiculo;

@Component
public class VeiculoRepository {
    private List<Veiculo> veiculos = new ArrayList<Veiculo>();
    private int nextCodigo = 1;

    /**
     * Funcao que vai salvar um veiculo na lista de veiculos.
     * @param veiculo a ser salvo.
     * @return {@code true}
     */
    public Veiculo adicionaVeiculo(Veiculo veiculo){
        veiculo.setCodigo(nextCodigo++);
        veiculos.add(veiculo);
        return veiculo;
    }

    /**
     * Remove um veiculo da lista de veiculos.
     * @param veiculo a ser removido
     * @return
     */
    public Boolean removeVeiculo(Veiculo veiculo){
        return veiculos.remove(veiculo);
    }

    /**
     * Funcao que retorna para o {@code VeiculoService} todos os veiculos cadastrados.
     * @return Retorna uma lista de veiculos: {@code List<Veiculo> veiculos}
     */
    public List<Veiculo> getAllVeiculos(){
        return veiculos;
    }

    /**
     * Funcao que retorna um veiculo pelo codigo informado.
     * @param codigo do veiculo
     * @return O retorno e' do tipo {@code Optional} para ser tratado no service de veiculo.
     */
    public Optional<Veiculo> getVeiculoByCodigo(int codigo){
        for (Veiculo veiculo : veiculos) {
            if(veiculo.getCodigo()==codigo)
            {
                return Optional.of(veiculo);
            }
        }
        return Optional.empty();
    }

    /**
     * Funcao que realiza a atualizacao dos dados de um dado veiculo
     * @param veiculo com as modificacoes a serem enviadas para o objeto
     * @return Retorna o objeto {@code Veiculo} atualizado.
     */
    public Veiculo updateVeiculo(Veiculo veiculo){

        //Obtendo o veiculo
        Veiculo nVeiculo = getVeiculoByCodigo(veiculo.getCodigo()).get();

        if(nVeiculo != null){
            nVeiculo.setModelo( veiculo.getModelo() );
            nVeiculo.setValorDiaria(veiculo.getValorDiaria());
        }
        
        return nVeiculo;
    }

}
