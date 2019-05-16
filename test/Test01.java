package org.tr.edu.yildiz.ce.specfun.test;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.tr.edu.yildiz.ce.specfun.utils.Complex;
import org.tr.edu.yildiz.ce.specfun.utils.H;
import org.tr.edu.yildiz.ce.specfun.utils.H_Talbot;

public class Test01 {
	private static INDArray a;
	private static INDArray A;
	private static INDArray b;
	private static INDArray B;

	public static void main(String[] args) {
		a = Nd4j.zeros(0,0);
		A = Nd4j.zeros(0,0);
		b = Nd4j.zeros(1,1).transpose();
		B = Nd4j.ones(1,1).transpose();
		
		double x = 1.14;
		
		System.out.println(Math.exp(-x));
		
		H h = new H(a, A, b, B, 1, 0, 0, 1, 10000); // e uzeri -x
		H_Talbot ht = new H_Talbot(a, A, b, B, 1, 0, 0, 1, 10, 5, 10000);
		
		h.evaluate(Complex.valueOf(x, 0)).printCartesian();
		ht.evaluate(Complex.valueOf(x, 0)).printCartesian();
	}

}
