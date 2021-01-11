package consumindo.viacep.service

import consumindo.viacep.dto.CepDTO
import consumindo.viacep.integration.ViaCepIntegration
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class CepService(val viaCepIntegration: ViaCepIntegration) {

    fun getCep(cep: Int?): CepDTO? {
        val cepSize = cep.toString().length
        val cepDTO: CepDTO

        if (cepSize != 8) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP deve conter 8 dígitos.")
        } else {
            cepDTO = cep?.let { viaCepIntegration.buscaPeloCep(it) }!!
            if (Objects.isNull(cepDTO.cep)) {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "CEP não encontrado.")
            }
        }
        return cepDTO
    }
}
