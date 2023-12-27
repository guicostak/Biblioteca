package main.service.usuario;

import main.model.curso.Curso;
import main.model.itens.ItemBiblioteca;
import main.model.usuario.Usuario;
import main.service.gerarmassa.GerarMassaServiceImpl;
import main.utils.BibliotecaUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioServiceImpl implements UsuarioService {

    private static UsuarioServiceImpl instancia;

    private final GerarMassaServiceImpl dados = GerarMassaServiceImpl.getInstance();

    public static UsuarioServiceImpl getInstance() {
        if (instancia == null) {
            instancia = new UsuarioServiceImpl();
        }

        return instancia;
    }

    @Override
    public void imprimirListaUsuarios() {
        System.out.println("Lista de Usuários:");

        for (Usuario usuario : dados.getUsuarios()) {
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println();
        }
    }

    @Override
    public void adicionarInteressePorItemEmprestado(int userId, ItemBiblioteca item) {

        final var usuario = encontrarUsuario(userId);

        if (usuario != null) {
            List<String> listaInteresses = usuario.getCategoriasDeInteresse();
            if (listaInteresses == null) {
                listaInteresses = new ArrayList<>();
            }

            adicionarInteressesDoItem(listaInteresses, item);

            usuario.setCategoriasDeInteresse(listaInteresses);
        }
    }

    private void adicionarInteressesDoItem(List<String> listaInteresses, ItemBiblioteca item) {
        listaInteresses.add(item.getAutor());
        listaInteresses.add(item.getTipoItem());
        listaInteresses.add(item.getTitulo());
        listaInteresses.add(item.getCategoria());
    }

    @Override
    public void adicionarInteresseManualmente(int userId) {

        final var usuario = encontrarUsuario(userId);

        if (usuario != null) {
            final var scanner = new Scanner(System.in);
            System.out.println("Digite um interesse: ");
            String interesse = scanner.nextLine();

            List<String> listaInteresses = usuario.getCategoriasDeInteresse();
            if (listaInteresses == null) {
                listaInteresses = new ArrayList<>();
            }

            listaInteresses.add(interesse);
            System.out.println("Interesse adicionado com sucesso!");
        }
    }

    @Override
    public void imprimirInteressesPorIdUsuario(int userId) {

        final var usuario = encontrarUsuario(userId);

        if (usuario != null) {
            List<String> listaInteresses = usuario.getCategoriasDeInteresse();

            if (listaInteresses != null && !listaInteresses.isEmpty()) {
                System.out.println("Interesses do usuário com ID " + userId + ":");

                for (String interesse : listaInteresses) {
                    System.out.println(interesse);
                }
                System.out.println();
            } else {
                System.out.println("O usuário com ID " + userId + " não tem interesses.");
            }
        } else {
            System.out.println("Usuário com ID " + userId + " não encontrado.");
        }
    }

    public void cadastrarUsuarios(String nome, String curso, String relacionados) {
        final var novoCurso = criarCurso(curso, relacionados);
        final var usuario = new Usuario(nome, novoCurso);

        usuario.setCategoriasDeInteresse(obterListaInteresses(relacionados));
        dados.getUsuarios().add(usuario);
    }

    private Curso criarCurso(String curso, String relacionados) {
        final var novoCurso = new Curso();
        novoCurso.setNome(curso);

        if (!relacionados.isEmpty()) {
            novoCurso.setTopicosRelacionados(obterListaInteresses(relacionados));
        }

        return novoCurso;
    }

    private List<String> obterListaInteresses(String relacionados) {
        List<String> topicos = new ArrayList<>();

        if (relacionados.contains(",")) {
            String[] relacionadosParts = relacionados.split(",");

            for (String parte : relacionadosParts) {
                topicos.add(parte.trim());
            }
        }

        if (!relacionados.contains(",")) {
            topicos.add(relacionados);
        }

        return topicos;
    }

    private Usuario encontrarUsuario(int userId) {
        return BibliotecaUtils.encontrarUsuarioPorId(userId);
    }
}
