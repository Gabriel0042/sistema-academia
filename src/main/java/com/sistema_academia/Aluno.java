package com.sistema_academia;

// Classe Aluno
public class Aluno {
    private String nome;
    private String cpf;
    private String email;
    private boolean ativo;

    public Aluno(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.ativo = true;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}