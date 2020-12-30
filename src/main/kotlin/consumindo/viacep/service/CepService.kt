package consumindo.viacep.service

import consumindo.viacep.dto.CepDTO
import consumindo.viacep.integration.ViaCepIntegration
import org.springframework.stereotype.Service

@Service
class CepService (val viaCepIntegration : ViaCepIntegration) {

    fun getCep(cep: Int?): CepDTO? {
        return cep?.let { viaCepIntegration!!.buscaPeloCep(it) }
    }
}