class InsertionSortEmployeeIDs {
    public void insertionSort(int[] ids) {
        for (int i = 1; i < ids.length; i++) {
            int key = ids[i];
            int j = i - 1;
            while (j >= 0 && ids[j] > key) {
                ids[j + 1] = ids[j];
                j = j - 1;
            }
            ids[j + 1] = key;
        }
    }

    public void printIDs(int[] ids) {
        for (int id : ids) {
            System.out.print(id + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        InsertionSortEmployeeIDs sorter = new InsertionSortEmployeeIDs();
        int[] employeeIDs = {104, 101, 109, 102, 106, 103};
        sorter.insertionSort(employeeIDs);
        sorter.printIDs(employeeIDs);
    }
}
