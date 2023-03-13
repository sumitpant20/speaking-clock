package com.wisdomleaf.clock.exception;

public class InvalidTimeFormatException extends RuntimeException {

   /**
    * 
    */
   private static final long serialVersionUID = 6180462726538390799L;

   public InvalidTimeFormatException() {
      super();
   }

   public InvalidTimeFormatException(String message) {
      super(message);
   }

}
