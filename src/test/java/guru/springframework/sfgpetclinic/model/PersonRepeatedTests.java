package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelRepeatedTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

public class PersonRepeatedTests implements ModelRepeatedTests {

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} out of {totalRepetitions}")
    @DisplayName("Repeated test spam")
    void repeatMe() {
        //todo
    }

    @RepeatedTest(5)
    void myRepeatedTestWithDependencyInjection(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.printf("%s: %s\n", testInfo.getDisplayName(), repetitionInfo.getCurrentRepetition());
    }

    @RepeatedTest(value = 5, name = "{displayName} : {currentRepetition} - {totalRepetitions}")
    @DisplayName("Assignment test")
    void assignmentTest() {
        //todo
    }
}
