package org.example.clinica.model;

public class Paciente extends Usuario {
    private String CPF;

    public Paciente(){}

    public Paciente(String nome, String email, String telefone, String senha, String CPF) {
        super(nome, email, telefone, senha);
        this.CPF = CPF;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}