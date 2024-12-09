package modelo;

import java.util.Scanner;
import modelo.BaseDeDados;
import modelo.Ocorrencia;
import modelo.RegraCorredorOnibus;
import modelo.RegraRodizio;
import modelo.RegraVelocidade;

public class main2 {
    public static void main(String[] args) {
        BaseDeDados baseDeDados = new BaseDeDados();
        baseDeDados.inicializaRegras();

        Scanner scanner = new Scanner(System.in);
        boolean executar = true;

        while (executar) {
            System.out.println("\n==== Sistema de Controle de Multas ====");
            System.out.println("1. Importar ocorrências de arquivo");
            System.out.println("2. Cadastrar nova ocorrência");
            System.out.println("3. Processar ocorrências");
            System.out.println("4. Listar ocorrências não processadas");
            System.out.println("5. Listar multas geradas");
            System.out.println("6. Buscar multas por data");
            System.out.println("7. Buscar multas por placa");
            System.out.println("8. Cadastrar nova regra de multa");
            System.out.println("9. Exportar multas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1: // Importar ocorrências de arquivo
                    System.out.print("Digite o caminho do arquivo CSV: ");
                    String caminhoArquivo = scanner.nextLine();
                    try {
                        baseDeDados.importarOcorrenciasDeArquivo(caminhoArquivo);
                        System.out.println("Ocorrências importadas com sucesso.");
                    } catch (Exception e) {
                        System.err.println("Erro ao importar ocorrências: " + e.getMessage());
                    }
                    break;

                case 2: // Cadastro de nova ocorrência
                    System.out.print("Digite a placa do veículo: ");
                    String placa = scanner.nextLine();
                    if (!baseDeDados.validarPlaca(placa)) {
                        System.out.println("Placa inválida! Exemplo válido: ABC1234");
                        break;
                    }

                    System.out.print("Digite o logradouro: ");
                    String logradouro = scanner.nextLine();

                    System.out.print("Digite a data e hora (yyyy-MM-dd HH:mm:ss): ");
                    String dataHora = scanner.nextLine();
                    if (!baseDeDados.validarDataHora(dataHora)) {
                        System.out.println("Data e hora inválidas! Exemplo válido: 2024-12-07 14:30:00");
                        break;
                    }

                    System.out.print("Digite o tipo de ocorrência (ex: velocidade ou hora): ");
                    int tipoOcorrencia = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha

                    Ocorrencia novaOcorrencia = new Ocorrencia(placa, logradouro, dataHora, tipoOcorrencia);
                    baseDeDados.adicionarOcorrencia(novaOcorrencia);
                    System.out.println("Ocorrência cadastrada com sucesso.");
                    break;

                case 3: // Processar ocorrências
                    baseDeDados.processarOcorrencias();
                    System.out.println("Ocorrências processadas com sucesso.");
                    break;

                case 4: // Listar ocorrências não processadas
                    System.out.println("Ocorrências não processadas:");
                    baseDeDados.listarOcorrenciasNaoProcessadas()
                            .forEach(System.out::println);
                    break;

                case 5: // Listar multas geradas
                    System.out.println("Multas geradas:");
                    baseDeDados.listarMultas().forEach(System.out::println);
                    break;

                case 6: // Buscar multas por data
                    System.out.print("Digite a data (yyyy-MM-dd): ");
                    String dataBusca = scanner.nextLine();
                    baseDeDados.buscarMultasPorData(dataBusca)
                            .forEach(System.out::println);
                    break;

                case 7: // Buscar multas por placa
                    System.out.print("Digite a placa: ");
                    String placaBusca = scanner.nextLine();
                    baseDeDados.buscarMultasPorPlaca(placaBusca)
                            .forEach(System.out::println);
                    break;

                case 8: // Cadastro de nova regra
                    System.out.println("Escolha o tipo de regra para cadastrar:");
                    System.out.println("1. Regra de Velocidade");
                    System.out.println("2. Regra de Rodízio");
                    System.out.println("3. Regra de Corredor de Ônibus");
                    System.out.print("Tipo: ");
                    int tipoRegra = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha

                    if (tipoRegra == 1) {
                        System.out.print("Digite o limite de velocidade: ");
                        int limiteVelocidade = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o logradouro: ");
                        String logradouroVelocidade = scanner.nextLine();
                        baseDeDados.adicionarRegra(new RegraVelocidade(limiteVelocidade, logradouroVelocidade));
                    } else if (tipoRegra == 2) {
                        System.out.print("Digite o dia da semana (1-7): ");
                        int diaSemana = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite os logradouros separados por vírgula: ");
                        String[] logradourosRodizio = scanner.nextLine().split(",");
                        System.out.print("Digite o dígito final da placa: ");
                        int digitoFinalPlaca = scanner.nextInt();
                        scanner.nextLine();
                        baseDeDados.adicionarRegra(new RegraRodizio(diaSemana, logradourosRodizio, digitoFinalPlaca));
                    } else if (tipoRegra == 3) {
                        System.out.print("Digite a hora de início: ");
                        int horaInicio = scanner.nextInt();
                        System.out.print("Digite a hora de fim: ");
                        int horaFim = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o logradouro: ");
                        String logradouroOnibus = scanner.nextLine();
                        baseDeDados.adicionarRegra(new RegraCorredorOnibus(horaInicio, horaFim, logradouroOnibus));
                    } else {
                        System.out.println("Tipo de regra inválido.");
                    }
                    break;

                case 9: // Exportar multas
                    System.out.println("Escolha o formato de exportação:");
                    System.out.println("1. JSON");
                    System.out.println("2. CSV");
                    System.out.print("Formato: ");
                    int formato = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Digite o caminho para salvar o relatório: ");
                    String caminhoExportacao = scanner.nextLine();

                    if (formato == 1) {
                        baseDeDados.exportarMultasComoJSON(caminhoExportacao);
                    } else if (formato == 2) {
                        baseDeDados.exportarMultasComoCSV(caminhoExportacao);
                    } else {
                        System.out.println("Formato inválido.");
                    }
                    break;

                case 0:
                    executar = false;
                    System.out.println("Saindo do sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
