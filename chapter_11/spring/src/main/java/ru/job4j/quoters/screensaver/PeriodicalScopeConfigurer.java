package ru.job4j.quoters.screensaver;

import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;


/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.08.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class PeriodicalScopeConfigurer implements Scope {
    private static final Logger LOG = LogManager.getLogger(PeriodicalScopeConfigurer.class);

    Map<String, Pair<LocalTime, Object>> map = new HashMap<>();
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        if (map.containsKey(name)) {
            Pair<LocalTime, Object> pair = map.get(name);
            int secondsSinceLastRequest = now().getSecond() - pair.getKey().getSecond();
            if (secondsSinceLastRequest > 3) {
                map.put(name, new Pair<>(now(), objectFactory.getObject()));
            }
        } else {
            map.put(name, new Pair<>(now(), objectFactory.getObject()));
        }
        return map.get(name).getValue();
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
