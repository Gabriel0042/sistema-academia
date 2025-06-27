package com.sistema_academia.robusto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recepcionista {
    private List<Aluno> alunos;
    private List<Agendamento> agendamentos;
    private Map<String, Boolean> statusPagamentos;
    private List<String> notificacoesEnviadas;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Recepcionista() {
        this.alunos = new ArrayList<>();
        this.agendamentos = new ArrayList<>();
        this.statusPagamentos = new HashMap<>();
        this.notificacoesEnviadas = new ArrayList<>();
    }

    // Método para cadastrar aluno com validações
    public void cadastrarAluno(Aluno aluno) throws IllegalArgumentException {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo");
        }

        if (aluno.getCpf() == null || aluno.getCpf().isEmpty()) {
            throw new IllegalArgumentException("CPF do aluno é obrigatório");
        }

        if (alunoExiste(aluno.getCpf())) {
            throw new IllegalArgumentException("Aluno com CPF " + aluno.getCpf() + " já cadastrado");
        }

        alunos.add(aluno);
        statusPagamentos.put(aluno.getCpf(), true); // Pagamento em dia por padrão
        enviarNotificacao("Novo aluno cadastrado: " + aluno.getNome());
    }

    // Método para agendar aula completo
    public Agendamento agendarAula(Aluno aluno, String dataHoraStr) throws Exception {
        validarAluno(aluno);

        LocalDateTime dataHora = validarDataHora(dataHoraStr);

        if (aulaJaAgendada(aluno.getCpf(), dataHora)) {
            throw new IllegalStateException("Aluno já possui aula agendada para este horário");
        }

        if (!statusPagamentos.getOrDefault(aluno.getCpf(), false)) {
            throw new IllegalStateException("Aluno com pagamento pendente não pode agendar aulas");
        }

        Agendamento novoAgendamento = new Agendamento(aluno.getCpf(), dataHoraStr, "AULA");
        agendamentos.add(novoAgendamento);

        enviarNotificacao("Aula agendada para " + aluno.getNome() + " em " + dataHoraStr);

        return novoAgendamento;
    }

    // Método para confirmar aula completo
    public void confirmarAula(Aluno aluno, String dataHoraStr) throws Exception {
        validarAluno(aluno);
        LocalDateTime dataHora = validarDataHora(dataHoraStr);

        Agendamento agendamento = buscarAgendamento(aluno.getCpf(), dataHoraStr);

        if (agendamento == null) {
            throw new IllegalArgumentException("Agendamento não encontrado");
        }

        agendamento.confirmar();

        enviarNotificacao("Aula confirmada para " + aluno.getNome() + " em " + dataHoraStr);
    }

    // Método para consultar status de pagamento completo
    public String consultarStatusPagamento(Aluno aluno) throws IllegalArgumentException {
        validarAluno(aluno);

        Boolean status = statusPagamentos.get(aluno.getCpf());
        if (status == null) {
            throw new IllegalStateException("Status de pagamento não encontrado para o aluno");
        }

        return status ? "Pagamento em dia" : "Pagamento pendente";
    }

    // Método para registrar pagamento
    public void registrarPagamento(Aluno aluno, boolean pago) throws IllegalArgumentException {
        validarAluno(aluno);
        statusPagamentos.put(aluno.getCpf(), pago);

        String msg = pago
                ? "Pagamento registrado para " + aluno.getNome()
                : "Pagamento marcado como pendente para " + aluno.getNome();

        enviarNotificacao(msg);
    }

    // Método para enviar notificações completo
    public void enviarNotificacao(String mensagem) {
        if (mensagem == null || mensagem.isEmpty()) {
            System.err.println("Erro: Mensagem de notificação vazia");
            return;
        }

        // Aqui seria a integração com um sistema real de notificações
        notificacoesEnviadas.add(mensagem);
        System.out.println("[NOTIFICAÇÃO] " + mensagem);
    }

    // Métodos auxiliares privados
    private boolean alunoExiste(String cpf) {
        return alunos.stream().anyMatch(a -> a.getCpf().equals(cpf));
    }

    private void validarAluno(Aluno aluno) throws IllegalArgumentException {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo");
        }

        if (!alunoExiste(aluno.getCpf())) {
            throw new IllegalArgumentException("Aluno não cadastrado");
        }
    }

    private LocalDateTime validarDataHora(String dataHoraStr) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(dataHoraStr, formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Formato de data/hora inválido. Use dd/MM/yyyy HH:mm", dataHoraStr, 0);
        }
    }

    private boolean aulaJaAgendada(String cpf, LocalDateTime dataHora) {
        return agendamentos.stream()
                .anyMatch(a -> a.getCpfAluno().equals(cpf) &&
                        LocalDateTime.parse(a.getDataHora(), formatter).equals(dataHora));
    }

    private Agendamento buscarAgendamento(String cpf, String dataHoraStr) {
        return agendamentos.stream()
                .filter(a -> a.getCpfAluno().equals(cpf) && a.getDataHora().equals(dataHoraStr))
                .findFirst()
                .orElse(null);
    }

    // Getters para acesso aos dados
    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos); // Retorna cópia para evitar modificações externas
    }

    public List<Agendamento> getAgendamentos() {
        return new ArrayList<>(agendamentos);
    }

    public List<String> getNotificacoesEnviadas() {
        return new ArrayList<>(notificacoesEnviadas);
    }
}

