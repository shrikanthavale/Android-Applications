package at.fhooe.mcm.it.adid.model.cube;

import com.threed.jpct.Matrix;

/**
 * 
 * Class with static variables neede for cube calculations.
 * 
 * @author Mario Kraml
 * 
 */
public class CubeMath {

	/**
	 * Returns the absolute angle of an angle. If the angle is smaller that 0
	 * than the angle is calculated as 360 + angleDeg
	 * 
	 * @param angleDeg
	 *            The angeDeg for which the absolute value will be calculated
	 * @return The abolute angle of an angle.If the angle is smaller that 0 than
	 *         the angle is calculated as 360 + angleDeg.
	 */
	public static float getAbsoluteAngle(float angleDeg) {
		if (angleDeg < 0) {
			return 360 + angleDeg;
		}
		return angleDeg;
	}

	/**
	 * Normalizes the rotation matrix of a cube object, so that the position of
	 * the cube objects is at a fixed position again without any small float
	 * differences.
	 * 
	 * @param rotationMatrix
	 *            The matrix of the cube object that gets normalized
	 * @return The normalized rotation matrix
	 */
	public static Matrix normalizeRotationMatrix(Matrix rotationMatrix) {
		int[][] newMatrix = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (rotationMatrix.get(i, j) < -0.5) {
					newMatrix[i][j] = -1;
				} else {
					newMatrix[i][j] = Math.round(rotationMatrix.get(i, j));
				}
			}
		}

		Matrix returnMatrix = new Matrix();
		returnMatrix.setRow(0, newMatrix[0][0], newMatrix[0][1],
				newMatrix[0][2], newMatrix[0][3]);
		returnMatrix.setRow(1, newMatrix[1][0], newMatrix[1][1],
				newMatrix[1][2], newMatrix[1][3]);
		returnMatrix.setRow(2, newMatrix[2][0], newMatrix[2][1],
				newMatrix[2][2], newMatrix[2][3]);
		returnMatrix.setRow(3, newMatrix[3][0], newMatrix[3][1],
				newMatrix[3][2], newMatrix[3][3]);
		return returnMatrix;
	}
}
