package org.tr.edu.yildiz.ce.specfun.utils;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * A class to multiply INDArrays that represent arrays of complex numbers.
 * 
 * @author Puya
 *
 */
public class CplxMMul {
	/**
	 * This function takes two matrixes that represent arrays of Complex numbers,
	 * one 2xN and one Nx2 matrix, in first matrix the first row and in second
	 * matrix the first column represent the real part and in first matrix the
	 * second row and in second matrix the second column represent the imaginar
	 * parts of the complex numbers, this method multiplies these two matrixes and
	 * returns one complex value.
	 * 
	 * @param a 2xN INDArray
	 * @param b Nx2 INDArray
	 * @return A complex value that is the result of the multiplication of matrixes
	 */
	public static Complex evaluate(INDArray a, INDArray b) {
		if (a.shape()[1] != b.shape()[0] || a.shape()[0] != 2 || b.shape()[1] != 2) {
			throw new InvalidParameterException("Can't multiply complex matrixes. Shape mismatch.");
		}

		return Complex.valueOf(
				a.getRow(0).mmul(b.getColumn(0)).getDouble(0) - a.getRow(1).mmul(b.getColumn(1)).getDouble(0),
				a.getRow(0).mmul(b.getColumn(1)).getDouble(0) + a.getRow(1).mmul(b.getColumn(0)).getDouble(0));
	}
}
