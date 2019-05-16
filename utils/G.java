package org.tr.edu.yildiz.ce.specfun.utils;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class G {
	/**
	 * @param length  the length of the input array, this is p and q in the Theta
	 *                function.
	 * @param n       the n and m in the Theta function.
	 * @param reverse simply true for a and false for bs in the Theta function.
	 * @param array   the array of as and bs, it has to be a matrix of 1 column and x rows.
	 * @return small a and small b parts of the Theta function.
	 */
	public static INDArray evaluate(int length, int n, boolean reverse, INDArray array) {
		if (length <= 0 || array.length() == 0 || array.isEmpty()) {
			return Nd4j.zeros(0,0);
		}
		if (!reverse) {
			/*return Nd4j.concat(1, Nd4j.zeros(n), Nd4j.ones(length - n)).transpose()
					.add(Matrix.genMatrix(length, n, reverse).mmul(array));*/
			INDArray tmp1 = null;
			if (length == n) {
				tmp1 = Nd4j.zeros(n);
			} else if (n == 0){
				tmp1 = Nd4j.ones(length);
			} else {
				tmp1 = Nd4j.concat(1, Nd4j.zeros(n), Nd4j.ones(length - n));
			}
			tmp1 = tmp1.transpose();
			INDArray tmp2 = Matrix.genMatrix(length, n, reverse);
			tmp2 = tmp2.mmul(array);
			tmp1 = tmp1.add(tmp2);
			return tmp1;
		} else {
			/*return Nd4j.concat(1, Nd4j.ones(n), Nd4j.zeros(length - n)).transpose()
					.add(Matrix.genMatrix(length, n, reverse).mmul(array));*/
			INDArray tmp1 = null;
			if (length == n) {
				tmp1 = Nd4j.ones(n);
			} else if (n == 0){
				tmp1 = Nd4j.zeros(length);
			} else {
				tmp1 = Nd4j.concat(1, Nd4j.ones(n), Nd4j.zeros(length - n));
			}
			tmp1 = tmp1.transpose();
			INDArray tmp2 = Matrix.genMatrix(length, n, reverse);
			tmp2 = tmp2.mmul(array);
			tmp1 = tmp1.add(tmp2);
			return tmp1;
		}
	}
}
