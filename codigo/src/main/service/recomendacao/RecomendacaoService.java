package main.service.recomendacao;

import main.model.itens.ItemBiblioteca;
import main.model.usuario.Usuario;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public interface RecomendacaoService {

    void gerarRecomendacoesPorIdUsuario(int userId);

    boolean isItemRelevante(ItemBiblioteca item, Usuario usuario, Set<ItemBiblioteca> itensEmprestados);

    Comparator<ItemBiblioteca> getItemComparator(Usuario usuario);

    List<ItemBiblioteca> gerarRecomendacoesAleatorias();
}
