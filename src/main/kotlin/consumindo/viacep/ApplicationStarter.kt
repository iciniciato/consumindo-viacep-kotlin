package consumindo.viacep

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class ApplicationStarter

fun main(args: Array<String>) {
	runApplication<ApplicationStarter>(*args)
}
