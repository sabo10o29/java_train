package gui.ex14;

import java.awt.Color;

//時計表示に関するフィールド値を持つクラス
public class Parameter implements Cloneable{
	
	public String 	font_name 	= "Arial";			//初期フォント
	public double 	font_multi 	= 1;				//初期フォントサイズ（倍率）
	public Color 	font_color 	= Color.black;		//初期フォントカラー
	public Color 	back_color	= Color.white;		//背景カラー
	public double	window_x 	= 0.0;
	public double	window_y 	= 0.0;
	public double	width 		= 1000.0;
	public double	height 		= 500.0;
	
	@Override
    public Parameter clone() { 
        Parameter b=null;
        try {
            b=(Parameter)super.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        return b;
    }
}
