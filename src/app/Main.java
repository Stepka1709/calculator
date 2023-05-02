package app;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Input: ");
        String input = in.nextLine();
        in.close();
        System.out.print("Output: " + calc(input));
    }

    public static String calc(String input) throws Exception {
        String regexArabic = "1|2|3|4|5|6|7|8|9|10";
        String regexRome = "I|II|III|IV|V|VI|VII|VIII|IX|X";
        boolean isRome = false;
        input = input.replaceAll(" ", "");
        if (!(input.contains("+") || input.contains("-") || input.contains("*") || input.contains("/")))
            throw new Exception("There are no valid characters (+, -, *, /)");
        String[] nums = input.split("[+\\-*/]");
        if (nums.length > 2) throw new Exception("More than two numbers");
        for (String num : nums) {
            if (num.matches(regexRome)) isRome = true;
            if (!num.matches(regexArabic) && !isRome)
                throw new Exception("The condition of one of the numbers (< 1 or > 10) is violated");
            if (num.matches(regexArabic) && isRome)
                throw new Exception("Different number systems are used simultaneously");
            if (!num.matches(regexRome) && isRome) throw new Exception("These are not Roman numerals");
        }
        if (isRome) {
            if (input.contains("+"))
                return getRomeNumber(Integer.toString(getArabicNumber(nums[0]) + getArabicNumber(nums[1])));
            if (input.contains("-"))
                return getRomeNumber(Integer.toString(getArabicNumber(nums[0]) - getArabicNumber(nums[1])));
            if (input.contains("*"))
                return getRomeNumber(Integer.toString(getArabicNumber(nums[0]) * getArabicNumber(nums[1])));
            if (input.contains("/"))
                return getRomeNumber(Integer.toString(getArabicNumber(nums[0]) / getArabicNumber(nums[1])));
        } else {
            if (input.contains("+")) return Integer.toString(Integer.parseInt(nums[0]) + Integer.parseInt(nums[1]));
            if (input.contains("-")) return Integer.toString(Integer.parseInt(nums[0]) - Integer.parseInt(nums[1]));
            if (input.contains("*")) return Integer.toString(Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]));
            if (input.contains("/")) return Integer.toString(Integer.parseInt(nums[0]) / Integer.parseInt(nums[1]));
        }
        return input;
    }

    public static int getArabicNumber(String num) throws Exception {
        return switch (num) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> throw new Exception("Unknown error");
        };
    }

    public static String getRomeNumber(String num) throws Exception {
        String numRome = "";
        if (Integer.parseInt(num) == 100) return "C";
        if (Integer.parseInt(num) <= 0) throw new Exception("Roman numerals cannot be (<= 0)");
        if (num.length() == 2) {
            switch (num.charAt(0)) {
                case '1' -> numRome = "X";
                case '2' -> numRome = "XX";
                case '3' -> numRome = "XXX";
                case '4' -> numRome = "XL";
                case '5' -> numRome = "L";
                case '6' -> numRome = "LX";
                case '7' -> numRome = "LXX";
                case '8' -> numRome = "LXXX";
                case '9' -> numRome = "XC";
            }
            switch (num.charAt(1)) {
                case '1' -> numRome += "I";
                case '2' -> numRome += "II";
                case '3' -> numRome += "III";
                case '4' -> numRome += "IV";
                case '5' -> numRome += "V";
                case '6' -> numRome += "VI";
                case '7' -> numRome += "VII";
                case '8' -> numRome += "VIII";
                case '9' -> numRome += "IX";
            }
        } else {
            switch (num) {
                case "1" -> numRome = "I";
                case "2" -> numRome = "II";
                case "3" -> numRome = "III";
                case "4" -> numRome = "IV";
                case "5" -> numRome = "V";
                case "6" -> numRome = "VI";
                case "7" -> numRome = "VII";
                case "8" -> numRome = "VIII";
                case "9" -> numRome = "IX";
            }
        }
        return numRome;
    }
}
