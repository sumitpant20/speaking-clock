package com.wisdomleaf;

import java.util.Scanner;
import com.wisdomleaf.clock.SpeakingClock;
import com.wisdomleaf.clock.exception.InvalidTimeFormatException;
import com.wisdomleaf.clock.impl.TwentyFourSpeakingClock;

public class TimeMain {
   public static void main(String[] args) {

      Scanner sc = new Scanner(System.in);
      System.out.println("Please enter time in HH:mm (24 hour format)");
      String time = sc.nextLine();
      SpeakingClock speakingClock = new TwentyFourSpeakingClock();
      try {
         System.out.println(speakingClock.convertTimeIntoWords(time));
      } catch (InvalidTimeFormatException ex) {
         System.out.println(ex.getMessage());
      }
   }

}
