//read specific cells from excel to arrays

package excelToSerial;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class excelToSerial {

	static SerialPort chosenPort;
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
			//while(true) {
			// excel workbook operation
			
					HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("D:\\Projects folder\\JAVA_Programming\\Java Excel interface\\merge.xls"));
					HSSFSheet sheet = workbook.getSheetAt(0);
					String[] component = new String[22];
					double[] value = new double[22];
					int j=0;
									
					for (int i=31; i<53; i++) 
											
					{
					  HSSFRow row = sheet.getRow(i);
					  component [j] = row.getCell(1).getStringCellValue();
					  value[j] = row.getCell(2).getNumericCellValue();
					  j++;
					}
										
				/* for (int i=0; i<21; i++) 
					{
					  System.out.println(component[i]);
					  System.out.println(value[i]);
					}  */
					
		// create and configure the window
				JFrame window = new JFrame();
				window.setTitle("Result Publisher");
				window.setSize(400, 170);
				window.setLayout(new BorderLayout());
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		// create a drop-down box and connect button, then place them at the top of the window
				JComboBox<String> portList = new JComboBox<String>();
				JButton connectButton = new JButton("Connect");
				JPanel topPanel = new JPanel();
				topPanel.add(portList);
				topPanel.add(connectButton);
				window.add(topPanel, BorderLayout.NORTH);
				
		// populate the drop-down box
				SerialPort[] portNames = SerialPort.getCommPorts();
				for(int i = 0; i < portNames.length; i++) {
					portList.addItem(portNames[i].getSystemPortName());
		// print the serial ports detected to terminal			
					System.out.println(portNames[i]);  
				}	
				
		// configure the connect button and use another thread to send data
				connectButton.addActionListener(new ActionListener(){
					@Override public void actionPerformed(ActionEvent arg0) {
						if(connectButton.getText().equals("Connect")) {
			// attempt to connect to the serial port
							chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
							// print selected port on terminal			
							System.out.println(chosenPort);
							chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
							if(chosenPort.openPort()) {
								connectButton.setText("Disconnect");
								portList.setEnabled(false);			
				
	// create a new thread for sending data to the arduino
			Thread thread = new Thread(){
				@Override public void run() {
					// wait after connecting, so the bootloader can finish
					try {Thread.sleep(100); } catch(Exception e) {}

					// enter an infinite loop that sends text to the arduino
					PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
		// print selected port on terminal			
					System.out.println(chosenPort);
					
					for (int i=0; i<21; i++) 
					{
						output.print(component[i]);
						output.print(value[i]);
					} output.flush();
					try {Thread.sleep(5000); } catch(Exception e) {}
					
				/*	while(true) {
						output.print(new SimpleDateFormat("hh:mm:ss a     MMMMMMM dd, yyyy").format(new Date()));
						output.flush();
						try {Thread.sleep(100); } catch(Exception e) {}
					} */
				}
			};
			thread.start();
		}
	} else {
		// disconnect from the serial port
			chosenPort.closePort();
			portList.setEnabled(true);
			connectButton.setText("Connect");
			}
		}
	});
		// show the window
		window.setVisible(true);
		
		//}
		
	}
} 
						
						