public class LinearSearchWordInSentence {

    public static void main(String[] args) {
        String[] sentences = {
            "The quick brown fox jumps over the lazy dog.",
            "Hello world, this is Java.",
            "Java programming is fun.",
            "I love solving problems."
        };
        
        String word = "Java";
        String result = findSentenceWithWord(sentences, word);
        
        System.out.println(result);
    }

    public static String findSentenceWithWord(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                return sentence;
            }
        }
        return "Not Found";
    }
}
