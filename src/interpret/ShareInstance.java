package interpret;

/**
 * 複数のタブで生成したクラスを共有するための共有オブジェクト
 * 
 * @author YoshikazuMurase
 *
 */
public class ShareInstance {

	private Class clazz; //
	private Object instance; //
	private String name; // インスタンス名
	public String ID;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

	public String toString() {
		return this.name;
	}

	public ShareInstance(String name, Object instance) {
		this.name = name;
		if (instance != null)
			this.clazz = instance.getClass();
		this.instance = instance;

	}

}
