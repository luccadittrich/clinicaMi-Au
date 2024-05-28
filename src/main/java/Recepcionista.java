package main.java;
import java.sql.Date;

public class Recepcionista extends Pessoa{
    private double ramal;

    public Recepcionista(String nome, String cpf, String email, String endereco, int telefone, Date dataNascimento, double ramal){
        super(nome, cpf, email, endereco, telefone, dataNascimento);
        this.ramal = ramal;
    }
}
