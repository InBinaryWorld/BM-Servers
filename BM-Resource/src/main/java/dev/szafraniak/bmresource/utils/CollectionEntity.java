package dev.szafraniak.bmresource.utils;

import lombok.Data;

import java.util.List;

@Data
public class CollectionEntity<T> {
    private int total;
    private List<T> items;

    public CollectionEntity(List<T> items) {
        this.items = items;
        total = items.size();
    }
}
