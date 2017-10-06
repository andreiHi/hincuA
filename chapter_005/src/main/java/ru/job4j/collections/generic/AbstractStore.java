package ru.job4j.collections.generic;

/**
 * Абстракный класс для хранилищь.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 06.10.17;
 * @version $Id$
 * @since 0.1
 */
public class AbstractStore<E extends Base> implements Store {
    /**
     * Хранилище данных.
     */
    private SimpleArray<E> simpleArray;

    /**
     * Конструктор.
     * @param size размер хранилища.
     */
    public AbstractStore(int size) {
        this.simpleArray = new SimpleArray<E>(size);
    }

    /**
     * Метод добовления новых элементов в хранилище.
     * @param model элемент.
     * @return возврат добавленного элемента.
     */
    @Override
    public Base add(Base model) {
        simpleArray.add((E) model);
        return model;
    }

    /**
     * Обновление элемента.
     * @param model элемент.
     * @return возврат обновленного элемента.
     */
    @Override
    public Base update(Base model) {
        simpleArray.update((E) model);
        return model;
    }

    /**
     * Удаление элемента.
     * @param id id элемента.
     * @return удачно или нет.
     */
    @Override
    public boolean delete(String id) {
        Base base =(E) new User(id);
        return simpleArray.delete((E) base);
    }

    /**
     * возвращает элемент по номеру.
     * @param index номер.
     * @return элемент.
     */
    public Base getByIndex(int index) {
     return  simpleArray.getValue(index);
    }
}
