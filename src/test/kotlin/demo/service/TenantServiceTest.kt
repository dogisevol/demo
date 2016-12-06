package demo.repository

import demo.Application
import demo.BootStrap
import demo.model.Receipt
import demo.model.Tenant
import demo.service.TenantService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(Application::class))
class TenantServiceTest {

    @Autowired
    lateinit var repository: TenantRepository
    @Autowired
    lateinit var receiptRepository: ReceiptRepository
    @Autowired
    lateinit var service: TenantService
    @Autowired
    lateinit var bootstrap: BootStrap

    @Before
    fun setup() {
    }

    @Test
    fun testCreate() {
        val result = service.create(Tenant("Test", 300))
        assert(result.name == "Test")
        assert(result.weeklyRentAmount == 300.toLong())
    }

    @Test
    fun testCreateReceipt() {
        var tenant = repository.save(Tenant("Test", 100))
        val date = tenant.paidToDate

        var receipt = service.createReceipt(Receipt(100.toDouble()), tenant.id)
        assert(receipt.amount == 100.toDouble())
        assert(receipt.tenant?.id == tenant.id)

        tenant = service.getById(tenant.id)
        assert(service.getById(tenant.id).receipts.size == 1)
        assert((tenant.paidToDate.time - date.time) == 1000 * 60 * 60 * 24 * 7L)
        assert(tenant.credit == 0.toDouble())

        service.createReceipt(Receipt(50.toDouble()), tenant.id)
        tenant = service.getById(tenant.id)
        assert(service.getById(tenant.id).receipts.size == 2)
        assert((tenant.paidToDate.time - date.time) == 1000 * 60 * 60 * 24 * 7L)
        assert(tenant.credit == 50.toDouble())

        service.createReceipt(Receipt(51.toDouble()), tenant.id)
        tenant = service.getById(tenant.id)
        assert((tenant.paidToDate.time - date.time) == 1000 * 60 * 60 * 24 * 14L)
        assert(tenant.credit == 1.toDouble())
    }

    @Test
    fun testGetReceipt() {
        var tenant = repository.findAll().iterator().next()
        val size = tenant.receipts.size
        service.createReceipt(Receipt(100.toDouble()), tenant.id)
        service.createReceipt(Receipt(200.toDouble()), tenant.id)
        service.createReceipt(Receipt(300.toDouble()), tenant.id)
        val result = service.getReceipts(tenant.id)
        assert(result.size == size + 3)
    }


    @Test
    fun testFindUpdates() {
        var tenant = service.getById(0)
        assert(tenant.receipts.size == 5)
        service.createReceipt(Receipt(100.toDouble()), tenant.id)
        service.createReceipt(Receipt(100.toDouble()), tenant.id)
        service.createReceipt(Receipt(100.toDouble()), tenant.id)
        assert(service.getReceipts(tenant.id).size == 8)
        assert(service.findUpdates(1).size == 1)
        tenant = service.getById(2)
        assert(tenant.receipts.size == 3)
        service.createReceipt(Receipt(100.toDouble()), tenant.id)
        service.createReceipt(Receipt(100.toDouble()), tenant.id)
        service.createReceipt(Receipt(100.toDouble()), tenant.id)
        assert(service.findUpdates(1).size == 2)
    }
}
