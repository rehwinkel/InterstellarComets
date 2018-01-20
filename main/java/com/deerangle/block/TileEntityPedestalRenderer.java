package com.deerangle.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityPedestalRenderer extends TileEntitySpecialRenderer {
	

	@Override
	public void render(TileEntity tee, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		super.render(tee, x, y, z, partialTicks, destroyStage, alpha);
		
		TileEntityPedestal entity = (TileEntityPedestal) tee;
		float speed = 3;
		IItemHandler itemHandler = entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		ItemStack stack = itemHandler.getStackInSlot(0);
		
		if(!stack.isEmpty()){
			boolean isItem = !(stack.getItem() instanceof ItemBlock);
			if(!isItem){
				Block block = ((ItemBlock)stack.getItem()).getBlock();
				isItem = block instanceof BlockBush;
			}
			GlStateManager.enableRescaleNormal();
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
			GlStateManager.enableBlend();
			RenderHelper.enableStandardItemLighting();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			GlStateManager.pushMatrix();
			GlStateManager.translate(x + 0.5, y + 0.60 + (isItem ? 0.15 : 0), z + 0.5);
			GlStateManager.rotate((entity.getWorld().getTotalWorldTime() - entity.placeTime * speed) % 360, 0, 1, 0);
			IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, entity.getWorld(), null);
			model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);
			Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
			GlStateManager.popMatrix();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
		}
	}
	
}