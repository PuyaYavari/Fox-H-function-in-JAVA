package org.tr.edu.yildiz.ce.specfun.utils;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * This class creates matrixes or vectors containing 1s and -1s. Each these
 * matrixes or vectors contain 'length' number of 1s and -1s in total, it's
 * whether n 1s in the beginning and the rest is -1 or vice versal and this
 * order is defined by the 'reverse' parameter, 'reverse'=0 is n 1s and q-n -1s,
 * and 'reverse'=1 is n -1s and q-n 1s.
 * 
 * @author Puya
 *
 */
public class Matrix {
	public static INDArray genVector(int length, int n, boolean reverse) {
		if (length < 0)
			throw new InvalidParameterException("Illegal arguments!");
		if (length == 0)
			return Nd4j.zeros(0,0);

		if (!reverse) {
			// return Nd4j.concat(1, Nd4j.ones(n), Nd4j.ones(length - n).mul(-1));
			INDArray tmp1 = null;
			if (length == n) {
				tmp1 = Nd4j.ones(n);
			} else if (n > 0) {
				tmp1 = Nd4j.concat(1, Nd4j.ones(n), Nd4j.ones(length - n).mul(-1));
			} else if (n == 0) {
				tmp1 =  Nd4j.ones(length);
			} else {
				throw new InvalidParameterException("Illegal arguments!");
			}
			return tmp1;
		} else {
			// return Nd4j.concat(1, Nd4j.ones(n).mul(-1), Nd4j.ones(length - n));
			INDArray tmp1 = null;
			if (length == n) {
				tmp1 = Nd4j.ones(n).mul(-1);
			} else if (n > 0) {
				tmp1 = Nd4j.concat(1, Nd4j.ones(n).mul(-1), Nd4j.ones(length - n));
			} else if (n == 0) {
				tmp1 =  Nd4j.ones(length);
			} else {
				throw new InvalidParameterException("Illegal arguments!");
			}
			return tmp1;
		}
	}

	public static INDArray genMatrix(int length, int n, boolean reverse) {
		if (length == 0) {
			return Nd4j.zeros(0, 0);
		} else {
			return Nd4j.diag(genVector(length, n, reverse));
		}

	}
}
