package org.tr.edu.yildiz.ce.specfun.utils;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Numerical computation of Fox H-function.
 * 
 * @author Puya
 *
 */
public class H_Talbot {
	private int N;
	private INDArray constant;
	private double sigma, mu, nu;
	private int domain = 5;
	private static double eps = 1e-10;

	/**
	 * @param a the small as array from the Theta function formula
	 * @param A the big As array from the Theta function formula
	 * @param b the small bs array from the Theta function formula
	 * @param B the big Bs array from the Theta function formula
	 * @param p the p from the Theta function formula
	 * @param q the q from the Theta function formula
	 * @param m the m from the Theta function formula
	 * @param n the n from the Theta function formula
	 * @param mu the mu from the Talbot formula
	 * @param nu the nu from the Talbot formula
	 * @param N
	 */
	public H_Talbot(INDArray a, INDArray A, INDArray b, INDArray B, int m, int n, int p, int q, double mu, double nu, int N) {
		if (N > 1) {
			this.N = N;
		} else {
			throw new InvalidParameterException("N smaller than 2!");
		}

		if (mu <= 0 || nu <= 0)
			throw new InvalidParameterException("mu or nu smaller than 0!");

		this.mu = mu;
		this.nu = nu;

		Complex cplx, cplx2;

		Theta theta = new Theta(m, n, p, q, a, A, b, B);

		sigma = Sigma.evaluate(a, A, b, B, m, n);

		System.out.println("m,n = " + m + "," + n);
		System.out.println("p,q = " + p + "," + q);
		System.out.println("a = " + a.toString());
		System.out.println("A = " + A.toString());
		System.out.println("b = " + b.toString());
		System.out.println("B = " + B.toString());
		System.out.println("mu,nu = " + mu + "," + nu);
		System.out.println("Sigma = " + sigma);
		System.out.println("Theta = " + theta.evaluate(Complex.valueOf(sigma)).toString());

		constant = Nd4j.zeros(N - 1, 2);
		double delta = 2.0 * Math.PI / N;
		for (int i = domain; i < N - domain + 1; i++) {
			double tn = (delta * i) - Math.PI; // The n'th theta of the Talbot method
			cplx = Complex.valueOf(sigma + (mu * tn / Math.tan(tn + eps)), mu * nu * tn);
			cplx = theta.evaluate(cplx);
			cplx2 = Complex.valueOf((mu / Math.tan(tn + eps)) - (mu * tn * Math.pow(1.0 / Math.sin(tn + eps), 2.0)), mu * nu);
			cplx = cplx.multiply(cplx2);
			cplx = cplx.multiply(Complex.valueOf(delta));

			constant.putScalar(i - 1, 0, cplx.re());
			constant.putScalar(i - 1, 1, cplx.im());
		}
	}

	public Complex evaluate(Complex z) {
		Complex cplx;

		double delta = 2.0 * Math.PI / N;
		INDArray variable = Nd4j.zeros(N - 1, 2);
		for (int i = domain; i < N - domain + 1; i++) {
			double tn = (delta * i) - Math.PI;
			cplx = z.pow(Complex.valueOf(-sigma - (mu * tn / Math.tan(tn)), -1.0 * mu * nu * tn));
			variable.putScalar(i - 1, 0, cplx.re());
			variable.putScalar(i - 1, 1, cplx.im());
		}

		return CplxMMul.evaluate(constant.transpose(), variable).multiply(Complex.valueOf(1.0 / (2.0 * Math.PI)));
	}

}
