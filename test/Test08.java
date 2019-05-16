package org.tr.edu.yildiz.ce.specfun.test;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.tr.edu.yildiz.ce.specfun.utils.Complex;
import org.tr.edu.yildiz.ce.specfun.utils.Gamma;
import org.tr.edu.yildiz.ce.specfun.utils.H;
import org.tr.edu.yildiz.ce.specfun.utils.H_Talbot;

public class Test08 {
	private static INDArray a;
	private static INDArray A;
	private static INDArray b;
	private static INDArray B;

	public static void main(String[] args) {
		int p = 1;
		int q = 1;
		int n = 1;
		int m = 1;
		
		double alfa = 0.5;
		double beta = 0.3;

		a = Nd4j.ones(1).mul(alfa);
		A = Nd4j.ones(1);
		b = Nd4j.ones(1).mul(beta);
		B = Nd4j.ones(1);

		double x = 1.2;

		System.out.println(
				"Expected result = " + Gamma.evaluate(Complex.valueOf(1-alfa+beta)).multiply(Complex.valueOf(Math.pow(x, beta)*Math.pow(x+1, alfa-beta-1))));

		H h = new H(a, A, b, B, m, n, p, q, 10000);
		H_Talbot ht = new H_Talbot(a, A, b, B, m, n, p, q, 10, 10, 10000);

		System.out.println("Result = " + h.evaluate(Complex.valueOf(x, 0)).toString());
		System.out.println("Result = " + ht.evaluate(Complex.valueOf(x, 0)).toString());
	}

}
