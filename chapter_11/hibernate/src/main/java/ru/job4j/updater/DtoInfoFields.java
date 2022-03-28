package ru.job4j.updater;

import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

public enum  DtoInfoFields {
    SSO_ID(
            Dto::getSsoId,
            v -> v != null && !v.toString().isEmpty(),
            (res, val) -> res.setSsoId((String) val)),
    LAST_NAME(
            Dto::getLastName,
            v -> v != null && !v.toString().isEmpty(),
            (res, val) -> res.setLastName((String) val)),
    STRINGS(
            Dto::getStrings,
            Objects::nonNull,
            (res, val) -> res.setStrings((List<String>) val)
    );

    public final Function<Dto, ?> getValue;
    public final Predicate<Object> checkVal;
    public final BiConsumer<Entity, Object> updateVal;

    public void update(Entity doc, Dto dto) {
        Object val = getValue.apply(dto);
        if (checkVal.test(val)) {
            updateVal.accept(doc, val);
        }
    }

    DtoInfoFields(Function<Dto, Object> getValue, Predicate<Object> checkVal, BiConsumer<Entity, Object> updateVal) {
        this.getValue = getValue;
        this.checkVal = checkVal;
        this.updateVal = updateVal;
    }
}
