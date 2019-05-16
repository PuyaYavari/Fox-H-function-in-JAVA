package org.tr.edu.yildiz.ce.specfun.utils;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class F {
	/**
	 * @param length  the length of the input array, this is p and q in the Theta
	 *                function.
	 * @param n       the n and m in the Theta function.
	 * @param reverse simply true for A and false for Bs in the Theta function.
	 * @param array   the array of As and Bs, it has to be a matrix of 1 column and x rows.
	 * @return Big A and Big B parts of the Theta function.
	 */
	public static INDArray evaluate(int length, int n, boolean reverse, INDArray array) {
		if (length <= 0 || array.length() == 0 || array.isEmpty()) {
			return Nd4j.zeros(0,0);
		}
		
		return Matrix.genMatrix(length, n, reverse).mmul(array);
	}
}
