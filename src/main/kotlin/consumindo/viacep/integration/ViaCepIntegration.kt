package consumindo.viacep.integration

import consumindo.viacep.dto.CepDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
interface ViaCepIntegration {

    @GetMapping("{cep}/json/")
    fun buscaPeloCep(@PathVariable("cep") cep: Int): CepDTO
}