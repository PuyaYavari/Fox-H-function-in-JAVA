package org.tr.edu.yildiz.ce.specfun.test;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.tr.edu.yildiz.ce.specfun.utils.Complex;
import org.tr.edu.yildiz.ce.specfun.utils.H;
import org.tr.edu.yildiz.ce.specfun.utils.H_Talbot;

public class Test15 {
	private static INDArray a;
	private static INDArray A;
	private static INDArray b;
	private static INDArray B;

	public static void main(String[] args) {
		int p = 3;
		int q = 3;
		int n = 1;
		int m = 3;
		
		double alfa = 0.2;
		double arra[] = {alfa, alfa+0.5, alfa+1};

		a = Nd4j.create(arra).transpose();
		A = Nd4j.ones(3).transpose();
		b = Nd4j.ones(3).mul(alfa).transpose();
		B = Nd4j.ones(3).transpose();
		
		double x = 2.0;

		System.out.println(
				"Expected result = " + ((2*Math.pow(x, alfa))/Math.sqrt(Math.PI)) * (1 / Math.sinh(Math.pow(1 / Math.sqrt(x), 2))));

		H h = new H(a, A, b, B, m, n, p, q, 10000);
		H_Talbot ht = new H_Talbot(a, A, b, B, m, n, p, q, 10, 10, 10000);

		System.out.println("Result = " + h.evaluate(Complex.valueOf(x, 0)).toString());
		System.out.println("Result = " + ht.evaluate(Complex.valueOf(x, 0)).toString());

	}

}
