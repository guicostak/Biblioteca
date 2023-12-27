package main.model.curso;

import java.util.ArrayList;
import java.util.List;

public class Curso {

    private String nome;

    private List<String> topicosRelacionados;

    public Curso(String nome, List<String> topicosRelacionados) {
        this.nome = nome;
        this.topicosRelacionados = topicosRelacionados;
    }

    public Curso() {
        this.topicosRelacionados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getTopicosRelacionados() {
        return topicosRelacionados;
    }

    public void setTopicosRelacionados(List<String> topicosRelacionados) {
        this.topicosRelacionados = topicosRelacionados;
    }
}
