package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Andrei Hincu (andreih1981@gmail.com)
* @version $Id$
* @since 0.1
*/
public class CalculateTest {
/**
* Test echo.
*/
	@Test
public void whenTakeNameThenTreeEchoPlusName() {
    String input = "Petr Arsentev";
	String expect = "Echo, echo, echo : Petr Arsentev";
    Calculate calc = new Calculate();
    String result = calc.echo(input);
    assertThat(result, is(expect));
  }
}