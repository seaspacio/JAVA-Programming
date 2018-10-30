//read specific cells from excel to arrays

package excelReadArray;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class readArray
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
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
		
		for (int i=0; i<21; i++) 
		{
		     System.out.println(component[i]);
		     System.out.println(value[i]);
		}
	}
}