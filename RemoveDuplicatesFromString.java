import java.util.HashSet;

class RemoveDuplicatesFromString {
    public static String removeDuplicates(String input) {
        StringBuilder sb = new StringBuilder();
        HashSet<Character> seen = new HashSet<>();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (!seen.contains(currentChar)) {
                sb.append(currentChar);
                seen.add(currentChar);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "programming";
        String result = removeDuplicates(input);
        System.out.println("String without duplicates: " + result);
    }
}
