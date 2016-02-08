/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SathyaSrini
 */
 
import java.util.*;


public class Driver {

    static void denoteTermination() {
        System.out.println("==================");
        System.out.println("Program Terminated");
        System.out.println("==================");
    }

    public static void main(String[] args) {

        String xCoeff;
        String constant;

        try (Scanner in = new Scanner(System.in)) {
            System.out.println("====LINE 1===");
            System.out.println("Enter X Coeff");
            xCoeff = in.nextLine();
            System.out.println("Enter constant");
            constant = in.nextLine();

            Line line1 = Line.naturalParser(xCoeff, constant);

            if (line1 != null) {

                System.out.println("====LINE 2===");
                System.out.println("Enter X Coeff");
                xCoeff = in.nextLine();
                System.out.println("Enter constant");
                constant = in.nextLine();

                Line line2 = Line.naturalParser(xCoeff, constant);

                if (line2 != null) {
                    float line1Slope = Line.computeSlope(line1);
                    float line2Slope = Line.computeSlope(line2);

                    Line.displayLine(line1);
                    Line.displayLine(line2);

                    if (line1Slope == line2Slope) {
                        System.out.println("Not possible for intersection");

                    } else {
                        System.out.println("================");

                        Line line3 = Line.computeIntersection(line1, line2);

                        Line.displayPoints(line3);

                    }
                } else {
                    denoteTermination();
                }
            } else {
                denoteTermination();
            }
        }

    }

}

class Line {

    private final float xCoeff;
    private final float constant;

    Line(float x, float c) {
        xCoeff = x;
        constant = c;
    }

    public float getConstant() {
        return this.constant;
    }

    public float getxCoeff() {
        return this.xCoeff;
    }

    static float computeSlope(Line inputObj) {

        float slope = inputObj.xCoeff;

        return slope;
    }

    static Line naturalParser(String xCoeff, String constant) {

        Line retObj = null;

        int yCoeff_Int = 0;
        int xCoeff_Int = 0;

        float yCoeff_Float = 0;
        float xCoeff_Float = 0;

        String yCoeff_Str;
        String xCoeff_Str;

        try {
            yCoeff_Float = Float.parseFloat(xCoeff);
        } catch (Exception e1) {
            try {
                String[] split = xCoeff.split("/");
                if (!(split.length == 0)) {

                    String Numer = split[0];
                    String Denom = split[1];

                    double fract = Double.parseDouble(Numer) / Double.parseDouble(Denom);

                    yCoeff_Float = (float) fract;

                } else {

                    throw new Exception();

                }

            } catch (Exception e2) {
                System.out.println("\nSorry buddy, you have a bad line equation - specifically its X-Coeffecient\n");
                return retObj;
            }
        }

        try {

            xCoeff_Float = Float.parseFloat(constant);

        } catch (Exception e1) {

            try {

                String[] split = constant.split("/");

                if (!(split.length == 0)) {
                    String Numer = split[0];
                    String Denom = split[1];

                    double fract = Double.parseDouble(Numer) / Double.parseDouble(Denom);

                    xCoeff_Float = (float) fract;

                } else {

                    throw new Exception();

                }

            } catch (Exception e2) {
                System.out.println("\nSorry buddy, you have a bad equation- specifically its Constant\n");
                return retObj;
            }
        }

        retObj = new Line(yCoeff_Float, xCoeff_Float);

        return (retObj != null ? retObj : null);
    }

    static void displayLine(Line obj) {

        if (obj.constant >= 0) {
            System.out.println("y = " + computeSlope(obj) + "x" + "+" + obj.constant);
        } else {
            System.out.println("y = " + computeSlope(obj) + "x" + obj.constant);
        }
    }

    static void displayPoints(Line obj) {

        System.out.println("===========");
        System.out.println("Y Coord : " + obj.xCoeff);
        System.out.println("X Coord : " + obj.constant);
    }

    static Line computeIntersection(Line line1, Line line2) {

        Line retObj = null;

        float x = (float) ((line2.getConstant() - line1.getConstant()) / (line1.getxCoeff() - line2.getxCoeff()));
        float c = (float) (line1.getxCoeff() * x + line1.getConstant());

        retObj = Line.computeIntersection(line1, line2);

        return (retObj != null ? retObj : null);

    }
}
