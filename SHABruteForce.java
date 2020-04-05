import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SHABruteForce {
    private static String[] strToBreak = {"IV1013 security", "Security is fun", "Yes, indeed", "Secure IV1013", "No way"};
    static String textEncoding = "UTF-8";
    static String digestAlgorithm = "SHA-256";

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Integer> results = new HashMap<>();
        for(String s : strToBreak) {
            byte[] inputBytes = s.getBytes(textEncoding);
            byte[] digest = getDigest(inputBytes);
            byte[] first24bits = {digest[0], digest[1], digest[2]};
            results.put(s, break24Bits(first24bits));
        }
        for(Map.Entry<String, Integer> res : results.entrySet()){
            System.out.println("\"" + res.getKey() + "\" took " + res.getValue() + " tries to break the first 24 bits");
        }
    }

    private static int break24Bits(byte[] targetHash) throws NoSuchAlgorithmException {
        int counter = 0;
        for(int i = 0; i < Math.pow(2, 24); i++){
            counter++;
            byte[] testBytes = get4ByteArrFromInt(i);
            byte[] testDigest = getDigest(testBytes);
            if(Arrays.equals(new byte[]{testDigest[0], testDigest[1], testDigest[2]}, targetHash)){
                FlipFirstBit.printBytesAsString(testBytes);
                FlipFirstBit.printBytesAsString(targetHash);
                FlipFirstBit.printBytesAsString(new byte[]{testDigest[0], testDigest[1], testDigest[2]});
                break;
            }
        }
        return counter;
    }

    private static byte[] get4ByteArrFromInt(int i){
        byte[] testBytes = new byte[4];
        testBytes[0] = (byte) ((i & 0xFF000000) >> 24);
        testBytes[1] = (byte) ((i & 0x00FF0000) >> 16);
        testBytes[2] = (byte) ((i & 0x0000FF00) >> 8);
        testBytes[3] = (byte) ((i & 0x000000FF));
        return testBytes;
    }

    private static byte[] getDigest(byte[] toDigest) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(digestAlgorithm);
        md.update(toDigest);
        return md.digest();
    }
}
