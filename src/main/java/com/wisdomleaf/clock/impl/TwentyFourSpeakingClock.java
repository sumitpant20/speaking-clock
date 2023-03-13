package com.wisdomleaf.clock.impl;

import com.wisdomleaf.clock.SpeakingClock;
import com.wisdomleaf.clock.exception.InvalidTimeFormatException;

public class TwentyFourSpeakingClock implements SpeakingClock {

   private static final String[] NUM_ONES =
         {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
               "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

   private static final String[] NUM_TENS = {"", "", "twenty", "thirty", "forty", "fifty"};

   public String convertTimeIntoWords(String timeInHHmm) {
      String result = "";
      String invalidTime = "Invalid time, Please provide valid time in HH:mm format";
      if (timeInHHmm == null || timeInHHmm.isEmpty() || !timeInHHmm.contains(":") || timeInHHmm.trim().startsWith(":"))
         throw new InvalidTimeFormatException(invalidTime);
      String[] hourMinute = timeInHHmm.trim().split(":");
      if (hourMinute.length != 2)
         throw new InvalidTimeFormatException(invalidTime);
      int hour = validateHour(hourMinute[0]);
      int minute = validateMinute(hourMinute[1]);
      result = hourMinuteInWords(hour, minute);
      if (result == null || result.isEmpty())
         throw new InvalidTimeFormatException(invalidTime);

      return result;

   }

   private int validateMinute(String minuteValue) {
      int minute;
      String minuteException = "Invalid Minutes, Please provide valid time in HH:mm format";
      if (minuteValue.length() != 2) {
         throw new InvalidTimeFormatException(minuteException);
      }
      try {
         minute = Integer.parseInt(minuteValue);
      } catch (NumberFormatException e) {
         throw new InvalidTimeFormatException(minuteException);
      }
      if (minute < 0 || minute > 59) {
         throw new InvalidTimeFormatException(minuteException);
      }
      return minute;
   }

   private int validateHour(String hourValue) {
      String hourException = "Invalid Hours, Please provide valid time in HH:mm format";
      int hour;
      if (hourValue.length() != 2) {
         throw new InvalidTimeFormatException(hourException);
      }
      try {
         hour = Integer.parseInt(hourValue);
      } catch (NumberFormatException e) {
         throw new InvalidTimeFormatException(hourException);
      }
      if (hour < 0 || hour > 23) {
         throw new InvalidTimeFormatException(hourException);
      }
      return hour;
   }

   private String hourMinuteInWords(int hour, int minute) {
      StringBuilder words = new StringBuilder();
      if (hour == 12 && minute == 0) {
         return words.append("It's Midday").toString();
      } else if (hour == 0 && minute == 0) {
         return words.append("It's Midnight").toString();
      } else if (minute == 0 && hour > 0 && hour < 24) {
         words.append("It's ").append(convertNumberToWords(hour));
      } else {
         words.append("It's ").append(convertNumberToWords(hour)).append(" ").append(convertNumberToWords(minute));
      }
      return words.toString();
   }

   private String convertNumberToWords(int number) {
      if (number < 20)
         return NUM_ONES[number];
      return NUM_TENS[number / 10] + ((number % 10 > 0) ? " " + NUM_ONES[number % 10] : "");
   }
}
