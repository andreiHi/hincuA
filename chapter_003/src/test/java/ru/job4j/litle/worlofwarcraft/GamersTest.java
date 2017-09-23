package ru.job4j.litle.worlofwarcraft;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import ru.job4j.litle.worldofwarcraft.solgers.mage.MageOfElvis;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 22.09.17;
 * @version $Id$
 * @since 0.1
 */
public class GamersTest {
    /**
     * Тест.
     */
    @Test
    public void whenPowerOfDamageTests() {
        MageOfElvis mageOfElvis = new MageOfElvis();
        mageOfElvis.moveToPremium();
        mageOfElvis.setGottenBaff(0.5);
        mageOfElvis.setCurse(true);
       double result =  mageOfElvis.poverOfDamage(0.5);
        System.out.println(result);
       double re = mageOfElvis.poverOfDamage(10);
       assertThat(re, is(10.0));
    }
}
