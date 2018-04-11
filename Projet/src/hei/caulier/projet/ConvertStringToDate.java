package hei.caulier.projet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertStringToDate {
	
	String aconvertir;
	
	public static LocalDate StringToDate(String aconvertir) {
		
		String pattern = "dd/MM/yyyy";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.parse(aconvertir, formatter);
        System.out.println("Today = " + localDate.format(formatter));
        return localDate;
	}
}
