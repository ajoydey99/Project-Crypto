import java.io.*;

/**
 * This class demonstrates how to encrypt and decrypt a message
 * @author Ajoy
 * @version 1.0
 */
public class Crypto {
    //.....................STEPS  FOR ENCRYPT A MESSAGE.....................

    final static String ALPHABET="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * Step 1
     * This method returns normalized message
     * @param message the messege to be normalize
     * @return normalizedMessage
     */
    public static String normalizeText(String message){
        String normalizedMessage="";
        normalizedMessage=message.replaceAll("[^A-z]","").toUpperCase();
        return normalizedMessage;
    }

    /**
     * Step 2
     * This method convert normalize string into obified string by adding "OB" before every vowel
     * @param normalizedMessage the message to be obify
     * @return obifiedMessage
     */

    public static String obify(String normalizedMessage){
        String obifiedMessage="";
        for(int i=0;i<normalizedMessage.length();i++) {
            if(normalizedMessage.charAt(i)=='A'||normalizedMessage.charAt(i)=='E'|normalizedMessage.charAt(i)=='I'){
                obifiedMessage+="OB"+normalizedMessage.charAt(i);
            }else if(normalizedMessage.charAt(i)=='O'||normalizedMessage.charAt(i)=='U'|normalizedMessage.charAt(i)=='Y'){
                obifiedMessage+="OB"+normalizedMessage.charAt(i);
            }else {
                obifiedMessage+=normalizedMessage.charAt(i);
            }
        }
        return obifiedMessage;
    }

    /**
     * step 3
     * This method shift every letter of obified text by given shifting value
     * @param obifiedMessage the message to be cipher
     * @param shift number for shifting every character in the messeage
     * @return cipherText
     */
    public static String caeserify(String obifiedMessage,int shift) {
        String cipherText="";
        for(int i=0;i<obifiedMessage.length();i++){
            int getPos=ALPHABET.indexOf(obifiedMessage.charAt(i));
            int keyVal=(getPos+shift)%26;
            if(keyVal<0){
                keyVal= ALPHABET.length()+keyVal;
            }
            int replaceVal=ALPHABET.charAt(keyVal);
            char c=(char)replaceVal;
            cipherText+=c;
        }
        return cipherText;
    }

    /**
     * step 4
     * This method insert whitespace in cyphertext by seperating string into given size
     * @param cipherText the messeage to be groupify
     * @param group number of letters in every group
     * @return groupifiedText
     */
    public static String groupify(String cipherText,int group) {
        int len = cipherText.length();
        String groupifiedText = "";
        int i=0,j=0;
        for (; i < len; i += group) {
            for (; j != (i + group); j++) {
                if (j >= len)
                    break;
                groupifiedText = groupifiedText + cipherText.charAt(j);
            }
            groupifiedText+=" ";
        }
        int leng=groupifiedText.length();
        groupifiedText=groupifiedText.substring(0,leng-1);
        if(len%group==0)
            return groupifiedText;
        else
            return groupifiedText+="x";
    }

    /**
     * By calling this method from main we can see the encrypted string
     * @param message the String to be encrypted
     * @param shift number for shifting every character in the messeage
     * @param group number of letters in every group
     * @return y encrypted message
     */
    public static String encryptString(String message,int shift,int group){
        String a=normalizeText(message);
        String j=obify(a);
        String o=caeserify(j,shift);
        String y=groupify(o,group);
        return y;
    }

    //.........................STEPS FOR DECRYPT A MESSAGE......................

    /**
     * Step 1
     * By calling this method we can decrypt groupified message
     * @param groupified message which is groupified
     * @return ungroupify
     */
    public static String ungroupify(String groupified){
        String ungroupify="";
        ungroupify=groupified.replaceAll("[\" \"x]","");
        return ungroupify;
    }

    /**
     * Step 2
     * By calling this method we can decrypt caesarified message
     * @param cipherText message which is groupified
     * @return decaeserify ungroupified message
     */
    public static String decaeserify(String cipherText,int shift){
        String decaeserify="";
        decaeserify=caeserify(cipherText,-1*shift);
        return decaeserify;
    }

    /**
     * Step 3
     * By calling this method we can decrypt obified message
     * @param obified message which is obified
     * @return unobify
     */
    public static String unobify(String obified){
        String unobify="";
        unobify=obified.replace("OB","");
        return unobify;
    }

    /**
     * By calling this method from main we can see the decrypted message without space
     * @param encryptedMsg the messeage to be decrypted
     * @param shift number for shifting every character in the messeage in reverse
     * @return y
     */
    public static String decryptString(String encryptedMsg,int shift){
        String a=ungroupify(encryptedMsg);
        String j=decaeserify(a,shift);
        String o=unobify(j);
        String y=o;
        return y;
    }

    /**
     * This method demonstrates encryptString() and decryptString() methods
     * @param args Unused
     * @return nothing
     * @exception IOException On input error
     * @see IOException
     */
    public static void main(String[] args) throws IOException {
        //Create a BufferedReader using System.in
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter any message: ");
        String msg=br.readLine();
        String encryptedMsg=encryptString(msg,1,4);
        System.out.println("Encrypted Message: "+encryptedMsg);
        String decryptedMsg=decryptString(encryptedMsg,1);
        System.out.print("Decrypted Message: "+decryptedMsg);
    }
}
