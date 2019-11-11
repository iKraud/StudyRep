package hw02Task3;

/**
 * Сортировка пузырьком
 */
class BubbleSort implements Sort {
    public void sort(Person[] p) {

/**
 * Сортировка по имени
 */
        for (int i=Task3.count-1; i>=1; i--) {
            for (int j=0; j<i; j++) {
                String currStrWithoutSpace = p[j].getName().trim();
                String nextStrWithoutSpace = p[j+1].getName().trim();
                int minLength = Integer.min(currStrWithoutSpace.length(), nextStrWithoutSpace.length());
                for (int n=0; n<minLength; n++) {
                    if ((int)p[j].getName().charAt(n)-(int)'a'+1 > (int)p[j+1].getName().charAt(n)-(int)'a'+1) {
                        swap(p, j,j+1);
                        break;
                    }
                }
            }
        }
/**
 * Сортировка по возрасту
 */
        for (int i=Task3.count-1; i>=1; i--) {
            for (int j=0; j<i; j++) {
                if (p[j].getAge() > p[j+1].getAge()) {
                    swap(p, j,j+1);
                }
            }
        }
/**
 * Сортировка по полу
 */
        for (int i=Task3.count-1; i>=1; i--) {
            for (int j=0; j<i; j++) {
                if (p[j].getSex().ordinal() > p[j+1].getSex().ordinal()) {
                    swap(p, j,j+1);
                }
            }
        }

    }
    private void swap(Person[] p, int jCur, int jNext) {
        Person temp = p[jCur];
        p[jCur]=p[jNext];
        p[jNext]=temp;
    }
}
