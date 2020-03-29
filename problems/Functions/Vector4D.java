package problems.Functions;

public class Vector4D {


    public float x1;
    public float x2;
    public float x3;
    public float x4;

    public Vector4D(float x1, float x2, float x3, float x4) {
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
