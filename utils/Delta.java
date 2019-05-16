package org.tr.edu.yildiz.ce.specfun.utils;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * This class is an implementation of the Complex part that is inside the Gamma
 * functions of the Theta formula.
 * 
 * @author Puya
 *
 */
public class Delta {
	/**
	 * @param s the s from the Theta function
	 * @param g g function for small as and small bs
	 * @param f f function for big As and big Bs
	 * @return a 2 column doubles matrix that the first column is the real and
	 *         second column is the imaginar part.
	 */
	public static INDArray evaluate(Complex s, INDArray g, INDArray f) {
		if (g.length() == 0 || g.isEmpty())
			return Nd4j.zeros(0, 0);
		if (g.length() != f.length())
			throw new InvalidParameterException("Illegal arguments!");
		return Nd4j.hstack(g.add(f.mul(s.re())), f.mul(s.im()));
	}
}
