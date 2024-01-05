package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

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

    @Test
    void testAssumptionTrue() {
        //Failed assumption results in a test being aborted instead of failed - can be useful to check conditions (like the required data is resent)
        assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testOnMacOS() {}

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testOnWidows() {}

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testOnJava8() {}

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testOnJava11() {}

    @EnabledIfEnvironmentVariable(named = "USER", matches = "Micha")
    @Test
    void testIfUserJT() {}

    @EnabledIfEnvironmentVariable(named = "USER", matches = "fred")
    @Test
    void testIfUserFred() {}

}