package consumindo.viacep.controller

import consumindo.viacep.service.CepService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class Cep (val cepService : CepService) {

    companion object {
        const val URL = "/cep/"
    }


    @GetMapping("$URL{cep}")
    fun getCep(@PathVariable cep: Int): ResponseEntity<*>? {
        return ResponseEntity.ok().body(cepService.getCep(cep))
    }
}