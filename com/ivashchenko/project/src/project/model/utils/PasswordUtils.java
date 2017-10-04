package project.model.utils;

import java.util.Random;

public class PasswordUtils {
    private static final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int size = chars.length();
    private static final Random random = new Random();

    public static String generatePassword() {
        StringBuilder passwordBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int randomInt = random.nextInt(size);
            passwordBuilder.append(chars.charAt(randomInt));
        }
        return passwordBuilder.toString();
    }

    public static String getHashedPassword(String rawPassword) {
        return rawPassword;
    }
}
