package org.tr.edu.yildiz.ce.specfun.test;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.tr.edu.yildiz.ce.specfun.utils.Complex;
import org.tr.edu.yildiz.ce.specfun.utils.H;
import org.tr.edu.yildiz.ce.specfun.utils.H_Talbot;

public class Test04 {
	private static INDArray a;
	private static INDArray A;
	private static INDArray b;
	private static INDArray B;

	public static void main(String[] args) {
		double beta = 0.5;
		double arra[] = { 1.0, beta };

		a = Nd4j.create(arra).transpose();
		A = Nd4j.ones(2).transpose();
		b = Nd4j.ones(1).mul(beta).transpose();
		B = Nd4j.ones(1).transpose();

		double x = 2;

		System.out.println("Expected result = " + (Math.sin((arra[0]-arra[1]) * Math.PI) / Math.PI) * Math.pow(x, arra[0]-1) * Math.exp(1.0/x));

		H h = new H(a, A, b, B, 0, 1, 2, 1, 10000);
		H_Talbot ht = new H_Talbot(a, A, b, B, 0, 1, 2, 1, 10, 10, 10000);

		System.out.println("Result = " + h.evaluate(Complex.valueOf(x, 0)).toString());
		System.out.println("Result = " + ht.evaluate(Complex.valueOf(x, 0)).toString());
	}

}
