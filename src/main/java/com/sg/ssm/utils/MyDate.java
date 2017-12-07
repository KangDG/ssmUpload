package com.sg.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class MyDate implements Converter<String, Date> {

	/*private SimpleDateFormat [] sdfs = {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
			new SimpleDateFormat("yyyy/MM/dd"),
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyyMMdd")
	};*/


    private String [][] arr = {
            {"yyyy-MM-dd HH:mm:ss","\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"},
            {"yyyy/MM/dd","\\d{4}/\\d{2}/\\d{2}"},
            {"yyyyMMdd","\\d{8}"}
    };



    public Date convert(String source) {
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/

		/*for (int i = 0; i < sdfs.length; i++) {
			try {
				return sdfs[i].parse(source);
			} catch (ParseException e) {
				continue;
			}
		}*/


        //应用正则表达式来匹配
        try {
            for (int i = 0; i < arr.length; i++) {
                String [] str = arr[i];
                if(source.matches(str[1])){
                    SimpleDateFormat sdf = new SimpleDateFormat(str[0]);
                    return sdf.parse(source);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}

