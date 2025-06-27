package com.sistema_academia;

import java.util.ArrayList;
import java.util.List;

public class AlunoAcademia {
    // Dados estáticos simulando um "banco de dados"
    private static List<Aluno> alunos = new ArrayList<>();
    private static List<Agendamento> agendamentos = new ArrayList<>();
    private static List<Pagamento> pagamentos = new ArrayList<>();

    static {
        // Inicialização dos dados estáticos
        alunos.add(new Aluno("João Silva", "11122233344", "joao@email.com", true));
        alunos.add(new Aluno("Maria Oliveira", "55566677788", "maria@email.com", true));
        alunos.add(new Aluno("Carlos Souza", "99988877766", "carlos@email.com", false));
    }

    // Classe Aluno (interna para exemplo)
    public static class Aluno {
        private String nome;
        private String cpf;
        private String email;
        private boolean ativo;

        public Aluno(String nome, String cpf, String email, boolean ativo) {
            this.nome = nome;
            this.cpf = cpf;
            this.email = email;
            this.ativo = ativo;
        }
        // Getters omitidos para brevidade
    }

    // Classe Agendamento (interna)
    public static class Agendamento {
        private String cpfAluno;
        private String dataHora;
        private String tipo; // "AULA" ou "AVALIACAO"

        public Agendamento(String cpfAluno, String dataHora, String tipo) {
            this.cpfAluno = cpfAluno;
            this.dataHora = dataHora;
            this.tipo = tipo;
        }
    }

    // Classe Pagamento (interna)
    public static class Pagamento {
        private String cpfAluno;
        private boolean pago;

        public Pagamento(String cpfAluno, boolean pago) {
            this.cpfAluno = cpfAluno;
            this.pago = pago;
        }
    }

    // Métodos estáticos para operações
    public static List<Aluno> listarAlunos() {
        return alunos; // Retorna todos os alunos
    }

    public static String consultarStatusPagamento(String cpf) {
        // Simulação: Maria está com pagamento pendente
        if ("55566677788".equals(cpf)) {
            return "PENDENTE";
        }
        return "EM DIA";
    }

    public static boolean agendarAvaliacao(String cpfAluno, String dataHora) {
        // Verifica se o aluno existe e está ativo
        boolean alunoValido = alunos.stream()
            .anyMatch(a -> a.cpf.equals(cpfAluno) && a.ativo);
        
        if (alunoValido) {
            agendamentos.add(new Agendamento(cpfAluno, dataHora, "AVALIACAO"));
            return true;
        }
        return false;
    }

    public static List<Agendamento> consultarAgendamentos(String cpfAluno) {
        // Retorna agendamentos do aluno (exemplo estático)
        List<Agendamento> resultado = new ArrayList<>();
        if ("11122233344".equals(cpfAluno)) {
            resultado.add(new Agendamento(cpfAluno, "15/11/2023 14:00", "AULA"));
        }
        return resultado;
    }

    public static void main(String[] args) {
        // Demonstração
        System.out.println("=== ALUNOS CADASTRADOS ===");
        listarAlunos().forEach(a -> System.out.println(a.nome + " - " + a.cpf));

        System.out.println("\n=== STATUS PAGAMENTO ===");
        System.out.println("Maria: " + consultarStatusPagamento("55566677788"));

        System.out.println("\n=== AGENDAMENTO ===");
        boolean sucesso = agendarAvaliacao("11122233344", "20/11/2023 10:00");
        System.out.println("Agendamento " + (sucesso ? "bem-sucedido" : "falhou"));
    }
}

