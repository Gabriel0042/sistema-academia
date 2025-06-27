package com.sistema_academia;

public class Agendamento {
    private String cpfAluno;
    private String dataHora;
    private String tipo; // "AULA" ou "AVALIACAO"

    public Agendamento(String cpfAluno, String dataHora, String tipo) {
        this.cpfAluno = cpfAluno;
        this.dataHora = dataHora;
        this.tipo = tipo;
    }

    // Getters
    public String getCpfAluno() { return cpfAluno; }
    public String getDataHora() { return dataHora; }
    public String getTipo() { return tipo; }
}

