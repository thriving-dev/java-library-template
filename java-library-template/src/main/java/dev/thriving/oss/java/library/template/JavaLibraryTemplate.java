package dev.thriving.oss.java.library.template;

/**
 * Javadoc for {@link JavaLibraryTemplate}
 */
public class JavaLibraryTemplate {

    /**
     * Converts input to fUnKyCaSe...
     *
     * @param str the input text
     * @return the funky output of course
     */
    public static String toFunkyCase(String str) {
        final char[] chars = str.toCharArray();

        char c;
        for (int i = 0; i < chars.length; i++) {
            c = chars[i];

            chars[i] = i % 2 == 0
                    ? Character.toLowerCase(c)
                    : Character.toUpperCase(c);
        }

        return new String(chars);
    }

}
