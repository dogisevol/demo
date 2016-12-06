package demo.service;

import demo.model.Receipt
import demo.model.Tenant

interface TenantService {

    fun create(tenant: Tenant): Tenant

    fun getById(id: Long): Tenant

    fun createReceipt(receipt: Receipt, tenantId:Long): Receipt

    fun getReceipts(tenantId: Long) : List<Receipt>

    fun findUpdates(hours: Int) : List<Tenant>
}

