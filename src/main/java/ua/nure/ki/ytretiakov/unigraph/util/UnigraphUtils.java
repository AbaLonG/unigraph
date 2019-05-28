package ua.nure.ki.ytretiakov.unigraph.util;

import java.util.Arrays;

public class UnigraphUtils {

    private UnigraphUtils() {
        throw new RuntimeException("Ты не пройдешь");
    }

    public static String hashFileName(final byte[] file, final String format) {
        final int hash = Arrays.hashCode(file);
        final String hex = Integer.toHexString(hash);
        return (hex.length() <= 10 ? hex : hex.substring(0, 10)) + format;
    }

    public static String getFormatFromName(final String originalFilename) {
        final int dotIndex = originalFilename.lastIndexOf('.');
        return originalFilename.substring(dotIndex);
    }
}
