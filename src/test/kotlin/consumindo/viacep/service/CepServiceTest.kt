package consumindo.viacep.service

import consumindo.viacep.dto.CepDTO
import consumindo.viacep.integration.ViaCepIntegration
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.web.server.ResponseStatusException

@RunWith(MockitoJUnitRunner::class)
class CepServiceTest {

    @InjectMocks
    private val cepService: CepService? = null

    @Mock
    private val viaCepIntegration: ViaCepIntegration? = null

    @Test(expected = ResponseStatusException::class)
    fun whenCepMinusThenEight() {
        cepService!!.getCep(1234567)
    }

    @Test(expected = ResponseStatusException::class)
    fun whenCepNotFound() {
        val cep = 99999999
        //Não soube instanciar um objeto vazio, essa foi a única forma que encontrei
        val cepDTO: CepDTO = CepDTO(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        Mockito.`when`(viaCepIntegration!!.buscaPeloCep(cep)).thenReturn(cepDTO)
        cepService!!.getCep(cep)
    }

    @Test
    fun whenCepFound() {
        val cep = 17012170
        val cepDTO: CepDTO = CepDTO(
            "17012170",
            "Rua São Gonçalo",
            "de Quadra 4 ao fim",
            "Vila Altinópolis",
            "Bauru",
            "SP",
            "3506003",
            "2094",
            "14",
            "6219"
        )
        Mockito.`when`(viaCepIntegration!!.buscaPeloCep(cep)).thenReturn(cepDTO)
        cepService!!.getCep(cep)
    }
}