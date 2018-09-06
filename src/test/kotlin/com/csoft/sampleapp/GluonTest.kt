package com.csoft.sampleapp

import com.csoft.gluon.Gluon
import com.csoft.gluon.spi.Router
import com.csoft.gluon.utils.Utils.sendHttpRequest
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GluonTest {

    private val gluon = Gluon(8080)
            .withRouter(
                    Router()
                            .get("/test", SampleApp.MyController())
            )

    @BeforeAll
    fun beforeAll() {
        gluon.start()
    }

    @AfterAll
    fun afterAll() {
        gluon.stop()
    }

    @Test
    fun shouldReturnBasicTestPage() {
        val response = sendHttpRequest("http://localhost:8080/test")
        assertNotNull(response.body())
        assertEquals(
                "This is the response",
                response.body()!!.string()
        )
    }
}
