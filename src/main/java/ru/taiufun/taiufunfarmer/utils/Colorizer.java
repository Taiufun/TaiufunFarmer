package ru.taiufun.taiufunfarmer.utils;

public class Colorizer {

    private static final char COLOR_CHAR = '§';

    public static String use(String textToTranslate) {
        if (textToTranslate == null) throw new IllegalStateException("Text is null");
        final char[] charArray = textToTranslate.toCharArray();
        int i = 0;
        while (i < charArray.length - 1) {
            if (charArray[i] == '&') {
                final char nextChar = charArray[i + 1];
                if (isColorCharacter(nextChar)) {
                    charArray[i] = COLOR_CHAR;
                    charArray[++i] = (char) (nextChar | 0x20);
                }
            }
            i++;
        }

        return new String(charArray);
    }

    private static boolean isColorCharacter(final char c) {
        return (c >= '0' && c <= '9') ||
                (c >= 'a' && c <= 'f') ||
                c == 'r' ||
                (c >= 'k' && c <= 'o') ||
                c == 'x' ||
                (c >= 'A' && c <= 'F') ||
                c == 'R' ||
                (c >= 'K' && c <= 'O') ||
                c == 'X';
    }
}