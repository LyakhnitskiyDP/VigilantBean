package com.ldp.vigilantBean.controller.extractor;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class ParamExtractor {

   public ParamExtractor() { }

   /**
    *
    * Extracts Integer parameter out of a request if present.
    * @param request A request the parameter to be extracted out of.
    * @param paramName Name of Integer parameter.
    * @return Extracted Integer value or 0 if given format is invalid;
    */
   public static Integer safelyExtractInteger(
           HttpServletRequest request,
           String paramName) {

      try {
         return request.getParameter(paramName)
                       .isEmpty() ? 0 : Integer.parseInt(request.getParameter(paramName));

      } catch (NumberFormatException numberFormatException) {
         return 0;
      }
   }

   /**
    *
    * Extracts Long parameter out of a request if present.
    * @param request A request the parameter to be extracted out of.
    * @param paramName Name of Long parameter.
    * @return extracted Long value, 0L if give format is invalid.
    */
   public static Long safelyExtractLong(
           HttpServletRequest request,
           String paramName) {

      try {
         return request.getParameter(paramName)
                       .isEmpty() ? 0 : Long.parseLong(request.getParameter(paramName));

      } catch (NumberFormatException numberFormatException) {
         return 0L;
      }
   }


   /**
    *
    * Extracts BigDecimal parameter out of a request if present.
    * @param request A request the parameter to be extracted out of.
    * @param paramName Name of BigDecimal parameter.
    * @return extracted BigDecimal object, BigDecimal.ZERO if give format is invalid.
    */
   public static BigDecimal safelyExtractBigDecimal(
           HttpServletRequest request,
           String paramName) {

      try {
         return request.getParameter(paramName)
                 .isEmpty() ?
                  BigDecimal.ZERO : BigDecimal.valueOf(Long.parseLong(request.getParameter(paramName)));

      } catch (NumberFormatException numberFormatException) {
         return BigDecimal.ZERO;
      }
   }
}
