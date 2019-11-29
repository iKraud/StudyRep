package hw09Task1;

public class Helpers {
    private long startTime = 0L;
    void setStartTime () {
        this.startTime = System.currentTimeMillis();
    }
    long getRunTime () {
        return System.currentTimeMillis() - startTime;
    }
}