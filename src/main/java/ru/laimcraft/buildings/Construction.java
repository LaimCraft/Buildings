package ru.laimcraft.buildings;

import java.io.Serializable;

public class Construction implements Serializable {

    private final int version;

    public Construction(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }
}
