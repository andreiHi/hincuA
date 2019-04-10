package ru.job4j.refactor;


import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 03.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Metric {

    public static final String TOTAL = "< total";

    private String nettNgLevel;
    private String tradeNumber;
    private String counterParty;
    private String book;
    private String portfolio;
    private String curve;
    private String bucket;
    private String backetPillar;

    /**
     * Метод сравнивает поля и если они разные присваивает значение по умолчанию
     * экземпляру текушего класса
     * @param m1 первый объект
     * @param m2 второй объект
     */
    public void setTotalIfNotEguals(Metric m1, Metric m2) {
        this.nettNgLevel =  fold(m1, m2, Metric::getNettNgLevel);
        this.tradeNumber =  fold(m1, m2, Metric::getTradeNumber);
        this.counterParty = fold(m1, m2, Metric::getCounterParty);
        this.book =         fold(m1, m2, Metric::getBook);
        this.portfolio =    fold(m1, m2, Metric::getPortfolio);
        this.curve =        fold(m1, m2, Metric::getCurve);
        this.bucket =       fold(m1, m2, Metric::getBucket);
        this.backetPillar = fold(m1, m2, Metric::getBacketPillar);
    }

    public void foldFields(Metric m1, Metric m2) {
        Function<Function<Metric, String>, String> folder = getter -> fold(m1, m2, getter);
        this.nettNgLevel =  folder.apply(Metric::getNettNgLevel);
        this.tradeNumber =  folder.apply(Metric::getTradeNumber);
        this.counterParty = folder.apply(Metric::getCounterParty);
        this.book =         folder.apply(Metric::getBook);
        this.portfolio =    folder.apply(Metric::getPortfolio);
        this.curve =        folder.apply(Metric::getCurve);
        this.bucket =       folder.apply(Metric::getBucket);
        this.backetPillar = folder.apply(Metric::getBacketPillar);
    }

    private String fold(Metric m1, Metric m2, Function<Metric, String> getter) {
        return Objects.equals(getter.apply(m1), getter.apply(m2)) ? getter.apply(m1) : TOTAL;
    }

//    public void foldCarry(Metric m1, Metric m2) {
//        Function<Function<Metric, String>, String> folder = curry(this::fold).apply(m1).apply(m2);
//    }

    public static <P1, P2, R> Function<P1, Function<P2, R>> curry(BiFunction<P1, P2, R> biFunction) {
        return t -> u -> biFunction.apply(t, u);
    }
    public String getNettNgLevel() {
        return nettNgLevel;
    }

    public String getTradeNumber() {
        return tradeNumber;
    }

    public String getCounterParty() {
        return counterParty;
    }

    public String getBook() {
        return book;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public String getCurve() {
        return curve;
    }

    public String getBucket() {
        return bucket;
    }

    public String getBacketPillar() {
        return backetPillar;
    }


}
