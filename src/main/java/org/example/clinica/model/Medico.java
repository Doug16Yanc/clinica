package org.example.clinica.model;

public class Medico extends Usuario {
    private String CRM;

    public Medico(int id, String nome, String email, String senha, String telefone, String CRM) {
        super(id, nome, email, senha, telefone);
        this.CRM = CRM;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }
}