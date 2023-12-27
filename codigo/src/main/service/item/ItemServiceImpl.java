package main.service.item;

import main.emprestimo.EmprestimoService;
import main.model.itens.ItemBiblioteca;
import main.model.itens.factory.*;
import main.service.gerarmassa.GerarMassaServiceImpl;
import main.service.usuario.UsuarioServiceImpl;
import main.utils.BibliotecaUtils;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class ItemServiceImpl implements ItemService {

    private final GerarMassaServiceImpl dados = GerarMassaServiceImpl.getInstance();
    private final UsuarioServiceImpl usuarioService = UsuarioServiceImpl.getInstance();

    private final GerarMassaServiceImpl gerarMassaService = new GerarMassaServiceImpl();
    private static ItemServiceImpl instancia;

    private final EmprestimoService emprestimoService = new EmprestimoService();

    private ItemServiceImpl() {
    }

    public static ItemServiceImpl getInstance() {
        if (instancia == null) {
            instancia = new ItemServiceImpl();
        }

        return instancia;
    }

    @Override
    public void cadastrarItem() {
        final var scanner = new Scanner(System.in);
        System.out.println("Qual tipo de item deseja adicionar ?");
        System.out.println("1 - CD");
        System.out.println("2 - DVD");
        System.out.println("3 - LIVRO");
        System.out.println("4 - REVISTA");
        System.out.println("5 - TESE");

        System.out.println("Qual nome do autor?");
        String autor = scanner.nextLine();
        System.out.println("Qual titulo deseja adicionar?");
        String titulo = scanner.nextLine();
        System.out.println("Qual o ano de publicação?");
        int ano = scanner.nextInt();
        System.out.println("Qual é a categoria?");
        String categoria = scanner.nextLine();
        System.out.println("Qual é a quantidade de exemplares disponíveis ?");
        int quantidadeExemplares = scanner.nextInt();

        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1 -> {
                final ItemFactoryMethod cd = new CDFactory();
                dados.getItens().add(cd.criarItem(titulo, autor, ano, quantidadeExemplares, categoria));
            }
            case 2 -> {
                final ItemFactoryMethod dvd = new DVDFactory();
                dados.getItens().add(dvd.criarItem(titulo, autor, ano, quantidadeExemplares, categoria));
            }
            case 3 -> {
                final ItemFactoryMethod livro = new LivroFactory();
                dados.getItens().add(livro.criarItem(titulo, autor, ano, quantidadeExemplares, categoria));
            }
            case 4 -> {
                final ItemFactoryMethod revista = new RevistaFactory();
                dados.getItens().add(revista.criarItem(titulo, autor, ano, quantidadeExemplares, categoria));
            }
            case 5 -> {
                final ItemFactoryMethod tese = new TeseFactory();
                dados.getItens().add(tese.criarItem(titulo, autor, ano, quantidadeExemplares, categoria));
            }
        }
    }

    public void cadastrarItem(String tipo, String autor, String titulo, int ano, String categoria, int quantidadeExemplares) {
        ItemFactoryMethod itemFactory;
        switch (tipo) {
            case "CD" -> itemFactory = new CDFactory();
            case "DVD" -> itemFactory = new DVDFactory();
            case "LIVRO" -> itemFactory = new LivroFactory();
            case "REVISTA" -> itemFactory = new RevistaFactory();
            case "TESE" -> itemFactory = new TeseFactory();
            default -> {
                return;
            }
        }
        dados.getItens().add(itemFactory.criarItem(titulo, autor, ano, quantidadeExemplares, categoria));
    }

    @Override
    public void editarItem(final int id) {

        for (ItemBiblioteca itensBiblioteca : dados.getItens()) {
            final var scanner = new Scanner(System.in);

            if (itensBiblioteca.getId() == id) {
                System.out.println("Qual nome do autor para alteração?");
                final var autor = scanner.nextLine();
                System.out.println("Qual titulo para alteração?");
                final var titulo = scanner.nextLine();
                System.out.println("Qual o ano de alteração?");
                final var ano = scanner.nextInt();
                System.out.println("Qual a tese de alteração?");
                final var tipo = scanner.nextLine();
                System.out.println("Qual a categoria de alteração?");
                final var categoria = scanner.nextLine();

                if (titulo != null) itensBiblioteca.setTitulo(titulo);
                if (autor != null) itensBiblioteca.setAutor(autor);
                if (ano != 0) itensBiblioteca.setAnoPublicacao(ano);
                if (tipo != null) itensBiblioteca.setTipoItem(tipo);
                if (categoria != null) itensBiblioteca.setCategoria(categoria);

                BibliotecaUtils.imprimirResultados(dados.getItens());
            }
        }
    }

    @Override
    public boolean emprestarItem(int idUsuario, int itemId) throws Exception {
        final var item = BibliotecaUtils.encontrarItemPorId(itemId);
        final var usuario = BibliotecaUtils.encontrarUsuarioPorId(idUsuario);
        final var random = new Random();

        if (usuario == null) {
            return false;
        }

        if (usuario.getItensEmprestados().stream().anyMatch(itemBiblioteca -> itemBiblioteca.getId() == itemId)) {
            throw new Exception("Usuário já possui um exemplar do item " + item.getTitulo());
        }

        if (!emprestimoService.isElegivelAEmprestimo(usuario, item)) {
            return false;
        }

        usuarioService.adicionarInteressePorItemEmprestado(idUsuario, item);

        if (usuario.verificaUsuario() && item.getQuantidadeExemplares() > 1) {
            item.setEmprestado(true);
            item.setDataEmprestimo(LocalDate.now());
            item.setQuantidadeExemplares();
            item.emprestar();
            item.setQuantidadeVezesEmprestados();
            usuario.adicionarItemEmprestado(item);
            item.setDataDevolucao(LocalDate.now().plusDays(15));

            return true;

        }
//        else if (item.getQuantidadeExemplares() <= 1) {
//            throw new Exception("A quantidade de exemplares disponíveis deve ser maior que 1.");
//        }

        return true;
    }
}
