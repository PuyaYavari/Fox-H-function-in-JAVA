package org.tr.edu.yildiz.ce.specfun.utils;

public class GammaLog {
	public static Complex evaluate(Complex z) {
		Complex cplx = Gamma.evaluate(z);
		cplx = Complex.log(cplx);
		return cplx;
	}
}
