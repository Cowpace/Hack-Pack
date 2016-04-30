
public class Permutation {

    public static void permute(String str, int index, StringBuilder result, boolean[] used) {

        if(index == str.length()) {
            //Do something when a permutation is built.
        }

        else {
            for(int i = 0; i < str.length(); i++) {

                //Skip over if the character is already used in the current permutation
                if (used[i]) continue;

                //Add the character to the current permutation and mark it as used
                result.append(str.charAt(i));
                used[i] = true;

                //Recursively find the next character
                permute(str, index + 1, result, used);

                //Unmark the fixed character and remove it from the current permutation
                result.deleteCharAt(result.length() - 1);
                used[i] = false;

            }
        }
    }
}
