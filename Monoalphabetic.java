import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Monoalphabetic extends Cipher{
    Monoalphabetic(String key, String encryptionOrDecryption) {
        super(key, encryptionOrDecryption);
        System.out.println("Created Monoalphabetic object.");
    }

    @Override
    public void encrypt(String fileName) {
        try {
            File message = new File(fileName);
            FileWriter messageWriter = new FileWriter(fileName.substring(0, fileName.length()-4) + "_ENCRYPTED.txt");
            Scanner messageReader = new Scanner(message);

            while (messageReader.hasNextLine()){
                String line = messageReader.nextLine().toLowerCase();
                String k = this.key.toLowerCase();

                for (char c : line.toCharArray()) {
                    if (Character.isLetter(c)){
                        messageWriter.write(k.charAt(this.alphabet.indexOf(c)));    
                    } else {
                        messageWriter.write(c);
                    }
                    
                }
            }
            messageWriter.close();
            messageReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File error: Monoalphabetic");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File error: Monoalphabetic");
            e.printStackTrace();
        }
    }

    @Override
    public void decrypt(String fileName) {
        try {
            File message = new File(fileName);
            FileWriter messageWriter = new FileWriter(fileName.substring(0, fileName.length()-4) + "_DECRYPTED.txt");
            Scanner messageReader = new Scanner(message);

            while (messageReader.hasNextLine()) {
                String line = messageReader.nextLine().toLowerCase();
                String k = this.key.toLowerCase();

                for (char c : line.toCharArray()) {
                    if (Character.isLetter(c)) {
                        messageWriter.write(this.alphabet.charAt(k.indexOf(c)));
                    } else {
                        messageWriter.write(c);
                    }
                }
            }
            messageWriter.close();
            messageReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File error: Monoalphabetic");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File error: Monoalphabetic");
            e.printStackTrace();
        }
    }
}