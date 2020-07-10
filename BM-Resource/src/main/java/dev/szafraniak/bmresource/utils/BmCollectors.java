package dev.szafraniak.bmresource.utils;

import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public final class BmCollectors {

    public static <T> Collector<T, List<T>, BmCollection<T>> toCollection() {
        return java.util.stream.Collector.of(ArrayList::new, List::add, ListUtils::union, BmCollection::new);
    }
}
