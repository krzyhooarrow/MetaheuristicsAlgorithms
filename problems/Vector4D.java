package problems;

public class Vector4D {


    public Integer x1;
    public Integer x2;
    public Integer x3;
    public Integer x4;

    public Vector4D(Integer x1, Integer x2, Integer x3, Integer x4) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
    }

    @Override
    public String toString() {
        return "Vector4D{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", x3=" + x3 +
                ", x4=" + x4 +
                '}';
    }
}
