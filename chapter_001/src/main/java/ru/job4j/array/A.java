package ru.job4j.array;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 18.12.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class A extends B {
    public A() {
        doSom();
    }

    void doSom() {
        System.out.println("A");
    }

    public static void main(String[] args) {
        B a = new A();
        a.doSom();
    }
}

class B  {
    public B() {
        doSom();
    }

    void doSom() {
        System.out.println("B");
    }
}
/*
Объяснение простое, если знать о неявных параметрах при вызове методов.
Каждый раз при вызове нестатического метода (т.е метода экземпляра) в метод передается неявный параметр - ссылка на объект, у которого был вызван метод.
Т.е при вызове
ref.method()
 на самом деле происходит вызов
ref.method(ref)
где ref - ссылка на объект, у которого вызывается метод.
Более того, если при вызове метода явно не указано ключевое слово this, оно все равно присутствует, просто является неявным.
То есть:
method()
абсолютно равносильно (и, более того, в рантайме имеет следующий вид):
this.method()
А this - это ни что иное, как значение неявного параметра, переданного в метод вызывающей функцией. То есть, если есть вызов:
ref.method(ref)
и method имеет следующий вид:
method() {
   doSomething();
}
То происходит следующий вызов:
method(ref) {
   ref.doSomething(ref);
}
Вооружившись этими знаниями, несложно понять работу этого кода.
Здесь у нас неявный параметр - это ссылка типа B, указывающая на объект А. Мы объявляем ее в этом выражении:
B test = new A();
Здесь мы можем с уверенностью сказать, что буква B на консоль не выведется. Почему? Потому что мы работаем с ссылкой на
объект типа А, то есть неявный параметр всегда будет указывать на объект типа А, поэтому вызов метода из объекта типа Б
невозможен (по крайней мере без явного требования этого через super).
То есть на данном этапе уже можно дать ответ: если всегда будет выводиться буква А, она выведется три раза: сперва
конструктор класса А вызовет конструктор супертипа Б, который напечатает А, потом выполнится сам, напечатав А,
а потом произойдет вызов по ссылке.
Если это неочевидно, можно опять взглянуть на неявные параметры при вызовах.
Первый вызов через неявный параметр:
B test = new A();
равносильно:
B test = new A(test);
это, в свою очередь, вызывает конструктор класса A с неявным параметром test:
public A(test) {
        super(test);
        test.doSomething(test);
    }
Если рассмотреть каждую строку конструктора, то первая вызовет конструктор суперкласса (напоминаю, что компилятор
всегда добавляет неявный вызов super(), если это не указано явно):
super(test);
Что в свою очередь вызовет конструктор суперкласса:
public B(test) {
        test.doSomething(test);
    }
Напоминаю, что test - это ссылка на объект типа A, то есть этим кодом будет вызван метод doSomething() класса A, и он
выведет 'a'. Итого - уже выведена одна 'a'.
После вызова конструктора суперкласса будет вызван уже конструктор класса A. Все, что он содержит - это:
doSomething();
что равносильно:
this.doSomething();
А поскольку this - это неявный параметр конструктора класса А, то есть ссылка на объект типа А, мы снова вызываем
doSomething() класса А. Печатается вторая 'a'.
И, наконец, после создания объекта вызываем
test.doSomething();
Что по принципу полиморфизма очевидно выведет 'a', так как test указывает на объект типа А. Собственно, те самые три 'a',
о которых можно было догадаться еще давно.
 */