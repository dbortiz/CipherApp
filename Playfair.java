import java.util.ArrayList;

class Playfair extends Cipher{
    Playfair(String key, String encryptionOrDecryption) {
        super(key, encryptionOrDecryption);
        System.out.println("Playfair object created");
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
}