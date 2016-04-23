package codeerrors;
import codeerrors.BitString;
import codeerrors.HammingCode;
import java.util.Scanner;

public class CodeErrors {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        boolean exit = false;
        int option;
        System.out.println("~~~This is a project on data encoding and error correction by Stephen Melnick~~~");
        do{
            System.out.print("\nWhat would you like to do?\n1:detect Hamming Distance \n2:encode a bitstring\n3:check a bitstring for errors\n4:fix error with nearest neightbor\n5:exit\n");
            String optionUnparsed = kb.nextLine();
            if((optionUnparsed.matches("[0-5]+")&& optionUnparsed.length()>0)){
                option = Integer.parseInt(optionUnparsed);
                switch(option) {

                    case 1:{
                        System.out.println("How many bitstrings are in the code?");
                        String numStringUnparsed =kb.nextLine();
                        if((numStringUnparsed.matches("[0-9]+")&& numStringUnparsed.length()>0)){
                            int numString = Integer.parseInt(numStringUnparsed);
                            int stringLength = 0;
                            BitString[] code = new BitString[numString];
                            for(int i =0;i<numString;i++){
                                System.out.println("Input bitstring #"+(i+1));
                                String input = kb.nextLine();
                                BitString value = BitString.parseString(input);
                                if(value!=null)
                                    if(value.length()==stringLength||stringLength==0){
                                        code[i]=value;
                                        stringLength = input.length();
                                    }
                                    else{
                                        System.out.println("The bitstring is the wrong length!");
                                        i--;
                                    }
                                else
                                    i--;
                            }
                            Code container = new Code(code);
                            System.out.println("The Hamming Distance is: "+container.getDist()+", we can detect up to " + (container.getDist()-1) + " errors and correct " + ((container.getDist()-1)/2));
                        }
                        else System.out.println("That is not a valid code length!");
                        break;
                    }

                    case 2:{
                        System.out.println("Input the string you want to encode");
                        String input = kb.nextLine();
                        BitString value = BitString.parseString(input);
                        if(value!=null)
                            System.out.println("The encoded bitstring is: " + HammingCode.encodeP(value));
                        break;
                    }

                    case 3:{
                        System.out.println("Input the string you want to check");
                        String input = kb.nextLine();
                        BitString error = BitString.parseString(input);
                        int ErrorLocation = HammingCode.checkParity(error);
                        if(ErrorLocation>0){
                            error.flipBit(ErrorLocation-1);//the errorlocation returns the index starting at 1, but array indexes start at 0
                            System.out.println("The correct bitstring is " + error);
                        }
                        break;
                    }

                    case 4:{
                        System.out.println("How many bitstrings are in the code?");
                        String numStringUnparsed =kb.nextLine();
                        if((numStringUnparsed.matches("[0-9]+")&& numStringUnparsed.length()>0)){
                            int numString = Integer.parseInt(numStringUnparsed);
                            int stringLength = 0;
                            BitString[] code = new BitString[numString];
                            for(int i =0;i<numString;i++){
                                System.out.println("Input bitstring #"+(i+1));
                                String input = kb.nextLine();
                                BitString value = BitString.parseString(input);
                                if(value!=null)
                                    if(value.length()==stringLength||stringLength==0){
                                        code[i]=value;
                                        stringLength = input.length();
                                    }
                                    else{
                                        System.out.println("The bitstring is the wrong length!");
                                        i--;
                                    }
                                else
                                    i--;
                            }
                            System.out.println("Input codeword");
                                String input = kb.nextLine();
                                BitString value = BitString.parseString(input);
                            Code container = new Code(code);
                            BitString repaired =container.findClosest(value);
                            if(repaired!=null)
                                System.out.println("The closest codeword is: "+repaired.toString());
                            else
                                System.out.println("There are too many errors to repair");
                        }break;
                    }
                    
                    case 5:{
                        exit=true;
                        break;
                    }
                }
            }
        }while(!exit);        
    }
}
