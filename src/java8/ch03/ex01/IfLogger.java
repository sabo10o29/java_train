package java8.ch03.ex01;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ログレベルに合わせてログを表示する ポイント：ログレベルを判断してからラムダ式から文字を取得する
 * get()が呼ばれた時だけラムダ式を実行するため、重たい処理を実行する際に有用
 * 
 * @author YoshikazuMurase
 *
 */
public class IfLogger {

	public static final Logger ifLogger = Logger.getLogger("IfLogger");

	/**
	 * 
	 * @param level
	 * @param checker
	 * @param message
	 */
	public static void logIf(Level level, BooleanSupplier checker, Supplier<String> message) {

		if (ifLogger.isLoggable(level))
			if (checker.getAsBoolean())
				ifLogger.log(level, message);

	}

	public static void main(String[] args) {

		int[] a = { 1, 2, 3 };

		// テスト１：ロギング判定＝偽、条件式＝真or偽の場合に条件式以降を判定しないか確認
		System.out.println("テスト１：ロギング判定＝偽、条件式＝真or偽の場合に条件式以降を判定しないか確認");
		IfLogger.ifLogger.setLevel(Level.WARNING);
		IfLogger.logIf(Level.FINE, () -> {
			System.out.println("条件式がチェックされました。");
			if (a.length == 2)
				return true;
			return false;

		}, () -> {
			System.out.println("メッセージが書き込まれました。");
			return "test1";
		});

		// テスト２：ロギング判定＝真、条件式＝偽の場合にログを出力しないか確認
		System.out.println("テスト２：ロギング判定＝真、条件式＝偽の場合にログを出力しないか確認");
		IfLogger.ifLogger.setLevel(Level.WARNING);
		IfLogger.logIf(Level.WARNING, () -> {
			System.out.println("条件式がチェックされました。");
			if (a.length == 2)
				return true;
			return false;

		}, () -> {
			System.out.println("メッセージが書き込まれました。");
			return "test２";
		});

		// テスト３：ロギング判定＝真、条件式＝真の場合にログが出力されるか確認
		System.out.println("テスト３：ロギング判定＝真、条件式＝真の場合にログが出力されるか確認");
		IfLogger.ifLogger.setLevel(Level.ALL);
		IfLogger.logIf(Level.WARNING, () -> {
			System.out.println("条件式がチェックされました。");
			if (a.length == 3)
				return true;
			return false;

		}, () -> {
			System.out.println("メッセージが書き込まれました。");
			return "test３";
		});

	}

}
