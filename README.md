# Sistema-para-Controle-de-Multas-de-Transito
Este sistema foi desenvolvido para gerenciar e processar ocorrências de trânsito, calcular multas e gerar relatórios de infrações.

# Funcionalidades
-Cadastro e Processamento de Ocorrências:
Adiciona ocorrências de trânsito com informações como placa, logradouro, data/hora e tipo de ocorrência.
Processa as ocorrências para calcular as multas com base nas regras definidas.

-Regras de Multa:
O sistema suporta várias regras para cálculo de multas, como velocidade, rodízio e corredores de ônibus.
As multas são geradas automaticamente com base nas ocorrências e regras.
-Busca e Listagem:
Permite buscar multas por data ou placa.
Lista todas as ocorrências processadas, não processadas e as multas geradas.
-Exportação de Relatórios:
Exporta as multas geradas em formato CSV ou JSON para relatórios e análises posteriores.

#Tecnologias Utilizadas
Gson (biblioteca): Para exportação de dados em formato JSON.
Manipulação de Arquivos (CSV e TXT): Para importação de ocorrências e exportação de relatórios.

#Estrutura do Projeto
O projeto é dividido nas seguintes classes principais:

-Ocorrencia: Representa uma ocorrência de trânsito com dados como placa, logradouro, data/hora e tipo de ocorrência.
-Multa: Representa uma multa gerada, contendo a placa do veículo, o logradouro, a descrição da infração, o nível da multa e o valor da multa.
-RegraMulta: Classe abstrata para representar uma regra de multa. Existem subclasses específicas para cada tipo de infração (velocidade, rodízio, corredor de ônibus, etc.).
-BaseDeDados: Responsável por armazenar as ocorrências e multas, processando as ocorrências de acordo com as regras e exportando relatórios.
#Requisitos
-Java 8 ou superior para compilar e rodar o sistema.
-Biblioteca Gson para exportação de dados em JSON. (Incluída no pom.xml se estiver utilizando Maven ou o JAR pode ser adicionado manualmente).


#Como Rodar o Projeto
-Clone o repositório do projeto ou baixe o código-fonte em seu computador.
-Compilar o código: Se estiver usando IDE (como IntelliJ IDEA ou Eclipse), basta importar o projeto e ele cuidará da compilação automaticamente.
-Rodar o código: Para rodar a classe principal do sistema

#Testar o Sistema
-Importar Ocorrências: Para testar o sistema, primeiro você pode importar ocorrências de um arquivo de texto no formato CSV (exemplo: ocorrencias.txt). O arquivo deve ter o seguinte formato:

PLACA,LOGRADOURO,DATAS/HORA,TIPO_OCORRENCIA
TES1234,Rua TES,2024-12-07 08:00:00,1

-Processar as Ocorrências: Após importar as ocorrências, você pode rodar o método de processamento para gerar as multas.
-Exportar Relatório: Você pode exportar as multas geradas em formato CSV ou JSON com os comandos de exportação já implementados.

#Como Funciona a Lógica de Multas
-Regras de Multa:
As multas são calculadas com base nas regras configuradas no sistema, como velocidade (quando o veículo ultrapassa o limite da via), rodízio (quando o veículo circula em um dia não permitido), e corredores de ônibus (quando o veículo circula em vias exclusivas de ônibus).
-Processamento de Ocorrências:
O sistema verifica cada ocorrência e aplica a multa correspondente, dependendo da infração detectada com base nas regras.


