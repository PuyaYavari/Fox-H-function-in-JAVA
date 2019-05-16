package org.tr.edu.yildiz.ce.specfun.utils;

/**
 * The ComplexNumber class allows complex numbers to be computable on java
 * It not only allows algebraic computation but also finds root of the given complex number
 */
import java.util.*;

public strictfp class Complex implements Comparable<Complex> {
	public final static Complex ZERO = new Complex(0.0, 0.0);
	public final static Complex ONE = new Complex(1.0, 0.0);
	public final static Complex TWO = new Complex(2.0, 0.0);
	public final static Complex PI = new Complex(Math.PI, 0.0);
	public final static Complex E = new Complex(Math.E, 0.0);
	public final static Complex NaN = new Complex(Double.NaN, Double.NaN);
	public final static Complex EULER = new Complex(0.577215664901532860606512090082402431042159335939923598805, 0.0);
	public final static Complex CONST = new Complex(0.989055995327972555395395651500634707939183520728214090443, 0.0);

	private double re; // real part
	private double im; // imaginary part
	private double mag; // magnitude
	private double ang; // angle in complex graph

	/**
	 * Initializes complex number by taking real and imaginary part of the number as
	 * parameters The number is stored with real and imaginary part along with
	 * magnitude and the angle of the number's vector projection
	 * 
	 * @param re the real part that is going to be stored
	 * @param im the imaginary part that is going to be stored
	 */
	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
		this.mag = Math.sqrt(Math.pow(re, 2) + Math.pow(im, 2));
		if (re != 0) {
			this.ang = Math.atan(im / re);
			if (re < 0) {
				this.ang += Math.PI;
			}
		} else if (im != 0) {
			this.ang = Math.PI / 2;
			if (im < 0) {
				this.ang *= -1;
			}
		}
	}

	public double re() {
		return re;
	}

	public double im() {
		return im;
	}

	public double mag() {
		return mag;
	}

	public double ang() {
		return ang;
	}

	/**
	 * Takes integer round as a parameter and to prints out the number in Cartesian
	 * form Basic format is given by real part + imaginary part * i and the each
	 * part is rounded using the round method with the given input decimal places
	 * 
	 * @param round the integer that sets how many decimal places that numbers will
	 *              be rounded
	 * @return void
	 */
	public void printCartesian(int round) {
		if (re != 0 || im == 0) {
			System.out.print(round(re, round) + " ");
		}
		if (im < 0) {
			System.out.print("- " + -1 * round(im, round) + "i");
		} else if (im > 0) {
			System.out.print("+ " + round(im, round) + "i");
		}
		System.out.println();
	}

	/**
	 * Prints out the number in Cartesian form without taking any parameter The
	 * default rounding is given by up to 3 decimal places
	 * 
	 * @return void
	 */
	public void printCartesian() {
		this.printCartesian(3);
	}

	/**
	 * Takes integer round as a parameter and prints out the number in polar form
	 * Basic format is given by magnitude * e^(angle * i) and the each part is
	 * rounded using the round method with the given input decimal places
	 * 
	 * @param round the integer that sets how many decimal places that numbers will
	 *              be rounded
	 * @return void
	 */
	public void printPolar(int round) {
		System.out.print(round(mag, round));
		if (ang != 0) {
			System.out.print("e^(" + round(ang, round) + "i)");
		}
		System.out.println();
	}

	/**
	 * Prints out the in polar form number without taking any parameter The default
	 * rounding is given by up to 3 decimal places
	 * 
	 * @return void
	 */
	public void printPolar() {
		this.printPolar(3);
	}

	/**
	 * Takes integer round as a parameter and prints out the number in angular form
	 * Basic format is given by magnitude * (cos(angle) + i * sin(angle)) and the
	 * each part is rounded using the round method with the given input decimal
	 * places
	 * 
	 * @param round the integer that sets how many decimal places that numbers will
	 *              be rounded
	 * @return void
	 */
	public void printAngular(int round) {
		System.out.println(round(mag, round) + "(cos(" + round(ang, round) + ") + isin(" + round(ang, round) + "))");
	}

	/**
	 * Prints out the in angular form number without taking any parameter The
	 * default rounding is given by up to 3 decimal places
	 * 
	 * @return void
	 */
	public void printAngular() {
		this.printAngular(3);
	}

	/**
	 * Takes other complex number and adds to this complex number Returns the sum of
	 * the complex numbers
	 * 
	 * @param other the other complex number which would be added
	 * @return new complex number after addition
	 */
	public Complex add(Complex other) {
		return new Complex(re + other.re, im + other.im);
	}

	/**
	 * Takes other complex number and subtracts from this complex number Returns the
	 * resulting complex number after the subtraction
	 * 
	 * @param other the other complex number which would be subtracted
	 * @return new complex number after subtraction
	 */
	public Complex subtract(Complex other) {
		return new Complex(re - other.re, im - other.im);
	}

	/**
	 * Takes other complex number and multiplies to this complex number Returns the
	 * multiple of the complex numbers
	 * 
	 * @param other the other complex number which would be multiplied
	 * @return new complex number after multiplication
	 */
	public Complex multiply(Complex other) {
		return new Complex(re * other.re - im * other.im, re * other.im + im * other.re);
	}

	/**
	 * Takes other complex number and divides this complex number with the input It
	 * will rationalize the denominator by multiplying the conjugate to it Returns
	 * the resulting complex number after the division
	 * 
	 * @param other the other complex number which would be divided with
	 * @return new complex number after division
	 */
	public Complex divide(Complex other) {
		double reNum = re * other.re + im * other.im; // real part of numerator
		double imNum = -re * other.im + im * other.re; // imaginary part of numerator
		double denom = Math.pow(other.mag, 2); // denominator
		return new Complex(reNum / denom, imNum / denom);
	}

	/**
	 * Returns conjugate of this complex number
	 * 
	 * @return new complex number which is the conjugate of this
	 */
	public Complex conjugate() {
		return new Complex(re, -im);
	}

	/**
	 * Takes in exponent and returns the number that is raised to that power
	 * 
	 * @param exp the double that will be the exponent
	 * @return new complex number that is raised to the power
	 */
	public Complex pow(double exp) {
		double newAng = ang * exp;
		double newMag = Math.pow(mag, exp);
		return new Complex(newMag * Math.cos(newAng), newMag * Math.sin(newAng));
	}

	public Complex pow(Complex z) {
		return pow(this, z);
	}

	public final static Complex pow(Complex z, Complex y) {
		Complex a = y.multiply(log(z));
		return exp(a);
	}

	/** compute the natural logarithm of the complex number */
	public final static Complex log(Complex z) {
		double rpart = Math.sqrt(z.re() * z.re() + z.im() * z.im());
		double ipart = Math.atan2(z.im(), z.re());
		if (ipart > Math.PI)
			ipart = ipart - 2.0 * Math.PI;
		return Complex.valueOf(Math.log(rpart), ipart);
	}

	public final static Complex exp(Complex z) {
		double epart = Math.exp(z.re());
		double cpart = Math.cos(z.im());
		double spart = Math.sin(z.im());
		return Complex.valueOf(epart * cpart, epart * spart);
	}

	public final static Complex sin(Complex z) {
		return Complex.valueOf(Math.sin(z.re()) * Math.cosh(z.im()), Math.cos(z.re()) * Math.sinh(z.im()));
	}

	/**
	 * Takes in roots and computes the roots of the number Returns the queue of the
	 * roots
	 * 
	 * @throws IllegalArgumentException if the root is negative or 0
	 * @param root the integer number of roots that should be computed
	 * @return queue of the roots of the complex number
	 */
	public Queue<Complex> findRoots(int root) {
		if (root <= 0) {
			throw new IllegalArgumentException();
		}
		Queue<Complex> roots = new LinkedList<Complex>();
		double rootAngle = ang / root;
		double addAngle = 2 * Math.PI / root;
		for (int i = 0; i < root; i++) {
			double newMag = Math.pow(mag, 1.0 / root);
			double newAng = rootAngle + i * addAngle;
			roots.add(new Complex(newMag * Math.cos(newAng), newMag * Math.sin(newAng)));
		}
		return roots;
	}

	/**
	 * Takes other complex number and compares the magnitude with this complex
	 * number Returns -1 if this is smaller, 0 if it is the same, 1 if this is
	 * bigger
	 * 
	 * @overrides comparable interface
	 * @param other other complex number that would be compared with
	 * @return integer that indicates if magnitude of this number is bigger,
	 *         smaller, or the same
	 */
	public int compareTo(Complex other) {
		if (mag < other.mag) {
			return -1;
		} else if (mag == other.mag) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * Takes double number that would be rounded and integer that indicates the
	 * number of decimal digits Returns the number that is rounded
	 * 
	 * @param number double that would be rounded
	 * @param round  integer that indicates the number of decimal digits
	 * @return double number that is rounded
	 */
	public double round(double number, int round) {
		if (round < 0) {
			throw new IllegalArgumentException();
		}
		double precision = Math.pow(10, round);
		number = Math.round(number * precision) / precision;
		return number;
	}

	public boolean isZero() {
		return re == 0 & im == 0;
	}

	public boolean isOne() {
		return re == 1 & im == 0;
	}

	public boolean isNaN() {
		return Double.isNaN(re) || Double.isNaN(im);
	}

	public boolean isFinite() {
		return Double.isFinite(re) & Double.isFinite(im);
	}

	public static Complex valueOf(double real) {
		return valueOf(real, 0);
	}

	public static Complex valueOf(double real, double imag) {
		return new Complex(real, imag);
	}

	public static Complex valueOf(Complex z) {
		return valueOf(z.re, z.im);
	}
	
	public String toString() {
		String string = "";
		if (re != 0 || im == 0) {
			string += re + " ";
		}
		if (im < 0) {
			string += "- " + -1 * im + "i";
		} else if (im > 0) {
			string += "+ " + im + "i";
		}
		return string;
	}

}