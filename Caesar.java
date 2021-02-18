import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Caesar extends Cipher {
    Caesar(String key, String encryptionOrDecryption){
        super(key, encryptionOrDecryption);
        System.out.println("\nCreated Caesar object.");
    }

    @Override
    public void encrypt(String fileName) {
        try {
            File message = new File(fileName);
            FileWriter messageWriter = new FileWriter(fileName.substring(0, fileName.length()-4) + "_ENCRYPTED.txt");
            Scanner messageReader = new Scanner(message);
            int k = Integer.parseInt(this.key);

            while (messageReader.hasNextLine()){
                String line = messageReader.nextLine().toLowerCase();
        
                for (char c : line.toCharArray()) {
                    if (Character.isLetter(c)){
                        messageWriter.write(this.alphabet.charAt((this.alphabet.indexOf(c) + k) % 26));    
                    } else {
                        messageWriter.write(c);
                    }
                    
                }
            }
            messageWriter.close();
            messageReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File error: Caesar");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File error: Caesar");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Integer parse error: Caesar");
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
                int k = Integer.parseInt(this.key);

                for (char c : line.toCharArray()) {
                    if (Character.isLetter(c)) {
                        messageWriter.write(this.alphabet.charAt((((this.alphabet.indexOf(c) - k) % 26) + 26) % 26));
                    } else {
                        messageWriter.write(c);
                    }
                }
            }
            messageWriter.close();
            messageReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File error: Caesar");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File error: Caesar");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Parse error: Caesar");
            e.printStackTrace();
        }
    }
}