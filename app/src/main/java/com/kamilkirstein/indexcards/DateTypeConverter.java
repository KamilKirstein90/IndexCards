package com.kamilkirstein.indexcards;

import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTypeConverter {

    @TypeConverter
    public static Date toDate(String stringDate) throws ParseException {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        return stringDate == null ? null : dateTimeFormat.parse(stringDate);
    }

    @TypeConverter
    public static String dateToString(Date date){
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        return dateTimeFormat.format(date);
    }
}
