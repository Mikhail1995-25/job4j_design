package ru.job4j.collection;

import java.util.Objects;

public class Model {
    private String colour;
    private int number;

    public Model(String colour, int number) {
        this.colour = colour;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Model model = (Model) o;
        return number == model.number && Objects.equals(colour, model.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colour, number);
    }
}