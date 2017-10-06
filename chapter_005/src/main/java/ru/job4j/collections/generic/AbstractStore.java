package ru.job4j.collections.generic;

/**
 * Абстракный класс для хранилищь.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 06.10.17;
 * @version $Id$
 * @since 0.1
 */
public class AbstractStore implements Store {
    /**
     * Хранилище данных.
     */
    private SimpleArray<Base> simpleArray;

    /**
     * Конструктор.
     * @param size размер хранилища.
     */
    public AbstractStore(int size) {
        this.simpleArray = new SimpleArray<>(size);
    }

    /**
     * Метод добовления новых элементов в хранилище.
     * @param model элемент.
     * @return возврат добавленного элемента.
     */
    @Override
    public Base add(Base model) {
        simpleArray.add(model);
        return model;
    }

    /**
     * Обновление элемента.
     * @param model элемент.
     * @return возврат обновленного элемента.
     */
    @Override
    public Base update(Base model) {
        simpleArray.update(model);
        return model;
    }

    /**
     * Удаление элемента.
     * @param id id элемента.
     * @return удачно или нет.
     */
    @Override
    public boolean delete(String id) {
        return simpleArray.delete(new User(id));
    }

    /**
     * возвращает элемент по номеру.
     * @param index номер.
     * @return элемент.
     */
    public Base getByIndex(int index) {
     return    simpleArray.getValue(index);
    }
}
