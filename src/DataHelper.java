import java.util.Arrays;

/**
 * Created by Christian Goldapp on 07.10.2015.
 */
public class DataHelper {

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
            for (int j = 0; j < 8; j++) {
                byteArray[j + (i * 8)] = byteChunk[j];
            }
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
}
