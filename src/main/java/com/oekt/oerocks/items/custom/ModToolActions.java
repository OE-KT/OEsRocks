package com.oekt.oerocks.items.custom;

import com.google.common.collect.Sets;
import net.minecraftforge.common.ToolAction;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Set.of;

public class ModToolActions {
    public static final ToolAction HAMMER_SWING = ToolAction.get("hammer_swing");

    public static final Set<ToolAction> DEFAULT_HAMMER_ACTIONS = of(HAMMER_SWING);
    private static Set<ToolAction> of(ToolAction... actions) {
        return Stream.of(actions).collect(Collectors.toCollection(Sets::newIdentityHashSet));
    }
}
