package model;

public class Pixel {
    private int phiangle;
    private  int rposition;
    private int xposition;
    private int yposition;
    private int redValue;
    private int greenValue;
    private int blueValue;

    public Pixel(int xposition, int yposition, int redValue, int greenValue, int blueValue) {
        this.xposition = xposition;
        this.yposition = yposition;
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }

    public int getRedValue() {
        return redValue;
    }

    public int getGreenValue() {
        return greenValue;
    }

    public int getBlueValue() {
        return blueValue;
    }

    public int getXposition() { return xposition; }

    public int getYposition() {
        return yposition;
    }

    @Override
    public String toString() {
        return String.format("x = %3d, y = %3d, r = %3d, g = %3d  b = %3d",
                xposition + 1, yposition + 1, redValue, greenValue, blueValue);
    }
}
