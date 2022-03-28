package ru.job4j.builder.stream;

import java.util.List;
import java.util.function.Predicate;

public class Job4JStream {
    private List<String> list;
    private Job4JStream(Builder builder) {
        for (String s : builder.array) {
            if (builder.predicate.test(s)) {
                builder.list.add(s);
            }
        }
        this.list = builder.list;
    }

    public List<String> getList() {
        return list;
    }

    public static class Builder  {
        private String[] array;
        private Predicate<String> predicate;
        private List<String> list;

        public  Builder of(String[] array) {
            this.array = array;
            return self();
        }

        public Builder filter(Predicate<String> predicate) {
            this.predicate = predicate;
            return self();
        }

        public Builder collect(List<String> list) {
            this.list = list;
            return self();
        }
        public Job4JStream build() {
            return new Job4JStream(this);
        }

        private  Builder self() {
            return this;
        }

    }

}

