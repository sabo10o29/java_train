package jpl.ch22.ex13;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * 
 * @author YoshikazuMurase
 *
 */
public class AttributedImpl implements Attributed, Iterable<Attr> {

	protected Map<String, Attr> attrTable = new HashMap<String, Attr>();

	/**
	 * ファイルから名前＝属性のセットを読み込んでその属性Attrを保持するAttributedImplを返すメソッド
	 * 
	 * @param source
	 * @return
	 * @throws IOException
	 */
	public static Attributed readAttrs(Reader source) throws IOException {
		StreamTokenizer in = new StreamTokenizer(source);
		AttributedImpl attrs = new AttributedImpl();
		Attr attr = null;
		in.commentChar('#'); // ’#’は行の最後まで無視されるコメント
		in.ordinaryChar('/'); // コメント文字でなくす
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			if (in.ttype == StreamTokenizer.TT_WORD) {
				if (attr != null) {
					attr.setValue(in.sval);
					attr = null;
				} else {
					attr = new Attr(in.sval);
					attrs.add(attr);
				}
			} else if (in.ttype == '=') {
				if (attr == null) {
					throw new IOException("misplaced '='");
				}
			} else {
				if (attr == null) {
					throw new IOException("bad Attr name");
				}
				attr.setValue(new Double(in.nval));
				System.out.println(attr.getName() + "\"=\"" + attr.getValue());
				attr = null;
			}
		}
		return attrs;
	}

	/**
	 * スキャナを使用してreadAttrsを実現する
	 * 
	 * @param source
	 * @return
	 * @throws IOException
	 */
	public static Attributed scanAttrs(Reader source) throws IOException {

		AttributedImpl attrs = new AttributedImpl();

		Scanner in = new Scanner(source);
		Pattern attrPat = Pattern.compile("([^=]*?)=(.*)$", Pattern.MULTILINE);
		while (in.hasNextLine()) {
			String line = in.findInLine(attrPat);
			if (line != null) {
				MatchResult m = in.match();
				//左辺が空文字野場合のチェック
				if (m.group(2).contains("=")) {
					String str = m.group(2).substring(0, m.group(2).indexOf("="));
					attrs.add(new Attr(m.group(1), str));
					System.out.println(m.group(1) + "'='" + str);
					throw new IOException("misplaced '='");
				} else {
					attrs.add(new Attr(m.group(1), m.group(2)));
					System.out.println(m.group(1) + "'='" + m.group(2));
				}

			}
			try {
				in.nextLine(); // 改行を読み飛ばし
			} catch (NoSuchElementException e) {
				System.out.println("入力が存在しないため終了します。");
				break;
			}
		}
		return attrs;
	}

	@Override
	public Iterator<Attr> iterator() {
		return attrs();
	}

	@Override
	public void add(Attr newAttr) {
		attrTable.put(newAttr.getName(), newAttr);
	}

	@Override
	public Attr find(String attrName) {
		return attrTable.get(attrName);
	}

	@Override
	public Attr remove(String attrName) {
		return attrTable.remove(attrName);
	}

	@Override
	public Iterator<Attr> attrs() {
		return attrTable.values().iterator();
	}

	public static void main(String[] args) {
		String current = new File(".").getAbsoluteFile().getParent();
		String input = current + "/src/jpl/ch22/ex13/input.txt";
		File file = new File(input);
		FileReader filereader = null;
		try {
			filereader = new FileReader(file);
			// Attributed test1 = AttributedImpl.readAttrs(filereader);
			Attributed test2 = AttributedImpl.scanAttrs(filereader);
			System.out.println(test2.find("power").getValue());

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			if (filereader != null)
				try {
					filereader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

}
