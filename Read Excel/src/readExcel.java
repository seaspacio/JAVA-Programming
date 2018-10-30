
public class readExcel {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File f=new File("C:\\Users\\DELL INSPIRON\\Desktop\\data1.xlsx");
		
		Workbook wb=Workbook.getWorkbook(f);
		Sheet s=wb.getSheet(0);
		int row=s.getColumns();
		for (int i=0; i < row; i++) {
			for (int j =0; j < col; j++) {
				Cell c=s.getCell(j, i);
				System.out.print(c.getContents());
			}
			System.out.println("");
		}
		
	}

}
