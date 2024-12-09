package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.Gson;

public class BaseDeDados {
    private List<Ocorrencia> ocorrenciasNaoProcessadas = new ArrayList<>();
    private List<Ocorrencia> ocorrenciasProcessadas = new ArrayList<>();
    private List<Multa> multas = new ArrayList<>();
    private List<RegraMulta> regrasMulta = new ArrayList<>();

    // Inicializa as regras de multas
    public void inicializaRegras() {
        // Adicionando algumas regras de multa para começar
        regrasMulta.add(new RegraVelocidade(60, "Avenida Washington Luiz"));
        regrasMulta.add(new RegraVelocidade(70, "Avenida Nações Unidas"));
        regrasMulta.add(new RegraRodizio(1, new String[]{"Avenida Bandeirantes", "Avenida 23 de Maio"}, 1));
        regrasMulta.add(new RegraCorredorOnibus(6, 23, "Avenida Santo Amaro"));
        regrasMulta.add(new RegraCorredorOnibus(0, 24, "Avenida Vereador José Diniz"));
        // Mais regras podem ser adicionadas aqui conforme necessário
        regrasMulta.add(new RegraVelocidade(50, "Rua da Consolação"));
        regrasMulta.add(new RegraVelocidade(80, "Marginal Pinheiros"));
        regrasMulta.add(new RegraRodizio(2, new String[]{"Rua Augusta"}, 2));
        regrasMulta.add(new RegraRodizio(3, new String[]{"Avenida Paulista"}, 3));
        regrasMulta.add(new RegraCorredorOnibus(7, 22, "Avenida Brigadeiro Faria Lima"));
        // Continue adicionando outras regras, se necessário
    }

    // Adiciona uma nova ocorrência à lista de ocorrências não processadas
    public void adicionarOcorrencia(Ocorrencia ocorrencia) {
        ocorrenciasNaoProcessadas.add(ocorrencia);
    }

    // Processa todas as ocorrências não processadas e gera multas, se aplicável
    public void processarOcorrencias() {
        for (Ocorrencia ocorrencia : ocorrenciasNaoProcessadas) {
            // Verifica cada regra de multa e calcula a multa, se necessário
            for (RegraMulta regra : regrasMulta) {
                Multa multa = regra.calcularMulta(ocorrencia);
                if (multa != null) {
                    multas.add(multa); // Se a multa for válida, adiciona à lista
                }
            }
            ocorrenciasProcessadas.add(ocorrencia); // Marca a ocorrência como processada
        }
        // Limpa a lista de ocorrências não processadas após o processamento
        ocorrenciasNaoProcessadas.clear();
    }

    // Busca multas por data (utilizando a descrição da multa para filtrar)
    public List<Multa> buscarMultasPorData(String data) {
        return multas.stream()
                .filter(multa -> multa.getDescricao().contains(data))
                .collect(Collectors.toList());
    }

    // Busca multas por placa do veículo
    public List<Multa> buscarMultasPorPlaca(String placa) {
        return multas.stream()
                .filter(multa -> multa.getPlaca().equalsIgnoreCase(placa))
                .collect(Collectors.toList());
    }

    // Retorna uma lista de todas as ocorrências não processadas
    public List<Ocorrencia> listarOcorrenciasNaoProcessadas() {
        return new ArrayList<>(ocorrenciasNaoProcessadas);
    }

    // Retorna uma lista de todas as ocorrências processadas
    public List<Ocorrencia> listarOcorrenciasProcessadas() {
        return new ArrayList<>(ocorrenciasProcessadas);
    }

    // Retorna uma lista de todas as multas
    public List<Multa> listarMultas() {
        return new ArrayList<>(multas);
    }

    // Importa ocorrências a partir de um arquivo CSV
    public void importarOcorrenciasDeArquivo(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Divide cada linha do arquivo em campos separados por ponto e vírgula
                String[] dados = linha.split(";"); // Alterado para ";"
                if (dados.length == 4) {
                    // Cria uma nova ocorrência a partir dos dados da linha
                    String placa = dados[0].trim();
                    String logradouro = dados[1].trim();
                    String dataHora = dados[2].trim();
                    int tipoOcorrencia = Integer.parseInt(dados[3].trim());

                    if (!validarPlaca(placa)) {
                        System.err.println("Placa inválida no arquivo: " + placa);
                        continue; // Ignorar esta linha e passar para a próxima
                    }

                    if (!validarDataHora(dataHora)) {
                        System.err.println("Data e hora inválidas no arquivo: " + dataHora);
                        continue; // Ignorar esta linha e passar para a próxima
                    }

                    Ocorrencia ocorrencia = new Ocorrencia(placa, logradouro, dataHora, tipoOcorrencia);
                    adicionarOcorrencia(ocorrencia);
                } else {
                    // Caso a linha não tenha o formato esperado
                    System.err.println("Linha inválida no arquivo: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter o tipo de ocorrência: " + e.getMessage());
        }
    }
    // Retorna a lista de regras de multa
    public List<RegraMulta> getRegrasMulta() {
        return regrasMulta;
    }

    // Adiciona uma nova regra de multa à lista
    public void adicionarRegra(RegraMulta regra) {
        regrasMulta.add(regra);
        System.out.println("Regra adicionada com sucesso: " + regra.obterDescricaoMulta());
    }

    // Valida se a data e hora fornecidas estão no formato correto
    public boolean validarDataHora(String dataHora) {
        try {
            // Define o formato esperado para a data e hora
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // Tenta analisar a data e hora no formato especificado
            LocalDateTime.parse(dataHora, formatter);
            return true; // Se o formato for válido, retorna true
        } catch (DateTimeParseException e) {
            // Caso a data e hora estejam no formato errado
            System.err.println("Data e hora inválidas: " + dataHora);
            return false;
        }
    }

    // Valida se a placa do veículo está no formato correto
    public boolean validarPlaca(String placa) {
        // O formato da placa é: 3 letras, 1 número, 1 letra ou número, e 2 números
        return placa.matches("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}");
    }

    // Exporta a lista de multas para um arquivo JSON
    public void exportarMultasComoJSON(String caminhoArquivo) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            // Converte a lista de multas para JSON e escreve no arquivo
            gson.toJson(multas, writer);
            System.out.println("Relatório exportado com sucesso em JSON para: " + caminhoArquivo);
        } catch (IOException e) {
            // Caso ocorra um erro ao exportar para JSON
            System.err.println("Erro ao exportar relatório JSON: " + e.getMessage());
        }
    }

    // Exporta a lista de multas para um arquivo CSV
    public void exportarMultasComoCSV(String caminhoArquivo) {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            // Escreve o cabeçalho do arquivo CSV
            writer.append("Placa,Logradouro,Descrição,Nível Multa,Valor Multa\n");
            // Escreve cada multa no formato CSV
            for (Multa multa : multas) {
                writer.append(multa.getPlaca()).append(",")
                    .append(multa.getLogradouro()).append(",")
                    .append(multa.getDescricao()).append(",")
                    .append(String.valueOf(multa.getNivelMulta())).append(",")
                    .append(String.valueOf(multa.getValorMulta())).append("\n");
            }
            System.out.println("Relatório exportado com sucesso em CSV para: " + caminhoArquivo);
        } catch (IOException e) {
            // Caso ocorra um erro ao exportar para CSV
            System.err.println("Erro ao exportar relatório CSV: " + e.getMessage());
        }
    }
}
