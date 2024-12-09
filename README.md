# Integrantes:
 Kevin Dias Pereira - kevinkkdp@gmail.com - BEC


Tiago josé batschke de souza - tiagojo1234@gmail.com - BEC

# Sistema de Multas e Ocorrências de Trânsito
Este sistema foi desenvolvido para gerenciar e processar ocorrências de trânsito, calcular multas e gerar relatórios de infrações. O projeto permite a importação de dados de ocorrências, processamento dessas ocorrências para aplicar multas baseadas em regras, e exportação de relatórios em formatos CSV e JSON.
# Funcionalidades
- Cadastro e Processamento de Ocorrências:
- Adiciona ocorrências de trânsito com informações como placa, logradouro, data/hora e tipo de ocorrência.
- Processa as ocorrências para calcular as multas com base nas regras definidas.
# Regras de Multa:
- O sistema suporta várias regras para cálculo de multas, como velocidade, rodízio e corredores de ônibus.
- As multas são geradas automaticamente com base nas ocorrências e regras.
# Busca e Listagem:
- Permite buscar multas por data ou placa.
- Lista todas as ocorrências processadas, não processadas e as multas geradas.
# Exportação de Relatórios:
- Exporta as multas geradas em formato CSV ou JSON para relatórios e análises posteriores.
# Tecnologias Utilizadas
Java: Para implementação da lógica do sistema.
Gson (biblioteca): Para exportação de dados em formato JSON.
Manipulação de Arquivos (CSV e TXT): Para importação de ocorrências e exportação de relatórios.
# Estrutura do Projeto
O projeto é dividido nas seguintes classes principais:
- Ocorrencia: Representa uma ocorrência de trânsito com dados como placa, logradouro, data/hora e tipo de ocorrência.
- Multa: Representa uma multa gerada, contendo a placa do veículo, o logradouro, a descrição da infração, o nível da multa e o valor da multa.
- RegraMulta: Classe abstrata para representar uma regra de multa. Existem subclasses específicas para cada tipo de infração (velocidade, rodízio, corredor de ônibus, etc.).
- BaseDeDados: Responsável por armazenar as ocorrências e multas, processando as ocorrências de acordo com as regras e exportando relatórios.
# Requisitos
- Java 8 ou superior para compilar e rodar o sistema.
- Biblioteca Gson para exportação de dados em JSON. (Incluída no pom.xml se estiver utilizando Maven ou o JAR pode ser adicionado manualmente).
# Como Rodar o Projeto
1. Baixar o código-fonte
Clone o repositório do projeto ou baixe o código-fonte em seu computador.
bash : Master
- git clone [https://github.com/usuario/repo.git](https://github.com/KaytosNikolaevich/Sistema-para-Controle-de-Multas-de-Tr-nsito.git)


2. Compilar o código
- Se estiver usando Maven:


- mvn compile


- Se estiver usando IDE (como IntelliJ IDEA ou Eclipse), basta importar o projeto e ele cuidará da compilação automaticamente.


3. Rodar o código
Para rodar a classe principal do sistema, execute o seguinte comando (se estiver usando linha de comando):


- mvn exec:java -Dexec.mainClass="modelo.Main"


Se estiver rodando pela sua IDE, basta clicar com o botão direito na classe Main e selecionar "Run" ou "Executar".
4. Testar o Sistema
Importar Ocorrências: Para testar o sistema, primeiro você pode importar ocorrências de um arquivo de texto no formato CSV (exemplo: ocorrencias.txt). O arquivo deve ter o seguinte formato:




 PLACA,LOGRADOURO,DATAS/HORA,TIPO_OCORRENCIA
 
 ABC1234,Rua XYZ,2024-12-07 08:00:00,1




# Processar as Ocorrências 
Após importar as ocorrências, você pode rodar o método de processamento para gerar as multas.


- Exportar Relatório: Você pode exportar as multas geradas em formato CSV ou JSON com os comandos de exportação já implementados.
# Como Funciona a Lógica de Multas
- Regras de Multa:
As multas são calculadas com base nas regras configuradas no sistema, como velocidade (quando o veículo ultrapassa o limite da via), rodízio (quando o veículo circula em um dia não permitido), e corredores de ônibus (quando o veículo circula em vias exclusivas de ônibus).
Processamento de Ocorrências:
- O sistema verifica cada ocorrência e aplica a multa correspondente, dependendo da infração detectada com base nas regras.

# Destaque:
Possuimos duas mains para rodarem separadamente:

- Main: Um código que apresenta uma interface para aplicação das multas.
- Main2: Um código mais completo, que permite escolher uma opção de 1 a 8, sendo elas:
1. Importar ocorrências de arquivo
2. Cadastrar nova ocorrência
3. Processar ocorrências
4. Listar ocorrências não processadas
5. Listar multas geradas
6. Buscar multas por data
7. Buscar multas por placa
8. Cadastrar nova regra de multa
9. Exportar multas
0. Sair

- Nesta Main2, após importar ocorrência de arquivo ou cadastrar uma ocorrência, selecionar a opção 3 para processar a mesma, assim poderá localizar ela nas consultas.
