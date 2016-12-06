package demo.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.*

@JsonAutoDetect
@Entity
@EntityListeners(AuditingEntityListener::class)
data class Tenant(
        var name: String = "",
        var weeklyRentAmount: Long = 0,
        @JsonSerialize(using = JsonDateSerializer::class)
        var paidToDate: Date = Date(),
        var credit: Double = 0.toDouble(),
        @OneToMany(mappedBy = "tenant", fetch = javax.persistence.FetchType.EAGER, cascade = arrayOf(javax.persistence.CascadeType.MERGE))
        @JsonIgnore
        var receipts: List<Receipt> = emptyList(),
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
        var id: Long = -1
) {
    override fun toString() = ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(this)
}

open class JsonDateSerializer : JsonSerializer<Date>() {
    override fun serialize(value: Date?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen?.writeString(SimpleDateFormat("yyyy-MM-dd").format(value))
    }
}