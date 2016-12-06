package demo

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.web.bind.annotation.RestController
import java.text.SimpleDateFormat
import java.util.*

@SpringBootApplication
@RestController
@EnableJpaAuditing
open class Application : InitializingBean {

    val log = LoggerFactory.getLogger(Application::class.java)

    @Bean
    open fun customModule(): Module {
        val module = SimpleModule()
        module.addSerializer(Date::class.java, JsonDateSerializer())
        return module
    }

    @Autowired
    lateinit var bs: BootStrap;

    override fun afterPropertiesSet() {
        log.info("init after launch ... ")
        // inject data
        bs.initData();
    }
}

// entry point
fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}


class JsonDateSerializer : JsonSerializer<Date>() {
    override fun serialize(value: Date?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen?.writeString(SimpleDateFormat("yyyy-MM-dd").format(value))
    }
}
