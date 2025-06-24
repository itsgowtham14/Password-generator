import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()_+-=[]{}|;:,.<>?";
    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int length, boolean useUppercase, boolean useLowercase,
                                         boolean useNumbers, boolean useSymbols) {
        if (!useUppercase && !useLowercase && !useNumbers && !useSymbols) {
            throw new IllegalArgumentException("At least one character type must be selected.");
        }
        if (length < 8 || length > 50) {
            throw new IllegalArgumentException("Length must be between 8 and 50.");
        }

        StringBuilder chars = new StringBuilder();
        if (useUppercase) chars.append(UPPERCASE);
        if (useLowercase) chars.append(LOWERCASE);
        if (useNumbers) chars.append(NUMBERS);
        if (useSymbols) chars.append(SYMBOLS);

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }
}