package math;

public class Vector3 {
    public double x;			//横坐标
    public double y;			//竖坐标
    public double z;			//纵坐标

    public Vector3() {
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distanceXZ(Vector3 pos) {
        double t1x = this.x;
        double t1z = this.z;

        double t2x = pos.x;
        double t2z = pos.z;

        return Math.sqrt(Math.pow((t1x -t2x), 2) + Math.pow((t1z - t2z) , 2));
    }

    /**
     * 返回XZ面的单位向量
     * 原向量如果
     * @return
     */
    public Vector3 normalize() {
        double m = 1 / Math.sqrt(x * x + z * z);
        if(Double.isNaN(m)) {
            x = 0;
            z = 0;
        } else {
            x = x * m;
            z = z * m;
        }
        return this;
    }


    /**
     * XZ面上，this向量向base的单位向量的标量投影
     * 注意是单位向量
     * @param unitBase
     * @return
     */
    public double projectionOnUnit(Vector3 unitBase) {
        return this.x * unitBase.x + this.z * unitBase.z;
    }
}
