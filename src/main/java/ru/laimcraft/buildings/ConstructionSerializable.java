package ru.laimcraft.buildings;

import ru.laimcraft.api.bytes.FlagBytes;
import ru.laimcraft.api.exception.UnsupportedVersionException;

public class ConstructionSerializable {

    private static final int defaultInitialCapacity = 8;

    public byte[] serializable(Construction construction) {
        switch (construction.getVersion()) {
            case 1:
                return version1(construction);
            default:
                throw new UnsupportedVersionException(
                        "UnsupportedVersionException\n" +
                                "ConstructionSerializable\n" +
                                "version: " + construction.getVersion());
        }
    }

    private byte[] version1(Construction construction) {
        return null;
    }
}
