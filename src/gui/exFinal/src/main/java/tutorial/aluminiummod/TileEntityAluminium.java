package tutorial.aluminiummod;

import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityAluminium extends TileEntity implements ITickable{

	private int id;
	private World world;
	private int x;
	private int y;
	private int z;
	private int time_index; // h2h1 : m2m1 : s2s1
							// 0: ":", 1: "s1", 2: "s2", 3: "m1", 4: "m2", 5:
							// "h1", 6: "h2"
	private ForgeDirection front = ForgeDirection.EAST;
	private boolean rightNeighvor = false;
	private boolean leftNeighvor = false;
	private int timercount = 9;
	private int alarmcount1 = 0;
	private int alarmcount2 = 0;
	private boolean isClock = true;
	private boolean isTimer = false;
	private boolean alarm;
	
	public boolean isAlarm() {
		return alarm;
	}
	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}

	public boolean isClock() {
		return isClock;
	}

	public void setClock(boolean isClock) {
		this.isClock = isClock;
	}

	public boolean isTimer() {
		return isTimer;
	}

	public void setTimer(boolean isTimer) {
		this.isTimer = isTimer;
	}

	public int getTimercount() {
		return timercount;
	}

	public void setTimercount(int timercount) {
		this.timercount = timercount;
	}

	public int getAlarmcount1() {
		return alarmcount1;
	}

	public void setAlarmcount1(int alarmcount1) {
		this.alarmcount1 = alarmcount1;
	}

	public int getAlarmcount2() {
		return alarmcount2;
	}

	public void setAlarmcount2(int alarmcount2) {
		this.alarmcount2 = alarmcount2;
	}

	public boolean isRightNeighvor() {
		return rightNeighvor;
	}

	public void setRightNeighvor(boolean rightNeighvor) {
		this.rightNeighvor = rightNeighvor;
	}

	public boolean isLeftNeighvor() {
		return leftNeighvor;
	}

	public void setLeftNeighvor(boolean leftNeighvor) {
		this.leftNeighvor = leftNeighvor;
	}

	public ForgeDirection getFront() {
		return front;
	}

	public void setFront(ForgeDirection front) {
		this.front = front;
	}

	public int getTimeIndex() {
		return this.time_index;
	}

	public void setTimeIndex(int time_index) {
		this.time_index = time_index;
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

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}
	
	@Override
	public void tick() {
		System.out.println("@@@@@TileEntity update tick()@@@@");
		
	}

}
