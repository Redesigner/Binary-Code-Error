package codeerrors;

public class BitString {
    private boolean[] values;
    public BitString(int length){
        values = new boolean[length];
    }
    public BitString(boolean[] x){
        values = x;
    }
    public int length(){
        return values.length;
    }
    public boolean getValue(int x){
        return values[x];
    }
    public static BitString Xor(BitString x, BitString y){
        if(x.length()!=y.length()){
            System.out.println("The BitStrings cannot be compared!");
            return null;
        }
        boolean[] output = new boolean[x.length()];
        for(int i=0;i<x.length();i++){
            output[i] = (x.getValue(i)!=y.getValue(i));
        }
        return new BitString(output);
    }
    public int size(){
        int sum = 0;
        for(int i=0;i<length();i++){
            if(values[i])
                sum+=1;
        }
        return sum;
    }
    public String toString(){
        String output="";
        for(int i=0;i<values.length;i++){
            output+=(values[i]?1:0);
        }
        return output;
    }
    public static BitString parseString(String x){
        boolean[] output = new boolean[x.length()];
        for(int i=0;i<x.length();i++){
            output[i]=(x.charAt(i)=='1');
        }
        return new BitString(output);
    }
}
