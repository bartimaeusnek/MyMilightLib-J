package com.github.bartimaeusnek.MyMilightLib;
public enum BasicCommands {
    COLOR_BIT(64),
    GROUP_ALL_OFF(65),
    GROUP_ALL_ON(66),
    DISCO_SPEED_SLOWER(67),
    DISCO_SPEED_FASTER(68),
    GROUP_001_ON(69),
    GROUP_001_OFF(70),
    GROUP_002_ON(71),
    GROUP_002_OFF(72),
    GROUP_003_ON(73),
    GROUP_003_OFF(74),
    GROUP_004_ON(75),
    GROUP_004_OFF(76),
    DISCO(77),
    BRIGHTNESS_BIT(78),
    COLOR_WHITE(194),
    ;
    private final int command;

    BasicCommands(int command) {
        this.command = command;
    }

    public int getCommand() {
        return this.command;
    }
}
