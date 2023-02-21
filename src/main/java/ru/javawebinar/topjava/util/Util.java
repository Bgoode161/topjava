package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Util {

    public static  <T extends Comparable<T>> boolean isBetweenHalfOpen(T value, T from, T to) {
        return (from == null || value.compareTo(from) >= 0) && (to == null || value.compareTo(to) < 0);
    }
}
