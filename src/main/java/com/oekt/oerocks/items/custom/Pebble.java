package com.oekt.oerocks.items.custom;

import com.oekt.oerocks.items.util.PebbleType;
import net.minecraft.world.item.Item;

public class Pebble extends Item {
    private int power = 0;
    private PebbleType type;
    public Pebble(Properties prop, PebbleType type, int power) {
        super(prop);
        this.power = power;
        this.type = type;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public PebbleType getType() {
        return type;
    }

    public void setType(PebbleType type) {
        this.type = type;
    }
}
