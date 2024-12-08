package modelo;

public class RegraRodizio extends RegraMulta {
    private int diaSemana;
    private String[] logradouros;
    private int digitoFinalPlaca;

    public RegraRodizio(int diaSemana, String[] logradouros, int digitoFinalPlaca) {
        this.diaSemana = diaSemana;
        this.logradouros = logradouros;
        this.digitoFinalPlaca = digitoFinalPlaca;
    }

    @Override
    public Multa calcularMulta(Ocorrencia ocorrencia) {
        // Verifica se a ocorrência infringiu as regras de rodízio
        int nivel = verificaNivelMulta(ocorrencia);
        if (nivel > 0) {
            // Se a infração ocorreu, retorna uma multa com os detalhes da violação
            return new Multa(
                ocorrencia.getPlaca(),
                String.join(", ", logradouros), // Combina os logradouros em uma única string
                obterDescricaoMulta(),
                nivel
            );
        }
        return null;
    }

    @Override
    public int verificaNivelMulta(Ocorrencia ocorrencia) {
        // Assume que o tipo da ocorrência corresponde ao dia da semana (1 a 7)
        int dia = ocorrencia.getTipoOcorrencia();
        // Obtém o último dígito da placa para verificar a regra
        int placaFinal = Integer.parseInt(
            ocorrencia.getPlaca().substring(ocorrencia.getPlaca().length() - 1)
        );
        // Retorna nível 2 de infração se o dia e o dígito final da placa corresponderem ao rodízio
        return (dia == diaSemana && placaFinal == digitoFinalPlaca) ? 2 : 0;
    }

    @Override
    public String obterDescricaoMulta() {
        // Retorna uma descrição detalhada da multa por desrespeito ao rodízio
        return "Infração de rodízio nos seguintes logradouros: " +
               String.join(", ", logradouros) +
               ". Dia do rodízio: " + diaSemana + ".";
    }
}
