package itau.desafio_api_itau.models;

public class Statistic {
    private int count;
    private double sum;
    private double average;
    private double min;
    private double max;

    public Statistic(int count, double max, double min, double average, double sum) {
        this.count = count;
        this.max = max;
        this.min = min;
        this.average = average;
        this.sum = sum;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
