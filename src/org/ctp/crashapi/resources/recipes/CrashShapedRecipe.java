package org.ctp.crashapi.resources.recipes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.ctp.crashapi.resources.util.CrashValidate;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;
import org.ctp.crashapi.resources.util.JsonBuilder;
import org.ctp.crashapi.resources.util.RecipeModificationResult;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CrashShapedRecipe implements CrashRecipe {

	private NamespacedKey id;
	private ItemStack result;
	private String[] rows;
	private Map<Character, RecipeChoice> ingredients = new HashMap<>();

	public CrashShapedRecipe(NamespacedKey id, ItemStack result) {
		this.id = id;
		this.result = result;
	}

	@Override
	public RecipeModificationResult activate(boolean reload) {
		return activate(reload, id, toJson(), toJsonObject());
	}

	@Override
	public RecipeModificationResult deactivate(boolean reload) {
		return deactivate(reload, id);
	}

	public void shape(String... shape) {
		CrashValidate.notNull(shape, "Must provide a shape");
		CrashValidate.isTrue(shape.length > 0 && shape.length < 4, "Crafting recipes should be 1, 2 or 3 rows, not ", shape.length);

		int lastLen = -1;
		for(String row: shape) {
			CrashValidate.notNull(row, "Shape cannot have null rows");
			CrashValidate.isTrue(row.length() > 0 && row.length() < 4, "Crafting rows should be 1, 2, or 3 characters, not ", row.length());

			CrashValidate.isTrue(lastLen == -1 || lastLen == row.length(), "Crafting recipes must be rectangular");
			lastLen = row.length();
		}
		this.rows = new String[shape.length];
		for(int i = 0; i < shape.length; i++)
			this.rows[i] = shape[i];

		// Remove character mappings for characters that no longer exist in the shape
		HashMap<Character, RecipeChoice> newIngredients = new HashMap<>();
		for(String row: shape)
			for(Character c: row.toCharArray())
				newIngredients.put(c, ingredients.get(c));
		this.ingredients = newIngredients;
	}

	public void setIngredient(char key, RecipeChoice ingredient) {
		CrashValidate.isTrue(ingredients.containsKey(key), "Symbol does not appear in the shape:", key);

		ingredients.put(key, ingredient);
	}

	public Map<Character, RecipeChoice> getChoiceMap() {
		Map<Character, RecipeChoice> result = new HashMap<>();
		for(Map.Entry<Character, RecipeChoice> ingredient: ingredients.entrySet())
			if (ingredient.getValue() == null) result.put(ingredient.getKey(), null);
			else
				result.put(ingredient.getKey(), ingredient.getValue().clone());
		return result;
	}

	@Override
	public JsonObject toJsonObject() {
		JsonObject json = new JsonObject();

		json.addProperty("type", "minecraft:crafting_shaped");

		CrashValidate.notNull(result, "A result must exist for recipe: " + id.toString());
		json.add("result", new JsonBuilder().add("item", "minecraft:" + result.getType().name().toLowerCase()).build());

		CrashValidate.notNull(rows, "A pattern must exist for recipe: " + id.toString());
		JSONArray parseRows;
		try {
			parseRows = (JSONArray) (new JSONParser().parse(new Gson().toJson(rows)));
			json.add("pattern", new Gson().fromJson(parseRows.toJSONString(), JsonArray.class));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		CrashValidate.notNull(getChoiceMap(), "A key must exist for recipe: " + id.toString());
		JsonObject key = new JsonObject();
		Iterator<Entry<Character, RecipeChoice>> iter = getChoiceMap().entrySet().iterator();
		while (iter.hasNext()) {
			JsonArray array = new JsonArray();
			Entry<Character, RecipeChoice> entry = iter.next();
			if (entry.getValue() == null) continue;
			if (entry.getValue() instanceof MaterialChoice) {
				MaterialChoice c = (MaterialChoice) entry.getValue();
				if (c.getChoices().size() > 1) {
					for (Material m : c.getChoices())
						array.add(new JsonBuilder().add("item", "minecraft:" + m.toString().toLowerCase()).build());
					key.add(entry.getKey().toString(), array);
				} else
					key.add(entry.getKey().toString(), new JsonBuilder().add("item", "minecraft:" + c.getItemStack().getType().name().toLowerCase()).build());
			} else if (entry.getValue() instanceof ExactChoice) {
				ExactChoice c = (ExactChoice) entry.getValue();
				if (c.getChoices().size() > 1) {
					for (ItemStack i : c.getChoices())
						array.add(new JsonBuilder().add("item", i.toString()).build());
					key.add(entry.getKey().toString(), array);
				} else
					key.add(entry.getKey().toString(), new JsonBuilder().add("item", c.getItemStack().toString()).build());
			}
		}
		json.add("key", key);

		return json;
	}

	@Override
	public String toJson() {
		return JsonBuilder.GSON.toJson(toJsonObject());
	}

	public NamespacedKey getId() {
		return id;
	}

	public void setId(NamespacedKey id) {
		this.id = id;
	}

	public ItemStack getResult() {
		return result;
	}

	public void setResult(ItemStack result) {
		this.result = result;
	}

	@Override
	public Recipe getRecipe() {
		ShapedRecipe r = new ShapedRecipe(id, result);
		r.shape(rows);
		Iterator<Entry<Character, RecipeChoice>> iter = ingredients.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Character, RecipeChoice> entry = iter.next();
			r.setIngredient(entry.getKey(), entry.getValue());
		}
		return r;
	}

	public String[] getShape() {
		return rows;
	}

}
