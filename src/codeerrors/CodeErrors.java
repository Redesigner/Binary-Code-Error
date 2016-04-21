package codeerrors;
import codeerrors.BitString;
import codeerrors.HammingCode;

public class CodeErrors {

    public static void main(String[] args) {
        BitString z = BitString.parseString("11");
        BitString t = BitString.parseString("00");
        BitString[]x ={z,t};
        Code thing = new Code(x);
        System.out.println("Can detect " + (thing.getDist()-1)+" errors.");
        System.out.println("Can correct " + ((thing.getDist()-1)/2)+" errors.");
        BitString g = BitString.parseString("100000000");
        System.out.println(HammingCode.encode(g).toString());
        
    }
}
