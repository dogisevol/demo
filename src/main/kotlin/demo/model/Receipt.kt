package demo.model

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.text.SimpleDateFormat
import java.util.*
import javax.annotation.Generated
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
data class Receipt(
        var amount: Double = 0.toDouble(),
        @ManyToOne(targetEntity = demo.model.Tenant::class)
        @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property = "@id")
        @JsonIgnore
        var tenant: Tenant? = null,
        @CreatedDate
        @JsonSerialize(using = JsonDateSerializer::class)
        var createdDate: Date? = null,
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
        var id: Long = -1
) {
    override fun toString() = ObjectMapper().setDateFormat(SimpleDateFormat("yyyy-MM-dd")).writer().withDefaultPrettyPrinter().writeValueAsString(this)
}


