
public class LCM {

    public static long lcmTwoNumbers(long a, long b) {

        if(a == 0 || b == 0) return 0;
        return (a * b) / (GCD.gcdTwoNumbers(a, b));

    }

    public static long lcmArray(long[] numbers) {

        if(numbers.length < 2) return 0;

        long lcm = lcmTwoNumbers(numbers[0], numbers[1]);

        for(int i = 2; i < numbers.length; i++) {
            lcm = lcmTwoNumbers(lcm, numbers[i]);
        }

        return lcm;

    }

}
