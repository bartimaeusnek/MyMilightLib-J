package com.github.bartimaeusnek.MyMilightLib;

public class RawCommandValue implements ICommandValue {
    private int commandValue;

    public RawCommandValue(int commandValue) {
        this.commandValue = commandValue;
    }

    public int getCommandValue() {
        return this.commandValue;
    }
}
