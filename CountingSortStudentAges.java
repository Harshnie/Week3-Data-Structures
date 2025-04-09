class CountingSortStudentAges {
    public void countingSort(int[] ages, int maxAge) {
        int[] count = new int[maxAge + 1];
        int[] output = new int[ages.length];

        for (int age : ages) {
            count[age]++;
        }

        for (int i = 1; i <= maxAge; i++) {
            count[i] += count[i - 1];
        }

        for (int i = ages.length - 1; i >= 0; i--) {
            output[count[ages[i]] - 1] = ages[i];
            count[ages[i]]--;
        }

        for (int i = 0; i < ages.length; i++) {
            ages[i] = output[i];
        }
    }

    public void printAges(int[] ages) {
        for (int age : ages) {
            System.out.print(age + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CountingSortStudentAges sorter = new CountingSortStudentAges();
        int[] studentAges = {12, 15, 11, 10, 18, 13, 14, 15, 12};
        sorter.countingSort(studentAges, 18);
        sorter.printAges(studentAges);
    }
}
