package tutorial.aluminiummod;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SetupGUIHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
//		if (!world.blockExists(x, y, z))
//			return null;
//		TileEntity tileentity = world.getTileEntity(x, y, z);
//		if (tileentity instanceof TileEntityAluminium) {
//			return new ContainerAluminiumChest(player, (TileEntityAluminium) tileentity);
//		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if (!world.blockExists(x, y, z))
			return null;
		TileEntity tileentity = world.getTileEntity(x, y, z);
		if (tileentity instanceof TileEntityAluminium) {
			return new SetupGUI(player, (TileEntityAluminium) tileentity);
		}
		return null;
	}

}
