package demo.web

import demo.model.Tenant
import demo.service.TenantService
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.internal.matchers.Contains
import org.mockito.internal.matchers.Find
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class TenantControllerTest {

    lateinit var mockMvc: MockMvc

    @Mock
    lateinit var tenantService: TenantService

    @InjectMocks
    lateinit var controller: TenantController

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
    }

    @Test
    fun testGetTenant() {
        Mockito.`when`(tenantService.getById(1)).thenReturn(Tenant("Test1", 100))
        mockMvc.perform(MockMvcRequestBuilders.get("/tenant/1"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(Find("id.*:.*0")))
    }


    @Test
    fun testCreate() {
        Mockito.`when`(tenantService.create(anyObject())).thenReturn(Tenant("Test", 100))
        mockMvc.perform(MockMvcRequestBuilders.post("/tenant").content("""{"name": "Test", "weeklyRentAmount": 300}""")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(Find("id.*:.*0")))
                .andExpect(MockMvcResultMatchers.content().string(Find("name.*:.*Test")))
    }

    @Test
    fun testGetUpdates() {
        Mockito.`when`(tenantService.findUpdates(1)).thenReturn(listOf(Tenant("Test1", 100), Tenant("Test2", 100)))
        mockMvc.perform(MockMvcRequestBuilders.get("/tenant/updates/1"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(Contains("Test1")))
                .andExpect(MockMvcResultMatchers.content().string(Contains("Test2")))
    }

    private fun <T> anyObject(): T {
        return Mockito.anyObject<T>()
    }
}
