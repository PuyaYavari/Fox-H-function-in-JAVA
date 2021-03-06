package org.tr.edu.yildiz.ce.specfun.test;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.tr.edu.yildiz.ce.specfun.utils.Complex;
import org.tr.edu.yildiz.ce.specfun.utils.H;
import org.tr.edu.yildiz.ce.specfun.utils.H_Talbot;

public class Test13 {
	private static INDArray a;
	private static INDArray A;
	private static INDArray b;
	private static INDArray B;

	public static void main(String[] args) {
		int p = 0;
		int q = 4;
		int n = 0;
		int m = 4;
		
		double beta = 0.8;
		double arrb[] = {beta,beta+0.25,beta+0.5,beta+0.75};

		a = Nd4j.zeros(0,0);
		A = Nd4j.zeros(0,0);
		b = Nd4j.create(arrb).transpose();
		B = Nd4j.ones(4).transpose();

		double x = 2.0;

		System.out.println(
				"Expected result = " + Math.sqrt(2) * Math.pow(Math.PI, 3.0/2.0) * Math.pow(x, beta) * Math.exp(-4 * Math.sqrt(Math.sqrt(x))));

		H h = new H(a, A, b, B, m, n, p, q, 10000);
		H_Talbot ht = new H_Talbot(a, A, b, B, m, n, p, q, 10, 10, 10000);

		System.out.println("Result = " + h.evaluate(Complex.valueOf(x, 0)).toString());
		System.out.println("Result = " + ht.evaluate(Complex.valueOf(x, 0)).toString());

	}

}
