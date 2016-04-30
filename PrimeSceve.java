import java.util.Arrays;

public class PrimeSceve {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] primes = new int[1000001];
		Arrays.fill(primes, 1);
		primes[0] = 0;
		for (int i=2; i < primes.length; i++){
			if (primes[i] > 0){
				for (long j = 2*i; j < primes.length; j += i){
					//System.out.println(j + " " + j*j + " " + primes.length);
					primes[(int)j] = 0;
				}
			}
		}
	}

}
