import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;


class Playfair extends Cipher{
    Playfair(String key, String encryptionOrDecryption) {
        super(key, encryptionOrDecryption);
        System.out.println("Playfair object created");
    }

    @Override
    public void encrypt(String fileName){
        try {
            File message = new File(fileName);
            FileWriter messageWriter = new FileWriter(fileName.substring(0, fileName.length()-4) + "_ENCRYPTED.txt");
            Scanner messageReader = new Scanner(message);
            String k = this.key.toLowerCase();
            char[][] encoder = this.populateEncoder(k);

            while (messageReader.hasNextLine()){
                String line = messageReader.nextLine().toLowerCase();
                ArrayList<Character> messageLetters = new ArrayList<Character>();
                ArrayList<Character> specialLetters = new ArrayList<Character>();
                ArrayList<Integer> specialLettersPos = new ArrayList<Integer>();

                // Get characters of line and separate them to their appropriate ArrayList
                int pos = 0;
                for (char c : line.toLowerCase().toCharArray()) {
                    if (Character.isLetter(c)){
                        messageLetters.add(c);
                    } else {
                        specialLetters.add(c);
                        specialLettersPos.add(pos);
                    }
                    pos++;
                }

                // Message now contains padding
                ArrayList<Character> paddedMessage = this.addPadding(messageLetters);

                // Playfair encryption
                ArrayList<Character> encryptedMessage = new ArrayList<Character>();
                for (int i = 0; i < paddedMessage.size(); i += 2) {
                    char one = paddedMessage.get(i);
                    char two = paddedMessage.get(i+1);

                    int[] indexOne = this.findIndex(encoder, one);
                    int[] indexTwo = this.findIndex(encoder, two);

                    // Same row
                    if(indexOne[0] == indexTwo[0]) {
                        encryptedMessage.add(encoder[indexOne[0]][(indexOne[1] + 1) % 5]);
                        encryptedMessage.add(encoder[indexTwo[0]][(indexTwo[1] + 1) % 5]);
                    
                    // Same column
                    } else if (indexOne[1] == indexTwo[1]) {
                        encryptedMessage.add(encoder[(indexOne[0] + 1) % 5][indexOne[1]]);
                        encryptedMessage.add(encoder[(indexTwo[0] + 1) % 5][indexTwo[1]]);
                    
                    // Across from each other
                    } else {
                        encryptedMessage.add(encoder[indexOne[0]][indexTwo[1]]);
                        encryptedMessage.add(encoder[indexTwo[0]][indexOne[1]]);
                    }
                }
                // Adds special characters from original message into encrypted message
                for(int i = 0; i < specialLetters.size(); i++) {
                    encryptedMessage.add(specialLettersPos.get(i), specialLetters.get(i));
                }

                // Writes encrypted message to file
                for(char c : encryptedMessage) {
                    messageWriter.write(c);
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

    // 2D matrix encoder used to encrypt/decrypt message
    private char[][] populateEncoder(String key) {
        char[][] encoder = new char[5][5];
        ArrayList<Character> charsRead = new ArrayList<Character>();

        int row = 0;
        int col = 0;

        // Adds key to encoder
        for (char c : key.toCharArray()) {
            if ((c == 'i' || c == 'j') && !charsRead.contains('i')) {
                charsRead.add('i');
            } else if (c != 'j'){
                charsRead.add(c);
            } else {
                continue;
            }
            
            if (col == 5) {
                col = 0;
                row++;
            }
            encoder[row][col] = c;
            col++;
        }

        // Adds rest of encoder
        if (col != 5) {
            for (char c : this.alphabet.toCharArray()) {
                boolean flag = false;
                if ((c == 'i' || c == 'j') && !charsRead.contains('i')) {
                    charsRead.add('i');
                    flag = true;
                } else if (c != 'j' && !charsRead.contains(c)){
                    charsRead.add(c);
                    flag = true;
                } else {
                    continue;
                }

                if (flag) {
                    if (col == 5) {
                        col = 0;
                        row++;
                    }
                    encoder[row][col] = c;
                    col++;
                }
            }
        }

        return encoder;
    }

    // Playfair relies on using pairs of letters in a message to encrypt with the encoder
    // If there is a pair with the same letter, then it should be padded with an x
    private ArrayList<Character> addPadding(ArrayList<Character> message) {
        if (message.size() == 0) {
            return message;
        }

        if (message.size() == 1) {
            if (message.get(0).equals('x')) {
                message.add('y');
            } else {
                message.add('x');
            }
            return message;
        } else {
            if (message.get(0) == message.get(1)) {
                if (message.get(0).equals('x')) {
                    message.add(1, 'y');
                } else {
                    message.add(1, 'x');
                }

                ArrayList<Character> firstHalf = new ArrayList<Character>(message.subList(0,2));
                ArrayList<Character> secondHalf = new ArrayList<Character>(message.subList(2, message.size()));
                secondHalf = addPadding(secondHalf);
                firstHalf.addAll(secondHalf);

                return firstHalf;
            } else {
                ArrayList<Character> firstHalf = new ArrayList<Character>(message.subList(0,2));
                ArrayList<Character> secondHalf = addPadding(new ArrayList<Character>(message.subList(2, message.size())));
                firstHalf.addAll(secondHalf);

                return firstHalf;
            }
        }
    }

    // Finds the index of the letter within the encoder
    private int[] findIndex(char[][] encoder, char letter) {
        int[] index = new int[2];
        char c = letter;
        if (c == 'j') {
            c = 'i';
        }

        for(int i = 0; i < encoder.length; i++) {
            for(int j = 0; j < encoder[i].length; j++) {
                if (encoder[i][j] == c) {
                    index[0] = i;
                    index[1] = j;
                    
                    return index;
                }
            }
        }
        
        return index;
    }

}