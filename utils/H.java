package org.tr.edu.yildiz.ce.specfun.utils;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Numerical computation of Fox H-function.
 * 
 * @author Puya
 *
 */
public class H {
	private int N;
	private INDArray constant;
	private double sigma;
	private int domain = 2;

	/**
	 * @param a the small as array from the Theta function formula
	 * @param A the big As array from the Theta function formula
	 * @param b the small bs array from the Theta function formula
	 * @param B the big Bs array from the Theta function formula
	 * @param p the p from the Theta function formula
	 * @param q the q from the Theta function formula
	 * @param m the m from the Theta function formula
	 * @param n the n from the Theta function formula
	 * @param N
	 */
	public H(INDArray a, INDArray A, INDArray b, INDArray B, int m, int n, int p, int q, int N) {
		if (N > 1) {
			this.N = N;
		} else {
			throw new InvalidParameterException("N smaller than 2!");
		}

		Complex cplx;
		double y;

		Theta theta = new Theta(m, n, p, q, a, A, b, B);

		sigma = Sigma.evaluate(a, A, b, B, m, n);

		constant = Nd4j.zeros(N - 1, 2);
		for (int i = domain; i < N - domain + 1; i++) {
			y = ((double) i * 2.0 / (double) N) - 1;
			cplx = theta.evaluate(Complex.valueOf(sigma, y / (Math.sqrt(1.0 - Math.pow(y, 2)))))
					.multiply(Complex.valueOf(
							((Math.sqrt(1.0 - Math.pow(y, 2)) + ((Math.pow(y, 2) / Math.sqrt(1.0 - Math.pow(y, 2)))))
									/ (1.0 - Math.pow(y, 2)))))
					.multiply(Complex.valueOf(2.0 / N));

			constant.putScalar(i - 1, 0, cplx.re());
			constant.putScalar(i - 1, 1, cplx.im());
		}
	}

	public Complex evaluate(Complex z) {
		Complex cplx;
		double y;

		INDArray variable = Nd4j.zeros(N - 1, 2);
		for (int i = domain; i < N - domain + 1; i++) {
			y = ((double) i * 2.0 / (double) N) - 1;
			cplx = z.pow(Complex.valueOf(-sigma, -y / (Math.sqrt(1.0 - Math.pow(y, 2)))));
			variable.putScalar(i - 1, 0, cplx.re());
			variable.putScalar(i - 1, 1, cplx.im());
		}

		return CplxMMul.evaluate(constant.transpose(), variable).multiply(Complex.valueOf(1.0 / (2.0 * Math.PI)));
	}

}
