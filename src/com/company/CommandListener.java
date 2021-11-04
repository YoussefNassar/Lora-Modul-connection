package com.company;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class CommandListener implements SerialPortDataListener {

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        byte[] buffer = new byte[serialPortEvent.getSerialPort().bytesAvailable()];
        serialPortEvent.getSerialPort().readBytes(buffer, buffer.length);

        ReformatBuffer.parseByteArray(buffer);
    }
}
