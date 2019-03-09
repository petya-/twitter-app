package Tool;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Jeffrey
 */
@WebService(serviceName = "WordCounter")
public class WordCounter {

    @WebMethod(operationName = "CountWords")
    public int WordCount(@WebParam(name = "fullText") String txt) {

        if (txt == null || txt.isEmpty()) {
            return 0;
        } else if (!txt.isEmpty() || txt != null) {

            int wordCount = 0;
            boolean word = false;
            int endOfLine = txt.length() - 1;

            for (int i = 0; i < txt.length(); i++) {
                if (Character.isLetter(txt.charAt(i)) && i != endOfLine) {
                    word = true;
                    // if char isn't a letter and there have been letters before, counter goes up.
                } else if (!Character.isLetter(txt.charAt(i)) && word) {
                    wordCount++;
                    word = false;
                    // last word of String; if it doesn't end with a non letter, it wouldn't count without this.
                } else if (Character.isLetter(txt.charAt(i)) && i == endOfLine) {
                    wordCount++;
                }
            }
            return wordCount;
        } else {
            return 0;
        }
    }

}
