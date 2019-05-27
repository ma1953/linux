package it.ma;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static  String dateToString(Date date, String form){
        SimpleDateFormat sf=new SimpleDateFormat(form);
        return sf.format(date);
    }
    public  static  Date stringToDate(String str,String patt) throws Exception{
        SimpleDateFormat sf=new SimpleDateFormat(patt);
       return sf.parse(str);


    }
}
