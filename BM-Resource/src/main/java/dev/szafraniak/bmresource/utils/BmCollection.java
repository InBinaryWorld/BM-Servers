package dev.szafraniak.bmresource.utils;

import lombok.Data;

import java.util.List;

@Data
public class BmCollection<T> {
    private int total;
    private List<T> items;

    public BmCollection(List<T> items) {
        this.items = items;
        total = items.size();
    }
}
