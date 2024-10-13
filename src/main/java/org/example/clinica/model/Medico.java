package org.example.clinica.model;

public class Medico extends Usuario {
    private String CRM;
    private String especialidade;

    public Medico(){}

    public Medico(int id, String nome, String email, String senha, String telefone, String CRM, String especialidade) {
        super(id, nome, email, senha, telefone);
        this.CRM = CRM;
        this.especialidade = especialidade;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}