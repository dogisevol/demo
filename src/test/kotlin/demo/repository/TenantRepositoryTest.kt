package demo.repository

import demo.Application
import demo.BootStrap
import demo.model.Tenant
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(Application::class))
class TenantRepositoryTest {

    @Autowired
    lateinit var repository: TenantRepository

    @Test
    fun testFindOne() {
        val tenant = repository.save(Tenant("Dudley", 500))
        val result = repository.findOne(tenant.id)
        assert(result.id == tenant.id)
        assert(result.name == tenant.name)
        assert(result.weeklyRentAmount == tenant.weeklyRentAmount)
    }
}
