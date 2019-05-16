package org.tr.edu.yildiz.ce.specfun.test;


import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.nd4j.linalg.api.iter.NdIndexIterator;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.NDArrayIndex;
import org.tr.edu.yildiz.ce.specfun.utils.Complex;
import org.tr.edu.yildiz.ce.specfun.utils.Delta;
import org.tr.edu.yildiz.ce.specfun.utils.F;
import org.tr.edu.yildiz.ce.specfun.utils.G;
import org.tr.edu.yildiz.ce.specfun.utils.H;
import org.tr.edu.yildiz.ce.specfun.utils.Theta;
import org.tr.edu.yildiz.ce.specfun.utils.Matrix;


@SuppressWarnings("unused")
public class ND4JTest {
	public static INDArray myArray = Nd4j.ones(5,2);
	public static INDArray yourArray = Nd4j.ones(5,2);
	
	public static void main(String[] args) {
		double arr[] = {1,2,3,4};
		INDArray s = Nd4j.create(arr).transpose();
		
		myArray.addi(5);
		myArray.getRow(1).divi(3);
		myArray.getRow(2).subi(2);
		myArray.getRow(3).muli(5).addi(60);
		System.out.println("==============================orginal==============================");
		System.out.println("my array");
		System.out.println(myArray);
		System.out.println("your array");
		System.out.println(yourArray);
		System.out.println("elementwise divide");
		System.out.println(yourArray.div(myArray));
		System.out.println("==============================hstack==============================");
		System.out.println(Nd4j.hstack(myArray,yourArray));
		System.out.println("==============================vstack==============================");
		System.out.println(Nd4j.vstack(myArray,yourArray));
		System.out.println("==============================concat0==============================");
		//System.out.println(Nd4j.concat(0, myArray, yourArray));
		System.out.println("==============================concat1==============================");
		System.out.println(Nd4j.concat(1, myArray, yourArray));
		System.out.println("==============================myArrayPad==============================");
		System.out.println(Nd4j.pad(myArray, new int[]{5,2}, Nd4j.PadMode.CONSTANT));
		System.out.println("==============================myArray2,1==============================");
		System.out.println(myArray.getInt(2,1));
		System.out.println(myArray.getFloat(2,1));
		System.out.println(myArray.getDouble(2,1));
		System.out.println("==============================setmyArray2,1==============================");
		myArray.putScalar(2, 1 , 5);
		System.out.println(myArray);
		System.out.println("==============================ItterateOvermyArray==============================");
		NdIndexIterator it = new NdIndexIterator(5,2);
		while (it.hasNext()){
			long[] nextIndex = it.next();
			System.out.println(myArray.getScalar(nextIndex));
		}
		System.out.println("==============================myArraySingleRowAllColumn==============================");
		System.out.println(myArray.get(NDArrayIndex.point(3), NDArrayIndex.all()));
		System.out.println("==============================myArrayRangeOfRowsAllColumn==============================");
		System.out.println(myArray.get(NDArrayIndex.interval(1,3), NDArrayIndex.all()));
		System.out.println("==============================myArrayaddyourArray==============================");
		System.out.println(myArray.add(yourArray));
		System.out.println("==============================myArrayEllementsSum==============================");
		System.out.println(myArray.sumNumber());
		System.out.println("==============================myArrayTranspose==============================");
		System.out.println(myArray.transpose());
		System.out.println("++++++++++++++++++++F Test++++++++++++++++++++++");
		System.out.println(F.evaluate(5, 3, false, Nd4j.ones(5).transpose()));
		System.out.println("++++++++++++++++++++G Test++++++++++++++++++++++");
		System.out.println(G.evaluate(5, 3, false, Nd4j.ones(5).transpose()));
		System.out.println("++++++++++++++++++++Delta Test++++++++++++++++++++++");
		System.out.println(Delta.evaluate(org.tr.edu.yildiz.ce.specfun.utils.Complex.ONE, Nd4j.ones(5).transpose(), Nd4j.ones(5).transpose()));
		System.out.println("++++++++++++++++++++Theta Test++++++++++++++++++++++");
		System.out.println("BY INSTANTIATING:");
		Theta t = new Theta(10, 10, 4, 4, Nd4j.ones(10).transpose(), Nd4j.ones(10).transpose(), Nd4j.ones(10).transpose(), Nd4j.ones(10).transpose());
		t.evaluate(Complex.ONE).printCartesian();
		System.out.println("BY STATIC CALL:");
		Theta.theta(Complex.ONE, 10, 10, 1, 1, Nd4j.ones(10).transpose(), Nd4j.ones(10).transpose(), Nd4j.ones(10).transpose(), Nd4j.ones(10).transpose()).printCartesian();
	}
}
