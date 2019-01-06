import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/*
 *  Write a function named persistence that takes in a positive parameter "num" 
 *  and returns its multiplicative persistence, which is the number of times you 
 *  must multiply the digits in num until you reach a single digit.
 *
 * @author DaveCat
 */
public class Persist {
private static int persistenceValue;
private static long initialProduct = 1;
private static List<Long> digitList = new ArrayList<>();    

    public static int persistence(long num) {
        //resets persistenceValue
        persistenceValue = 0;
        
        //Resolves if num is single digit to conserve memory      
        if (num <= 9)
        {
            return 0;
        }

        //breaks long into individual digits
        char[] digits = evaluateDigits(num);

        //determines if either of last two digits are zero to conserve memory
        if(containsZero(digits)){ 
          return 1;
        }

        //converts digits to longs and populates list of digits
        resetDigitList(digits);

        //calculates product of all digits
        long finalProduct = multiplyElements(digitList);

        //breaks finalProduct into char[] to count digits
        char[] finalValue = evaluateDigits(finalProduct);

        //If not satisfied in the first cycle, it is handled in loop
        while (finalValue.length > 1)
        {
            digits = evaluateDigits(finalProduct);
            resetDigitList(digits);
            finalProduct = multiplyElements(digitList);
            finalValue = evaluateDigits(finalProduct);
        }

        return persistenceValue;
    }

    private static long multiplyElements(List<Long> digitList)
    {
        long finalProduct = 0;

        for (int i = 0; i < digitList.size(); i++)
        {
            //evalute if this is the first digit in the series
            if (i == 0)
            {
                finalProduct = initialProduct * digitList.get(i);
            }
            else
            {
              //otherwise, multiply the next digit by the previous product
              finalProduct = finalProduct * digitList.get(i);
            }
        }
        persistenceValue++;
        return finalProduct;
    }

    private static char[] evaluateDigits(long number)
    {
        //splits numbers digits     
        String splitableNumber = Long.toString(number);
        char[] digits = splitableNumber.toCharArray();
        return digits;
    }

    private static void resetDigitList(char[] digits)
    {
        digitList.clear();

        //change data type back
        for (char value : digits)
        {
            String digit = Character.toString(value);
            long trueValue = Long.parseLong(digit);
            digitList.add(trueValue);
        }
    }
    
    private static boolean containsZero(char[] digits)
    {
        for (char c : digits)
        {
            int digit = Integer.parseInt(String.valueOf(c));

            if (digit == 0)
            {
                return true;
            }
        }
        return false;
    }
    
}