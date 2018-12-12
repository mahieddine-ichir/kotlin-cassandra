package poc.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import poc.repositories.BillingsRepository

@RestController
@RequestMapping("/billings")
class Billings {

    @Autowired
    lateinit var billingsRepo : BillingsRepository

    @GetMapping
    fun all() = billingsRepo.findAll()

    @GetMapping("{codeclient}")
    fun byCodeClient(@PathVariable("codeclient") codeClient: String) = billingsRepo.findByCodeClient(codeClient)

    @GetMapping("{codeclient}/{refuser}")
    fun byCodeClient(@PathVariable("codeclient") codeClient: String, @PathVariable("refuser") refUser: String) = billingsRepo.findByCodeClientAndRefUser(codeClient, refUser)
}