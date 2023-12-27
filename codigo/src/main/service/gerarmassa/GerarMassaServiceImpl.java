package main.service.gerarmassa;

import main.model.curso.Curso;
import main.model.curso.factory.CursoFactory;
import main.model.curso.factory.CursoFactoryMethod;
import main.model.itens.ItemBiblioteca;
import main.model.usuario.Usuario;
import main.model.usuario.factory.UsuarioFactory;
import main.model.usuario.factory.UsuarioFactoryMethod;
import main.model.itens.factory.*;
import main.utils.BibliotecaUtils;

import java.util.ArrayList;
import java.util.List;

public class GerarMassaServiceImpl implements GerarMassaService {

    private final List<ItemBiblioteca> itens;

    private final List<Usuario> usuarios;

    private static GerarMassaServiceImpl instancia;

    public GerarMassaServiceImpl() {
        this.usuarios = new ArrayList<>();
        this.itens = new ArrayList<>();
    }

    public static GerarMassaServiceImpl getInstance() {
        if (instancia == null) {
            instancia = new GerarMassaServiceImpl();
        }

        return instancia;
    }

    @Override
    public void popularBiblioteca() {

        final ItemFactoryMethod cd = new CDFactory();
        final ItemFactoryMethod dvd = new DVDFactory();
        final ItemFactoryMethod livro = new LivroFactory();
        final ItemFactoryMethod revista = new RevistaFactory();
        final ItemFactoryMethod tese = new TeseFactory();
        final UsuarioFactoryMethod usuario = new UsuarioFactory();
        final CursoFactoryMethod curso = new CursoFactory();

        this.itens.add(cd.criarItem("Bohemian Rhapsody", "Queen", 1975, 3, "Rock"));
        this.itens.add(cd.criarItem("Highway to Hell", "ACDC", 1979, 10, "Rock"));
        this.itens.add(cd.criarItem("Sweet Child O' Mine", "Guns N' Roses", 1987, 1, "Rock"));
        this.itens.add(cd.criarItem("Images and Words", "Dream Theater", 1982, 5, "Rock"));
        this.itens.add(cd.criarItem("Temple of Shadows", "ANGRA", 2003, 2, "Rock"));
        this.itens.add(cd.criarItem("Rebirth", "ANGRA", 2001, 1, "Rock"));

        this.itens.add(dvd.criarItem("Monstros S.A.", "Pixar", 2001, 10, "Infatil"));
        this.itens.add(dvd.criarItem("Top Gun", "Paramount Pictures", 1986, 5, "Ação"));
        this.itens.add(dvd.criarItem("Matrix", "Warner Bros.", 1999, 5, "Ficção Científica"));
        this.itens.add(dvd.criarItem("Tropa de Elite", "Zazen Produções", 2007, 3, "Ação"));
        this.itens.add(dvd.criarItem("O Hobbit", "Peter Jackson", 2012, 1, "Fantasia"));
        this.itens.add(dvd.criarItem("A Origem", "Christopher Nolan", 2006, 2, "Ficção Científica"));

        this.itens.add(livro.criarItem("Dom Casmurro", "Machado de Assis", 1899, 10, "Romance"));
        this.itens.add(livro.criarItem("1984", "George Orwell", 1954, 3, "Romance"));
        this.itens.add(livro.criarItem("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, 1, "Fantasia"));
        this.itens.add(livro.criarItem("A Tormenta de Espadas", "George R. R. Martin", 2000, 4, "Fantasia"));
        this.itens.add(livro.criarItem("A Fúria dos Reis", "George R. R. Martin", 1998, 4, "Aventura"));
        this.itens.add(livro.criarItem("O Alquimista", "Paulo Coelho", 1988, 5, "Romance"));
        this.itens.add(livro.criarItem("Memórias Póstumas de Brás Cubas", "Machado de Assis", 1881, 4, "Clássico"));
        this.itens.add(livro.criarItem("A volta do mundo em 80 dias", "Julio Verne", 1872, 3, "Romance"));

        this.itens.add(revista.criarItem("National Geographic", "National Geographic Society", 2005, 30, "Ciência"));
        this.itens.add(revista.criarItem("Time", "Time Inc.", 2018, 10, "Notícias"));
        this.itens.add(revista.criarItem("Scientific American", "Springer Nature", 2023, 5, "Ciência"));
        this.itens.add(revista.criarItem("Veja", "Editora Abril", 2023, 5, "Notícias"));
        this.itens.add(revista.criarItem("Quatro Rodas", "Editora Globo", 2022, 20, "Automobilismo"));
        this.itens.add(revista.criarItem("Superinteressante", "Editora Abril", 2021, 13, "Ciência"));

        this.itens.add(tese.criarItem("Astronomia Observacional em Buracos Negros", "Maria da Silva", 2020, 1, "Ciência"));
        this.itens.add(tese.criarItem("Inteligência Artificial na Medicina", "João Santos", 2019, 10, "Tecnologia"));
        this.itens.add(tese.criarItem("Sustentabilidade na Indústria", "Ana Oliveira", 2022, 1, "Ciência"));
        this.itens.add(tese.criarItem("Impacto da Cultura Afro-Brasileira na Sociedade", "Fernanda Oliveira", 2020, 4, "Ciência"));
        this.itens.add(tese.criarItem("Desenvolvimento Sustentável na Agricultura Familiar", "Ricardo Santos", 2019, 3, "Notícias"));
        this.itens.add(tese.criarItem("Inovações Tecnológicas na Educação", "Carla Silva", 2022, 2, "Notícias"));

        List<String> cursoEngenharia = new ArrayList<>();
        cursoEngenharia.add("Tecnologia");
        cursoEngenharia.add("Engenharia");
        cursoEngenharia.add("Robotica");

        List<String> cursoMedicina = new ArrayList<>();
        cursoMedicina.add("Ciência");
        cursoMedicina.add("Natureza");

        Curso curso1 = new Curso("Engenharia de Software", cursoEngenharia);
        Curso curso2 = new Curso("Medicina", cursoMedicina);

        this.usuarios.add(usuario.criarUsuario("Paulo", curso1));
        this.usuarios.add(usuario.criarUsuario("Joaquim", curso2));

        BibliotecaUtils.imprimirResultados(itens);
    }

    public List<ItemBiblioteca> getItens() {
        return itens;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
