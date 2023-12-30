package org.ctp.crashapi.resources.recipes;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.ctp.crashapi.resources.util.CrashValidate;
import org.ctp.crashapi.resources.util.JsonBuilder;
import org.ctp.crashapi.resources.util.RecipeModificationResult;

import com.google.gson.JsonObject;

public class CrashSmithingRecipe implements CrashRecipe {

	private ItemStack item;
	private NamespacedKey id;
	private RecipeChoice.MaterialChoice base, add;

	public CrashSmithingRecipe(NamespacedKey id, ItemStack item, RecipeChoice.MaterialChoice base, RecipeChoice.MaterialChoice add) {
		this.setID(id);
		this.setItem(item);
		this.setBase(base);
		this.setAdd(add);
	}

	@Override
	public RecipeModificationResult activate(boolean reload) {
		return activate(reload, id, toJson(), toJsonObject());
	}

	@Override
	public RecipeModificationResult deactivate(boolean reload) {
		return deactivate(reload, id);
	}

	@Override
	public JsonObject toJsonObject() {
		JsonObject json = new JsonObject();

		json.addProperty("type", "minecraft:smithing");

		CrashValidate.notNull(item, "A result must exist for recipe: " + id.toString());
		json.add("result", new JsonBuilder().add("item", "minecraft:" + item.getType().name().toLowerCase()).build());

		CrashValidate.notNull(base, "A base type must exist for recipe: " + id.toString());
		json.add("base", new JsonBuilder().add("item", "minecraft:" + base.getItemStack().getType().name().toLowerCase()).build());

		CrashValidate.notNull(add, "An addition type must exist for recipe: " + id.toString());
		json.add("addition", new JsonBuilder().add("item", "minecraft:" + add.getItemStack().getType().name().toLowerCase()).build());

		return json;
	}

	@Override
	public String toJson() {
		return JsonBuilder.GSON.toJson(toJsonObject());
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}

	public NamespacedKey getID() {
		return id;
	}

	public void setID(NamespacedKey id) {
		this.id = id;
	}

	public RecipeChoice.MaterialChoice getBase() {
		return base;
	}

	public void setBase(RecipeChoice.MaterialChoice base) {
		this.base = base;
	}

	public RecipeChoice.MaterialChoice getAdd() {
		return add;
	}

	public void setAdd(RecipeChoice.MaterialChoice add) {
		this.add = add;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Recipe getRecipe() {
		SmithingRecipe r = new SmithingRecipe(id, item, base, add);
		return r;
	}

}
