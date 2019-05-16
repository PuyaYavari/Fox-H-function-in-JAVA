package org.tr.edu.yildiz.ce.specfun.test;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.tr.edu.yildiz.ce.specfun.utils.Complex;
import org.tr.edu.yildiz.ce.specfun.utils.H;
import org.tr.edu.yildiz.ce.specfun.utils.H_Talbot;

public class Test05 {
	private static INDArray a;
	private static INDArray A;
	private static INDArray b;
	private static INDArray B;

	public static void main(String[] args) {
		int p = 3;
		int q = 0;
		int n = 1;
		int m = 0;

		double alfa = -25;
		double arra[] = { alfa, alfa - (2.0 / 3.0), alfa - (1.0 / 3.0) };
		double arrA[] = { 1.0, 1.0, 1.0 };

		a = Nd4j.create(arra).transpose();
		A = Nd4j.create(arrA).transpose();
		b = Nd4j.zeros(0, 0);
		B = Nd4j.zeros(0, 0);

		double x = 11.9553345;
		
		System.out.println("Expected result = "
				+ (Math.pow(x, alfa - 1) / (2.0 * Math.sqrt(3.0) * Math.PI)) * Math.exp(-3.0 / (2.0 * Math.pow(-x, 1.0 / 3.0)))
						* (2.0 * Math.cos((3.0 * Math.sqrt(3.0)) / (2.0 * Math.pow(-x, 1.0 / 3.0)))
								+ Math.exp(9.0 / (2.0 * Math.pow(-x, 1.0 / 3.0)))));

		H h = new H(a, A, b, B, m, n, p, q, 10000);
		H_Talbot ht = new H_Talbot(a, A, b, B, m, n, p, q, 10, 10, 10000);

		System.out.println("Result = " + h.evaluate(Complex.valueOf(x, 0)).toString());
		System.out.println("Result = " + ht.evaluate(Complex.valueOf(x, 0)).toString());
	}

}
