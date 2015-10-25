import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Created by Christian Goldapp on 07.10.2015.
 */
public class DataHelper {

    static Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static long[] stringToLongs(String in){
        return bytesToLongs(in.getBytes(DEFAULT_CHARSET));
    }

    public static String longsToString(long[] in){
        return new String(longsToBytes(in),DEFAULT_CHARSET);
    }

    public static long[] bytesToLongs(byte[] byteArray){
        long[] longArray = new long[byteArray.length / 8 + 1];
        for (int i = 0; i < longArray.length; i++) {
            byte[] byteChunk = Arrays.copyOfRange(byteArray,i * 8, i * 8 + 8);
            longArray[i] = bytesToLong(byteChunk);
        }
        return longArray;
    }

    public static byte[] longsToBytes(long[] longArray){
        byte[] byteArray = new byte[8 * longArray.length];
        for(int i = 0; i<longArray.length; i++){
            byte[] byteChunk = longToBytes(longArray[i]);
            for (int j = 0; j < 8; j++) byteArray[j + (i * 8)] = byteChunk[j];
        }
        return byteArray;
    }

    public static byte[] longToBytes(long l) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            result[i] = (byte)(l & 0xFF);
            l >>= 8;
        }
        return result;
    }

    public static long bytesToLong(byte[] b) {
        long result = 0;
        for (int i = 0; i < 8; i++) {
            result <<= 8;
            result |= (b[i] & 0xFF);
        }
        return result;
    }

    public static String toHexString(byte... input){
        StringBuilder sb = new StringBuilder("HEX:");
        for(byte b : input){
            String s = Integer.toHexString(b).toUpperCase();
            if(s.length()>2) s = s.substring(s.length()-2);
            sb.append(s);
        }
        return sb.toString();
    }

    public static String toHexString(long... input){
        StringBuilder sb = new StringBuilder();
        for(long b : input) sb.append(Long.toHexString(b).toUpperCase());
        return sb.toString();
    }
}
