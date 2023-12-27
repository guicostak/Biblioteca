package main.model.itens;

import java.time.LocalDate;

public abstract class ItemBiblioteca {

    private static int proximoId = 0;
    protected int id;
    protected String titulo;
    protected String autor;
    protected int anoPublicacao;
    protected int quantidadeTotalExemplares;
    protected static int exemplaresDisponiveis;
    protected String tipoItem;
    protected boolean emprestavel = true;

    protected boolean isEmprestado = false;

    protected int periodoEmprestimo;

    protected LocalDate dataEmprestimo;
    protected int quantidadeVezesEmprestados;

    protected String categoria;

    protected LocalDate dataDevolucao;

    public ItemBiblioteca(String titulo, String autor, int anoPublicacao, int quantidadeExemplares, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeTotalExemplares = quantidadeExemplares;
        this.exemplaresDisponiveis = quantidadeExemplares;
        this.id = proximoId++;
        this.quantidadeVezesEmprestados = 0;
        this.categoria = categoria;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public int getPeriodoEmprestimo() {
        return periodoEmprestimo;
    }

    public void setPeriodoEmprestimo(int periodoEmprestimo) {
        this.periodoEmprestimo = periodoEmprestimo;
    }

    public boolean isEmprestado() {
        return isEmprestado;
    }

    public void setEmprestado(boolean emprestado) {
        isEmprestado = emprestado;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getQuantidadeExemplares() {
        return quantidadeTotalExemplares;
    }

    public void setQuantidadeExemplares() {
        quantidadeTotalExemplares--;
    }

    public int getExemplaresDisponiveis() {
        return exemplaresDisponiveis;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void emprestar() {
        exemplaresDisponiveis--;
    }

    public boolean isEmprestavel() {
        return this.emprestavel && quantidadeTotalExemplares > 1;
    }

    public void devolver() {
        if (exemplaresDisponiveis < quantidadeTotalExemplares) {
            exemplaresDisponiveis++;
        } else {
            System.out.println("Todos os exemplares estão disponíveis na biblioteca.");
        }
    }

    public void setQuantidadeVezesEmprestados() {
        this.quantidadeVezesEmprestados++;
    }

    public int getQuantidadeVezesEmprestados() {
        return quantidadeVezesEmprestados;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
