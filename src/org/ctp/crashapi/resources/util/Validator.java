package org.ctp.crashapi.resources.util;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

/**
 * Validation utility, used internally.
 */
public class Validator {
	public static void noNamespace(String id) {
		CrashValidate.notNull(id);
		CrashValidate.isTrue(!id.contains(":"), "The id parameter must not contain namespaces: ", id);
		CrashValidate.isTrue(!id.contains(" "), "The id parameter must not contain spaces: ", id);
	}

	public static void texture(String texture) {
		CrashValidate.notNull(texture);
		CrashValidate.isTrue(!texture.contains(":"), "Texture parameter must not contain namespace: ", texture);
		CrashValidate.isTrue(!texture.startsWith("textures/"), "Texture parameter must not include root directory (/textures): ", texture);
		CrashValidate.isTrue(!texture.contains("."), "Texture parameter must not specify file extension: ", texture);
		CrashValidate.isTrue(texture.contains("/"), "Texture parameter must specify subdirectory using slash (/): ", texture);
		CrashValidate.isTrue(!texture.endsWith("/"), "Texture parameter must specify a file in the directory: ", texture);
	}

	public static void nbt(@Nullable String nbt) {
		if (nbt != null) CrashValidate.isTrue(nbt.startsWith("{") && nbt.endsWith("}"), "NBT string must start and end with curly braces: ", nbt);
	}

	public static void minusToDisable(int value) {
		CrashValidate.isTrue(value >= -1, "Set to -1 to disable.");
	}

	public static void zeroToDisable(int value) {
		CrashValidate.isTrue(value >= 0, "Set to 0 to disable.");
	}
}
