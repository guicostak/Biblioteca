package main.service.biblioteca;

import main.model.itens.ItemBiblioteca;

import java.util.List;


public interface BibliotecaService {
    void popularBiblioteca();

    void cadastrarItem();

    void editarItem(final int id);

    void pesquisar(String itemPesquisado, int opcaoPesquisar);

    void imprimirTodos();

    boolean emprestarItem(int usuarioId, int itemId) throws Exception;

    void imprimirListaUsuarios();

    void imprimirRelatorioItensPorUsuario(int userId) throws Exception;

    void imprimirRelatorioItens();

    void imprimirInteressesPorIdUsuario(int userId);

    void adicionarInteresseManualmente(int userId);

    void gerarRecomendacoesPorIdUsuario(int userId);
}