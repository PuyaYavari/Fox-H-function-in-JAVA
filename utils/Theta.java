package org.tr.edu.yildiz.ce.specfun.utils;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * This class is an implementation of the theta function used in the Fox H
 * function. This class can be in two ways, by instantiating an object based on
 * your fox function parameters and then calling the 'evaluate' function for any
 * input s, or by directly calling static 'theta' function and passing all the
 * parameters. The first method is recommended.
 * 
 * @author Puya
 *
 */
public class Theta {
	private int p;
	private int q;
	private int m;
	private int n;
	private INDArray fB;
	private INDArray gB;
	private INDArray fA;
	private INDArray gA;
	private INDArray deltaA;
	private INDArray deltaB;
	private INDArray gammalogA;
	private INDArray gammalogB;
	private INDArray finalTheta;

	private static INDArray staticDeltaA;
	private static INDArray staticDeltaB;
	private static INDArray staticGammalogA;
	private static INDArray staticGammalogB;

	/**
	 * @param p the p from the Theta function formula
	 * @param q the q from the Theta function formula
	 * @param m the m from the Theta function formula
	 * @param n the n from the Theta function formula
	 * @param a the small as array from the Theta function formula
	 * @param A the big As array from the Theta function formula
	 * @param b the small bs array from the Theta function formula
	 * @param B the big Bs array from the Theta function formula
	 */
	public Theta(int m, int n, int p, int q, INDArray a, INDArray A, INDArray b, INDArray B) {
		if (	m < 0 ||
				q < m ||
				n < 0 ||
				p < n ||
				a.length() != A.length() ||
				b.length() != B.length() ||
				a.length() != p ||
				b.length() != q ) {			
			throw new InvalidParameterException("Illegal arguments!");
		}
		
		if (B.length() > 0 && B.minNumber().doubleValue() <= 0) {
			throw new InvalidParameterException("Illegal arguments! Smaller than 0 element in B.");			
		}
		
		if (A.length() > 0 && A.minNumber().doubleValue() <= 0) {
			throw new InvalidParameterException("Illegal arguments! Smaller than 0 element in A.");			
		}
		
		this.p = p;
		this.q = q;
		this.m = m;
		this.n = n;

		this.gA = G.evaluate(p, n, true, a);
		this.gB = G.evaluate(q, m, false, b);
		this.fA = F.evaluate(p, n, true, A);
		this.fB = F.evaluate(q, m, false, B);
	}

	/**
	 * @param s the s from the Theta function formula
	 * @return Theta value for the current Theta object for the input s
	 */
	public Complex evaluate(Complex s) {
		Complex cplx;

		deltaA = Delta.evaluate(s, gA, fA);
		deltaB = Delta.evaluate(s, gB, fB);

		gammalogA = Nd4j.zeros(p, 2);
		for (int i = 0; i < p; i++) {
			cplx = GammaLog.evaluate(Complex.valueOf(deltaA.getDouble(i, 0), deltaA.getDouble(i, 1)));
			gammalogA.putScalar(i, 0, cplx.re());
			gammalogA.putScalar(i, 1, cplx.im());
		}

		gammalogB = Nd4j.zeros(q, 2);
		for (int i = 0; i < q; i++) {
			cplx = GammaLog.evaluate(Complex.valueOf(deltaB.getDouble(i, 0), deltaB.getDouble(i, 1)));
			if (Double.isNaN(cplx.re()) || Double.isNaN(cplx.im())) {
				int a = 1;
			}
			gammalogB.putScalar(i, 0, cplx.re());
			gammalogB.putScalar(i, 1, cplx.im());
		}
		
		if (gammalogA.length() == 0 || gammalogA.isEmpty()) {
			finalTheta = Matrix.genVector(q, m, false).mmul(gammalogB);
		} else if (gammalogB.length() == 0 || gammalogB.isEmpty()) {
			finalTheta = Matrix.genVector(p, n, false).mmul(gammalogA);
		} else {
			finalTheta = Matrix.genVector(q, m, false).mmul(gammalogB).add(Matrix.genVector(p, n, false).mmul(gammalogA));
		}		

		return Complex.exp(Complex.valueOf(finalTheta.getDouble(0, 0), finalTheta.getDouble(0, 1)));
	}

	/**
	 * This function takes all the parameters needed for calculation of Theta
	 * function and returns the value of Theta function
	 * 
	 * @param s the s from the Theta function formula
	 * @param p the p from the Theta function formula
	 * @param q the q from the Theta function formula
	 * @param m the m from the Theta function formula
	 * @param n the n from the Theta function formula
	 * @param a the small as array from the Theta function formula
	 * @param A the big As array from the Theta function formula
	 * @param b the small bs array from the Theta function formula
	 * @param B the big Bs array from the Theta function formula
	 * @return Theta value based on the inputs
	 */
	public static Complex theta(Complex s, int p, int q, int m, int n, INDArray a, INDArray A, INDArray b, INDArray B) {
		if (	m < 0 ||
				q < m ||
				n < 0 ||
				p < n ||
				a.length() != A.length() ||
				b.length() != B.length() ||
				a.length() != p ||
				b.length() != q ) {			
			throw new InvalidParameterException("Illegal arguments!");
		}
		
		if (B.length() > 0 && B.minNumber().doubleValue() <= 0) {
			throw new InvalidParameterException("Illegal arguments! Smaller than 0 element in B.");
		}
		
		if (A.length() > 0 && A.minNumber().doubleValue() <= 0) {
			throw new InvalidParameterException("Illegal arguments! Smaller than 0 element in A.");			
		}

		staticDeltaA = Delta.evaluate(s, G.evaluate(p, n, true, a), F.evaluate(p, n, true, A));
		staticDeltaB = Delta.evaluate(s, G.evaluate(q, m, false, b), F.evaluate(q, m, false, B));

		staticGammalogA = Nd4j.zeros(p, 2);
		for (int i = 0; i < p; i++) {
			staticGammalogA.putScalar(i, 0, GammaLog
					.evaluate(Complex.valueOf(staticDeltaA.getDouble(i, 0), staticDeltaA.getDouble(i, 1))).re());
			staticGammalogA.putScalar(i, 1, GammaLog
					.evaluate(Complex.valueOf(staticDeltaA.getDouble(i, 0), staticDeltaA.getDouble(i, 1))).im());
		}

		staticGammalogB = Nd4j.zeros(q, 2);
		for (int i = 0; i < q; i++) {
			staticGammalogB.putScalar(i, 0, GammaLog
					.evaluate(Complex.valueOf(staticDeltaB.getDouble(i, 0), staticDeltaB.getDouble(i, 1))).re());
			staticGammalogB.putScalar(i, 1, GammaLog
					.evaluate(Complex.valueOf(staticDeltaB.getDouble(i, 0), staticDeltaB.getDouble(i, 1))).im());
		}

		return Complex.valueOf(
				Matrix.genVector(q, m, false).mmul(staticGammalogB)
						.add(Matrix.genVector(p, n, false).mmul(staticGammalogA)).getDouble(0, 0),
				Matrix.genVector(q, m, false).mmul(staticGammalogB)
						.add(Matrix.genVector(p, n, false).mmul(staticGammalogA)).getDouble(0, 1));
	}
}
