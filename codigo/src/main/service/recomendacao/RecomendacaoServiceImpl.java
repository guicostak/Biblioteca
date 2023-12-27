package main.service.recomendacao;

import main.model.itens.ItemBiblioteca;
import main.model.usuario.Usuario;
import main.service.gerarmassa.GerarMassaServiceImpl;
import main.utils.BibliotecaUtils;

import java.util.*;
import java.util.stream.Collectors;

public class RecomendacaoServiceImpl implements RecomendacaoService {

    private final GerarMassaServiceImpl dados = GerarMassaServiceImpl.getInstance();

    private static RecomendacaoServiceImpl instancia;

    private RecomendacaoServiceImpl() {
    }

    public static RecomendacaoServiceImpl getInstance() {
        if (instancia == null) {
            instancia = new RecomendacaoServiceImpl();
        }

        return instancia;
    }

    @Override
    public void gerarRecomendacoesPorIdUsuario(int userId) {

        final var usuario = BibliotecaUtils.encontrarUsuarioPorId(userId);
        List<ItemBiblioteca> itensRelevantes;

        if (usuario == null) {
            System.out.println("Usuário com ID " + userId + " não encontrado.");
            return;
        }

        if (usuario.getCategoriasDeInteresse().isEmpty() || usuario.getCategoriasDeInteresse().size() <= 1) {
            itensRelevantes = gerarRecomendacoesAleatorias();
        }

        else {
            atualizarInteressesUsuario(usuario);
            itensRelevantes = filtrarItensRelevantes(usuario);
        }

        BibliotecaUtils.imprimirResultados(itensRelevantes);
    }

    private void atualizarInteressesUsuario(Usuario usuario) {
        if (usuario.getCurso() != null && !usuario.getCurso().getTopicosRelacionados().isEmpty()) {
            usuario.getCategoriasDeInteresse().addAll(usuario.getCurso().getTopicosRelacionados());
        }
    }

    private List<ItemBiblioteca> filtrarItensRelevantes(Usuario usuario) {
        Set<ItemBiblioteca> itensEmprestados = new HashSet<>(usuario.getItensEmprestados());

        return dados.getItens().stream()
                .filter(item -> isItemRelevante(item, usuario, itensEmprestados))
                .sorted(getItemComparator(usuario))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isItemRelevante(ItemBiblioteca item, Usuario usuario, Set<ItemBiblioteca> itensEmprestados) {
        if (itensEmprestados.contains(item)) {
            return false;
        }

        return usuario.getCategoriasDeInteresse().stream().anyMatch(interesse ->
                item.getCategoria().equalsIgnoreCase(interesse) ||
                        item.getAutor().equalsIgnoreCase(interesse) ||
                        item.getTitulo().equalsIgnoreCase(interesse) ||
                        item.getTipoItem().equalsIgnoreCase(interesse));
    }

    @Override
    public Comparator<ItemBiblioteca> getItemComparator(Usuario usuario) {

        return (ItemBiblioteca item1, ItemBiblioteca item2) -> {
            if (usuario.getCategoriasDeInteresse().contains(item1.getCategoria()) && !usuario.getCategoriasDeInteresse().contains(item2.getCategoria())) {
                return -1;
            } else if (!usuario.getCategoriasDeInteresse().contains(item1.getCategoria()) && usuario.getCategoriasDeInteresse().contains(item2.getCategoria())) {
                return 1;
            }

            if (usuario.getCategoriasDeInteresse().contains(item1.getAutor()) && !usuario.getCategoriasDeInteresse().contains(item2.getAutor())) {
                return -1;
            } else if (!usuario.getCategoriasDeInteresse().contains(item1.getAutor()) && usuario.getCategoriasDeInteresse().contains(item2.getAutor())) {
                return 1;
            }

            if (usuario.getCategoriasDeInteresse().contains(item1.getTipoItem()) && !usuario.getCategoriasDeInteresse().contains(item2.getTipoItem())) {
                return -1;
            } else if (!usuario.getCategoriasDeInteresse().contains(item1.getTipoItem()) && usuario.getCategoriasDeInteresse().contains(item2.getTipoItem())) {
                return 1;
            }

            return 0;
        };
    }

    @Override
    public List<ItemBiblioteca> gerarRecomendacoesAleatorias() {

        System.out.println("Entrou");
        final var itens = new ArrayList<>(dados.getItens());

        Collections.shuffle(itens);

        return itens.stream()
                .limit(10)
                .collect(Collectors.toList()
                );
    }
}
