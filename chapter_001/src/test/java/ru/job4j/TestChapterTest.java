package ru.job4j;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestChapterTest {

    @Test
    public void whenWordConteinsSubwordThenTrue() throws InterruptedException {
        TestChapter testChapter = new TestChapter();
        boolean rezultTest = testChapter.contains("Привет", "иве");
        boolean ex = true;
        assertThat(rezultTest, is(ex));

    }
}
