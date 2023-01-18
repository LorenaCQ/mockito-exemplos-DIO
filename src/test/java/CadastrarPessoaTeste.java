import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class CadastrarPessoaTeste {

    @Mock
    private ApiDosCorreios apiDosCorreios;

    @InjectMocks
    private CadastrarPessoa cadastrarPessoa;

    @Test
    void validarDadosDeCadastro(){
        /*Foi criado um objeto qualquer (DadosLocalizacao), falando para o mockito:
        Quando a api dos correios for chamada, e ao inves de ir na API para fazer algo, retorna direto desse objeto que foi criado*/
        DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("GO", "Goiania", "Rua 30", "Casa", "Guanabara");
        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep("7945616")).thenReturn(dadosLocalizacao);
        Pessoa pessoa = cadastrarPessoa.cadastrarPessoa("Lorena", "8979846", LocalDate.now(), "7945616");

        /*Para ter certeza que o Mockito pegou as informações que foram passadas*/
        assertEquals("Lorena", pessoa.getNome());
        assertEquals("8979846", pessoa.getDocumento());
        assertEquals("GO", pessoa.getEndereco().getUf());
        assertEquals("Casa", pessoa.getEndereco().getComplemento());
    }
}
