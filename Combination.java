
public class Combination {

    //Exploit the fact that a character that follows another in the original string cannot come before in the combination
    public static void combination(String str, int index, StringBuilder result){

        //If index == str.length, there are no other characters to add
        if(index != str.length()) {

            for (int i = index; i < str.length(); i++) {

                //Add the character at index i and print out the current combination
                result = result.append(str.charAt(i));
                System.out.println(result.toString());

                //Try next possible characters and remove the fixed character
                combination(str, i + 1, result);
                result.deleteCharAt(result.length()-1);

            }
        }
    }

}
