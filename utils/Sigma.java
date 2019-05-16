package org.tr.edu.yildiz.ce.specfun.utils;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * This class generates a Sigma value based on the input arrays.
 * 
 * @author Puya
 *
 */
public class Sigma {
	private static double nSigma;
	private static double pSigma;

	/**
	 * @param tmpa the small as array from the Theta function formula
	 * @param tmpA the big As array from the Theta function formula
	 * @param tmpb the small bs array from the Theta function formula
	 * @param tmpB the big Bs array from the Theta function formula
	 * @return sigma value based on the input arrays.
	 */
	public static double evaluate(INDArray tmpa, INDArray tmpA, INDArray tmpb, INDArray tmpB, int m, int n) {
		INDArray a;
		INDArray A;
		INDArray b;
		INDArray B;

		if (n != 0) {
			a = Nd4j.zeros(n);
			A = Nd4j.zeros(n);
		} else {
			a = Nd4j.zeros(0, 0);
			A = Nd4j.zeros(0, 0);
		}

		if (m != 0) {
			b = Nd4j.zeros(m);
			B = Nd4j.zeros(m);
		} else {
			b = Nd4j.zeros(0, 0);
			B = Nd4j.zeros(0, 0);
		}

		for (int i = 0; i < n; i++) {
			a.putScalar(i, tmpa.getDouble(i));
			A.putScalar(i, tmpA.getDouble(i));
		}

		for (int i = 0; i < m; i++) {
			b.putScalar(i, tmpb.getDouble(i));
			B.putScalar(i, tmpB.getDouble(i));
		}
		

		if (a.length() != A.length() || b.length() != B.length()) {
			throw new InvalidParameterException("Illegal arguments!");
		}

		if (A.length() == 0 || A.isEmpty()) {
			if (B.length() == 0 || B.isEmpty())
				throw new InvalidParameterException("Both A and B arrays are empty!");
			if (B.minNumber().doubleValue() <= 0)
				throw new InvalidParameterException("Illegal arguments!");
			nSigma = b.mul(-1).div(B).maxNumber().doubleValue();
			return (nSigma + 1);
		} else {
			if (A.minNumber().doubleValue() <= 0)
				throw new InvalidParameterException("Illegal arguments!");
		}

		if (B.length() == 0 || B.isEmpty()) {
			if (A.length() == 0 || A.isEmpty())
				throw new InvalidParameterException("Both A and B arrays are empty!");
			if (A.minNumber().doubleValue() <= 0)
				throw new InvalidParameterException("Illegal arguments!");
			pSigma = Nd4j.ones(a.length()).sub(a).div(A).minNumber().doubleValue();
			return (pSigma - 1);
		} else {
			if (B.minNumber().doubleValue() <= 0)
				throw new InvalidParameterException("Illegal arguments!");
		}

		nSigma = b.mul(-1).div(B).maxNumber().doubleValue();
		pSigma = Nd4j.ones(a.length()).sub(a).div(A).minNumber().doubleValue();

		if (nSigma < 0 && Double.isInfinite(nSigma)) {
			return (pSigma - 1);
		}

		if (pSigma > 0 && Double.isInfinite(pSigma)) {
			return (nSigma + 1);
		}

		if (pSigma <= nSigma) {
			throw new InvalidParameterException("Coefficients are inconsistent.");
		}

		return ((nSigma + pSigma) / 2.0);
	}
}
