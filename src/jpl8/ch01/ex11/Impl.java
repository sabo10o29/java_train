package jpl8.ch01.ex11;

public class Impl {

	public static void main(String[] args) {
		class Pat1_I_abstract_J_abstract implements I_abstract, J_abstract {

			@Override
			public void f() {
				System.out.println("Pat1_I_abstract_J_abstract");
			}

		}

		class Pat2_I_abstract_J_default implements I_abstract, J_default {

			@Override
			public void f() {
				System.out.println("Pat2_I_abstract_J_default");
			}

		}

		class Pat3_I_abstract_J_static implements I_abstract, J_static {

			@Override
			public void f() {
				System.out.println("Pat3_I_abstract_J_static");

			}

		}

		class Pat4_I_default_J_default implements I_default, J_default {

			@Override
			public void f() {
				System.out.println("Pat4_I_default_J_default");
			}

		}

		class Pat5_I_default_J_static implements I_default, J_static {

		}

		class Pat6_I_static_J_static implements I_static, J_static {

		}

		class Pat7_K_abstract_I_abstract extends K_abstract implements I_abstract {

			@Override
			public void f() {
				System.out.println("Pat7_K_abstract_I_abstract");
			}

		}

		class Pat8_K_abstract_I_default extends K_abstract implements I_default {

			@Override
			public void f() {
				System.out.println("Pat8_K_abstract_I_default");
			}

		}

		class Pat9_K_abstract_I_static extends K_abstract implements I_static {

			@Override
			public void f() {
				System.out.println("Pat9_K_abstract_I_static");
			}

		}

//		class Pat10_K_static_I_abstract extends K_static implements I_abstract {
//
//		}
//
//		class Pat11_K_static_I_default extends K_static implements I_default {
//
//		}

		class Pat12_K_static_I_static extends K_static implements I_static {

		}

		// Pat1_I_abstract_J_abstract
		Pat1_I_abstract_J_abstract pat1 = new Pat1_I_abstract_J_abstract();
		pat1.f();

		// Pat2_I_abstract_J_default
		Pat2_I_abstract_J_default pat2 = new Pat2_I_abstract_J_default();
		pat2.f(); // 必ずオーバーライドする必要があるため、デフォルトメソッドを使用したい場合にはオーバライド内で呼び出す必要がある

		// Pat3_I_abstract_J_default
		Pat3_I_abstract_J_static pat3 = new Pat3_I_abstract_J_static();
		pat3.f(); // 必ずオーバライドする必要があるため、スタティックメソッドを使用したい場合にはオーバーライド内で呼び出す必要がある

		// Pat4_I_default_J_default
		Pat4_I_default_J_default pat4 = new Pat4_I_default_J_default();
		pat4.f(); // 必ずオーバライドする必要がある(どちらのメソッドが呼ばれるか不明である)ため、スタティックメソッドを使用したい場合にはオーバーライド内で呼び出す必要がある

		//
		Pat5_I_default_J_static pat5 = new Pat5_I_default_J_static();
		pat5.f(); // デフォルトメソッドが呼ばれる

		//
		Pat6_I_static_J_static pat6 = new Pat6_I_static_J_static();
		// 何もできない
		
		Pat7_K_abstract_I_abstract pat7 = new Pat7_K_abstract_I_abstract();
		pat7.f();
		
		Pat8_K_abstract_I_default pat8 = new Pat8_K_abstract_I_default();
		pat8.f();
		
		Pat9_K_abstract_I_static pat9 = new Pat9_K_abstract_I_static();
		pat9.f();
		
		Pat12_K_static_I_static pat12 = new Pat12_K_static_I_static();
		pat12.f();
		
		

	}

}
