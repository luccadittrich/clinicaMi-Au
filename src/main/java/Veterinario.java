package main.java;
import java.sql.Date;

public class Veterinario extends Pessoa {
    private String CRMV;
    private String especialidade;

    public Veterinario(String nome, String cpf, String email, String endereco, int telefone, Date dataNascimento, String CRMV, String especialidade) {
        super(nome, cpf, email, endereco, telefone, dataNascimento);
        this.CRMV = CRMV;
        this.especialidade = especialidade;
    }
}
