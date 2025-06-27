package com.sistema_academia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Classe Recepcionista
public class Recepcionista {
    private List<Aluno> alunos;
    private List<Agendamento> agendamentos;
    private Map<String, Boolean> statusPagamentos; //Mapeia CPF para o status de pagamento

    public Recepcionista() {
        this.alunos = new ArrayList<>();
        this.agendamentos = new ArrayList<>();
        this.statusPagamentos = new HashMap<>();
    }

    // Método para cadastrar aluno
    public void cadastrarAluno(Aluno aluno) {
        alunos.add(aluno);
        statusPagamentos.put(aluno.getCpf(), true); // Aluno cadastrado com pagamento "em dia"
    }

    // Método para agendar aula
    public boolean agendarAula(Aluno aluno, String dataHora) {
        if (aluno.isAtivo()) {
            Agendamento agendamento = new Agendamento(aluno.getCpf(), dataHora, "AULA");
            agendamentos.add(agendamento);
            return true; // Agendamento bem-sucedido
        }
        return false; // Aluno inativo, não pode agendar
    }
    // Método para confirmar aula
    public boolean confirmarAula(Aluno aluno, String dataHora) {
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getCpfAluno().equals(aluno.getCpf()) && agendamento.getDataHora().equals(dataHora)) {
                // Aqui você pode adicionar lógica para marcar a aula como confirmada
                return true; // Aula confirmada
            }
        }
        return false; // Aula não encontrada
    }
    // Método para consultar status de pagamento
    public String consultarStatusPagamento(Aluno aluno) {
        Boolean status = statusPagamentos.get(aluno.getCpf());
        return status != null && status ? "Status de pagamento: em dia" : "Status de pagamento: pendente";
    }
    // Método para enviar notificações
    public void enviarNotificacao(String mensagem) {
        // Simulação de envio de notificação
        System.out.println("Notificação enviada: " + mensagem);
    }
    // Métodos para acessar dados (opcional)
    public List<Aluno> getAlunos() {
        return alunos;
    }
    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }
}