package interpret;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//ボタンに合わせて表示が変化するようにする

public class StepMenuPanel extends JPanel {

	private static int step = 0;
	public static final String[] STEP = { "  1.はじめに  ", "  2.クラスの選択  ", "  3.コンストラクタの選択  ", "  4.インスタンスの生成  ",
			"  5.インスタンスの操作  " };
	private final ImageIcon icon = new ImageIcon("./java.png");

	public JLabel[] steplabel = new JLabel[STEP.length];
	public JPanel[] steppanel = new JPanel[STEP.length];
	public CardLayout[] stepcard = new CardLayout[STEP.length];

	// 初期設定Step0の画面を表示する
	StepMenuPanel() {

		Font font = new Font("ＭＳ ゴシック", Font.BOLD, 15); // フォントサイズ（共通設定）

		// ラベルの表示縦にステップ項目を表示する
		// カードレイアウトを用いてグレーアウトを実現する
		// ステップのラベルごとにパネルを作成
		// ラベルに表示とグレーアウトの2種類をカードとしてadd
		// パネルをthisに追加

		for (int i = 0; i < STEP.length; i++) {

			// パネルにカードレイアウトを追加
			steppanel[i] = new JPanel();
			steppanel[i].setLayout(null);
			stepcard[i] = new CardLayout();
			steppanel[i].setLayout(stepcard[i]);

			// ステップのパネルを作成 カードレイアウトで黒色と灰色色を設定
			JLabel tmp1 = new JLabel(STEP[i]);
			tmp1.setForeground(Color.black);
			tmp1.setFont(font);
			steppanel[i].add(tmp1, "black");

			JLabel tmp2 = new JLabel(STEP[i]);
			tmp2.setForeground(Color.gray);
			tmp2.setFont(font);
			steppanel[i].add(tmp2, "gray");

			// Step1以外は文字色をグレーに設定する
			if (i != step) {
				CardLayout cl = (CardLayout) (steppanel[i].getLayout());
				cl.show(steppanel[i], "gray");
			}
			// ステップパネルをthisパネルに追加
			this.add(steppanel[i]);
		}
		// 画像の追加
		JPanel imgpanel = new JPanel();
		JLabel label = new JLabel(icon);
		imgpanel.setLayout(new FlowLayout());
		imgpanel.add(label, "Center");
		this.add(imgpanel);

		// パネル上にステップラベルを縦に配置
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	public void nextStep() {
		if (step != 4) {
			step++;
			changeStepTitle();
		} else {

		}
	}

	public void backStep() {
		if (step != 0) {
			step--;
			changeStepTitle();
		} else {

		}
	}

	//
	private final void changeStepTitle() {
		CardLayout cl;
		if (this.steplabel != null) {
			for (int i = 0; i < STEP.length; i++) {
				cl = (CardLayout) (steppanel[i].getLayout());
				if (i == step) {
					cl.show(steppanel[i], "black");
				} else {
					cl.show(steppanel[i], "gray");
				}
			}
		}
	}

	class DeatilWindow {

	}

}
