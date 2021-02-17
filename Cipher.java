import java.util.Scanner;

public class Cipher {
    protected String key;
    protected String encryptionOrDecryption;
    
    public Cipher(String key, String encryptionOrDecryption) {
        this.key = key;
        this.encryptionOrDecryption = encryptionOrDecryption;
    }

    public void encrypt(String fileName) {
        System.out.println(fileName + " has been selected");
    }

    public void decrypt(String fileName) {
        System.out.println(fileName + " has been selected");
    }
}