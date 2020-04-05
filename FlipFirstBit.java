import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class FlipFirstBit {
    static String textEncoding = "UTF-8";
    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] toFlip = args[0].getBytes(textEncoding);
        printBytesAsString(toFlip);
        toFlip[0] = (byte) (toFlip[0] ^ (1 << 6));
        printBytesAsString(toFlip);
    }

    public static void printBytesAsString(byte[] bs) {
        String s = new String(bs, StandardCharsets.UTF_8);
        System.out.println(s);
    }
}
