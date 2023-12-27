package main.service.menu;

import main.enums.OpcoesEnum;
import main.service.biblioteca.BibliotecaServiceImpl;
import main.view.InterfaceGrafica;

import java.util.Scanner;

public class MenuService {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BibliotecaServiceImpl biblioteca = BibliotecaServiceImpl.getInstance();
    private static final InterfaceGrafica interfaceGrafica = new InterfaceGrafica();

    public static void gerarMenu() throws Exception {

        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case OpcoesEnum.POPULAR_BIBLIOTECA -> biblioteca.popularBiblioteca();
                case OpcoesEnum.PESQUISAR_ITENS -> pesquisarItens();
                case OpcoesEnum.CRIAR_ITEM -> biblioteca.cadastrarItem();
                case OpcoesEnum.EDITAR_ITEM -> biblioteca.editarItem(escolherId());
                case OpcoesEnum.IMPRIMIR_TODOS -> biblioteca.imprimirTodos();
                case OpcoesEnum.EMPRESTAR_ITEM -> emprestarItem();
                case OpcoesEnum.IMPRIMIR_RELATORIOS -> imprimirRelatorios();
                case OpcoesEnum.LISTAR_USUARIOS -> biblioteca.imprimirListaUsuarios();
                case OpcoesEnum.LISTAR_INTERESSES -> listarInteresses();
                case OpcoesEnum.ADICIONAR_INTERESSE_USUARIO -> adicionarInteresses();
                case OpcoesEnum.GERAR_RECOMENDACOES -> gerarRecomendacoes();
                case OpcoesEnum.INTERFACE_GRAFICA -> interfaceGrafica.mainInterface();
                case OpcoesEnum.SAIR -> encerrarPrograma();
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("Escolha uma opção abaixo:");
        System.out.println("1 - Popular biblioteca");
        System.out.println("2 - Pesquisar itens da biblioteca");
        System.out.println("3 - Criar item");
        System.out.println("4 - Editar item");
        System.out.println("5 - Imprimir todos");
        System.out.println("6 - Emprestar item");
        System.out.println("7 - Imprimir relatórios");
        System.out.println("8 - Listar Usuarios");
        System.out.println("9 - Listar Interesses");
        System.out.println("10 - Adicionar Interesse ao usuário");
        System.out.println("11 - Listar Recomendados");
        System.out.println("12 - Interface Grafica");
        System.out.println("-1 - Sair");
    }

    private static int escolherOpcaoPesquisar() {

        System.out.println("Digite pelo que você gostaria de pesquisar: ");
        System.out.println("1 - Titulo");
        System.out.println("2 - Autor");
        System.out.println("3 - Ano de publicação");
        System.out.println("4 - Tipo de item");

        return scanner.nextInt();
    }

    private static void encerrarPrograma() {
        System.out.println("Encerrando o programa.");
        scanner.close();
        System.exit(0);
    }

    private static void pesquisarItens() {
        int opcaoPesquisar = escolherOpcaoPesquisar();
        System.out.println("Digite o que você quer pesquisar: ");
        scanner.nextLine();
        String itemPesquisado = scanner.nextLine();

        biblioteca.pesquisar(itemPesquisado, opcaoPesquisar);
    }

    private static int escolherId() {

        System.out.println("Digite o id do item: ");

        return scanner.nextInt();
    }

    private static void emprestarItem() throws Exception {

        System.out.println("Digite o ID do item que deseja emprestar: ");
        int idItem = scanner.nextInt();
        System.out.println("Digite o ID do usuario para quem será emprestado: ");
        int idUsuario = scanner.nextInt();
        biblioteca.emprestarItem(idUsuario, idItem);
    }

    private static void imprimirRelatorios() throws Exception {

        System.out.println("Você deseja imprimir relatório por item ou por usuário?");
        System.out.println("1 - Imprimir relatório por usuário");
        System.out.println("2 - Imprimir relatório por item");
        int tipoRelatorio = scanner.nextInt();
        if (tipoRelatorio == 1) {
            System.out.println("Digite o ID do usuário que deseja ver o relatório");
            int id = scanner.nextInt();
            biblioteca.imprimirRelatorioItensPorUsuario(id);
        }
        if (tipoRelatorio == 2) biblioteca.imprimirRelatorioItens();
    }

    private static void listarInteresses() {
        System.out.println("Digite o ID do usuário que deseja ver os interesses");
        int userId = scanner.nextInt();
        biblioteca.imprimirInteressesPorIdUsuario(userId);
    }

    private static void adicionarInteresses() {
        System.out.println("Digite o ID do usuário que deseja adicionar um interesse");
        biblioteca.adicionarInteresseManualmente(scanner.nextInt());
    }

    private static void gerarRecomendacoes() {
        System.out.println("Digite o ID do usuário que deseja gerar recomendações");
        biblioteca.gerarRecomendacoesPorIdUsuario(scanner.nextInt());
    }
}
