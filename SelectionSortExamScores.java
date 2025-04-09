class SelectionSortExamScores {
    public void selectionSort(int[] scores) {
        int n = scores.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (scores[j] < scores[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = scores[minIdx];
            scores[minIdx] = scores[i];
            scores[i] = temp;
        }
    }

    public void printScores(int[] scores) {
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SelectionSortExamScores sorter = new SelectionSortExamScores();
        int[] examScores = {85, 72, 93, 60, 78, 88};
        sorter.selectionSort(examScores);
        sorter.printScores(examScores);
    }
}
