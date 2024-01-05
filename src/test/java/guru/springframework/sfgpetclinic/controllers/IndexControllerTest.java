package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setup() {
        controller = new IndexController();
    }

    @DisplayName("Test proper view name is returned for index page")
    @Test
    void index() {
        assertEquals("index", controller.index());
        assertEquals("index", controller.index(), "Wrong View Returned");
        assertEquals("index", controller.index(), () -> "Lambdas are an expensive way to create an error message");

    }

    @Test
    @DisplayName("Test exceptions")
    void oopsHandler() {
        //assertTrue("notimplemented".equals(controller.oopsHandler()), () -> "Lambdas are expensive to create an error message. OupsHandler failed");
        assertThrows(ValueNotFoundException.class, () -> controller.oopsHandler());
    }

    @Disabled(value = "Just an example of timeout tests - designed to fail")
    @Test
    void testTimeOut() {
        //Runs the method as long as it takes, then performs the assertion. Doesn't spawn a second thread
        assertTimeout(Duration.ofMillis(100), () -> Thread.sleep(2000));

        System.out.println("single threaded timeout got here");
    }

    @Disabled(value = "Another example of a timeout test - designed to fail too")
    @Test
    void testTimeOutPrempt() {
        //Once the duration is exceeded - it terminates your test early. The test runs in a different thread here.
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> Thread.sleep(2000));

        System.out.println("pre-emptive timeout got here");
    }
}