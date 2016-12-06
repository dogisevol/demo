package demo.service;

import demo.model.Receipt
import demo.model.Tenant
import demo.repository.ReceiptRepository
import demo.repository.TenantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Service("tenantService")
@Transactional
open class TenantServiceImpl @Autowired constructor(val tenantRepository: TenantRepository, val receiptRepository: ReceiptRepository) : TenantService {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun findUpdates(hours: Int): List<Tenant> {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.HOUR_OF_DAY, hours * -1)
        return tenantRepository.findByCreatedDateGreaterThan(calendar.getTime())
    }

    override fun createReceipt(receipt: Receipt, tenantId: Long): Receipt {
        val tenant = tenantRepository.findOne(tenantId)
        receipt.tenant = tenant
        val credit = tenant.credit + receipt.amount
        val weeks = Math.floor((credit / tenant.weeklyRentAmount))
        if (weeks > 0) {
            val calendar = Calendar.getInstance()
            calendar.time = tenant.paidToDate
            calendar.add(Calendar.DAY_OF_YEAR, weeks.toInt() * 7)
            tenant.paidToDate = calendar.time
        }
        tenant.credit = credit - weeks * tenant.weeklyRentAmount
        entityManager.merge(tenant)
        return receiptRepository.save(receipt)
    }

    override fun getReceipts(tenantId: Long): List<Receipt> {
        return tenantRepository.findOne(tenantId).receipts ?: arrayListOf()
    }

    override fun create(tenant: Tenant): Tenant {
        return tenantRepository.save(tenant)
    }

    override fun getById(id: Long): Tenant {
        return tenantRepository.findOne(id)
    }
}
