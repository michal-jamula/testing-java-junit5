package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTests {

    @Test
    void groupedAssertions() {
        //given
        Person person = new Person(11L, "Joe", "Buck");
        //then
        assertAll("Test properties set Set",
                () -> assertEquals("Joe", person.getFirstName(), "Person's first name failed"),
                () -> assertEquals( "Buck", person.getLastName(),"Person's last name failed"));

    }

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} out of {totalRepetitions}")
    @DisplayName("Repeated test spam")
    void repeatMe() {
        //todo
    }

    @RepeatedTest(5)
    void myRepeatedTestWithDependencyInjection(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.printf("%s: %s\n", testInfo.getDisplayName(), repetitionInfo.getCurrentRepetition());
    }
}