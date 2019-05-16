package org.tr.edu.yildiz.ce.specfun.test;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.tr.edu.yildiz.ce.specfun.utils.Complex;
import org.tr.edu.yildiz.ce.specfun.utils.H;
import org.tr.edu.yildiz.ce.specfun.utils.H_Talbot;

public class Test12 {
	private static INDArray a;
	private static INDArray A;
	private static INDArray b;
	private static INDArray B;

	public static void main(String[] args) {
		int p = 4;
		int q = 4;
		int n = 3;
		int m = 3;
		
		double alfa = 0.4;
		double arr[] = {alfa,alfa,alfa,alfa+0.5};

		a = Nd4j.create(arr).transpose();
		A = Nd4j.ones(4).transpose();
		b = Nd4j.create(arr).transpose();
		B = Nd4j.ones(4).transpose();

		double x = 1.1;

		System.out.println(
				"Expected result = " + ((Math.pow(x, alfa) * Math.pow(Math.log(x), 2)) / (2*Math.PI*(1.0 - x))));

		//H h = new H(a, A, b, B, m, n, p, q, 10000);
		H_Talbot ht = new H_Talbot(a, A, b, B, m, n, p, q, 5, 5, 10000);

		//System.out.println("Result = " + h.evaluate(Complex.valueOf(x, 0)).toString());
		System.out.println("Result = " + ht.evaluate(Complex.valueOf(x, 0)).toString());

	}

}
