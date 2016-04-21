package codeerrors;
import codeerrors.BitString;
public class Code {
    BitString[] codewords;
    int bitlength;
    int n;
    
    public Code(BitString[] code_words, int bit_length, int _n){
        codewords = code_words;
        bitlength = bit_length;
        n = _n;
    }
    public Code(BitString[] code_words){
        codewords = code_words;
        bitlength = code_words[0].length();
        n = codewords.length;
    }
    
    public int getDist(){
        int minDist = bitlength;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i!=j){
                    BitString test = BitString.Xor(codewords[i],codewords[j]);
                    int hamming = test.size();
                    //System.out.println("the size is " + test.size()+ " of " + test.toString());
                    if(hamming<=minDist)
                        minDist=hamming;
                }  
            }   
        }
    return minDist;
    }
    /*public int[] findErrors(){
        int[] output;
        for(int i=0;i<bitlength;i++){
            
        }
    }*/
}
