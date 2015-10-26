import java.util.Random;

/**
 * Created by Christian Goldapp on 25.10.2015.
 */
public class Key24 {
    final byte seed;
    final byte shift;
    final byte offset;

    public Key24(byte seed, byte shift, byte offset) {
        this.seed = seed;
        this.shift = shift;
        this.offset = offset;
    }

    public void apply(byte[] data) {
        apply(data,seed,shift,offset);
    }

    public String apply(String input){
        byte[] arr;
        apply(arr = input.getBytes(DataHelper.DEFAULT_CHARSET));
        return new String(arr, DataHelper.DEFAULT_CHARSET);
    }

    public static void apply(byte[] data, byte seed, byte shift, byte offset){
        byte code = (byte) (shift * seed - offset);
        for (int i = 0; i < data.length; i++) {
            code = (byte) (shift * code - offset);
            data[i] = (byte) (data[i] ^ code);
        }
    }

    public static void apply(String input, byte seed, byte shift, byte offset){
        apply(input.getBytes(DataHelper.DEFAULT_CHARSET),seed,shift,offset);
    }

    public String toString() {
        return String.format("KEY_24-%02X-%02X-%02X", seed, shift, offset);
    }

    public static Key24 generate(){
        Random rand = new Random(System.nanoTime() + System.currentTimeMillis());
        byte[] bytes = new byte[3];
        rand.nextBytes(bytes);
        return new Key24(bytes[0],bytes[1],bytes[2]);
    }
}
