package ru.laimcraft.buildings;

import java.nio.ByteBuffer;
import java.util.HashMap;

public class FlagBytes {

    // Если initialCapacity = 8 тогда Flag будет весить 12 Байт, так как 4 байта нужно для initialCapacity

    final int initialCapacity;
    HashMap<Integer, Byte[]> data;

    public FlagBytes(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.data = new HashMap<>(this.initialCapacity + 1, 1.0F);
    }

    public byte[] get() {
        byte[] result = new byte[initialCapacity + 4];
        ByteBuffer.wrap(result, 0, 3).putInt(initialCapacity);
        for(int i = 0; i <= initialCapacity; i++) {
            Byte[] bytes = data.get(i);
            if(bytes == null) // Нужно записат;
        }
    }

    public void put(int flag, Byte[] bytes) {
        data.put(flag, bytes);
    }

    public void put(int flag, byte[] bytes) {
        Byte[] put = new Byte[bytes.length];
        for (int i = 0; i <= bytes.length; i++) {
            put[i] = bytes[i];
        }
        put(flag, put);
    }

    public void put(int _byte, int bit, byte[] bytes) {
        put(_byte + bit, bytes);
    }
}
