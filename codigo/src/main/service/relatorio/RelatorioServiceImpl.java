package main.service.relatorio;

import main.model.itens.ItemBiblioteca;
import main.service.gerarmassa.GerarMassaServiceImpl;
import main.utils.BibliotecaUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RelatorioServiceImpl implements RelatorioService {

    private static RelatorioServiceImpl instancia;

    private RelatorioServiceImpl() {
    }

    public static RelatorioServiceImpl getInstance() {
        if (instancia == null) {
            instancia = new RelatorioServiceImpl();
        }

        return instancia;
    }

    private final GerarMassaServiceImpl gerarMassaService = GerarMassaServiceImpl.getInstance();

    @Override
    public void imprimirRelatorioItensPorUsuario(int userId) throws Exception {

        final var usuario = BibliotecaUtils.encontrarUsuarioPorId(userId);

        if (usuario != null) {
            List<ItemBiblioteca> itensEmprestados = usuario.getItensEmprestados();

            System.out.println("Relatório de Itens por Usuário (ID " + userId + "):");
            System.out.println("Usuário: " + usuario.getNome());
            System.out.println("Itens Emprestados:");
            for (ItemBiblioteca item : usuario.getItensEmprestados()) {
                System.out.println("ID: " + item.getId());
                System.out.println("Titulo: " + item.getTitulo());
                System.out.println("Autor: " + item.getAutor());
                System.out.println("Ano publicado: " + item.getAnoPublicacao());
                System.out.println("Tipo de item: " + item.getTipoItem());
                System.out.println("Data de emprestimo: " + item.getDataEmprestimo());
                System.out.println("Data de devolução: " + item.getDataDevolucao());
                System.out.println();
            }
        } else {
            throw new Exception("Usuário com ID " + userId + " não encontrado.");
        }
    }

    @Override
    public void imprimirRelatorioItens() {
        List<ItemBiblioteca> copiaItens = new ArrayList<>(gerarMassaService.getItens());

        copiaItens.sort(Comparator.comparing(ItemBiblioteca::getAnoPublicacao));

        System.out.println("Relatório de Itens Mais Emprestados (por ano de publicação):");
        for (ItemBiblioteca item : copiaItens) {
            System.out.println("ID: " + item.getId());
            System.out.println("Título: " + item.getTitulo());
            System.out.println("Ano de Publicação: " + item.getAnoPublicacao());
            System.out.println("Número de exemplares: " + item.getQuantidadeExemplares());
            System.out.println("Quantidade de vezes emprestadas: " + item.getQuantidadeVezesEmprestados());
            System.out.println();
        }
    }
}
