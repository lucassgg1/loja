package br.com.dlei.control.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataManipulation {

	public Date converterStringDate(String dataString) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date dateConv = null;
		try {
			//converte String em date
			dateConv = format.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateConv;
	}

}
