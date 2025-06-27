import com.sistema_academia.Agendamento;
import com.sistema_academia.Aluno;
import com.sistema_academia.Pagamento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AcademiaServiceTest {

    @Test
    public void testCriarAlunoValido() {
        Aluno aluno = new Aluno("João Silva", "11122233344", "joao@email.com");
        assertEquals("João Silva", aluno.getNome());
        assertTrue(aluno.isAtivo()); // Deve estar ativo por padrão
    }

    @Test
    public void testAgendamentoAula() {
        Aluno aluno = new Aluno("Maria Oliveira", "55566677788", "maria@email.com");
        Agendamento agendamento = new Agendamento(aluno.getCpf(), "15/11/2023 14:00", "AULA");

        assertEquals("55566677788", agendamento.getCpfAluno());
        assertEquals("AULA", agendamento.getTipo());
    }

    @Test
    public void testPagamentoStatus() {
        Pagamento pagamento = new Pagamento("11122233344", true);
        assertTrue(pagamento.isPago());
    }

    @Test
    public void testDesativarAluno() {
        Aluno aluno = new Aluno("Carlos Souza", "99988877766", "carlos@email.com");
        aluno.setAtivo(false);
        assertFalse(aluno.isAtivo());
    }

    @Test
    public void testAgendamentoTipoInvalido() {
        // Teste para garantir que o tipo não é nulo
        assertThrows(NullPointerException.class, () -> {
            new Agendamento("12345678900", "20/11/2023 10:00", null);
        });
    }
}
