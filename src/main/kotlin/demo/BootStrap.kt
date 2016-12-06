package demo

import demo.model.Receipt
import demo.model.Tenant
import demo.service.TenantService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BootStrap @Autowired constructor(val tenantService: TenantService) {

    private val log = LoggerFactory.getLogger(BootStrap::class.java)

    fun initData() {
    }
}

