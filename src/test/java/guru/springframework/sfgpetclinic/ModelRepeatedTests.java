package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("repeated")
public interface ModelRepeatedTests {

    @BeforeEach
    default void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.format("Running {%s}. Iteration %s out of %s\n", testInfo.getDisplayName(), repetitionInfo.getCurrentRepetition(), repetitionInfo.getTotalRepetitions());
    }
}
