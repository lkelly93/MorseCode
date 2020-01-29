import java.util.*;

public class MorseCode{
    static String[] MorseAlpha = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
    static String[] MorseDigit = {"-----",".----","..--","...--","....-",".....","-....","--...","--..","---."};
    //************************FROM TEXT TO MORSE*******************************/
    /**
     * Converts a single line into morse code.
     * @param s The string you want converted
     * @throws IllegalArgumentException if there is something in the string it can't convert.(Not Alpha||Not Digit||Not Space.)
     */
    public static String convertTextToMorse(String s){
        String output = "";
        for(int i = 0; i < s.length(); i++){
            char letter = s.charAt(i);
            letter = Character.toUpperCase(letter);
            if(canConvertToMorse(letter)){
                output += " " + convertCharToMorse(letter);
            }else{
                throw new IllegalArgumentException("\""+ letter +"\" Can not be converted to morse code");
            }
        }
        return output;
    }
    /**
     * Converts a singular Char to morse
     * @param c the char you want to convert
     * @return the string value of that char in morse code
     */
    public static String convertCharToMorse(Character c){
        if(Character.isLetter(c)){
            int index = (int)(c - 65);
            return MorseAlpha[index];
        }else if(c == ' '){
            return "/";
        }else{
            int index = (int)(c-48);
            return MorseDigit[index];
        }
    }

    /**
     * Checks if the char can be converted to Morse with the current data
     * @param letter the char you want to check
     * @return true if it can false if it can't
     */
    public static boolean canConvertToMorse(Character letter){
        return (Character.isLetter(letter) || Character.isDigit(letter) || letter == ' ');
    }

    //************************FROM MORSE TO TEXT*******************************/
    /**
     * Convert from Morse to String.  
     * @param s the String of morse code you want to convert.
     * @return a String of Text in all caps that represents the Morse Code.
     */
    public static String convertMorseToText(String s){
        String output = "";
        Scanner processing = new Scanner(s);
        while(processing.hasNext()){
            //Grab the next letter
            String letter = processing.next();
            //Instantiate so I can use later.
            Character addToOutput;
            /*Check if it is a space. While this is rare if it is true it saves
            me alot of time because then I don't have to call all the other methods*/
            if(letter.equals("/")){
                addToOutput = ' ';
            }else{//If not a space
            //Find the index in the array
            /* My Arrays are setup in ascending order just like they are on the 
             * unicode table. So if the letter is A then I will get back the
             * index that it is in. Which is 0. The Unicode value for A is 65
             * 65+0 = 65. So if I create a char of value 65 it will convert to a
             * 'A' when printed or added to a string.
             * If I get a negative number then I know it is in the Digits Array.
             * I change the sign again and then add 48 to it because 48 is the
             * Unicode value of 0.
             * 
             */
            int index = convertToText(letter);
                if(index < 0){
                    index *= -1;
                    addToOutput = (char) (48 + index);
                }else{
                    addToOutput = (char) (65 + index);
                }
            }
            output += Character.toString(addToOutput);  
        }
        processing.close();
        return output;
    }

    /**
     * Converts a single letter to morse code.
     * @param letter The letter you want to convert to Morse
     * @return the index value of that letter in the arrays. The number will be negative
     * if it is in the MorseDigits array.
     * @throws IllegalArgumentException if the number can not be converted with the current data.
     */
    public static int convertToText(String letter){
        for(int i = 0; i < MorseAlpha.length; i++){
            if(MorseAlpha[i].equals(letter)){
                return i;
            }
            if(i < MorseDigit.length){
                if(MorseDigit[i].equals(letter)){
                    return i*-1;
                }
            }
        }
        throw new IllegalArgumentException("\""+ letter +"\" Can not be converted to morse code");
    }


}