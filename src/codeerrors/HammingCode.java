package codeerrors;
import codeerrors.BitString;

public class HammingCode {
    public static BitString encode(BitString x){
        int pbits = 2;
        int totalcontain=4;
        for(;totalcontain<=pbits+x.length();totalcontain*=2){//add a pbit and increase the max container size
            pbits++;
        }
        boolean[] values = new boolean[pbits+x.length()];
        int realindex = 0;
        for(int i=0;i<values.length;i++){
            if(((i & (i+1))!=0)){//if i is not a power of 2, assign values in order
                values[i] = x.getValue(realindex);
                //System.out.println("Encoding " + (i+1) + " to " + x.getValue(realindex));
                realindex++;
            }
        }
        for(int i=0;i<values.length;i++){// i must start at 1 for the bitwise and to work properly
            if((i & (i+1))==0){//if i is a power of 2, begin parity check
                //System.out.println((i+1) + " is a parity bit");
                int parity = 0;
                int iteration = i+1;
                for(;iteration+i<=values.length;iteration+=(i+1))
                    for(int k = 0;k<=i;k++){
                        //System.out.println("checking " + (iteration));
                        if(values[iteration-1])
                            parity++;
                        iteration++;
                    }
                //System.out.println("Encoding parity bit " + i + " to " + parity);
                values[i]=(parity%2!=0);
            }
        }
       return new BitString(values);
    }
}
