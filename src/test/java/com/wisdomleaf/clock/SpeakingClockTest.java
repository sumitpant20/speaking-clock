package com.wisdomleaf.clock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import com.wisdomleaf.clock.impl.TwentyFourSpeakingClock;

@TestInstance(Lifecycle.PER_CLASS)
class SpeakingClockTest {

   private SpeakingClock speakingClock;

   @BeforeAll
   void setup() {
      this.speakingClock = new TwentyFourSpeakingClock();
   }

   @Test
   void checkMidnightTime() {
      String timeInWords = speakingClock.convertTimeIntoWords("00:00");

      assertEquals("It's Midnight", timeInWords);
   }

   @Test
   void checkMiddayTime() {
      String timeInWords = speakingClock.convertTimeIntoWords("12:00");

      assertEquals("It's Midday", timeInWords);
   }

   @Test
   void checkTimeBeforeMidnight() {
      String timeInWords = speakingClock.convertTimeIntoWords("23:51");

      assertEquals("It's twenty three fifty one", timeInWords);
   }

   @Test
   void checkTimeAfterMidnight() {
      String timeInWords = speakingClock.convertTimeIntoWords("00:19");

      assertEquals("It's zero nineteen", timeInWords);
   }

   @Test
   void checkTimeBeforeMidday() {
      String timeInWords = speakingClock.convertTimeIntoWords("11:50");

      assertEquals("It's eleven fifty", timeInWords);
   }

   @Test
   void checkTimeAfterMidday() {
      String timeInWords = speakingClock.convertTimeIntoWords("12:23");

      assertEquals("It's twelve twenty three", timeInWords);
   }

   @Test
   void checkOnePMTime() {
      String timeInWords = speakingClock.convertTimeIntoWords("13:10");

      assertEquals("It's thirteen ten", timeInWords);
   }

   @Test
   void checkSevenPMTime() {
      String timeInWords = speakingClock.convertTimeIntoWords("19:21");

      assertEquals("It's nineteen twenty one", timeInWords);
   }
   
   
   @Test
   void checkTenPMTime() {
      String timeInWords = speakingClock.convertTimeIntoWords("22:21");

      assertEquals("It's twenty two twenty one", timeInWords);
   }

   @Test
   void checkFiveAMTime() {
      String timeInWords = speakingClock.convertTimeIntoWords("05:10");

      assertEquals("It's five ten", timeInWords);
   }

   @Test
   void checkEightAMTime() {
      String timeInWords = speakingClock.convertTimeIntoWords("08:34");

      assertEquals("It's eight thirty four", timeInWords);
   }
   
   @Test
   void checkTenAMTime() {
      String timeInWords = speakingClock.convertTimeIntoWords("10:27");

      assertEquals("It's ten twenty seven", timeInWords);
   }

   @Test
   void checkTimeBlankTime() {
      Exception exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid time, Please provide valid time in HH:mm format"));
   }

   @Test
   void checkTimeWithoutSeparator() {
      Exception exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("0159");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid time, Please provide valid time in HH:mm format"));
   }

   @Test
   void checkTimeWithInvalidValue() {
      Exception exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("HHsds");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid time, Please provide valid time in HH:mm format"));
   }

   @Test
   void checkTimeWithInvalidValueWihColon() {
      Exception exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("HHsd:::s");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid time, Please provide valid time in HH:mm format"));
   }

   @Test
   void checkTimeWithOneDigitLessInMinute() {
      Exception exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("01:5");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid Minutes, Please provide valid time in HH:mm format"));

      exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("02:9");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid Minutes, Please provide valid time in HH:mm format"));
   }

   @Test
   void checkTimeWithOneDigitLessInHour() {
      Exception exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("1:50");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid Hours, Please provide valid time in HH:mm format"));

      exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("4:50");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid Hours, Please provide valid time in HH:mm format"));
   }

   @Test
   void checkTimeWithInvalidHours() {
      Exception exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("HH:05");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid Hours, Please provide valid time in HH:mm format"));
   }

   @Test
   void checkTimeWithInvalidMinutes() {
      Exception exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("01:mm");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid Minutes, Please provide valid time in HH:mm format"));
   }

   @Test
   void checkTimeWithBlankHours() {
      Exception exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords(":01");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid time, Please provide valid time in HH:mm format"));
   }

   @Test
   void checkTimeWithBlankMinutes() {
      Exception exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("01:");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid time, Please provide valid time in HH:mm format"));
   }

   @Test
   void checkTimeWithInvalidHoursLessthanOne() {
      Exception exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("-1:05");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid Hours, Please provide valid time in HH:mm format"));

      exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("-5:05");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid Hours, Please provide valid time in HH:mm format"));
   }

   @Test
   void checkTimeWithInvalidHoursMoreThanEqualTwentyFour() {
      Exception exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("24:10");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid Hours, Please provide valid time in HH:mm format"));

      exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("27:10");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid Hours, Please provide valid time in HH:mm format"));
   }

   @Test
   void checkTimeWithInvalidMinutesLessOne() {
      Exception exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("07:-1");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid Minutes, Please provide valid time in HH:mm format"));

      exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("07:-10");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid Minutes, Please provide valid time in HH:mm format"));
   }

   @Test
   void checkTimeWithInvalidMinutesMoreThanEqualSixty() {
      Exception exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("04:60");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid Minutes, Please provide valid time in HH:mm format"));

      exception = assertThrows(RuntimeException.class, () -> {
         speakingClock.convertTimeIntoWords("04:70");
      });
      assertTrue(exception.getMessage().contentEquals("Invalid Minutes, Please provide valid time in HH:mm format"));

   }

}
