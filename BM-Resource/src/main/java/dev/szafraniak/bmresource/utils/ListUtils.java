package dev.szafraniak.bmresource.utils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ListUtils {

    public static <T> List<T> filter(List<T> list, Function<T, Boolean> predicate) {
        return list.stream().filter(predicate::apply).collect(Collectors.toList());
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> predicate) {
        return list.stream().map(predicate).collect(Collectors.toList());
    }
}
