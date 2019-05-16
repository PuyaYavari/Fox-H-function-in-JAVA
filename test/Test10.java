package org.tr.edu.yildiz.ce.specfun.test;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.tr.edu.yildiz.ce.specfun.utils.Complex;
import org.tr.edu.yildiz.ce.specfun.utils.H;
import org.tr.edu.yildiz.ce.specfun.utils.H_Talbot;

public class Test10 {
	private static INDArray a;
	private static INDArray A;
	private static INDArray b;
	private static INDArray B;

	public static void main(String[] args) {
		int p = 2;
		int q = 2;
		int n = 2;
		int m = 2;
		
		double alfa = 0.8;
		double beta = 1.4;
		double arra[] = {alfa, beta};
		double arrb[] = {beta, alfa};

		a = Nd4j.create(arra).transpose();
		A = Nd4j.ones(2).transpose();
		b = Nd4j.create(arrb).transpose();
		B = Nd4j.ones(2).transpose();

		double x = 2.0;

		System.out.println(
				"Expected result = " + Math.PI * (1/Math.sin((alfa-beta)*Math.PI)) * ((Math.pow(x, alfa) - Math.pow(x, beta))/(x - 1)));

		H h = new H(a, A, b, B, m, n, p, q, 10000);
		H_Talbot ht = new H_Talbot(a, A, b, B, m, n, p, q, 10, 10, 10000);

		System.out.println("Result = " + h.evaluate(Complex.valueOf(x, 0)).toString());
		System.out.println("Result = " + ht.evaluate(Complex.valueOf(x, 0)).toString());

	}

}
