package main.java;
import java.sql.Date;

public class Pessoa {
    private String nome;
    private String CPF;
    private String email;
    private String endereco;
    private int telefone;
    private Date dataNascimento;

    public Pessoa(String nome, String CPF, String email, String endereco, int telefone, Date dataNascimento) {
        this.nome = nome;
        this.CPF = CPF;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return CPF;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereço() {
        return endereco;
    }

    public int getTelefone() {
        return telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereço(String endereço) {
        this.endereco = endereço;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
