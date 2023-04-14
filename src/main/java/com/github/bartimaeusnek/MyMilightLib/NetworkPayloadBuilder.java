package com.github.bartimaeusnek.MyMilightLib;

import java.io.ByteArrayOutputStream;

public class NetworkPayloadBuilder {
    private ByteArrayOutputStream payload = new ByteArrayOutputStream();
    private boolean commandWasAdded = false;
    private byte[] result;
    public NetworkPayloadBuilder addBasicCommand(BasicCommands command) {
        checkIfWasBuild();
        payload.write((byte) command.getCommand());
        commandWasAdded = true;
        return this;
    }

    private void checkIfWasBuild() {
        if (result != null){
            throw new IllegalStateException("Already Build!");
        }
    }

    public NetworkPayloadBuilder addICommandValue(ICommandValue value) {
        checkIfWasBuild();
        if (!commandWasAdded){
            throw new IllegalStateException("You need to add a command fist!");
        }
        payload.write((byte) value.getCommandValue());
        commandWasAdded = false;
        return this;
    }

    public byte[] build(){
        if (commandWasAdded) {
            addICommandValue(ICommandValue.NONE);
        }

        if (result == null) {
            result = payload.toByteArray();
            payload = null;
        }

        return result;
    }
}
