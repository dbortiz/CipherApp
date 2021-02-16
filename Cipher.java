import java.util.Scanner;

public class Cipher {
    protected String key;
    protected boolean encryptionOrDecryption;
    
    public Cipher(String key, boolean encryptionOrDecryption) {
        this.key = key;
        this.encryptionOrDecryption = encryptionOrDecryption;
    }

    public void Encrypt(String fileName) {
        System.out.println(fileName + " has been selected");
    }

    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);

        System.out.println("Select a cipher: ");
        String cipher = scanObj.nextLine();
        System.out.println("Choose a key: ");
        String key = scanObj.nextLine();
        System.out.println("Choose to encrypt or decrypt: ");
        String encryptionOrDecryption = scanObj.nextLine();
        boolean encryptOrDecrypt;

        if (encryptionOrDecryption.equals("encrypt")){
            encryptOrDecrypt = true;
        } else {
            encryptOrDecrypt = false;
        }

        if (cipher.equals("caesar")) {
            Cipher c = new Caesar(key, encryptOrDecrypt);
            
            c.Encrypt("message.txt");

        }else {
            System.out.println("oops");
        }

    }
}