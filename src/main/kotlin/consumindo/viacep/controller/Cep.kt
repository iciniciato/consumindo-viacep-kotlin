package consumindo.viacep.controller

import consumindo.viacep.service.CepService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class Cep (val cepService : CepService) {

    @GetMapping("/cep/{cep}")
    fun getCep(@PathVariable cep: Int): ResponseEntity<*>? {
        val responseEntity: ResponseEntity<*>
        val cepSize = cep.toString().length
        responseEntity = if (cepSize != 8) {
            ResponseEntity.badRequest().body("O cep deve conter 8 dígitos.")
        } else {
            val cepDTO = cepService.getCep(cep)
            if (Objects.isNull(cepDTO!!.getCep())) {
                ResponseEntity.notFound().build()
            } else {
                ResponseEntity.ok().body<Any>(cepDTO)
            }
        }
        return responseEntity
    }
}