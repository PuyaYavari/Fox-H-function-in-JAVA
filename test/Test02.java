package org.tr.edu.yildiz.ce.specfun.test;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.tr.edu.yildiz.ce.specfun.utils.Complex;
import org.tr.edu.yildiz.ce.specfun.utils.H;
import org.tr.edu.yildiz.ce.specfun.utils.H_Talbot;

public class Test02 {
	private static INDArray a;
	private static INDArray A;
	private static INDArray b;
	private static INDArray B;

	public static void main(String[] args) {
		double ar = 0.34;
		a = Nd4j.ones(1,1).mul(ar);
		A = Nd4j.ones(1,1).transpose();
		b = Nd4j.zeros(0,0);
		B = Nd4j.zeros(0,0);
		
		double x = 2;
		
		System.out.println("Expected result = "+Math.pow(x, ar-1) * Math.exp(-1/x));
		
		H h = new H(a, A, b, B, 0, 1, 1, 0, 10000); 
		H_Talbot ht = new H_Talbot(a, A, b, B, 0, 1, 1, 0, 10, 10, 10000); 
		
		
		System.out.println("Result = "+h.evaluate(Complex.valueOf(x, 0)).toString());
		System.out.println("Result = "+ht.evaluate(Complex.valueOf(x, 0)).toString());

	}

}
