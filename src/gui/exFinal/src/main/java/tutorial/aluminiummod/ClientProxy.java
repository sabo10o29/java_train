package tutorial.aluminiummod;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy{
	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}
 
	/*
	 * client側のTileEntity登録メソッド。
	 * ここでは使用していませんが、TileEntityに独自レンダーを持たせたい場合、
	 * ClientRegistry.registerTileEntity(TileEntityのクラス名.class, "TileEntityの名前", new TileEntityのレンダ―クラス());
	 * のように記述して、レンダークラスとTileEntityを登録します。
	 * 
	 * レンダー関係のクラスはクライアント側にしか存在しないため、サーバ側で誤って登録しないよう、（このように）プロキシを通すなどの対策を必ず行います。
	 */
	@Override
	public void registerTileEntity() {
//		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAluminium.class, new AluminiumSpecialRender());
	}
}
