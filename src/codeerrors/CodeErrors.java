package codeerrors;
import codeerrors.BitString;

public class CodeErrors {

    public static void main(String[] args) {
        BitString z = BitString.parseString("00000000");
        BitString t = BitString.parseString("11111111");
        BitString f = BitString.parseString("01010111");
        //BitString g = BitString.parseString("10101111");
        BitString[]x ={z,t,f};
        Code thing = new Code(x);
        System.out.println("Can detect " + (thing.getDist()-1)+" errors.");
        System.out.println("Can correct " + ((thing.getDist()-1)/2)+" errors.");
        
    }
}
