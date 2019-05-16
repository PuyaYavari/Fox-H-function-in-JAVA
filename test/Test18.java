package org.tr.edu.yildiz.ce.specfun.test;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.tr.edu.yildiz.ce.specfun.utils.Complex;
import org.tr.edu.yildiz.ce.specfun.utils.H;
import org.tr.edu.yildiz.ce.specfun.utils.H_Talbot;

public class Test18 {
	private static INDArray a;
	private static INDArray A;
	private static INDArray b;
	private static INDArray B;

	public static void main(String[] args) {
		int p = 0;
		int q = 3;
		int n = 0;
		int m = 2;

		double beta = 1;
		double arrb[] = { beta, beta + 1.0 / 3.0, beta - 1.0 / 3.0 };

		a = Nd4j.zeros(0, 0);
		A = Nd4j.zeros(0, 0);
		b = Nd4j.create(arrb).transpose();
		B = Nd4j.ones(3).transpose();

		double x = 2.0;

		System.out.println("Expected result = "
				+ ((2 * Math.pow(x, beta - 1.0 / 3.0)) / Math.sqrt(3)) * Math.exp((-3 * Math.pow(x, 1.0 / 3.0)) / 2)
						* Math.sin(0.5 * Math.pow(3, 1.5) * Math.pow(x, 1.0 / 3.0)));

		H h = new H(a, A, b, B, m, n, p, q, 10000);
		H_Talbot ht = new H_Talbot(a, A, b, B, m, n, p, q, 10, 10, 10000);

		System.out.println("Result = " + h.evaluate(Complex.valueOf(x, 0)).toString());
		System.out.println("Result = " + ht.evaluate(Complex.valueOf(x, 0)).toString());

	}

}
