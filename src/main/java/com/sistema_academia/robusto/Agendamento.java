package com.sistema_academia.robusto;

public class Agendamento {
    private String cpfAluno;
    private String dataHora;
    private String tipo;
    private boolean confirmado;

    public Agendamento(String cpfAluno, String dataHora, String tipo) {
        this.cpfAluno = cpfAluno;
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.confirmado = false;
    }

    public void confirmar() {
        this.confirmado = true;
    }

    // Getters
    public String getCpfAluno() { return cpfAluno; }
    public String getDataHora() { return dataHora; }
    public String getTipo() { return tipo; }
    public boolean isConfirmado() { return confirmado; }
}
