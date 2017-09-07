package tutorial.aluminiummod;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockLever;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author YoshikazuMurase
 *
 */
public class AluminiumBlock extends Block implements ITileEntityProvider {

	public static IIcon[] iicon = new IIcon[50];
	public static RunTimer runTimer = new RunTimer();
	public static AtomicInteger count = new AtomicInteger(0);
	public static ForgeDirection[] blockDir = { ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH,
			ForgeDirection.WEST };
	public static Point3DList list = new Point3DList();

	/**
	 * 
	 * @param material
	 */
	public AluminiumBlock(Material material) {
		super(material);
		// クリエイティブタブの登録
		this.setCreativeTab(CreativeTabs.tabBlock);
		// 硬さの設定
		this.setHardness(5.0F);
		// 爆破耐性の設定
		this.setResistance(10.0F);
		// ブロックの上を歩いた時の音を登録する。
		this.setStepSound(Block.soundTypeMetal);
		// 回収するのに必要なツールを設定する。
		this.setHarvestLevel("pickaxe", 2);
		// 明るさの設定
		this.setLightLevel(1.0F);
		//
		this.isBlockContainer = false;

		// １秒ごとに更新するスレッドのスタート
		new Thread(runTimer).start();

	}

	/**
	 * 
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		for (int i = 0; i < 50; i++) {
			this.iicon[i] = register.registerIcon(this.getTextureName() + "-" + i);
		}
	}

	/**
	 * 
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {

		if (side == 3)
			return iicon[49];

		if (side != 4)
			return iicon[48];

		// 一秒間にかなりの回数呼び出されているためインスタンスをなるべく生成しないようにする
		// System.out.println("@@@@@@@@getting Icon@@@@@@@ ");
		int mm = Calendar.getInstance().get(Calendar.SECOND);
		int m = mm % 10;
		return iicon[m];

	}

	/**
	 * 隣のブロックが
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side) {
		if (side == 0 && side == 1)
			return iicon[48];
		TileEntityAluminium e = (TileEntityAluminium) block.getTileEntity(x, y, z);
		//アラームの設定
		Calendar c = Calendar.getInstance();
		if (e.isAlarm()) {
			if (c.get(Calendar.HOUR_OF_DAY) == e.getAlarmcount2()) {
				if (c.get(Calendar.MINUTE) == e.getAlarmcount1()) {
					list.setFlag(x, y, z, true);
				}else{
					list.setFlag(x, y, z, false);
				}
			}else{
				list.setFlag(x, y, z, false);
			}
		} else {
			list.setFlag(x, y, z, false);
		}

		return getIIconExt(e, side);
	}

	private IIcon getIIconExt(TileEntityAluminium t, int side) {

		ForgeDirection front = t.getFront();
		int timeIndex = t.getTimeIndex();
		boolean right = t.isRightNeighvor();
		boolean left = t.isLeftNeighvor();
		IIcon icon = null;
		if (ForgeDirection.NORTH.equals(front)) {
			if (side == 2) {
				icon = getFrontSide(timeIndex, right, left);
			} else if (side == 3) {
				icon = iicon[49];
			} else {
				icon = iicon[48];
			}
		} else if (ForgeDirection.SOUTH.equals(front)) {
			if (side == 3) {
				icon = getFrontSide(timeIndex, right, left);
			} else if (side == 2) {
				icon = iicon[49];
			} else {
				icon = iicon[48];
			}
		} else if (ForgeDirection.EAST.equals(front)) {
			if (side == 5) {
				icon = getFrontSide(timeIndex, right, left);
			} else if (side == 4) {
				icon = iicon[49];
			} else {
				icon = iicon[48];
			}
		} else if (ForgeDirection.WEST.equals(front)) {
			if (side == 4) {
				icon = getFrontSide(timeIndex, right, left);
			} else if (side == 5) {
				icon = iicon[49];
			} else {
				icon = iicon[48];
			}
		}

		return icon;

	}

	private IIcon getFrontSide(int timeIndex, boolean right, boolean left) {
		Calendar c = Calendar.getInstance();
		int num = 0;
		switch (timeIndex) {
		case 0:
			if (right == false && left == false) {
				num = 40;
			} else if (right == true && left == true) {
				num = 41;
			} else if (right == true && left == false) {
				num = 42;
			} else if (right == false && left == true) {
				num = 43;
			}
			return iicon[num];
		case 1:
			num = c.get(Calendar.SECOND) % 10;
			break;
		case 2:
			num = c.get(Calendar.SECOND) / 10;
			break;
		case 3:
			num = c.get(Calendar.MINUTE) % 10;
			break;
		case 4:
			num = c.get(Calendar.MINUTE) / 10;
			break;
		case 5:
			num = c.get(Calendar.HOUR_OF_DAY) % 10;
			break;
		case 6:
			num = c.get(Calendar.HOUR_OF_DAY) / 10;
			break;
		default:
			num = 40;
			break;
		}

		if (right == false && left == false) {
		} else if (right == true && left == true) {
			num += 10;
		} else if (right == true && left == false) {
			num += 20;
		} else if (right == false && left == true) {
			num += 30;
		}
		return iicon[num];
	}

	/**
	 * ブロックをおいたときに呼ばれるメソッド、TileEntityを取得できる
	 */
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase,
			ItemStack itemStack) {
		DigitalBlockLogger.info("@@@@@@@ Digital Block Placed @@@@@@");
		DigitalBlockLogger.info("Block point = X:" + x + ", Y:" + y + ", Z:" + z);
		TileEntityAluminium t = (TileEntityAluminium) world.getTileEntity(x, y, z);

		// ブロックの描画更新設定（スレッドへの追加）
		BlockData d = new BlockData(world, x, y, z, count.get(), this);
		runTimer.addBlock(count.get(), d);
		t.setId(count.get());
		count.incrementAndGet();
		world.scheduleBlockUpdate(x, y, z, this, 10);

		// 3次元座標情報をリスト登録
		list.addPoint3D(x, y, z);

		// ブロックの正面方向の設定
		// プレイヤーの向いている向きの取得
		int playerDir = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		// プレイヤーの向きとブロックの正面との対応表
		ForgeDirection front = blockDir[playerDir];
		t.setFront(front); // ブロックの正面方向の設定

		// ブロックの両隣の初期か
		setNeighbor(world, x, y, z);

	}

	/**
	 * ブロックの両隣の確認&セット
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param front
	 */
	private void setNeighbor(World world, int x, int y, int z) {

		TileEntityAluminium t = (TileEntityAluminium) world.getTileEntity(x, y, z);
		ForgeDirection front = t.getFront();
		TileEntity north = world.getTileEntity(x, y, z + 1);
		TileEntity south = world.getTileEntity(x, y, z - 1);
		TileEntity east = world.getTileEntity(x - 1, y, z);
		TileEntity west = world.getTileEntity(x + 1, y, z);

		if (ForgeDirection.NORTH.equals(front)) {
			if (isNeighbor(front, east)) {
				t.setRightNeighvor(true);
			} else {
				t.setRightNeighvor(false);
			}
			if (isNeighbor(front, west)) {
				t.setLeftNeighvor(true);
			} else {
				t.setLeftNeighvor(false);
			}
		} else if (ForgeDirection.SOUTH.equals(front)) {
			if (isNeighbor(front, west)) {
				t.setRightNeighvor(true);
			} else {
				t.setRightNeighvor(false);
			}

			if (isNeighbor(front, east)) {
				t.setLeftNeighvor(true);
			} else {
				t.setLeftNeighvor(false);
			}

		} else if (ForgeDirection.EAST.equals(front)) {
			if (isNeighbor(front, south)) {
				t.setRightNeighvor(true);
			} else {
				t.setRightNeighvor(false);
			}

			if (isNeighbor(front, north)) {
				t.setLeftNeighvor(true);
			} else {
				t.setLeftNeighvor(false);
			}

		} else if (ForgeDirection.WEST.equals(front)) {
			if (isNeighbor(front, north)) {
				t.setRightNeighvor(true);
			} else {
				t.setRightNeighvor(false);
			}

			if (isNeighbor(front, south)) {
				t.setLeftNeighvor(true);
			} else {
				t.setLeftNeighvor(false);
			}

		}
	}

	// 隣り合うブロックがDigitalClockでかつ、同じ方向を向いている場合にtrueを返す
	private boolean isNeighbor(ForgeDirection front, TileEntity neighbor) {
		if (neighbor == null)
			return false;

		if (neighbor instanceof TileEntityAluminium) {
			// if (((TileEntityAluminium) neighbor).getFront().equals(front))
			// return true;
			// return false;
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 */
	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {

		TileEntity e = world.getTileEntity(x, y, z);
		if (e instanceof TileEntityAluminium) {
			DigitalBlockLogger.info("@@@@@@@Removed Digital Block By Player@@@@@@");
			DigitalBlockLogger.info("Block point = X:" + x + ", Y:" + y + ", Z:" + z);
			TileEntityAluminium entity = (TileEntityAluminium) e;
			int i = entity.getId();
			runTimer.removeBlock(i);
		}
		return super.removedByPlayer(world, player, x, y, z, willHarvest);
	}

	/**
	 * ブロックがアクティベートされたときの処理
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7,
			float par8, float par9) {

		// 近隣の確認&変更
		setNeighbor(world, x, y, z);
		// GUIを開く
		entityPlayer.openGui(AluminiumMod.instance, 1, world, x, y, z);

		// タイマー情報をblockMapへ設定する
		TileEntityAluminium t = (TileEntityAluminium) world.getTileEntity(x, y, z);
		if (t.isAlarm()) {
			list.setFlag(x, y, z, true);
		} else {
			list.setFlag(x, y, z, false);
		}

		return true;

	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
		DigitalBlockLogger.info("@@@@@@@Neighbor Block change!!@@@@@@");
		DigitalBlockLogger.info("Block point = X:" + x + ", Y:" + y + ", Z:" + z);
		TileEntityAluminium e = (TileEntityAluminium) world.getTileEntity(x, y, z);
		// 近隣の確認&変更
		setNeighbor(world, x, y, z);

	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		world.scheduleBlockUpdate(x, y, z, this, 10);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityAluminium();
	}

	@Override
	public int tickRate(World p_149738_1_) {
		return 10;
	}

	/**
	 * GUIで設定した値をもとに、設定した時刻・アラームによってフラグが設定される
	 * メソッドはフラグの時に出力をONにする
	 */
	@Override
	public int isProvidingWeakPower(IBlockAccess block, int x, int y, int z, int side) {
		TileEntityAluminium e = (TileEntityAluminium) block.getTileEntity(x, y, z);

		// 座標に存在するブロックのフラグがtrueかどうかをチェックしてtrueなら出力する
		boolean flag = list.getFlag(x, y, z);
		if (flag)
			return 15;
		return 0;
	}

	@Override
	public boolean canProvidePower() {
		return true;
	}

}
