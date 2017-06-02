package jpl.ch21.ex05;

import java.util.AbstractList;

import javax.swing.AbstractAction;

public class ArrayBunchList<E> extends AbstractList<E> {

	private final E[][] arrays;
	private final int size;

	public ArrayBunchList(E[][] arrays) {
		this.arrays = arrays.clone();
		int s = 0;
		for (E[] array : arrays) {
			s += array.length;
		}
		size = s;

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public E get(int index) {
		int off = 0; // コレクションの先頭からのオフセット
		for (int i = 0; i < arrays.length; i++) {
			if (index < off + arrays[i].length) {
				return arrays[i][index - off];
			}
			off += arrays[i].length;
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

	public E set(int index, E value) {
		int off = 0; // コレクションの先頭からのオフセット
		for (int i = 0; i < arrays.length; i++) {
			if (index < off + arrays[i].length) {
				E ret = arrays[i][index - off];
				arrays[i][index - off] = value;
				return ret;
			}
			off += arrays[i].length;
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

}
