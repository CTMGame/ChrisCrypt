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

    void apply(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            byte code = (byte) (shift * seed - offset);
            data[i] = (byte) (data[i] ^ code);
        }
    }

    public String toString() {
        return String.format("KEY_24-%02X-%02X-%02X", seed, shift, offset);
    }
}
