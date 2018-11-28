package ru.fonzy.fnotes.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Importance {
    VERY_HIGH("Очень высокая"), HIGH("Высокая"), NORMAL("Обычная");

    private String translation;

    Importance(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return this.name();
    }

    public boolean isNormal(){
        return this.equals(Importance.NORMAL);
    }
}
