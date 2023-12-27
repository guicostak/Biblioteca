package main.service.relatorio;

public interface RelatorioService {

    void imprimirRelatorioItensPorUsuario(int userId) throws Exception;

    void imprimirRelatorioItens();
}
