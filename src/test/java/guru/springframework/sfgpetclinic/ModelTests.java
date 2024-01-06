package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.*;

@Tag("model")
public interface ModelTests {

    @BeforeEach
    default void beforeEach(TestInfo testInfo) {
        System.out.format("Running non-repetition model test named: {%s}\n", testInfo.getDisplayName());
    }

}
