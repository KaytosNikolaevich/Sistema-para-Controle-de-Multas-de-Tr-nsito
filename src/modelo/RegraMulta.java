package modelo;

public abstract class RegraMulta {
    // Valores padrão para os níveis de multa
    protected static final double VALOR_MULTA_LEVE = 100.00;
    protected static final double VALOR_MULTA_MEDIA = 200.00;
    protected static final double VALOR_MULTA_GRAVE = 300.00;

    // Define como calcular a multa com base na ocorrência
    public abstract Multa calcularMulta(Ocorrencia ocorrencia);

    // Verifica o nível da multa (0 - Sem infração, 1 - Leve, 2 - Média, 3 - Grave)
    public abstract int verificaNivelMulta(Ocorrencia ocorrencia);

    // Retorna uma descrição sobre a infração
    public abstract String obterDescricaoMulta();
}