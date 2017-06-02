package interpret;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class TestClass extends JFrame {

	public int a = 0;
	private int b = 1;
	protected int c = 2;
	public String name = "interpret.TestClass";
	private String[] strs = { "a", "b", "c" };
	public String[] strss = { "a", "b", "c" };
	public TestClass[] tests = new TestClass[2];
	public int[] hogeeeeeeeeee;
	public String[] hogehoge;
	private String testnull;

	public TestClass() {
		tests[0] = new TestClass(1);
		tests[1] = new TestClass(2);
	}

	public TestClass(int i) {

	}

	public TestClass(Object[] args) {

	}

	public TestClass(TestClass test) {

	}

	public TestClass(JComponent comp) {

	}

	public TestClass(int[] insts, String str) {

	}

	public TestClass(JFrame frame) {

	}

	public TestClass(String[] strs) {
		this.strs = strs;
		this.strss = strs;
	}

	// public static void main(String args[]){
	//// String str = "hogehoge";
	// int[] arr = { 1, 2, 3 };
	// Integer[] intarr = { 1, 2, 3 };
	//
	//// Integer tes = new Integer(3);
	// TestClass in = new TestClass();
	// Class<?> clazz = in.getClass();
	// Constructor<?>[] conss = clazz.getConstructors();
	// Constructor<?> cons = conss[3];
	//
	// Object[] argument = new Object[2];
	// Object arg1 = Array.newInstance(arr.getClass().getComponentType(),
	// arr.length);
	// System.arraycopy(arr, 0, arg1, 0, 3);
	//// int[] test =(int[])arg1;
	// int i=0;
	// for(Integer val : intarr){
	// Array.setInt(arg1, i, intarr[i]);
	// i++;
	// }
	//
	//
	// argument[0] = arg1;
	//// argument[1] = new String("hogeeeeee");
	//
	//// TestClass hoge = new TestClass(arr);
	//
	//// System.out.println(arr.getClass().getName());
	//// System.out.println(arr.getClass().getSuperclass().getName());
	////
	//// Object test = Array.newInstance(arr.getClass().getComponentType(),
	// arr.length);
	////
	////
	////
	////
	////
	//// Object[] args1 = new Object[2];
	//// args1[0] = test;
	//// args1[1] = str;
	// java.lang.reflect.Type[] types = cons.getGenericParameterTypes();
	// for(java.lang.reflect.Type t : types){
	// System.out.println(t.getTypeName()+t.getClass().getName());
	// }
	//
	//// System.out.println(cons.getGenericParameterTypes());
	// try {
	// Object instance = cons.newInstance(argument[0]);
	// System.out.println(instance.getClass().getName());
	// } catch (InstantiationException e) {
	// // TODO 自動生成された catch ブロック
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// // TODO 自動生成された catch ブロック
	// e.printStackTrace();
	// } catch (IllegalArgumentException e) {
	// // TODO 自動生成された catch ブロック
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// // TODO 自動生成された catch ブロック
	// e.printStackTrace();
	// }
	//
	// System.out.println("Finish!");
	//
	// }

	public TestClass(int[] test) {
		System.out.println("Test is success!!");
		for (int i : test) {
			System.out.println(i);
		}
		this.hogeeeeeeeeee = test;
	}

	public TestClass(int test, String[] str) {
		System.out.println("Test is success!!");
		for (String i : str) {
			System.out.println(i);
		}
		System.out.println(str);
	}

	// public E[] toPriArray(Class<E> type, int length){
	// E[] arr = (E[])Array.newInstance(type, length);
	// return arr;
	// }

}
