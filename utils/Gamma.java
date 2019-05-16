package org.tr.edu.yildiz.ce.specfun.utils;

public class Gamma {

	// The Gamma function for real(z) > 1
	private static Complex gamma_complex_0(Complex z) {
		Complex cplx = Complex.TWO.multiply(Complex.PI).divide(z).pow(0.5)
				.multiply(Complex.ONE.divide(Complex.E)
						.multiply(z.add(Complex.ONE.divide(
								(Complex.valueOf(12.0).multiply(z)).subtract(Complex.valueOf(1 / 10.0).divide(z)))))
						.pow(z));
		return cplx;
	}

	private static Complex gamma_complex_near_1(Complex z) {
//		% The Gamma function for abs(z-1) ~ 0
//		% The Taylor expansion of Gamma(x=1)
//		% const_2 = (6*eulergamma^2+pi^2)/12 = 0.9890 ...
//		function y = gamma_complex_near_1(z)
//		    the_eulergamma = ...
//		        0.577215664901532860606512090082402431042159335939923598805;
//		    const_2 = ...
//		        0.989055995327972555395395651500634707939183520728214090443;
//		    
//		    y = 1 - the_eulergamma .* (z-1) + const_2 .* (z-1).^2;
//		end
		Complex z_1 = z.subtract(Complex.ONE);
		return Complex.ONE.subtract(Complex.EULER.multiply(z_1)).add(Complex.CONST.multiply(z_1.pow(2.0)));
	}

	public static Complex evaluate(Complex z) {
//		function y = gamma_complex(z)
//		    % seperate matrix z to real(z) >= 1, real(z) < 1 and z ~ 1.0 
		if (z.re() >= 1) {
			return gamma_complex_0(z);
		}

		double mag = z.subtract(Complex.ONE).mag();

		if (z.re() < 1 & mag >= 0.35) {
			return Complex.PI.divide(Complex.sin(Complex.PI.multiply(z))).divide(gamma_complex_0(Complex.ONE.subtract(z))); //FIXME: NaN problem in test7 - test14
		}

		if (z.re() < 1 & mag < 0.35) {
			return gamma_complex_near_1(z);
		}

		return Complex.NaN;
	}
}
