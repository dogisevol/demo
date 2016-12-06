package demo.web

import demo.model.Tenant
import demo.service.TenantService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tenant")
class TenantController @Autowired constructor(val tenantService: TenantService) {

    @RequestMapping("/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseBody
    fun getTenant(@PathVariable id: Long)
            = tenantService.getById(id)

    @RequestMapping("", method = arrayOf(RequestMethod.POST), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseBody()
    fun create(@RequestBody tenant: Tenant): Tenant = tenantService.create(tenant)

    @RequestMapping("updates/{hours}", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseBody
    fun getUpdates(@PathVariable hours: Int)
            = tenantService.findUpdates(hours)
}