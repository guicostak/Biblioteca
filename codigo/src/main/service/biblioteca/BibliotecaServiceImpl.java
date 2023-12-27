package main.service.biblioteca;

import main.model.itens.ItemBiblioteca;
import main.service.gerarmassa.GerarMassaServiceImpl;
import main.service.item.ItemServiceImpl;
import main.service.recomendacao.RecomendacaoServiceImpl;
import main.service.relatorio.RelatorioServiceImpl;
import main.service.usuario.UsuarioServiceImpl;
import main.utils.BibliotecaUtils;

import java.util.List;

public class BibliotecaServiceImpl implements BibliotecaService {

    private static BibliotecaServiceImpl instancia;
    private final ItemServiceImpl itemService = ItemServiceImpl.getInstance();
    private final UsuarioServiceImpl usuarioService = UsuarioServiceImpl.getInstance();
    private final RecomendacaoServiceImpl recomendacaoService = RecomendacaoServiceImpl.getInstance();
    private final GerarMassaServiceImpl gerarMassaService = GerarMassaServiceImpl.getInstance();

    private final RelatorioServiceImpl relatorioService = RelatorioServiceImpl.getInstance();


    private BibliotecaServiceImpl() {
    }

    public static BibliotecaServiceImpl getInstance() {
        if (instancia == null) {
            instancia = new BibliotecaServiceImpl();
        }

        return instancia;
    }

    @Override
    public void pesquisar(String valorPesquisado, int opcaoPesquisar) {
        BibliotecaUtils.pesquisar(valorPesquisado, opcaoPesquisar);
    }

    public void imprimirResultados(List<ItemBiblioteca> itensResultado) {
        BibliotecaUtils.imprimirResultados(itensResultado);
    }

    public void imprimirTodos() {
        BibliotecaUtils.imprimirResultados();
    }

    @Override
    public void popularBiblioteca() {
        gerarMassaService.popularBiblioteca();
    }

    public void cadastrarItem() {
        itemService.cadastrarItem();
    }

    @Override
    public void editarItem(final int id) {
        itemService.editarItem(id);
    }

    @Override
    public boolean emprestarItem(int idUsuario, int itemId) throws Exception {
        return itemService.emprestarItem(idUsuario, itemId);
    }

    public void imprimirRelatorioItens() {
        relatorioService.imprimirRelatorioItens();
    }

    public void imprimirRelatorioItensPorUsuario(int userId) throws Exception {
        relatorioService.imprimirRelatorioItensPorUsuario(userId);
    }

    public void imprimirListaUsuarios() {
        usuarioService.imprimirListaUsuarios();
    }

    public void adicionarInteressePorItemEmprestado(int userId, ItemBiblioteca item) {
        usuarioService.adicionarInteressePorItemEmprestado(userId, item);
    }

    public void adicionarInteresseManualmente(int userId) {
        usuarioService.adicionarInteresseManualmente(userId);
    }

    public void imprimirInteressesPorIdUsuario(int userId) {
        usuarioService.imprimirInteressesPorIdUsuario(userId);
    }

    public void gerarRecomendacoesPorIdUsuario(int userId) {
        recomendacaoService.gerarRecomendacoesPorIdUsuario(userId);
    }
}
