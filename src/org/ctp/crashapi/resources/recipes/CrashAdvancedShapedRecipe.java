package org.ctp.crashapi.resources.recipes;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.ctp.crashapi.resources.util.RecipeModificationResult;

import com.google.gson.JsonObject;

public class CrashAdvancedShapedRecipe extends CrashShapedRecipe {

	public CrashAdvancedShapedRecipe(NamespacedKey id, ItemStack result) {
		super(id, result);
	}

	@Override
	public RecipeModificationResult activate(boolean reload, NamespacedKey id, String json,
	JsonObject jsonObject) {
		return new RecipeModificationResult(true, reload, "Loaded successfully.");
	}

	@Override
	public RecipeModificationResult deactivate(boolean reload, NamespacedKey id) {
		boolean exist = Bukkit.getRecipe(id) != null;
		boolean remove = Bukkit.removeRecipe(id);
		boolean existAndRemove = exist && remove;
		if (!remove) return new RecipeModificationResult(true, false, "Could not unload.");
		return new RecipeModificationResult(true, existAndRemove, "Unloaded successfully.");
	}

	public void register() {
		if (Bukkit.getRecipe(getId()) != null) return;
		ShapedRecipe sr = new ShapedRecipe(getId(), getResult());
		sr.shape(getShape());
		try {
			Field f = sr.getClass().getDeclaredField("ingredients");
			f.setAccessible(true);
			f.set(sr, getChoiceMap());
			Bukkit.addRecipe(sr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
