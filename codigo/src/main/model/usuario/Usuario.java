package main.model.usuario;

import main.model.curso.Curso;
import main.model.itens.ItemBiblioteca;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private int id;
    private static int proximoId = 1;
    private String nome;
    private int numItensEmprestados;
    private boolean emprestimoEmAtraso;
    private List<ItemBiblioteca> itensEmprestados;
    private List<String> categoriasDeInteresse;
    private Curso curso;

    public Usuario(String nome, Curso curso) {
        this.id = proximoId++;
        this.nome = nome;
        this.numItensEmprestados = 0;
        this.emprestimoEmAtraso = false;
        this.itensEmprestados = new ArrayList<>();
        this.categoriasDeInteresse = new ArrayList<>();
        this.curso = curso;
    }

    public int getId() {
        return id;
    }

    public List<ItemBiblioteca> getItensEmprestados() {
        return itensEmprestados;
    }

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumItensEmprestados() {
        return numItensEmprestados;
    }

    public void setNumItensEmprestados(int numItensEmprestados) {
        this.numItensEmprestados = numItensEmprestados;
    }

    public boolean getEmprestimoEmAtraso() {
        return emprestimoEmAtraso;
    }

    public void setEmprestimoEmAtraso(boolean emprestimoEmAtraso) {
        this.emprestimoEmAtraso = emprestimoEmAtraso;
    }

    public boolean verificaUsuario() {
        return this.numItensEmprestados < 3 || this.emprestimoEmAtraso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<String> getCategoriasDeInteresse() {
        return categoriasDeInteresse;
    }

    public void setCategoriasDeInteresse(List<String> categoriasDeInteresse) {
        this.categoriasDeInteresse = categoriasDeInteresse;
    }

    public void adicionarItemEmprestado(ItemBiblioteca item) {
        itensEmprestados.add(item);
        numItensEmprestados++;
    }
}
