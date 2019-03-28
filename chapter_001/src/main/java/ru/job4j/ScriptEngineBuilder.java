package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 29.03.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class ScriptEngineBuilder {
    private static final Logger LOG = LogManager.getLogger(ScriptEngineBuilder.class);

    public static void main(String[] args) throws ScriptException {
        final ScriptEngine js = new ScriptEngineManager().getEngineByName("js");
        final int eval = (int) js.eval("(2 + 3)*2");
        System.out.println(eval);
    }
}
