package passion.string;

/**
 * Fixes the '&' symbol decoding, usually the decoding adds a extra whitespace.
 * This attempts to provide no whitespace, however decoding is still top-notch.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class AndEscapeDecoder {

    public static void main(String... args) throws Exception {
        String sentence = "\\u0026symbol";
        System.out.println(sentence.replace("\\u0026", "&"));
    }

    /**
     * Unescapes the string.
     * @param string
     * @return
     */
    public static String unescape(String string) {
        return string.replace("\\u0026", "&");
    }

}
