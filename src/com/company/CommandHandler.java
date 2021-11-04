package com.company;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CommandHandler {

    private static SerialPort sendPort;

    InputStream portInputStream;
    OutputStream portOutputStream;

    public void sendRestSignal(String command) throws IOException, InterruptedException {
        SerialPort[] allAvailableComPorts = SerialPort.getCommPorts();

        if (sendPort == null) {
            for (SerialPort serialPort : allAvailableComPorts) {
                System.out.println("List of all available serial ports: " + serialPort.getDescriptivePortName());

                //always take the outgoing port
                if (serialPort.getDescriptivePortName().contains("COM10")) {
                    sendPort = serialPort;
                }
            }
        }

        if (!sendPort.isOpen()) {
            sendPort.openPort();
        }

        //check the name
        System.out.println("Opened port: " + sendPort.getDescriptivePortName());


        portInputStream = sendPort.getInputStream();
        portOutputStream = sendPort.getOutputStream();


        //to clean the input stream
        //portInputStream.skip(portInputStream.available());

        command = command + "\r\n";
        byte[] commandByte = command.getBytes();


        portOutputStream.write(commandByte);
        portOutputStream.flush();

        CommandListener listener = new CommandListener();

        sendPort.addDataListener(listener);
        Thread.sleep(5000);
        System.out.println("ready");
        System.out.println(sendPort.isOpen());
    }
}
