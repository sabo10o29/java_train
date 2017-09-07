package tutorial.aluminiummod;

import net.minecraft.world.World;
import net.minecraft.block.Block;

public class BlockData {

	private int id;
	private World world;
	private int x;
	private int y;
	private int z;
	private Block block;
	
	public BlockData(World world, int x, int y, int z, int id, Block block){
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
		this.block = block;
	}
	
	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
}
