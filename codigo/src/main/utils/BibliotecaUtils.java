package main.utils;

import main.model.itens.ItemBiblioteca;
import main.model.usuario.Usuario;
import main.service.gerarmassa.GerarMassaServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BibliotecaUtils {

    private static final GerarMassaServiceImpl dados = GerarMassaServiceImpl.getInstance();

    public static void imprimirResultados(List<ItemBiblioteca> itensResultado) {
        for (ItemBiblioteca item : itensResultado) {
            System.out.println("ID: " + item.getId());
            System.out.println("Titulo: " + item.getTitulo());
            System.out.println("Autor: " + item.getAutor());
            System.out.println("Ano publicado: " + item.getAnoPublicacao());
            System.out.println("Tipo de item: " + item.getTipoItem());
            System.out.println("Quantidade de exemplares: " + item.getQuantidadeExemplares());
            System.out.println("Categoria: " + item.getCategoria());
            System.out.println();
        }
    }

    public static void imprimirResultados() {
        imprimirResultados(dados.getItens());
    }

    public static Usuario encontrarUsuarioPorId(int userId) {
        for (Usuario usuario : dados.getUsuarios()) {
            if (usuario.getId() == userId) {
                return usuario;
            }
        }
        return null;
    }

    public static ItemBiblioteca encontrarItemPorId(int itemId) throws Exception {
        for (ItemBiblioteca item : dados.getItens()) {
            if (item.getId() == itemId) {
                return item;
            }
        }
        throw new Exception("Item não encontrado");
    }

    public static void pesquisar(String valorPesquisado, int opcaoPesquisar) {
        List<ItemBiblioteca> resultados = new ArrayList<>();

        for (ItemBiblioteca item : dados.getItens()) {
            switch (opcaoPesquisar) {
                case 1 -> {
                    if (item.getTitulo().contains(valorPesquisado)) {
                        resultados.add(item);
                    }
                }
                case 2 -> {
                    if (item.getAutor().contains(valorPesquisado)) {
                        resultados.add(item);
                    }
                }
                case 3 -> {
                    if (item.getAnoPublicacao() == Integer.parseInt(valorPesquisado)) {
                        resultados.add(item);
                    }
                }
                case 4 -> {
                    if (item.getTipoItem().contains(valorPesquisado.toUpperCase())) {
                        resultados.add(item);
                    }
                }
            }
        }

        if (resultados.isEmpty()) {
            System.out.println("Nenhum resultado encontrado para: " + valorPesquisado);
        } else {
            System.out.println("Resultados para: " + valorPesquisado);
            imprimirResultados(ordenarPorTitulo(resultados));
        }
    }

    private static List<ItemBiblioteca> ordenarPorTitulo(List<ItemBiblioteca> itens) {
        itens.sort(Comparator.comparing(ItemBiblioteca::getTitulo));
        return itens;
    }
}
