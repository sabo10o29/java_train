package jpl.ch21.ex04;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * ListIteratorはpreviousメソッドも実装しているため、ShortStringにさらにメソッドを実装する必要がある
 * このため、ListIteratorを実装することで、強制的に実装することになり、未実装を防ぐことができる。 拡張する必要はない
 * previousメソッドにも対応するようにする
 * 
 * @author YoshikazuMurase
 *
 */
public class ShortStringsV2 implements ListIterator<String> {

	private ListIterator<String> strings; // 元の文字列
	private String nextShort; // 次が不明ならばnull
	private String previousShort; // 前が不明ならばnull
	private final int maxLen; // この長さ以下の文字列だけを返す

	public ShortStringsV2(ListIterator<String> strings, int maxLen) {

		this.maxLen = maxLen;
		this.strings = strings;
		nextShort = null;
		previousShort = null;

	}

	@Override
	public boolean hasNext() {
		if (nextShort != null) {
			return true; // すでに見つけている
		}
		while (strings.hasNext()) {
			nextShort = strings.next(); // 実装しているIteratorの状態が次へ遷移する
			if (nextShort.length() <= maxLen) {
				return true; // 最大サイズ以上の場合にはスキップする
								// 最大サイズ以下の場合にのみtrueを返す
			}
		}
		nextShort = null; // 見つけることができなかった。
		return false;
	}

	@Override
	public String next() {
		if (nextShort == null && !hasNext()) {
			throw new NoSuchElementException();
		}
		String n = nextShort; // nextShortを記憶する
		nextShort = null; // nextShortを消費する
		return n; // nextShortを返す
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasPrevious() {
		if (previousShort != null) {
			return true; // 既にみつけている
		}
		while (strings.hasPrevious()) {
			previousShort = strings.previous();
			if (previousShort.length() <= maxLen) {
				return true;
			}
		}
		previousShort = null; // 見つけることができなかった。
		return false;
	}

	@Override
	public String previous() {
		if (previousShort == null && !hasPrevious()) {
			throw new NoSuchElementException();
		}
		String n = previousShort; // previousShortを記憶する
		previousShort = null; // previousShortを消費する
		return n; // previousShortを返す
	}

	@Override
	public int nextIndex() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int previousIndex() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void set(String e) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void add(String e) {
		throw new UnsupportedOperationException();
	}

	// テスト
	public static void main(String[] args) {

		// 入力のリストを作成
		LinkedList<String> list = new LinkedList<String>();

		list.add("aa");
		list.add("bb");
		list.add("cccc");
		list.add("ddd");
		list.add("eeeeee");
		list.add("ff");
		list.add("ggg");
		list.add("h");
		list.add("ii");
		list.add("jjjjjj");

		ShortStringsV2 test = new ShortStringsV2(list.listIterator(), 3); // Iteratorを実装している
		// hasNextを実行した段階でnextShortに値が保存されている
		System.out.println("前方から３文字以下を表示");
		while (test.hasNext()) {
			System.out.println(test.next()); // nextでは次へ遷移せず、値を返すだけ
		}
		System.out.println("後方から３文字以下を表示");
		while (test.hasPrevious()) {
			System.out.println(test.previous()); // nextでは次へ遷移せず、値を返すだけ
		}

	}
}
