package dev.thriving.oss.java.library.template;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaLibraryTemplateTest {

    @Test
    void toFunkyCase() {
        // given
        String input = "hello world!!!";

        // when
        String output = JavaLibraryTemplate.toFunkyCase(input);

        // then
        assertEquals("hElLo wOrLd!!!", output);
    }

    @Test
    void toFunkyCaseBroken() {
        // given
        String input = "hello world!!!";

        // when
        String output = JavaLibraryTemplate.toFunkyCase(input);

        // then
        assertEquals("hello world!!!", output);
    }

    @Test
    @org.junit.jupiter.api.Disabled
    void toFunkyCaseDisabled() {
        // given
        String input = "hello world!!!";

        // when
        String output = JavaLibraryTemplate.toFunkyCase(input);

        // then
        assertEquals("hElLo wOrLd!!!", output);
    }
}
