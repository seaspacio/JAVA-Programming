package excelToSerialConfig;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileReader;
import java.util.Properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.fazecast.jSerialComm.SerialPort;

public class excelToSerial1 {
	
static SerialPort chosenPort;
static String serialPortName;
static String filePath;
static String numberOfComponents;
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		try(FileReader reader = new FileReader("Config")) 
		 { 
			Properties prop = new Properties(); 
			prop.load(reader);
			
			serialPortName = prop.getProperty("serialPortName"); 
			filePath = prop.getProperty("filePath");
			numberOfComponents = prop.getProperty("numberOfComponents");
		 }
		catch (Exception e) {
			; 
			e.printStackTrace(); } 	
		
		 
		
		// excel workbook operation
			
					HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
					HSSFSheet sheet = workbook.getSheetAt(0);
					String[] component = new String[22];
					double[] value = new double[22];
					int j=0;
					workbook.close();
					
									
					for (int i=31; i<53; i++) 
											
					{
					  HSSFRow row = sheet.getRow(i);
					  component [j] = row.getCell(1).getStringCellValue();
					  value[j] = row.getCell(2).getNumericCellValue();
					  j++;
					}
										
				 for (int i=0; i<21; i++) 
					{
					  System.out.println(component[i]);
					  System.out.println(value[i]);
					} 
				 
	// attempt to connect to the serial port
				 
	    chosenPort = SerialPort.getCommPort(serialPortName);
		chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		chosenPort.openPort();	
	// create a new thread for sending data to the arduino
					Thread thread = new Thread(){
						@Override public void run() {
							// wait after connecting, so the bootloader can finish
							try {Thread.sleep(300); } catch(Exception e) {}

							// enter an infinite loop that sends text to the arduino
							PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
							
				//convert string to int number			
				int max = Integer.valueOf(numberOfComponents);	
				
							for (int i=0; i<max; i++) 
							{
								output.print(component[i]);
								output.print(",");
								output.print(value[i]);
								output.print(",");		
							}   output.flush();
							try {Thread.sleep(200); } catch(Exception e) {}
							
						}
					};
					thread.start();					
	}	
	
}
