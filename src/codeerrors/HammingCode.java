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
                for(;iteration<=values.length;iteration+=(i+1))
                    for(int k = 0;(k<=i&&iteration<=values.length);k++){
                        //System.out.println("checking " + (iteration));
                        if(values[iteration-1])
                            parity++;
                        iteration++;
                    }
                //System.out.println("Encoding parity bit " + (i+1) + " to " + parity);
                values[i]=(parity%2!=0);
            }
        }
       return new BitString(values);
    }
    public static BitString encodeP(BitString x){
        int pbits = 2;
        int totalcontain=4;
        for(;totalcontain<=pbits+x.length();totalcontain*=2){//add a pbit and increase the max container size
            pbits++;
        }
        boolean[] values = new boolean[pbits+x.length()+1];
        int realindex = 0;
        for(int i=0;i<values.length-1;i++){
            if(((i & (i+1))!=0)){//if i is not a power of 2, assign values in order
                values[i] = x.getValue(realindex);
                //System.out.println("Encoding " + (i+1) + " to " + x.getValue(realindex));
                realindex++;
            }
        }
        for(int i=0;i<values.length-1;i++){// i must start at 1 for the bitwise and to work properly
            if((i & (i+1))==0){//if i is a power of 2, begin parity check
                //System.out.println((i+1) + " is a parity bit");
                int parity = 0;
                int iteration = i+1;
                for(;iteration<=values.length-1;iteration+=(i+1))
                    for(int k = 0;(k<=i&&iteration<=values.length-1);k++){
                        //System.out.println("checking " + (iteration));
                        if(values[iteration-1])
                            parity++;
                        iteration++;
                    }
                //System.out.println("Encoding parity bit " + (i+1) + " to " + parity);
                values[i]=(parity%2!=0);
            }
        }
        int size = 0;
        for(int i=0;i<values.length-1;i++){
            if(values[i])
                size++;
        }
        values[values.length-1]=(size%2!=0);
        return new BitString(values);
    }
    public static int checkParity(BitString x){
        int errorLocation=0;
        for(int i=0;i<x.length();i++){
            if((i & (i+1))==0){
                //System.out.println((i+1) + " is a parity bit");
                int parity = 0;
                int iteration = i+1;
                for(;iteration<=x.length();iteration+=(i+1))
                    for(int k = 0;(k<=i&&iteration<=x.length());k++){
                        //System.out.println("checking " + (iteration));
                        if(x.getValue(iteration-1))
                            parity++;
                        iteration++;
                    }
                //System.out.println(parity);
                if(parity%2!=0){
                    //System.out.println("error at " + (i+1));
                    errorLocation+=(i+1);
                }
            }
        }
        if(errorLocation==0){
            System.out.println("There is no error");
            return 0;
        }
        else{
            System.out.println("There is an error at " + errorLocation + " in bitstring " + x.toString());
            return errorLocation;
        }
    }
    public static int checkParityP(BitString x){
        int errorLocation=0;
        boolean parityError = false;
        for(int i=0;i<x.length()-1;i++){
            if((i & (i+1))==0){
                //System.out.println((i+1) + " is a parity bit");
                int parity = 0;
                int iteration = i+1;
                for(;iteration<=x.length()-1;iteration+=(i+1))
                    for(int k = 0;(k<=i&&iteration<=x.length()-1);k++){
                        //System.out.println("checking " + (iteration));
                        if(x.getValue(iteration-1))
                            parity++;
                        iteration++;
                    }
                //System.out.println(parity);
                if(parity%2!=0){
                    //System.out.println("imparity at " + (i+1));
                    errorLocation+=(i+1);
                }
            }
        }
        int size = 0;
        for(int i=0;i<x.length();i++){
            if(x.getValue(i))
                size++;
        }
        parityError=((size%2==1));
        if(errorLocation==0&&!parityError){
            System.out.println("There are no errors in the bitstring " + x.toString());
            return 0;
        }
        else if(errorLocation!=0&&!parityError){
            System.out.println("There are two errors in the bitstring " + x.toString() + ", and they cannot be repaired.");
            return -1;
        }
        else if(errorLocation==0&&parityError){
            System.out.println("There is an error at " + x.length() + " in bitstring " + x.toString());
            return x.length();
        }
        else{
            System.out.println("There is an error at " + errorLocation + " in bitstring " + x.toString());
            return errorLocation;
        }
    }
}
