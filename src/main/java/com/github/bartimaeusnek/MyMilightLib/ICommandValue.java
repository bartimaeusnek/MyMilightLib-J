package com.github.bartimaeusnek.MyMilightLib;

public interface ICommandValue {
    int getCommandValue();
    ICommandValue NONE = () -> 0;
}
