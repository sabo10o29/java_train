package interpret;

//現在の状態を保存するクラス
public class ParameterConst {

	// パラメーター取得用定数（キー値）
	public final static String CLASS_NAME = "target_class"; // クラス名
	public final static String METHOD_NAME = "method_name"; // メソッド名
	public final static String CONST_NAME = "const_name"; // コンストラクタ名
	public final static String CONST_PARAMETERS = "const_parameters"; // コンストラクタの引数（Type型配列）
	public final static String CONST_PARAM_OBJ = "const_objects"; // インスタンス生成のための引数情報を持つオブジェクト型配列
	public final static String CONST_PARAM_OBJS = "consts_objects";
	public final static String CLASS_FIELD = "class_fields"; // クラスフィールド一覧
	public final static String CLASS_METHOD = "class_methods"; // クラスのメソッド一覧
	public final static String INST_VALUE = "instance_field_value"; // インスタンスの値一覧
	public final static String INST_VALUES = "instances_field_value";
	public final static String NUM_ARRAY_ELEMENT = "number_of_elements"; // 配列の要素数
	public final static String IS_ARRAY = "isArray"; // 指定したクラスが配列かどうかチェック

}
