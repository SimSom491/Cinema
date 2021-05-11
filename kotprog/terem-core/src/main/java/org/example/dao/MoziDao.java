package org.example.dao;

import java.util.List;

public interface MoziDao<T> {
    List<T> listaz();

    T keres(int id);

    T hozzaad(T type);

    void delete(int id);
}
