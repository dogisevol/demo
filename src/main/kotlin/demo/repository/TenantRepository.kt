package demo.repository

import demo.model.Tenant
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface TenantRepository : CrudRepository<Tenant, Long> {
    @Query("select distinct t from Tenant t inner join t.receipts as r where r.createdDate >= :createdDate")
    fun findByCreatedDateGreaterThan(@Param("createdDate") createdDate: Date): List<Tenant>
}