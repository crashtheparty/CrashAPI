package org.ctp.crashapi.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;

public class BlockSound {

	private static BlockSound ANVIL = new BlockSound("BLOCK_ANVIL_BREAK", Arrays.asList("ANVIL", "BELL", "CHIPPED_ANVIL", "DAMAGED_ANVIL"), 0.3f, 1.0f);
	private static BlockSound WOOD = new BlockSound("BLOCK_WOOD_BREAK", Arrays.asList("BEE_NEST", "BEEHIVE", "OAK_PLANKS", "SPRUCE_PLANKS", "BIRCH_PLANKS", "JUNGLE_PLANKS", "ACACIA_PLANKS", "DARK_OAK_PLANKS", "OAK_LOG", "SPRUCE_LOG", "BIRCH_LOG", "JUNGLE_LOG", "ACACIA_LOG", "DARK_OAK_LOG", "STRIPPED_SPRUCE_LOG", "STRIPPED_BIRCH_LOG", "STRIPPED_JUNGLE_LOG", "STRIPPED_ACACIA_LOG", "STRIPPED_DARK_OAK_LOG", "STRIPPED_OAK_LOG", "OAK_WOOD", "SPRUCE_WOOD", "BIRCH_WOOD", "JUNGLE_WOOD", "ACACIA_WOOD", "DARK_OAK_WOOD", "STRIPPED_OAK_WOOD", "STRIPPED_SPRUCE_WOOD", "STRIPPED_BIRCH_WOOD", "STRIPPED_JUNGLE_WOOD", "STRIPPED_ACACIA_WOOD", "STRIPPED_DARK_OAK_WOOD", "NOTE_BLOCK", "WHITE_BED", "ORANGE_BED", "MAGENTA_BED", "LIGHT_BLUE_BED", "YELLOW_BED", "LIME_BED", "PINK_BED", "GRAY_BED", "LIGHT_GRAY_BED", "CYAN_BED", "PURPLE_BED", "BLUE_BED", "BROWN_BED", "GREEN_BED", "RED_BED", "BLACK_BED", "BOOKSHELF", "TORCH", "WALL_TORCH", "SOUL_TORCH", "SOUL_WALL_TORCH", "REDSTONE_TORCH", "REDSTONE_WALL_TORCH", "OAK_STAIRS", "CHEST", "CRAFTING_TABLE", "OAK_SIGN", "SPRUCE_SIGN", "BIRCH_SIGN", "ACACIA_SIGN", "JUNGLE_SIGN", "DARK_OAK_SIGN", "OAK_DOOR", "OAK_WALL_SIGN", "SPRUCE_WALL_SIGN", "BIRCH_WALL_SIGN", "ACACIA_WALL_SIGN", "JUNGLE_WALL_SIGN", "DARK_OAK_WALL_SIGN", "LEVER", "OAK_PRESSURE_PLATE", "SPRUCE_PRESSURE_PLATE", "BIRCH_PRESSURE_PLATE", "JUNGLE_PRESSURE_PLATE", "ACACIA_PRESSURE_PLATE", "DARK_OAK_PRESSURE_PLATE", "OAK_FENCE", "PUMPKIN", "CARVED_PUMPKIN", "JACK_O_LANTERN", "REPEATER", "OAK_TRAPDOOR", "SPRUCE_TRAPDOOR", "BIRCH_TRAPDOOR", "JUNGLE_TRAPDOOR", "ACACIA_TRAPDOOR", "DARK_OAK_TRAPDOOR", "BROWN_MUSHROOM_BLOCK", "RED_MUSHROOM_BLOCK", "MUSHROOM_STEM", "MELON", "ATTACHED_PUMPKIN_STEM", "ATTACHED_MELON_STEM", "OAK_FENCE_GATE", "COCOA", "OAK_BUTTON", "SPRUCE_BUTTON", "BIRCH_BUTTON", "JUNGLE_BUTTON", "ACACIA_BUTTON", "DARK_OAK_BUTTON", "TRAPPED_CHEST", "LIGHT_WEIGHTED_PRESSURE_PLATE", "HEAVY_WEIGHTED_PRESSURE_PLATE", "COMPARATOR", "DAYLIGHT_DETECTOR", "WHITE_BANNER", "ORANGE_BANNER", "MAGENTA_BANNER", "LIGHT_BLUE_BANNER", "YELLOW_BANNER", "LIME_BANNER", "PINK_BANNER", "GRAY_BANNER", "LIGHT_GRAY_BANNER", "CYAN_BANNER", "PURPLE_BANNER", "BLUE_BANNER", "BROWN_BANNER", "GREEN_BANNER", "RED_BANNER", "BLACK_BANNER", "WHITE_WALL_BANNER", "ORANGE_WALL_BANNER", "MAGENTA_WALL_BANNER", "LIGHT_BLUE_WALL_BANNER", "YELLOW_WALL_BANNER", "LIME_WALL_BANNER", "PINK_WALL_BANNER", "GRAY_WALL_BANNER", "LIGHT_GRAY_WALL_BANNER", "CYAN_WALL_BANNER", "PURPLE_WALL_BANNER", "BLUE_WALL_BANNER", "BROWN_WALL_BANNER", "GREEN_WALL_BANNER", "RED_WALL_BANNER", "BLACK_WALL_BANNER", "OAK_SLAB", "SPRUCE_SLAB", "BIRCH_SLAB", "JUNGLE_SLAB", "ACACIA_SLAB", "DARK_OAK_SLAB", "SPRUCE_FENCE_GATE", "BIRCH_FENCE_GATE", "JUNGLE_FENCE_GATE", "ACACIA_FENCE_GATE", "DARK_OAK_FENCE_GATE", "SPRUCE_FENCE", "BIRCH_FENCE", "JUNGLE_FENCE", "ACACIA_FENCE", "DARK_OAK_FENCE", "SPRUCE_DOOR", "BIRCH_DOOR", "JUNGLE_DOOR", "ACACIA_DOOR", "DARK_OAK_DOOR", "END_ROD", "CHORUS_PLANT", "CHORUS_FLOWER", "NETHER_WART_BLOCK", "LOOM", "BARREL", "CARTOGRAPHY_TABLE", "FLETCHING_TABLE", "LECTERN", "SMITHING_TABLE", "CAMPFIRE", "COMPOSTER", "SPRUCE_STAIRS", "BIRCH_STAIRS", "JUNGLE_STAIRS", "ACACIA_STAIRS", "DARK_OAK_STAIRS", "PUMPKIN_STEM", "MELON_STEM"));
	private static BlockSound GRAVEL = new BlockSound("BLOCK_GRAVEL_BREAK", Arrays.asList("DIRT", "COARSE_DIRT", "PODZOL", "FARMLAND", "CLAY", "GRAVEL"));
	private static BlockSound GRASS = new BlockSound("BLOCK_GRASS_BREAK", Arrays.asList("GRASS_BLOCK", "OAK_LEAVES", "SPRUCE_LEAVES", "BIRCH_LEAVES", "JUNGLE_LEAVES", "ACACIA_LEAVES", "DARK_OAK_LEAVES", "SPONGE", "WET_SPONGE", "GRASS", "FERN", "DEAD_BUSH", "DANDELION", "POPPY", "BLUE_ORCHID", "ALLIUM", "AZURE_BLUET", "RED_TULIP", "ORANGE_TULIP", "WHITE_TULIP", "PINK_TULIP", "OXEYE_DAISY", "CORNFLOWER", "WITHER_ROSE", "LILY_OF_THE_VALLEY", "BROWN_MUSHROOM", "RED_MUSHROOM", "TNT", "SUGAR_CANE", "VINE", "MYCELIUM", "LILY_PAD", "HAY_BLOCK", "SUNFLOWER", "LILAC", "ROSE_BUSH", "PEONY", "TALL_GRASS", "LARGE_FERN", "GRASS_PATH", "DRIED_KELP_BLOCK", "OAK_SAPLING", "SPRUCE_SAPLING", "BIRCH_SAPLING", "JUNGLE_SAPLING", "ACACIA_SAPLING", "DARK_OAK_SAPLING", "JUKEBOX", "TARGET"));
	private static BlockSound METAL = new BlockSound("BLOCK_METAL_BREAK", Arrays.asList("POWERED_RAIL", "DETECTOR_RAIL", "GOLD_BLOCK", "IRON_BLOCK", "SPAWNER", "DIAMOND_BLOCK", "RAIL", "IRON_DOOR", "IRON_BARS", "EMERALD_BLOCK", "REDSTONE_BLOCK", "HOPPER", "ACTIVATOR_RAIL", "IRON_TRAPDOOR", "TURTLE_EGG"));
	private static BlockSound GLASS = new BlockSound("BLOCK_GLASS_BREAK", Arrays.asList("BLUE_ICE", "GLASS", "ICE", "GLOWSTONE", "NETHER_PORTAL", "WHITE_STAINED_GLASS", "ORANGE_STAINED_GLASS", "MAGENTA_STAINED_GLASS", "LIGHT_BLUE_STAINED_GLASS", "YELLOW_STAINED_GLASS", "LIME_STAINED_GLASS", "PINK_STAINED_GLASS", "GRAY_STAINED_GLASS", "LIGHT_GRAY_STAINED_GLASS", "CYAN_STAINED_GLASS", "PURPLE_STAINED_GLASS", "BLUE_STAINED_GLASS", "BROWN_STAINED_GLASS", "GREEN_STAINED_GLASS", "RED_STAINED_GLASS", "BLACK_STAINED_GLASS", "GLASS_PANE", "END_PORTAL_FRAME", "REDSTONE_LAMP", "WHITE_STAINED_GLASS_PANE", "ORANGE_STAINED_GLASS_PANE", "MAGENTA_STAINED_GLASS_PANE", "LIGHT_BLUE_STAINED_GLASS_PANE", "YELLOW_STAINED_GLASS_PANE", "LIME_STAINED_GLASS_PANE", "PINK_STAINED_GLASS_PANE", "GRAY_STAINED_GLASS_PANE", "LIGHT_GRAY_STAINED_GLASS_PANE", "CYAN_STAINED_GLASS_PANE", "PURPLE_STAINED_GLASS_PANE", "BLUE_STAINED_GLASS_PANE", "BROWN_STAINED_GLASS_PANE", "GREEN_STAINED_GLASS_PANE", "RED_STAINED_GLASS_PANE", "BLACK_STAINED_GLASS_PANE", "SEA_LANTERN", "PACKED_ICE", "FROSTED_ICE"));
	private static BlockSound WOOL = new BlockSound("BLOCK_WOOL_BREAK", Arrays.asList("WHITE_WOOL", "ORANGE_WOOL", "MAGENTA_WOOL", "LIGHT_BLUE_WOOL", "YELLOW_WOOL", "LIME_WOOL", "PINK_WOOL", "GRAY_WOOL", "LIGHT_GRAY_WOOL", "CYAN_WOOL", "PURPLE_WOOL", "BLUE_WOOL", "BROWN_WOOL", "GREEN_WOOL", "RED_WOOL", "BLACK_WOOL", "FIRE", "SOUL_FIRE", "CACTUS", "CAKE", "WHITE_CARPET", "ORANGE_CARPET", "MAGENTA_CARPET", "LIGHT_BLUE_CARPET", "YELLOW_CARPET", "LIME_CARPET", "PINK_CARPET", "GRAY_CARPET", "LIGHT_GRAY_CARPET", "CYAN_CARPET", "PURPLE_CARPET", "BLUE_CARPET", "BROWN_CARPET", "GREEN_CARPET", "RED_CARPET", "BLACK_CARPET"));
	private static BlockSound SAND = new BlockSound("BLOCK_SAND_BREAK", Arrays.asList("WHITE_CONCRETE_POWDER", "ORANGE_CONCRETE_POWDER", "MAGENTA_CONCRETE_POWDER", "LIGHT_BLUE_CONCRETE_POWDER", "YELLOW_CONCRETE_POWDER", "LIME_CONCRETE_POWDER", "PINK_CONCRETE_POWDER", "GRAY_CONCRETE_POWDER", "LIGHT_GRAY_CONCRETE_POWDER", "CYAN_CONCRETE_POWDER", "PURPLE_CONCRETE_POWDER", "BLUE_CONCRETE_POWDER", "BROWN_CONCRETE_POWDER", "GREEN_CONCRETE_POWDER", "RED_CONCRETE_POWDER", "BLACK_CONCRETE_POWDER", "SAND", "RED_SAND", "SOUL_SAND"));
	private static BlockSound SNOW = new BlockSound("BLOCK_SNOW_BREAK", Arrays.asList("SNOW", "SNOW_BLOCK"));
	private static BlockSound LADDER = new BlockSound("BLOCK_LADDER_BREAK", Arrays.asList("LADDER"));
	private static BlockSound SLIME_BLOCK = new BlockSound("BLOCK_SLIME_BLOCK_BREAK", Arrays.asList("SLIME_BLOCK", "SEA_PICKLE"));
	private static BlockSound HONEY_BLOCK = new BlockSound("BLOCK_HONEY_BLOCK_BREAK", Arrays.asList("HONEY_BLOCK"));
	private static BlockSound WET_GRASS = new BlockSound("BLOCK_WET_GRASS_BREAK", Arrays.asList("SEAGRASS", "TALL_SEAGRASS", "KELP", "KELP_PLANT", "TUBE_CORAL", "BRAIN_CORAL", "BUBBLE_CORAL", "FIRE_CORAL", "HORN_CORAL", "TUBE_CORAL_FAN", "BRAIN_CORAL_FAN", "BUBBLE_CORAL_FAN", "FIRE_CORAL_FAN", "HORN_CORAL_FAN", "TUBE_CORAL_WALL_FAN", "BRAIN_CORAL_WALL_FAN", "BUBBLE_CORAL_WALL_FAN", "FIRE_CORAL_WALL_FAN", "HORN_CORAL_WALL_FAN"));
	private static BlockSound CORAL_BLOCK = new BlockSound("BLOCK_CORAL_BLOCK_BREAK", Arrays.asList("TUBE_CORAL_BLOCK", "BRAIN_CORAL_BLOCK", "BUBBLE_CORAL_BLOCK", "FIRE_CORAL_BLOCK", "HORN_CORAL_BLOCK", "HONEYCOMB_BLOCK"));
	private static BlockSound BAMBOO = new BlockSound("BLOCK_BAMBOO_BREAK", Arrays.asList("BAMBOO"));
	private static BlockSound BAMBOO_SAPLING = new BlockSound("BLOCK_BAMBOO_SAPLING_BREAK", Arrays.asList("BAMBOO_SAPLING"));
	private static BlockSound SCAFFOLDING = new BlockSound("BLOCK_SCAFFOLDING_BREAK", Arrays.asList("SCAFFOLDING"));
	private static BlockSound SWEET_BERRY_BUSH = new BlockSound("BLOCK_SWEET_BERRY_BUSH_BREAK", Arrays.asList("SWEET_BERRY_BUSH"));
	private static BlockSound CROP = new BlockSound("BLOCK_CROP_BREAK", Arrays.asList("WHEAT", "CARROTS", "POTATOES", "BEETROOTS"));
	private static BlockSound NETHER_WART = new BlockSound("BLOCK_NETHER_WART_BREAK", Arrays.asList("NETHER_WART"));
	private static BlockSound LANTERN = new BlockSound("BLOCK_LANTERN_BREAK", Arrays.asList("LANTERN", "SOUL_LANTERN"));
	private static BlockSound STEM = new BlockSound("BLOCK_STEM_BREAK", Arrays.asList("CRIMSON_HYPHAE", "STRIPPED_CRIMSON_HYPHAE", "WARPED_HYPHAE", "STRIPPED_WARPED_HYPHAE", "WARPED_STEM", "CRIMSON_STEM", "STRIPPED_WARPED_STEM", "STRIPPED_CRIMSON_STEM"));
	private static BlockSound NYLIUM = new BlockSound("BLOCK_NYLIUM_BREAK", Arrays.asList("WARPED_NYLIUM", "CRIMSON_NYLIUM"));
	private static BlockSound FUNGUS = new BlockSound("BLOCK_FUNGUS_BREAK", Arrays.asList("WARPED_FUNGUS", "CRIMSON_FUNGUS"));
	private static BlockSound ROOTS = new BlockSound("BLOCK_ROOTS_BREAK", Arrays.asList("WARPED_ROOTS", "CRIMSON_ROOTS"));
	private static BlockSound SHROOMLIGHT = new BlockSound("BLOCK_SHROOMLIGHT_BREAK", Arrays.asList("SHROOMLIGHT"));
	private static BlockSound VINES = new BlockSound("BLOCK_WEEPING_VINES_BREAK", Arrays.asList("WEEPING_VINES", "WEEPING_VINES_PLANT", "TWISTING_VINES", "TWISTING_VINES_PLANT"));
	private static BlockSound SOUL_SAND = new BlockSound("BLOCK_SOUL_SAND_BREAK", Arrays.asList("SOUL_SAND"));
	private static BlockSound SOUL_SOIL = new BlockSound("BLOCK_SOUL_SOIL_BREAK", Arrays.asList("SOUL_SOIL"));
	private static BlockSound BASALT = new BlockSound("BLOCK_BASALT_BREAK", Arrays.asList("BASALT", "POLISHED_BASALT"));
	private static BlockSound WART_BLOCK = new BlockSound("BLOCK_WART_BLOCK_BREAK", Arrays.asList("NETHER_WART_BLOCK", "WARPED_WART_BLOCK"));
	private static BlockSound NETHERRACK = new BlockSound("BLOCK_NETHERRACK_BREAK", Arrays.asList("NETHERRACK"));
	private static BlockSound NETHER_BRICKS = new BlockSound("BLOCK_NETHER_BRICKS_BREAK", Arrays.asList("NETHER_BRICKS", "NETHER_BRICK_WALL", "NETHER_BRICK_FENCE", "NETHER_BRICK_STAIRS", "NETHER_BRICK_SLAB", "RED_NETHER_BRICK_WALL", "RED_NETHER_BRICKS", "RED_NETHER_BRICK_STAIRS", "RED_NETHER_BRICK_SLAB", "CHISELED_NETHER_BRICKS", "CRACKED_NETHER_BRICKS"));
	private static BlockSound NETHER_SPROUTS = new BlockSound("BLOCK_NETHER_SPROUTS_BREAK", Arrays.asList("NETHER_SPROUTS"));
	private static BlockSound NETHER_ORE = new BlockSound("BLOCK_NETHER_ORE_BREAK", Arrays.asList("NETHER_QUARTZ_ORE"));
	private static BlockSound BONE_BLOCK = new BlockSound("BLOCK_BONE_BLOCK_BREAK", Arrays.asList("BONE_BLOCK"));
	private static BlockSound NETHERITE_BLOCK = new BlockSound("BLOCK_NETHERITE_BLOCK_BREAK", Arrays.asList("NETHERITE_BLOCK"));
	private static BlockSound ANCIENT_DEBRIS = new BlockSound("BLOCK_ANCIENT_DEBRIS_BREAK", Arrays.asList("ANCIENT_DEBRIS"));
	private static BlockSound LODESTONE = new BlockSound("BLOCK_LODESTONE_BREAK", Arrays.asList("LODESTONE"));
	private static BlockSound CHAIN = new BlockSound("BLOCK_CHAIN_BREAK", Arrays.asList("CHAIN"));
	private static BlockSound NETHER_GOLD_ORE = new BlockSound("BLOCK_NETHER_GOLD_ORE_BREAK", Arrays.asList("NETHER_GOLD_ORE"));
	private static BlockSound GILDED_BLACKSTONE = new BlockSound("BLOCK_GILDED_BLACKSTONE_BREAK", Arrays.asList("GILDED_BLACKSTONE"));
	private static BlockSound STONE = new BlockSound("BLOCK_STONE_BREAK", Arrays.asList());
	private static List<BlockSound> SOUNDS = Arrays.asList(GILDED_BLACKSTONE, NETHER_GOLD_ORE, CHAIN, LODESTONE, ANCIENT_DEBRIS, NETHERITE_BLOCK, BONE_BLOCK, NETHER_ORE, NETHER_SPROUTS, NETHER_BRICKS, NETHERRACK, WART_BLOCK, BASALT, SOUL_SOIL, SOUL_SAND, VINES, SHROOMLIGHT, ROOTS, FUNGUS, NYLIUM, STEM, ANVIL, WOOD, GRAVEL, GRASS, METAL, GLASS, WOOL, SAND, SNOW, LADDER, SLIME_BLOCK, HONEY_BLOCK, WET_GRASS, CORAL_BLOCK, BAMBOO, BAMBOO_SAPLING, SCAFFOLDING, SWEET_BERRY_BUSH, CROP, NETHER_WART, LANTERN);

	private final List<MaterialSound> types;
	private final String sound;
	private final float volume, pitch;

	private BlockSound(String sound, List<String> types) {
		this(sound, types, 1.0F, 1.0F);
	}

	private BlockSound(String sound, List<String> types, float volume, float pitch) {
		this.sound = sound;
		List<MaterialSound> sounds = new ArrayList<MaterialSound>();
		for(String s: types)
			try {
				sounds.add(MaterialSound.valueOf(s));
			} catch (Exception ex) {}
		this.types = sounds;
		this.volume = volume;
		this.pitch = pitch;
	}

	public List<MaterialSound> getTypes() {
		return types;
	}

	public Sound getSound() {
		try {
			return Sound.valueOf(sound);
		} catch (Exception ex) {

		}
		return null;
	}

	public String getSoundString() {
		return sound;
	}

	public float getVolume() {
		return volume;
	}

	public float getPitch() {
		return pitch;
	}

	public float getVolume(Material m) {
		for(MaterialSound type: getTypes())
			if (type.getMaterial().hasMaterial() && type.getMaterial().getMaterial() == m && type.getVolume() > 0) return type.getVolume();
		return volume;
	}

	public float getPitch(Material m) {
		for(MaterialSound type: getTypes())
			if (type.getMaterial().hasMaterial() && type.getMaterial().getMaterial() == m && type.getPitch() > 0) return type.getPitch();
		return pitch;
	}

	public static BlockSound getSound(Material m) {
		for(BlockSound sound: SOUNDS) {
			if (sound.getSound() == null) continue;
			for(MaterialSound type: sound.getTypes())
				if (type.getMaterial().hasMaterial() && type.getMaterial().getMaterial() == m) return sound;
		}
		return STONE;
	}
}
