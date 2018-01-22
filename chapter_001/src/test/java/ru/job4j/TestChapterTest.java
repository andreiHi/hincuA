package ru.job4j;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 01.09.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class TestChapterTest {
    /**
     * Test.
     */
    @Test
    public void whenWordConteinsSubwordThenTrue() {
        TestChapter testChapter = new TestChapter();
        boolean rezultTest = testChapter.contains("Привет", "иве");
        boolean ex = true;
        assertThat(rezultTest, is(ex));
    }
}
