import java.util.ArrayList;
import java.util.*;

/**
 * Written on Friday, 2022-02-04 by Peri Hill
 */

public class SampleEncryptor {

    public static void main(String[] args) {
        // output("c S '17 ! B", 5, 3);
        System.out.println( output("c S '17 ! B 23 n", 5, 3));
    }

    public static boolean alphaNumeric(String str) {
        return str != null && str.matches(".*[a-zA-Z]+.*"); // "^[a-zA-Z0-9]*$"
    }

    private static String getPayload(String payload){
        if(alphaNumeric(payload)){
            return payload;
        } else{
            return "Invalid Payload";
        }
        // for android EditText
//        if(_payload.length() == 0 || !_payload.matches(".*[a-zA-Z]+.*")){
//            payload.setError("Invalid Payload");
//        }
    }

    private static int getArgAlpha(int input){
        List<Integer> alphaList = Arrays.asList(1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25);
        int alpha ;
        if(alphaList.contains(input)){
            alpha = input;
        } else{ alpha = 1; }
        return alpha;
    }

    private static int getArgBeta(int input){
        List<Integer> betaList = new ArrayList<>(); //betaList.size(); 
        for(int i=1; i<26; i++){
            betaList.add(i);
        }
        int beta;
        if(betaList.contains(input)){
            beta = input;
        } else{
            beta = 1;
        }
        return beta;
    }

    public static String output(String userPayloadInput, int userAlphaInput, int userBetaInput) { //void
        List<Character> charCapitalList = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
        List<Character> charLowerList = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
        Collections.sort(charCapitalList);
        Collections.sort(charLowerList);
        HashMap<Integer, Character> capitalMap = new HashMap<>();
        HashMap<Integer, Character> lowerMap = new HashMap<>();

        for (Integer i = 0; i < 26; i++) {
            capitalMap.put(i, charCapitalList.get(i));
            lowerMap.put(i, charLowerList.get(i));
        }

        getPayload(userPayloadInput);

        //int userAlphaInput2 = Integer.parseInt(userAlphaInput);
        getArgAlpha(userAlphaInput);

        //int userBetaInput2 = Integer.parseInt(userBetaInput);
        getArgBeta(userBetaInput);

        int encodedValue;
        char encryptedValue;

        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = userPayloadInput.toCharArray();
        for (char c: chars){
            if(!Character.isLetter(c)){
                stringBuilder.append(c);
                //System.out.println("Stringbuilder for digits: " + stringBuilder);
            } else{
                if(Character.isUpperCase(c)){
                    for (Map.Entry<Integer, Character> entry : capitalMap.entrySet()){
                        if(entry.getValue().equals(c)){
                            encodedValue = entry.getKey();
                            encodedValue = (((userAlphaInput * encodedValue) + userBetaInput) % 26);
                            encryptedValue = lowerMap.get(encodedValue); // returns char, gets value from the key
                            //System.out.println("encryptedValue for capitals: " + encryptedValue);
                            stringBuilder.append(encryptedValue);
                        }
                    }
                } else{
                    for (Map.Entry<Integer, Character> entry : lowerMap.entrySet()){
                        if(entry.getValue().equals(c)){
                            encodedValue = entry.getKey();
                            encodedValue = (((userAlphaInput * encodedValue) + userBetaInput) % 26);
                            encryptedValue = capitalMap.get(encodedValue); // returns char, gets value from the key
                            //System.out.println("encryptedValue for lowers: " + encryptedValue);
                            stringBuilder.append(encryptedValue);
                        }
                    }
                }
            }
        }
        return String.valueOf(stringBuilder);
        //System.out.println(stringBuilder);
    }


}
