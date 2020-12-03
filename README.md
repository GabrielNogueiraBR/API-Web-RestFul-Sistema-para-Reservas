# API Web RestFul Sistema para Reservas
Desenvolva uma aplicação do tipo API Web RestFul que permita gerenciar reservas de veículos. A aplicação deverá permitir:

1. Gerenciar Veículos-CRUD(Create-Read-Update-Delete)
    * Código
    * Modelo
    * Valor diária.
1. Gerenciar Clientes e listar as reservas de um Cliente-CRUD(Create-Read-Update-Delete)
    * Código
    * Nome
    * Endereço
    * CPF
1. Fazer uma Reserva de um veículo por um cliente. A reserva deverá ter:
    * Tem um número
    * Cliente deverá existir.
    * Veículo deverá existir.
    * Data de Início (Deverá ser maior que a data do sistema). Não pode começar no Domingo.
    * Data de Fim (Deverá ser maior que a data de Início). Não existe entrega no Domingo.
    * O total da reserva deverá ser calculado.
    * Um veículo pode ser reservado várias vezes, porém somente em períodos/datas diferentes.
    * O caminho para criar uma reserva é: POST de clientes/{id-cliente}/veiculos/{id-veiculo}
1. Listar uma reserva pelo número.
1. Listar as reservas de um cliente.
1. Listar as reservas de um veículo


## To do list
    - DONE
