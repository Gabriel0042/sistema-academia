package com.sistema_academia;

public class Pagamento {
    private String cpfAluno;
    private boolean pago;

    public Pagamento(String cpfAluno, boolean pago) {
        this.cpfAluno = cpfAluno;
        this.pago = pago;
    }

    // Getters
    public String getCpfAluno() { return cpfAluno; }
    public boolean isPago() { return pago; }
}

