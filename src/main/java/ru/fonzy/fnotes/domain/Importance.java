package ru.fonzy.fnotes.domain;

public enum Importance {
    VERY_HIGH, HIGH, NORMAL;


    @Override
    public String toString() {
        return this.name();
    }
}
