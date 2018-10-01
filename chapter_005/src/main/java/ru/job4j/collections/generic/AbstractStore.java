package ru.job4j.collections.generic;

/**
 * Абстракный класс для хранилищь.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 06.10.17;
 * @version $Id$
 * @since 0.1
 * @param <E> тип хранимых данных.
 */
public class AbstractStore<E extends Base> implements Store<E> {
    /**
     * Хранилище данных.
     */
    private SimpleArray<E> simpleArray;
    /**
     * размер хранилища.
     */
    private int size;
    /**
     * Конструктор.
     * @param size размер хранилища.
     */
    public AbstractStore(int size) {
        this.simpleArray = new SimpleArray<E>(size);
        this.size = size;
    }

    /**
     * Метод добовления новых элементов в хранилище.
     * @param model элемент.
     * @return возврат добавленного элемента.
     */
    @Override
    @SuppressWarnings("unchecked")
    public E add(E model) {
        simpleArray.add(model);
        return model;
    }

    /**
     * Обновление элемента.
     * @param model элемент.
     * @return возврат обновленного элемента.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean update(String id, E model) {
        boolean update = false;
        for (int i = 0; i < size; i++) {
            if (simpleArray.get(i).getId().equals(id)) {
                model.setId(id);
                update = simpleArray.update(i, model);
                break;
            }
        }
        return update;
    }

    /**
     * Удаление элемента.
     * @param id id элемента.
     * @return удачно или нет.
     */
    @Override
    public boolean delete(String id) {
        E base = null;
        for (int i = 0; i < size; i++) {
            base = simpleArray.getValue(i);
            if (id.equals(base.getId())) {
                simpleArray.delete(i);
                break;
            }
        }
        return base != null;
    }

    /**
     * возвращает элемент по номеру.
     * @param index номер.
     * @return элемент.
     */
    public E getByIndex(int index) {
        return  simpleArray.getValue(index);
    }
}
