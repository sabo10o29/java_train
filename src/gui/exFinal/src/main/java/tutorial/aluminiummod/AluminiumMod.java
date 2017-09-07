package tutorial.aluminiummod;
 
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
 
/**
 * 課題：正面の方向を記録
 * プレイヤーの方向に合わせて正面を設定
 * 正面の方向に対してとなりあうブロックを確認し、同じブロックならテクスチャを設定
 * @author YoshikazuMurase
 *
 */
@Mod(modid = AluminiumMod.MODID, name = AluminiumMod.MODNAME, version = AluminiumMod.VERSION)
public class AluminiumMod {
	
	public static final String MODID = "AluminiumMod";
	public static final String MODNAME = "Aluminium Mod";
	
	public static final String VERSION = "1.0.0";
	
	@Instance(MODID)
	public static AluminiumMod instance;
	
	/*
	 * 念の為にプロキシクラスを設定。
	 * 今回は使用していませんが、TileEntitySpecialRendererを使ってモデルを適用する場合などは、
	 * client、serverそれぞれで異なるメソッドを使ってTileEntityを登録します。
	 * （今回は割愛）
	 */
	@SidedProxy(clientSide = "tutorial.aluminiummod.ClientProxy", serverSide = "tutorial.aluminiummod.CommonProxy")
	public static CommonProxy proxy;
	
	/**
	 * 作成したブロックとアイテム
	 */
	public static Item aluminium;
	public static Block blockAluminium;
	public static int RenderID;
	
	/**
	 * 作成したブロックと、アイテムの登録
	 * @param event
	 */
	@EventHandler
	public void perInit(FMLPreInitializationEvent event) {
		// アルミニウムアイテム：自分には必要ないアイテム
		aluminium = new Item()
		.setCreativeTab(CreativeTabs.tabMaterials)
		.setUnlocalizedName("aluminium")
		.setTextureName("aluminiummod:aluminium");
		GameRegistry.registerItem(aluminium, "aluminium");
		
		//　デジタルブロックアイテムの生成
		blockAluminium = new AluminiumBlock(Material.rock)
		.setBlockName("blockAluminium")
		.setBlockTextureName("aluminiummod:aluminium_block")
		.setTickRandomly(false);
		
		//　デジタルブロックとアルミニウムアイテムの追加
		GameRegistry.registerBlock(blockAluminium, AluminiumItemBlock.class, "blockAluminium");
		GameRegistry.registerTileEntity(TileEntityAluminium.class, "TileEntityAluminium");
		
		//定型レシピの追加
		//追加アイテム・ブロックの使用
		//アルミニウム９個でアルミニウムブロック１個を作るレシピ
		GameRegistry.addRecipe(new ItemStack(AluminiumMod.blockAluminium),
				"XXX", "XYX", "XXX", 'X', Blocks.iron_block, 'Y', Items.redstone);
		
		//Guiハンドラの追加
		NetworkRegistry.INSTANCE.registerGuiHandler(this.instance, new SetupGUIHandler());
	}
	
	/**
	 * 
	 * @param event
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerTileEntity();
		//RenderIDを自動で割り振っている
//		this.RenderID = RenderingRegistry.getNextAvailableRenderId();
//	    //レンダーを指定する
		//ISimpleBlockRenderingHandler
//		RenderingRegistry.registerBlockHandler(new Render(blockAluminium));
	}
	
 
}