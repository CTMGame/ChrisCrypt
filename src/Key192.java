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

    void apply(long[] data) {
        for (int i = 0; i < data.length; i++) {
            long code = (shift * seed - offset);
            data[i] = (data[i] ^ code);
        }
    }

    @Override
    public String toString() {
        return String.format("KEY_192-%016X-%016X-%016X", seed, shift, offset);
    }
}
