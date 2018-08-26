package com.csoft;

import com.csoft.gluon.Gluon;
import com.csoft.gluon.spi.Controller;
import com.csoft.gluon.spi.Router;
import okhttp3.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

import static com.csoft.gluon.Gluon.newGluonServer;
import static com.csoft.utils.Utils.sendHttpRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GluonTest {

    private Gluon gluon = newGluonServer(8080)
                .withRouter(
                        Router.newGluonRouter()
                        .get("/test", new App.MyController())
                );

    @BeforeAll
    void beforeAll() {
        gluon.start();
    }

    @AfterAll
    void afterAll() {
        gluon.stop();
    }

    @Test
    void shouldReturnBasicTestPage() throws IOException {
        Response response = sendHttpRequest("http://localhost:8080/test");
        assertNotNull(response.body());
        assertEquals(
                "This is the response",
                response.body().string()
        );
    }
}
