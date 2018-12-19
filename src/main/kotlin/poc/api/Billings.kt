package poc.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import poc.repositories.BillingsRepository
import poc.repositories.SearchRepository
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/billings")
class Billings {

    @Autowired
    lateinit var billingsRepo : BillingsRepository

    @Autowired
    lateinit var searchRepository: SearchRepository

    /*
    @GetMapping
    fun all() = billingsRepo.findAll()
    */

    @GetMapping("{codeclient}")
    fun byCodeClient(@PathVariable("codeclient") codeClient: String) = billingsRepo.findByCodeClient(codeClient)

    @GetMapping("{codeclient}/{refuser}")
    fun byCodeClient(@PathVariable("codeclient") codeClient: String, @PathVariable("refuser") refUser: String) = billingsRepo.findByCodeClientAndRefUser(codeClient, refUser)

    @GetMapping("{codeclient}/{refuser}/{refMaileva}")
    fun byCodeClient(@PathVariable("codeclient") codeClient: String, @PathVariable("refuser") refUser: String, @PathVariable("refMaileva") refMaileva: String) = billingsRepo.findByCodeClientAndRefUserAndRefMaileva(codeClient, refUser, refMaileva)

    @GetMapping("{codeclient}/{refuser}/{dateRealisation}/search")
    fun search(@PathVariable("codeclient") codeClient: String,
               @PathVariable("refuser") refUser: String,
               @PathVariable("dateRealisation") dateRealisation: String,
               @RequestParam("refMaileva") refMaileva: String) = searchRepository.searchByRefMaileva(codeClient, refUser, dateRealisation, refMaileva)

    @Autowired
    lateinit var httpServletRequest: HttpServletRequest

    @GetMapping("search")
    fun searchAny() = searchRepository.searchAny(httpServletRequest.queryString)
}