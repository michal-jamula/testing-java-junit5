package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}