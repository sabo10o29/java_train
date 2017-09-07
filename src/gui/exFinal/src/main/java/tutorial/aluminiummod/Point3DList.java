package tutorial.aluminiummod;

import java.util.ArrayList;
import java.util.List;

public class Point3DList {

	private static List<Point3D> list = new ArrayList<Point3D>();

	public Point3DList() {

	}
	
	/**
	 * リストに座標が存在しない場合に登録する
	 * @param x
	 * @param y
	 * @param z
	 */
	public void addPoint3D(int x, int y, int z){
		
		DigitalBlockLogger.info("@@@@@@@@ Add point to Point3DList @@@@@@@");
		
		for(Point3D p : list){
			if(p.x == x && p.y == y && p.z == z)
				return;
		}
		
		this.list.add(new Point3D(x, y, z, false));
		
	}
	
	/**
	 * リストに座標が存在しない場合にはfalseを返し、存在する場合にはデータのフラグを返す
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public boolean getFlag(int x, int y, int z){
		
		for(Point3D p : list){
			if(p.x == x && p.y == y && p.z == z)
				return p.alarm;
		}
		
		return false;
		
	}
	
	/**
	 * 座標が存在していた場合にはフラグを登録する
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public void setFlag(int x, int y, int z, boolean flag){
		
		for(Point3D p : list){
			if(p.x == x && p.y == y && p.z == z){
				DigitalBlockLogger.info("@@@@@@@@ set flag to "+ flag +"@@@@@@@");
				p.alarm = flag;
			}
				
		}
		
	}

	class Point3D {

		public int x;
		public int y;
		public int z;
		public boolean alarm;

		public Point3D(int x, int y, int z, boolean flag) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.alarm = flag;
		}
		

	}

}
