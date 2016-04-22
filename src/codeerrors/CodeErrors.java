package codeerrors;
import codeerrors.BitString;
import codeerrors.HammingCode;

public class CodeErrors {

    public static void main(String[] args) {/*
        //~~~~~~~~~~~~~~~~~~~ PART 1 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        BitString z = BitString.parseString("11");
        BitString t = BitString.parseString("00");
        BitString[]x ={z,t};
        Code thing = new Code(x);
        System.out.println("Can detect " + (thing.getDist()-1)+" errors.");
        System.out.println("Can correct " + ((thing.getDist()-1)/2)+" errors.");*/
        
        //~~~~~~~~~~~~~~~~~~~~ Encoding to check ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        BitString g = BitString.parseString("111001");
        System.out.println(HammingCode.encode(g).toString());
        
        //~~~~~~~~~~~~~~~~~~~~ PART 2 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        
        BitString error = BitString.parseString("0110110111");
        int ErrorLocation = HammingCode.checkParity(error);
        if(ErrorLocation!=0)
            error.flipBit(ErrorLocation-1);//the errorlocation returns the index starting at 1, but array indexes start at 0
        System.out.println("The correct bitstring is " + error);
    }
}
