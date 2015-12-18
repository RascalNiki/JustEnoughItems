package mezz.jei.plugins.jei.description;

import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;

import mezz.jei.api.JEIManager;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.config.Config;
import mezz.jei.util.Translator;

public class ItemDescriptionRecipeCategory implements IRecipeCategory {
	public static final int recipeWidth = 160;
	public static final int recipeHeight = 125;
	@Nonnull
	private final IDrawable background;
	@Nonnull
	private final String localizedName;

	public ItemDescriptionRecipeCategory() {
		background = JEIManager.guiHelper.createBlankDrawable(recipeWidth, recipeHeight);
		localizedName = Translator.translateToLocal("gui.jei.category.itemDescription");
	}

	@Nonnull
	@Override
	public String getUid() {
		return VanillaRecipeCategoryUid.DESCRIPTION;
	}

	@Nonnull
	@Override
	public String getTitle() {
		return localizedName;
	}

	@Nonnull
	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft) {

	}

	@Override
	public void drawAnimations(Minecraft minecraft) {

	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		int xPos = (recipeWidth - 18) / 2;
		guiItemStacks.init(0, false, xPos, 0);
		guiItemStacks.setFromRecipe(0, recipeWrapper.getOutputs());

		if (Config.debugModeEnabled) {
			IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();
			guiFluidStacks.init(0, true, 0, 0, 24, 24, 2000, JEIManager.guiHelper.getSlotDrawable());
			guiFluidStacks.init(1, true, 24, 0, 24, 24, 2000, null);
			guiFluidStacks.set(0, recipeWrapper.getFluidInputs().get(0));
			guiFluidStacks.set(1, recipeWrapper.getFluidInputs().get(1));
		}
	}
}
