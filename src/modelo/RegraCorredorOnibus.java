package modelo;

public class RegraCorredorOnibus extends RegraMulta {
    private int horaInicio; // Hora inicial da restrição
    private int horaFim;    // Hora final da restrição
    private String logradouro; // Local onde a regra se aplica

    public RegraCorredorOnibus(int horaInicio, int horaFim, String logradouro) {
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.logradouro = logradouro;
    }

    @Override
    public Multa calcularMulta(Ocorrencia ocorrencia) {
        int nivel = verificaNivelMulta(ocorrencia);
        return nivel > 0 ? new Multa(ocorrencia.getPlaca(), logradouro, obterDescricaoMulta(), nivel) : null;
    }

    @Override
    public int verificaNivelMulta(Ocorrencia ocorrencia) {
        int hora = ocorrencia.getTipoOcorrencia(); // Hora registrada na ocorrência
        return (hora >= horaInicio && hora <= horaFim) ? 1 : 0; // Infração leve se dentro do horário
    }

    @Override
    public String obterDescricaoMulta() {
        return "Multa por uso irregular de corredor de ônibus no logradouro: " + logradouro;
    }

    public boolean verificarRegra(Ocorrencia ocorrencia) {
        return calcularMulta(ocorrencia) != null;
    }

    public void aplicarMulta(Ocorrencia ocorrencia) {
        Multa multa = calcularMulta(ocorrencia);
        if (multa != null) {
            System.out.println("Multa aplicada: " + multa.getDescricao() + " - R$ " + multa.getValorMulta());
        } else {
            System.out.println("Nenhuma multa aplicada.");
        }
    }
}
