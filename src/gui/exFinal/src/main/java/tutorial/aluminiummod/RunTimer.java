package tutorial.aluminiummod;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.omg.CORBA.TIMEOUT;

import net.minecraft.block.Block;

/**
 * 生成されたDigiClockBlockのTileEntityを保持して、一定時間ごとにブロックの更新メソッドを呼び出すクラス
 * @author YoshikazuMurase
 *
 */
public class RunTimer implements Runnable{
	
	public static final int SLEEP = 200;
	private Map<Integer, BlockData> blockQueue = new HashMap<Integer, BlockData>();
	private Map<Integer, TileEntityAluminium> entityQueue = new HashMap<Integer, TileEntityAluminium>();
//	public boolean flag = false;
	
	@Override
	public void run() {
		DigitalBlockLogger.info("@@@@@@@@Start RunTimer Thread@@@@@@@");
		while(true){
			try {
				Thread.sleep(SLEEP);
//				DigitalBlockLogger.info("@@@@@@@@ notify update @@@@@@@");
				for(BlockData d : blockQueue.values()){
					d.getWorld().markBlockForUpdate(d.getX(), d.getY(), d.getZ());
					//一定間隔ごとにisWeakを呼び出している
					d.getWorld().notifyBlocksOfNeighborChange(d.getX(), d.getY(), d.getZ(), d.getBlock());
					d.getWorld().scheduleBlockUpdate(d.getX(), d.getY(), d.getZ(), d.getBlock(), 10);
				}
//				if(this.flag){
//					System.out.println("________false_________");
//					this.flag = false;
//				}else{
//					System.out.println("_________true__________");
//					this.flag = true;
//				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	public void addBlock(Integer key, BlockData d){
		blockQueue.put(key, d);
	}
	
	public void addBlock(Integer key, TileEntityAluminium t){
		entityQueue.put(key, t);
	}
	
	public BlockData removeBlock(Integer key){
		return blockQueue.remove(key);
	}
	
	public TileEntityAluminium removeEntity(Integer key){
		return entityQueue.remove(key);
	}

}
