package math;

import java.util.ArrayList;
import java.util.List;

public class Vector {

    private static int count = 1000_000;

    public static void main(String[] args) {
        List<Vector3> list = new ArrayList<>();
        Vector3 start = new Vector3(12.86, 0, 19.51);
        Vector3 faceTarget = new Vector3(32.47, 123, 34.32);
        //在
        list.add(new Vector3(37.39, 0, 30.44));
        list.add(new Vector3(40.32, 0, 35.97));
        list.add(new Vector3(36.09, 0, 42.96));
        list.add(new Vector3(15.12, 0, 28.49));
        list.add(new Vector3(12.19, 0, 22.47));
        list.add(new Vector3(17.23, 0, 16.62));
        list.add(new Vector3(22.11, 0, 28.16));
        list.add(new Vector3(12.86, 0, 19.51));

        //不在
        list.add(new Vector3(38.04, 0, 26.7));
        list.add(new Vector3(43.08, 0, 38.57));
        list.add(new Vector3(39.02, 0, 43.45));
        list.add(new Vector3(18.04, 0, 35.64));
        list.add(new Vector3(8.61, 0, 21.82));
        list.add(new Vector3(13.98, 0, 15.32));

        System.out.println(test1(list, start, faceTarget, 34, 15.27));
        System.out.println(test2(list, start, faceTarget, 34, 15.27));

        long a = System.nanoTime();
        test1(list, start, faceTarget, 34, 15.27);
        long b = System.nanoTime();
        System.out.println((b - a) / 1000_000_000.0);


        a = System.nanoTime();
        test2(list, start, faceTarget, 34, 15.27);
        b = System.nanoTime();
        System.out.println((b - a) / 1000_000_000.0);
    }


    public static int test1(List<Vector3> candidates,
                      Vector3 start, Vector3 faceTarget,
                      double parallelLength, double verticalLength) {
        int c = 0;
        for(int i = 0; i < count; i++) {
            List<Vector3> results = getUnitObjsInRectangle(candidates, start, faceTarget, parallelLength, verticalLength);
            c = c + results.size();
        }
        return c;
    }

    public static int test2(List<Vector3> candidates,
                     Vector3 start, Vector3 faceTarget,
                     double parallelLength, double verticalLength) {
        int c = 0;
        for(int i = 0; i < count; i++) {
            List<Vector3> results = getUnitObjsInRectangle2(candidates, start, faceTarget, parallelLength, verticalLength);
            c = c + results.size();
        }
        return c;
    }



    public static List<Vector3> getUnitObjsInRectangle(List<Vector3> candidates,
                                                Vector3 start, Vector3 faceTarget,
                                                double parallelLength, double verticalLength) {
        List<Vector3> result = new ArrayList<>();

        //计算斜率k和b,y = kx + b,
        //Ax + By + c = 0
        double a = 0;
        double b = 0;
        //斜率无穷大的时候单独计算
        if(Math.abs(faceTarget.x - start.x) <= 0.0001) {
            a = 1;
        } else {
            //A=k, B=-1
            a = (faceTarget.z - start.z) / (faceTarget.x - start.x);
            b = -1;
        }

        double c = (-1) * b * faceTarget.z - a * faceTarget.x;
        for(Vector3 p : candidates) {

            //直线方程Ax+By+C=0(A≠0，B≠0)垂直的直线方程是Bx-Ay+m = 0,
            //计算垂直方程参数m = Ay - Bx
            double m = a * p.z - b * p.x;

            /// 求两直线交点坐标  ,解2次方程得到的
            //x = (-ac-mb)/(a*a + b*b), y = (am -bc)/(a*a + b*b)
            Vector3 pCross = new Vector3();
            pCross.x = (-1 * m * b - a * c) / (a * a + b * b);
            pCross.z = (a * m - b * c) / (a * a + b * b);

            //判断点到直线的交叉点是否在点到目标点的射线上, 不在反向延长线上
            if((pCross.z - start.z) * (faceTarget.z - start.z) < 0) continue;

            //判断交叉点没有超过矩形宽度
            if(pCross.distanceXZ(start) > parallelLength) continue;

            //到直线距离小于width, 直线Ax+By+C=0 坐标（Xo，Yo）那么这点到这直线的距离就为：│AXo+BYo+C│/√（A²+B²）
            double disToLine = Math.abs(a * p.x + b * p.z + c) / Math.sqrt(a * a + b * b);
            if(disToLine > verticalLength / 2) continue;

            result.add(p);
        }

        return result;
    }



    public static List<Vector3> getUnitObjsInRectangle2(List<Vector3> candidates,
                                                 Vector3 from, Vector3 to,
                                                 double parallelLength, double verticalLength) {
        List<Vector3> result = new ArrayList<>();

        Vector3 unitParallel = new Vector3(to.x - from.x, 0, to.z - from.z).normalize();
        Vector3 unitVertical = new Vector3(unitParallel.z, 0, -unitParallel.x);
        for(Vector3 T : candidates) {
            Vector3 vectorT = new Vector3(T.x - from.x, 0, T.z - from.z);
            double projectionP = vectorT.projectionOnUnit(unitParallel);
            if(projectionP >= 0 && projectionP <= parallelLength) {
                double projectionV = vectorT.projectionOnUnit(unitVertical);
                if(projectionV >= - verticalLength / 2 && projectionV <= verticalLength / 2) {
                    result.add(T);
                }
            }
        }
        return result;
    }

}
