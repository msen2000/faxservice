package org.sen.service.fax.service;

//Sun's serial port driver
import javax.comm.*;
import java.io.*;
import java.util.*;

public class Samplecomm implements Runnable, SerialPortEventListener {

	static CommPortIdentifier portId1;
	//static CommPortIdentifier portId2;
	
	InputStream inputStream;
//	OutputStream outputStream;
	SerialPort serialPort1;
	//SerialPort serialPort2;
	
	Thread readThread;
	protected String divertCode = "10";
	static String TimeStamp;

	public Samplecomm() {

        try {
        	System.out.println("Samplecomm!!!1");
        	
            TimeStamp = new java.util.Date().toString();
            serialPort1 = (SerialPort) portId1.open("ComControl", 2000);
            System.out.println(TimeStamp + ": " + portId1.getName() + " opened for scanner input");
            //serialPort2 = (SerialPort) portId2.open("ComControl", 2000);
            //System.out.println(TimeStamp + ": " + portId2.getName() + " opened for diverter output");

        } catch (PortInUseException e) {}
        try {
            inputStream = serialPort1.getInputStream();
        } catch (IOException e) {}
        try {
            serialPort1.addEventListener(this);
        } catch (TooManyListenersException e) {}
        	serialPort1.notifyOnDataAvailable(true);
        try {
            serialPort1.setSerialPortParams(9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);

            serialPort1.setDTR(false);
            serialPort1.setRTS(false);

//            serialPort2.setSerialPortParams(9600,
//                SerialPort.DATABITS_7,
//                SerialPort.STOPBITS_1,
//                SerialPort.PARITY_EVEN);

        } catch (UnsupportedCommOperationException e) {
        	e.printStackTrace();
        }
        readThread = new Thread(this);
        readThread.start();
    }
	
	public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
    }
	
	public void serialEvent(SerialPortEvent event) {
        switch(event.getEventType()) {
        case SerialPortEvent.BI:
        case SerialPortEvent.OE:
        case SerialPortEvent.FE:
        case SerialPortEvent.PE:
        case SerialPortEvent.CD:
        case SerialPortEvent.CTS:
        case SerialPortEvent.DSR:
        case SerialPortEvent.RI:
        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
            break;
        case SerialPortEvent.DATA_AVAILABLE:
            StringBuffer readBuffer = new StringBuffer();
            int c;
            try {
                 while ((c=inputStream.read()) != 10){
                   if(c!=13)  readBuffer.append((char) c);
                 }
                 String scannedInput = readBuffer.toString();
                 TimeStamp = new java.util.Date().toString();
                 System.out.println(TimeStamp + ": scanned input received:" + scannedInput);
                 inputStream.close();
                 System.out.println(divertCode.getBytes());
//                 if(scannedInput.substring(0,1).equals("F")){
//                   outputStream = serialPort1.getOutputStream();
//                   outputStream.write(divertCode.getBytes());
//                   System.out.println(TimeStamp + ": diverter fired");
//                   outputStream.close();
//                   } else {
//                   System.out.println(TimeStamp + ": diverter not diverted");
//                   }
            } catch (IOException e) {}

            break;
        }
    }
	
	public static void main(String[] args) {
		try {
			Enumeration portList = CommPortIdentifier.getPortIdentifiers();

	        while (portList.hasMoreElements()) {
	        	CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
	        	System.out.println("port type " + portId.getPortType());
	        	System.out.println("port name " + portId.getName());
	        }
	        	
			
			System.out.println("one 1"); 
			portId1 = CommPortIdentifier.getPortIdentifier("COM1");
			System.out.println("one 2");
			//portId2 = CommPortIdentifier.getPortIdentifier("COM1");
			System.out.println("one 3");
			Samplecomm reader = new Samplecomm();
		} catch (Exception e) {
			TimeStamp = new java.util.Date().toString();
			System.out.println(TimeStamp + ": COM1 " + portId1);
//			System.out.println(TimeStamp + ": COM1 " + portId2);
			System.out.println(TimeStamp + ": msg1 - " + e);
		}
	};

}
