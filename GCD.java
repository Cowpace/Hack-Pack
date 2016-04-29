public class GCD {
       
    // Iterative solution
    public static int gcdTwoNumbers(int a, int b) {

        if(a == 0 || b == 0) return 0;

        //Euclid's Algorithm
        while(a % b != 0) {
            int newB = a % b;
            a = b;
            b = newB;
        }

        return b;
    }

    public static int gcdArray(int[] numbers) {

        //Cannot compute GCD of less than two numbers
        if(numbers.length < 2) return 0;

        int gcd = gcdTwoNumbers(numbers[0], numbers[1]);

        for (int i = 2; i < numbers.length; i++) {
            gcd = gcdTwoNumbers(gcd, numbers[i]);
        }

        return gcd;
    }
}
