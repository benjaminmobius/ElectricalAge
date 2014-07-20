package mods.eln.sixnode.powerinductorsix;

import java.util.List;

import mods.eln.Eln;
import mods.eln.item.FerromagneticCoreDescriptor;
import mods.eln.misc.Obj3D;
import mods.eln.misc.series.ISerie;
import mods.eln.node.six.SixNodeDescriptor;
import mods.eln.wiki.Data;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class PowerInductorSixDescriptor extends SixNodeDescriptor {

	private Obj3D obj;

	public PowerInductorSixDescriptor(
			String name,
			Obj3D obj,
			ISerie serie

	) {
		super(name, PowerInductorSixElement.class, PowerInductorSixRender.class);
		this.serie = serie;
		this.obj = obj;
		if (obj != null) {

		}

	}

	ISerie serie;

	public double getlValue(int cableCount) {
		if(cableCount == 0) return 0;
		return serie.getValue(cableCount-1);
	}

	public double getlValue(IInventory inventory) {
		ItemStack core = inventory.getStackInSlot(PowerInductorSixContainer.cableId);
		if (core == null)
			return getlValue(0);
		else
			return getlValue(core.stackSize);
	}

	public double getRsValue(IInventory inventory) {
		ItemStack core = inventory.getStackInSlot(PowerInductorSixContainer.coreId);

		if (core == null) return 1000000000.0;
		FerromagneticCoreDescriptor coreDescriptor = (FerromagneticCoreDescriptor) FerromagneticCoreDescriptor.getDescriptor(core);

		double coreFactor = coreDescriptor.cableMultiplicator;

		return Eln.instance.lowVoltageCableDescriptor.electricalRs * coreFactor;
	}

	public void setParent(net.minecraft.item.Item item, int damage)
	{
		super.setParent(item, damage);
		Data.addEnergy(newItemStack());
	}

	void draw() {

	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		draw();
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer,
			List list, boolean par4) {

		super.addInformation(itemStack, entityPlayer, list, par4);

	}

}
