package vn.toanhda.springboottemplate.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/** Created by ToanHDA at May 07, 2020 */
public class DateTimeUtil {
  public static final String yyyyMMddHHmmssSSSFormat = "yyyy-MM-dd HH:mm:ss.SSS";
  public static final String yyyyMMddHHmmssFormat = "yyyy-MM-dd HH:mm:ss";
  public static final String yyyyMMddDateFormat = "yyyyMMdd";
  public static final String yyMMddDateFormat = "yyMMdd";
  public static final String yyyyMMdd = "yyyy/MM/dd";
  public static final String yyyyMM = "yyyy/MM/dd";
  public static final TimeZone GMT7TimeZone = TimeZone.getTimeZone("GMT+7");
  public static final long DURATION_MS_PER_DATE = 86400000L;

  public static String convertTimeStamp2Date(long timeStamp, String format) {
    DateFormat dateFormat;
    if (format == null) {
      dateFormat = new SimpleDateFormat(yyyyMMddHHmmssSSSFormat);
    } else {
      dateFormat = new SimpleDateFormat(format);
    }
    return dateFormat.format(new Date(timeStamp));
  }

  public static Timestamp convertLong2Timestamp(long time) {
    return Timestamp.from(Instant.ofEpochMilli(time));
  }

  public static Date convertYYYYMMDDtoDate(String yyyyMMdd) {
    try {
      if (yyyyMMdd != null && yyyyMMdd.length() == 8) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yyyyMMddDateFormat);
        simpleDateFormat.setLenient(false);

        return simpleDateFormat.parse(yyyyMMdd);
      }
    } catch (Exception e) {
      System.out.println("convertYYYYMMDDtoDate failed cause={}" + e);
    }
    return null;
  }

  public static long getCurrentMillisecond() {
    return Calendar.getInstance(GMT7TimeZone).getTimeInMillis();
  }

  public static String getDayIdOfPeriod(long timeInMillis) {
    LocalDateTime time =
        LocalDateTime.ofInstant(Instant.ofEpochMilli(timeInMillis), GMT7TimeZone.toZoneId());
    return "d" + new SimpleDateFormat(".yyyy.MM.dd").format(Timestamp.valueOf(time));
  }


  public static long getStartOfDayMillis(LocalDate localDate) {
    return localDate.atStartOfDay(GMT7TimeZone.toZoneId()).toEpochSecond() * 1000;
  }

  public static long getStartOfDayMillisFromNow(long deltaDays) {
    long timeInMillis = getCurrentMillisecond();
    return getStartOfDayMillisFromTimeStamp(timeInMillis, deltaDays);
  }

  public static long getStartOfDayMillisFromTimeStamp(long timeInMillis, long deltaDays) {
    LocalDateTime time =
        LocalDateTime.ofInstant(Instant.ofEpochMilli(timeInMillis), GMT7TimeZone.toZoneId());
    if (deltaDays < 0) {
      time = time.minusDays(-deltaDays);
    } else {
      time = time.plusDays(deltaDays);
    }
    return time.toLocalDate().atStartOfDay(GMT7TimeZone.toZoneId()).toEpochSecond() * 1000;
  }

  public static long getStartOfDayMillis(long timeInMillis) {
    LocalDateTime time =
        LocalDateTime.ofInstant(Instant.ofEpochMilli(timeInMillis), GMT7TimeZone.toZoneId());

    return time.toLocalDate().atStartOfDay(GMT7TimeZone.toZoneId()).toEpochSecond() * 1000;
  }

  public static String getCurrentTimestamp() {
    return convertTimeStamp2Date(getCurrentMillisecond(), yyyyMMddHHmmssFormat);
  }

  public static String getCurrentYYMMDD() {
    return convertTimeStamp2Date(getCurrentMillisecond(), yyMMddDateFormat);
  }

  public static long convertToMillisecondSafe(String dateStr, String format) {
    try {
      DateFormat dateFormat = new SimpleDateFormat(format);
      return dateFormat.parse(dateStr).getTime();
    } catch (Exception ex) {
      return 0;
    }
  }

  public static long convertTimestampToMillisecond(Instant instant) {
    return instant.atZone(GMT7TimeZone.toZoneId()).toInstant().toEpochMilli();
  }

  public static Long convertYYYYMMDDtoTs(String yyyyMMdd) {
    try {
      if (yyyyMMdd != null && yyyyMMdd.length() == 8) {
        SimpleDateFormat formatter = new SimpleDateFormat(yyyyMMddDateFormat);
        Date date = formatter.parse(yyyyMMdd);
        return date.getTime();
      }
    } catch (Exception e) {
      System.out.println("convertYYYYMMDDtoTs failed cause={}" + e);
    }
    return null;
  }

  public static long getDateDiff(long firstTime, long secondTime, TimeUnit timeUnit) {

    long firstTimeLocal =
        LocalDateTime.ofInstant(Instant.ofEpochMilli(firstTime), GMT7TimeZone.toZoneId())
                .toLocalDate()
                .atStartOfDay(GMT7TimeZone.toZoneId())
                .toEpochSecond()
            * 1000;

    long secondTimeLocal =
        LocalDateTime.ofInstant(Instant.ofEpochMilli(secondTime), GMT7TimeZone.toZoneId())
                .toLocalDate()
                .atStartOfDay(GMT7TimeZone.toZoneId())
                .toEpochSecond()
            * 1000;

    long diffInMillies = Math.abs(firstTimeLocal - secondTimeLocal);
    return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
  }

}
