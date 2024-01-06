package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("model")

class PersonTest {

    @Test
    void groupedAssertions() {
        //given
        Person person = new Person(11L, "Joe", "Buck");
        //then
        assertAll("Test properties set Set",
                () -> assertEquals("Joe", person.getFirstName(), "Person's first name failed"),
                () -> assertEquals( "Buck", person.getLastName(),"Person's last name failed"));

    }

    @Test
    void getFirstName() {
    }

    @Test
    void setFirstName() {
    }

    @Test
    void getLastName() {
    }

    @Test
    void setLastName() {
    }
}