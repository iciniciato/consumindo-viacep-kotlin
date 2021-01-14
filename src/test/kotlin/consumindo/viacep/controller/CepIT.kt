package consumindo.viacep.controller

import consumindo.viacep.dto.CepDTO
import consumindo.viacep.integration.ViaCepIntegration
import consumindo.viacep.service.CepService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.lang.Exception

@TestPropertySource(properties = ["url.via-cep=http://localhost:\${wiremock.server.port}"])
@ContextConfiguration(classes = [CepService::class, Cep::class])
@RunWith(SpringRunner::class)
@ImportAutoConfiguration(HttpMessageConvertersAutoConfiguration::class)
class CepIT {

    private var mockMvc: MockMvc? = null

    @MockBean(name = "consumindo.viacep.integration.ViaCepIntegration")
    private val viaCepIntegration: ViaCepIntegration? = null

    @Autowired
    private val cep: Cep? = null

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(cep).setControllerAdvice().build()
    }

    @Test
    @Throws(Exception::class)
    fun shouldFindCep() {
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

        Mockito.`when`(viaCepIntegration?.buscaPeloCep(cep)).thenReturn(cepDTO)
        mockMvc?.perform(
            MockMvcRequestBuilders.get(Cep.URL + cep).contentType(MediaType.APPLICATION_JSON)
        )
            ?.andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @Throws(Exception::class)
    fun NotFindCep() {
        val cep = 99999999
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
        Mockito.`when`(viaCepIntegration?.buscaPeloCep(cep)).thenReturn(cepDTO)
        mockMvc?.perform(
            MockMvcRequestBuilders.get(Cep.URL + cep).contentType(MediaType.APPLICATION_JSON)
        )
            ?.andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    @Throws(Exception::class)
    fun shouldBadReturnRequest() {
        val cep = 17012

        mockMvc?.perform(
            MockMvcRequestBuilders.get(Cep.URL + cep).contentType(MediaType.APPLICATION_JSON)
        )
            ?.andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

}