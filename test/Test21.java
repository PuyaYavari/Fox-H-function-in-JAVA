package org.tr.edu.yildiz.ce.specfun.test;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.tr.edu.yildiz.ce.specfun.utils.Complex;
import org.tr.edu.yildiz.ce.specfun.utils.H;
import org.tr.edu.yildiz.ce.specfun.utils.H_Talbot;

public class Test21 {
	private static INDArray a;
	private static INDArray A;
	private static INDArray b;
	private static INDArray B;

	public static void main(String[] args) {
		int p = 2;
		int q = 3;
		int n = 1;
		int m = 1;

		double alfa = 1.2;
		double arra[] = { alfa, alfa-0.5};
		double arrb[] = { alfa, alfa - 1, alfa - 0.5};

		a = Nd4j.create(arra).transpose();
		A = Nd4j.ones(2).transpose();
		b = Nd4j.create(arrb).transpose();
		B = Nd4j.ones(3).transpose();
		
		double x = 11.9553345;

		System.out.println("Expected result = "
				+ ((-2/Math.PI) * Math.exp(x/2) * Math.pow(x, alfa - 1) * Math.sinh(x/2)));

		H h = new H(a, A, b, B, m, n, p, q, 10000);
		H_Talbot ht = new H_Talbot(a, A, b, B, m, n, p, q, 10, 10, 10000);

		System.out.println("Result = " + h.evaluate(Complex.valueOf(x, 0)).toString());
		System.out.println("Result = " + ht.evaluate(Complex.valueOf(x, 0)).toString());

	}

}
