package com.oekt.oerocks.items.util;

import com.oekt.oerocks.entitty.ThrowableRock;

import java.util.HashMap;
import java.util.Map;

public enum PebbleType {
    BOOSTER(0),
    MUTIPLER(1),

    MORE(2),
    FIRE(3),
    ICE(4);
    public final int typeV;
    private PebbleType(int value)
    {
        typeV = value;
    }

    private static final Map<Integer, PebbleType> _map = new HashMap<Integer, PebbleType>();
    static
    {
        for ( PebbleType type : PebbleType.values())
            _map.put(type.typeV, type);
    }


    public static PebbleType from(int value)
    {
        return _map.get(value);
    }
}
