package demo.web

import demo.model.Receipt
import demo.model.Tenant
import demo.service.TenantService
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.internal.matchers.Contains
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class ReceiptControllerTest {

    lateinit var mockMvc: MockMvc

    lateinit var controller: ReceiptController

    @Mock
    lateinit var tenantService: TenantService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        controller = ReceiptController(tenantService)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
    }

    @Test
    fun testCreate() {
        Mockito.`when`(tenantService.createReceipt(anyObject(), Mockito.anyLong())).thenReturn(Receipt(1.toDouble(), Tenant("Bill", 100)))
        mockMvc.perform(MockMvcRequestBuilders.post("/receipt/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"amount": 500}""")
        ).andExpect(MockMvcResultMatchers.content().string(Contains("1.0")))
    }

    @Test
    fun testGet() {
        Mockito.`when`(tenantService.getReceipts(1)).thenReturn(listOf(Receipt(200.toDouble(), Tenant("Bill", 100))
                , Receipt(300.toDouble(), Tenant("Bill", 100))))
        mockMvc.perform(MockMvcRequestBuilders.get("/receipt/1")
        ).andExpect(MockMvcResultMatchers.content().string(Contains("200")))
                .andExpect(MockMvcResultMatchers.content().string(Contains("300")))
    }

    private fun <T> anyObject(): T {
        return Mockito.anyObject<T>()
    }

}
