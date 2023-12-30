package com.adventofcode.flashk.day24;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NeverTellMeTheOdds {

    private final List<Hailstone> hailstones;
    public NeverTellMeTheOdds(List<String> inputs, boolean isPartOne) {

        if(isPartOne) {
            hailstones = inputs.stream().map(s -> new Hailstone(s, true)).toList();
        } else {
            hailstones = inputs.stream().map(s -> new Hailstone(s, false)).toList();
        }

    }

    public long solveA(long min, long max) {

        // Compare all hailstones combinations
        long result = 0;

        List<Hailstone> hailstonesCopy = new ArrayList<>(hailstones);

        while(!hailstonesCopy.isEmpty()) {
            Iterator<Hailstone> hailstoneIterator = hailstonesCopy.iterator();
            Hailstone hailstoneA = hailstoneIterator.next();
            while(hailstoneIterator.hasNext()) {
                Hailstone hailstoneB = hailstoneIterator.next();
                if(hailstoneA.intersectsInFuture(hailstoneB, min, max)) {
                    result++;
                }
            }
            hailstonesCopy.remove(0);
        }

        return result;
    }

    public long solveB() {

        // 1. Pick 5 hailstones
        Hailstone hailstoneA = hailstones.get(0);
        Hailstone hailstoneB = hailstones.get(1);
        Hailstone hailstoneC = hailstones.get(2);
        Hailstone hailstoneD = hailstones.get(3);
        Hailstone hailstoneE = hailstones.get(4);

        RealVector solutionXY = solveXY(hailstoneA, hailstoneB, hailstoneC, hailstoneD, hailstoneE);
        RealVector solutionXZ = solveXZ(hailstoneA, hailstoneB, hailstoneC, hailstoneD, hailstoneE);

        long rockX = Math.round(solutionXY.getEntry(0));
        long rockY = Math.round(solutionXY.getEntry(1));
        long rockZ = Math.round(solutionXZ.getEntry(1));

        return rockX + rockY + rockZ;
    }

    private RealVector solveXZ(Hailstone hailstoneA, Hailstone hailstoneB, Hailstone hailstoneC, Hailstone hailstoneD, Hailstone hailstoneE) {

        // Generate 4 equations based on X and Z like this:
        // (dz'-dz)X+ (dx-dx')Z + (z-z')DX + (x'-x)DZ= x' dz' - z' dx' - x dz + z dx

        // Where:
        // - x,z: is the initial position for hailstone 1 (known)
        // - dx,dz: is the speed for hailstone 1 (known)
        // - x',z'': is the initial position for hailstone 2 (known)
        // - dx',dz': is the speed for hailstone 2 (known)
        // - X,Z: is the initial position for rock (unknown, it is known from previous equation solving, but just supposed it is not)
        // - DX,DZ: is the initial speed for rock (unknown, it is known from previous equation solving, but just supposed it is not)

        // 3. Solve the XZ equations:

        // 1. Obtain equation coefficients
        double[][] equationsCoefficients = new double[4][];
        equationsCoefficients[0] = coefficientsXZ(hailstoneA, hailstoneB);
        equationsCoefficients[1] = coefficientsXZ(hailstoneA, hailstoneC);
        equationsCoefficients[2] = coefficientsXZ(hailstoneA, hailstoneD);
        equationsCoefficients[3] = coefficientsXZ(hailstoneA, hailstoneE);

        // 2. Obtain equation independent factors
        double[] independentFactors = new double[4];
        independentFactors[0] = independentFactorXZ(hailstoneA, hailstoneB);
        independentFactors[1] = independentFactorXZ(hailstoneA, hailstoneC);
        independentFactors[2] = independentFactorXZ(hailstoneA, hailstoneD);
        independentFactors[3] = independentFactorXZ(hailstoneA, hailstoneE);

        // 3. Create matrices
        RealMatrix coefficients = new Array2DRowRealMatrix(equationsCoefficients, false);
        RealVector constants =  new ArrayRealVector(independentFactors, false);

        // 4. Create solver and solve
        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
        return solver.solve(constants);

    }

    private RealVector solveXY(Hailstone hailstoneA, Hailstone hailstoneB, Hailstone hailstoneC, Hailstone hailstoneD, Hailstone hailstoneE) {

        // Generates 4 equations based on X and Y like this:
        // (dy'-dy)X+ (dx-dx')Y + (y-y')DX + (x'-x)DY= x' dy' - y' dx' - x dy + y dx

        // Where:
        // - x,y: is the initial position for hailstone 1 (known)
        // - dx,dy: is the speed for hailstone 1 (known)
        // - x',y'': is the initial position for hailstone 2 (known)
        // - dx',dy': is the speed for hailstone 2 (known)
        // - X,Y: is the initial position for rock (unknown)
        // - DX,DY: is the initial speed for rock (unknown)

        // 1. Obtain equation coefficients
        double[][] equationsCoefficients = new double[4][];
        equationsCoefficients[0] = coefficientsXY(hailstoneA, hailstoneB);
        equationsCoefficients[1] = coefficientsXY(hailstoneA, hailstoneC);
        equationsCoefficients[2] = coefficientsXY(hailstoneA, hailstoneD);
        equationsCoefficients[3] = coefficientsXY(hailstoneA, hailstoneE);

        // 2. Obtain equation independent factors
        double[] independentFactors = new double[4];
        independentFactors[0] = independentFactorXY(hailstoneA, hailstoneB);
        independentFactors[1] = independentFactorXY(hailstoneA, hailstoneC);
        independentFactors[2] = independentFactorXY(hailstoneA, hailstoneD);
        independentFactors[3] = independentFactorXY(hailstoneA, hailstoneE);

        // 3. Create matrices
        RealMatrix coefficients = new Array2DRowRealMatrix(equationsCoefficients, false);
        RealVector constants =  new ArrayRealVector(independentFactors, false);

        // 4. Create solver and solve
        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
        return solver.solve(constants);

    }

    private double[] coefficientsXY(Hailstone hailstoneA, Hailstone hailstoneB) {

        double[] coefficients = new double[4];

        // Calculates the LHS (left Hand Side) coefficients for the equation:
        // (dy'-dy)X+ (dx-dx')Y + (y-y')DX + (x'-x)DY = x' dy' - y' dx' - x dy + y dx
        // Where LHS is: (dy'-dy)X+ (dx-dx')Y + (y-y')DX + (x'-x)DY

        coefficients[0] = hailstoneB.getSpeed().getY()-hailstoneA.getSpeed().getY(); // dy'-dy
        coefficients[1] = hailstoneA.getSpeed().getX()-hailstoneB.getSpeed().getX(); // dx-dx'
        coefficients[2] = hailstoneA.getPosition().getY()-hailstoneB.getPosition().getY(); // y-y'
        coefficients[3] = hailstoneB.getPosition().getX()-hailstoneA.getPosition().getX(); // x'-x

        return coefficients;

    }

    private double[] coefficientsXZ(Hailstone hailstoneA, Hailstone hailstoneB) {

        double[] coefficients = new double[4];

        // Calculates the LHS (left Hand Side) coefficients for the equation:
        // (dz'-dz)X+ (dx-dx')Z + (z-z')DX + (x'-x)DZ = x' dz' - z' dx' - x dz + z dx
        // Where LHS is: (dz'-dz)X+ (dx-dx')Z + (z-z')DX + (x'-x)DZ

        coefficients[0] = hailstoneB.getSpeed().getZ()-hailstoneA.getSpeed().getZ(); // dz'-dz
        coefficients[1] = hailstoneA.getSpeed().getX()-hailstoneB.getSpeed().getX(); // dx-dx'
        coefficients[2] = hailstoneA.getPosition().getZ()-hailstoneB.getPosition().getZ(); // z-z'
        coefficients[3] = hailstoneB.getPosition().getX()-hailstoneA.getPosition().getX(); // x'-x

        return coefficients;

    }

    private double independentFactorXY(Hailstone hailstoneA, Hailstone hailstoneB) {

        // Calculates the RHS (Right Hand Side), the independent factor for the equation:
        // (dy'-dy)X+ (dx-dx')Y + (y-y')DX + (x'-x)DY = x' dy' - y' dx' - x dy + y dx
        // Where RHS is: x' dy' - y' dx' - x dy + y dx

        double value1 = hailstoneB.getPosition().getX() * hailstoneB.getSpeed().getY(); // x' dy'
        double value2 = hailstoneB.getPosition().getY() * hailstoneB.getSpeed().getX(); // y' dx'
        double value3 = hailstoneA.getPosition().getX() * hailstoneA.getSpeed().getY(); // x dy
        double value4 = hailstoneA.getPosition().getY() * hailstoneA.getSpeed().getX(); // y dx

        return value1 - value2 - value3 + value4;
    }

    private double independentFactorXZ(Hailstone hailstoneA, Hailstone hailstoneB) {

        // Calculates the RHS (Right Hand Side), the independent factor for the equation:
        // (dz'-dz)X+ (dx-dx')Z + (z-z')DX + (x'-x)DZ = x' dz' - z' dx' - x dz + z dx
        // Where RHS is: x' dz' - z' dx' - x dz + z dx

        double value1 = hailstoneB.getPosition().getX() * hailstoneB.getSpeed().getZ(); // x' dz'
        double value2 = hailstoneB.getPosition().getZ() * hailstoneB.getSpeed().getX(); // z' dx'
        double value3 = hailstoneA.getPosition().getX() * hailstoneA.getSpeed().getZ(); // x dz
        double value4 = hailstoneA.getPosition().getZ() * hailstoneA.getSpeed().getX(); // z dx

        return value1 - value2 - value3 + value4;
    }

}
