package modelo;

public class RegraVelocidade extends RegraMulta {
    private int limiteVelocidade;
    private String logradouro;

    public RegraVelocidade(int limiteVelocidade, String logradouro) {
        this.limiteVelocidade = limiteVelocidade;
        this.logradouro = logradouro;
    }

    @Override
    public Multa calcularMulta(Ocorrencia ocorrencia) {
        // Verifica se a ocorrência ultrapassou o limite de velocidade permitido
        int nivel = verificaNivelMulta(ocorrencia);
        if (nivel > 0) {
            // Se o nível da infração for maior que zero, gera uma multa correspondente
            return new Multa(ocorrencia.getPlaca(), logradouro, obterDescricaoMulta(), nivel);
        }
        // Retorna null caso não tenha ocorrido nenhuma infração
        return null;
    }

    @Override
    public int verificaNivelMulta(Ocorrencia ocorrencia) {
        // Considera que o tipo da ocorrência contém a velocidade registrada no momento do evento
        int velocidade = ocorrencia.getTipoOcorrencia();
        // Se a velocidade registrada exceder o limite permitido, retorna o nível da multa
        return velocidade > limiteVelocidade ? 3 : 0;
    }

    @Override
    public String obterDescricaoMulta() {
        // Descrição detalhada da multa, incluindo o logradouro e o limite permitido
        return "Você foi multado por excesso de velocidade no local: " + logradouro +
               ". O limite permitido é de " + limiteVelocidade + " km/h.";
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