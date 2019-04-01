package FileTesting;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FileTesting {
	private static String backupLocation = "C:\\Home\\David\\Java\\QuotationFixer\\src\\FileTesting\\testDir";
	private static String BackupFileLabel = "";
	private static String url = "C:\\Home\\David\\Java\\QuotationFixer\\src\\FileTesting\\mainFile.txt";
	
	public static void main(String[] args) {
		String fileExt = url.substring(url.lastIndexOf('.'), url.length());
		GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance();
		String id = "_" + (cal.get(Calendar.MONTH) + 1) + "_" + cal.get(Calendar.DAY_OF_MONTH) + "_" + cal.get(Calendar.YEAR) + "_" + cal.get(Calendar.HOUR_OF_DAY) + "_" + cal.get(Calendar.MINUTE)+ "_" + cal.get(Calendar.SECOND);
				
		BackupFileLabel = "backup" + id + fileExt;
		String backupFilePath = backupLocation + "\\" + BackupFileLabel;
		File mainFile = new File(url);
		File backup = new File(backupFilePath);
		//Path backup = Paths.get(backupLocation);
		
		try {
			Files.copy(mainFile.toPath(), backup.toPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//BackupFileLabel.getText()
		//backup.;
	}

}
