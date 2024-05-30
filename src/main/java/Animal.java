package main.java;

public class Animal {
    private int id;
    private String nome, especie, raca, genero, dataNascimento, cor, tutor;
    private double peso;

    public Animal(int id, String nome, String especie, String raca, String genero, String dataNascimento, String cor,
            String tutor, double peso) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.cor = cor;
        this.tutor = tutor;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public String getGenero() {
        return genero;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getCor() {
        return cor;
    }

    public String getTutor() {
        return tutor;
    }

    public double getPeso() {
        return peso;
    }
}
