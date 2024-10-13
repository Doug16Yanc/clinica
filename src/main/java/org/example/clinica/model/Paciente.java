package org.example.clinica.model;

public class Paciente extends Usuario {
    private String CPF;

    public Paciente(int id, String nome, String email, String senha, String telefone, String CPF) {
        super(id, nome, email, senha, telefone);
        this.CPF = CPF;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}