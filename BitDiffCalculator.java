import java.io.UnsupportedEncodingException;

public class BitDiffCalculator {
    static String textEncoding = "UTF-8";
    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] aBytes = args[0].getBytes(BitDiffCalculator.textEncoding);
        byte[] bBytes = args[1].getBytes(BitDiffCalculator.textEncoding);
        printBytes(aBytes);
        printBytes(bBytes);
        byte[] xorBytes = new byte[aBytes.length];
        int i = 0;
        for(byte a : aBytes){
            xorBytes[i] = (byte) (a ^ bBytes[i]);
            i++;
        }
        printBytes(xorBytes);
        int diffCount = 0;
        for(byte b : xorBytes){
            diffCount += Integer.bitCount(b);
        }
        System.out.println("Number of differing bits: " + diffCount);
    }

    public static void printBytes(byte[] bs) {
        for(byte b :  bs){
            System.out.print(Integer.toBinaryString(b) + " ");
        }
        System.out.print("\n");
    }
}
