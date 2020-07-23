package com.example.homlee.otis;

import java.util.HashMap;
import java.util.Map;

/**
 * 奥的斯电梯楼层解码器
 */
public class OtisFloorDecoder {
    private volatile static OtisFloorDecoder mDecoder;
    private final Map<Integer, Character> map1;
    private final Map<String, String> map3;

    private OtisFloorDecoder() {
        map1 = new HashMap<>(128);
        map3 = new HashMap<>(32);
        initMap1();
        initMap3();
    }

    public static OtisFloorDecoder getInstance() {
        if (mDecoder == null) {
            synchronized (OtisFloorDecoder.class) {
                if (mDecoder == null) {
                    mDecoder = new OtisFloorDecoder();
                }
            }
        }

        return mDecoder;
    }

    private void initMap1() {
        map1.put(0xaaaa, '0');
        map1.put(0x8002, '1');
        map1.put(0x2b2b, '2');
        map1.put(0xa92b, '3');
        map1.put(0x8183, '4');
        map1.put(0xa9a9, '5');
        map1.put(0xaba9, '6');
        map1.put(0x802a, '7');
        map1.put(0xabab, '8');
        map1.put(0xa9ab, '9');

        map1.put(0x83ab, 'A');
        map1.put(0xb83b, 'B');
        map1.put(0x2aa8, 'C');
        map1.put(0xb83a, 'D');
        map1.put(0x2ba8, 'E');
        map1.put(0x03a8, 'F');
        map1.put(0xaaa9, 'G');
        map1.put(0x8383, 'H');
        map1.put(0x3838, 'I');
        map1.put(0x1a38, 'J');

        map1.put(0x4384, 'K');
        map1.put(0x2a80, 'L');
        map1.put(0x82c6, 'M');
        map1.put(0xc2c2, 'N');
        map1.put(0x03ab, 'P');
        map1.put(0xeaaa, 'Q');
        map1.put(0x43ab, 'R');
        map1.put(0x1038, 'T');
        map1.put(0xaa82, 'U');
        map1.put(0x0684, 'V');

        map1.put(0xc682, 'W');
        map1.put(0xa9a9, 'X');
        map1.put(0x1044, 'Y');
        map1.put(0x2c2c, 'Z');
        map1.put(0x0101, '-');
        map1.put(0x0000, ' ');
    }

    private void initMap3() {
        map3.put(combine(0xa9ab, 0xaaaa), "12A");
        map3.put(combine(0xa9ab, 0x8002), "12B");
        map3.put(combine(0xa9ab, 0x2b2b), "13A");
        map3.put(combine(0xa9ab, 0xa92b), "13B");
        map3.put(combine(0xa9ab, 0x8183), "15A");
        map3.put(combine(0xa9ab, 0xa9a9), "15B");
        map3.put(combine(0xa9ab, 0xaba9), "17A");
        map3.put(combine(0xa9ab, 0x802a), "17B");
        map3.put(combine(0xa9ab, 0xabab), "23A");
        map3.put(combine(0xa9ab, 0xa9ab), "33A");
    }

    private String combine(int high, int low) {
        return "" + map1.get(high) + map1.get(low);
    }

    public String getText(int high, int low) {
        System.out.println("getText: high = 0x" + Integer.toHexString(high) + ", low = 0x" + Integer.toHexString(low));
        String text = combine(high, low);
        return map3.containsKey(text) ? map3.get(text) : text;
    }
}
