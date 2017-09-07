package tutorial.aluminiummod;


import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
 
public class AluminiumItemBlock extends ItemBlockWithMetadata {
 
	public AluminiumItemBlock(Block block) {
		super(block, block);
	}
 
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return this.getUnlocalizedName() + "." + itemStack.getItemDamage();
	}
 
}
