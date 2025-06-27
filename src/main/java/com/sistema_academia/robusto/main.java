package com.sistema_academia.robusto;

public class Main {
    public static void main(String[] args) {
        try {
            Recepcionista recepcionista = new Recepcionista();

            // Cadastro
            Aluno aluno = new Aluno("Maria Silva", "12345678900", "maria@email.com");
            recepcionista.cadastrarAluno(aluno);

            // Agendamento
            Agendamento aula = recepcionista.agendarAula(aluno, "15/11/2023 14:00");
            System.out.println("Aula agendada: " + aula.getDataHora());

            // Consulta status
            System.out.println("Status: " + recepcionista.consultarStatusPagamento(aluno));

            // Confirmação
            recepcionista.confirmarAula(aluno, "15/11/2023 14:00");

            // Registrar pagamento
            recepcionista.registrarPagamento(aluno, false);
            System.out.println("Novo status: " + recepcionista.consultarStatusPagamento(aluno));

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
