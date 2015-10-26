import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Christian Goldapp on 25.10.2015.
 */
public class Key192 {
    final long seed;
    final long shift;
    final long offset;

    public Key192(long seed, long shift, long offset) {
        this.seed = seed;
        this.shift = shift;
        this.offset = offset;
    }

    public String apply(String in){
        long[] data = DataHelper.stringToLongs(in);
        apply(data);
        return DataHelper.longsToString(data);
    }

    public void apply(long[] data) {
        apply(data,seed,shift,offset);
    }

    public static String apply(String in, long seed, long shift, long offset){
        long[] data = DataHelper.stringToLongs(in);
        apply(data,seed,shift,offset);
        return DataHelper.longsToString(data);
    }

    public static void apply(long[] data, long seed, long shift, long offset){
        long code = (shift * seed - offset);
        for (int i = 0; i < data.length; i++) {
            code = shift * code - offset;
            data[i] = (data[i] ^ code);
        }
    }

    /** @return A text-representation of this key,
     * in the form "KEY_192-0000000000000000-0000000000000000-0000000000000000",
     * where the three numbers are the hex representation of seed, shift and offset, respectively.
     */
    @Override
    public String toString() {
        return String.format("KEY_192-%016X-%016X-%016X", seed, shift, offset);
    }

    public static Key192 generate(){
        Random rand = new Random(System.nanoTime() + System.currentTimeMillis());
        return new Key192(getSemiPrime(rand),getSemiPrime(rand),getSemiPrime(rand));
    }

    /**
     * @param rand The instance of Random to use for the generation.
     * @return A number long number whose absolute value is the sum of two probably prime numbers, with a randomly chosen sign.
     */
    private static long getSemiPrime(Random rand){
        long l = BigInteger.probablePrime(32,rand).multiply(BigInteger.probablePrime(32,rand)).longValue();
        if(rand.nextBoolean()) l = -1 * l;
        return l;
    }
}
