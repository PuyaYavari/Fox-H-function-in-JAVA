package org.tr.edu.yildiz.ce.specfun.test;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.tr.edu.yildiz.ce.specfun.utils.Complex;
import org.tr.edu.yildiz.ce.specfun.utils.H;
import org.tr.edu.yildiz.ce.specfun.utils.H_Talbot;

public class Test26 {
	private static INDArray a;
	private static INDArray A;
	private static INDArray b;
	private static INDArray B;

	public static void main(String[] args) {
		int p = 1;
		int q = 2;
		int n = 0;
		int m = 1;

		double alfa = 0.8;
		double beta = 1.2;
		double arra[] = { alfa };
		double arrb[] = { beta, alfa };

		a = Nd4j.create(arra).transpose();
		A = Nd4j.ones(1).transpose();
		b = Nd4j.create(arrb).transpose();
		B = Nd4j.ones(2).transpose();

		double x = 2.0;

		System.out.println("Expected result = "
				+ ((-Math.sin((beta-alfa) * Math.PI)/Math.PI) * Math.pow(x, beta) * Math.exp(x)));

		H h = new H(a, A, b, B, m, n, p, q, 10000);
		H_Talbot ht = new H_Talbot(a, A, b, B, m, n, p, q, 10, 10, 10000);

		System.out.println("Result = " + h.evaluate(Complex.valueOf(x, 0)).toString());
		System.out.println("Result = " + ht.evaluate(Complex.valueOf(x, 0)).toString());

	}

}
