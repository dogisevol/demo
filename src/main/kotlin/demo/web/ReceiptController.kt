package demo.web

import demo.model.Receipt
import demo.model.Tenant
import demo.service.TenantService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/receipt")
class ReceiptController @Autowired constructor(val tenantService: TenantService) {

    @RequestMapping("/{tenantId}", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseBody
    fun getReceipts(@PathVariable tenantId: Long)
            = tenantService.getReceipts(tenantId)

    @RequestMapping("/{tenantId}", method = arrayOf(RequestMethod.POST), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseBody
    fun create(@RequestBody receipt: Receipt, @PathVariable tenantId: Long) = "${tenantService.createReceipt(receipt, tenantId)}"
}

