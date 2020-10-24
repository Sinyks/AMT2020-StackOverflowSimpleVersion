package ch.heigvd.amt.project.domain.tag;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class TagTest {
    static Tag tagTest;

    static TagId tagId;
    static String name;

    @BeforeAll
    static void setBeforeAll() {
        tagId = new TagId();
        name = "shell";
    }

    @BeforeEach
    void setBeforeEach() {
        tagTest = null;
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void buildFullTagAndDeepCloneTest(boolean isDeepCloneTest) {
        tagTest = Tag.builder()
                .id(tagId)
                .name(name)
                .build();

        if (isDeepCloneTest) {
            tagTest = tagTest.deepClone();
        }

        assertNotNull(tagTest);
        assertEquals(tagId, tagTest.getId());
        assertEquals(name, tagTest.getName());
    }

    @Test
    void buildMinimalTagTest() {
        tagTest = Tag.builder()
                .name(name)
                .build();

        assertNotNull(tagTest);
        assertNotNull(tagTest.getId());
    }

    @Test
    void missingMandatoryNameTest() {

        try {
            tagTest = Tag.builder()
                    .build();

            fail("did not throw expected exception");
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }

    }

}
