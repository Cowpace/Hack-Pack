public class GCD {

    // Iterative solution using Euclid's Algorithm
    public static long gcdTwoNumbers(long a, long b) {

        if(a == 0 || b == 0) return 0;

        while(a % b != 0) {
            long newB = a % b;
            a = b;
            b = newB;
        }

        return b;

    }

    public static long gcdArray(long[] numbers) {

        //Cannot compute GCD of less than two numbers
        if(numbers.length < 2) return 0;

        long gcd = gcdTwoNumbers(numbers[0], numbers[1]);

        for (int i = 2; i < numbers.length; i++) {
            gcd = gcdTwoNumbers(gcd, numbers[i]);
        }

        return gcd;

    }

}
