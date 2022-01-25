public class SampleSizeDemonstrator {
    public static void main(String[] args){
        final int sampleSize = 100;
        final int criteriaCount = 100;
        int[][] group1 = new int[sampleSize / 2][criteriaCount];
        int[][] group2 = new int[sampleSize / 2][criteriaCount];
        populateWithRandomValues(group1);
        populateWithRandomValues(group2);
        for(int i = 0; i < criteriaCount; i++){
            System.out.println("Average sample values for criteria #" + (i + 1) + ":");
            System.out.println("Group 1: " + calculateAverageOfColumn(group1, i));
            System.out.println("Group 2: " + calculateAverageOfColumn(group2, i) + "\n");
        }
    }

    public static void populateWithRandomValues(int[][] array){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                array[i][j] = (int) (Math.random() * 100);
            }
        }
    }

    public static int calculateAverageOfColumn(int[][] array, int col){
        int sum = 0;
        for (int[] ints : array) {
            sum += ints[col];
        }
        return sum / array.length;
    }
}
