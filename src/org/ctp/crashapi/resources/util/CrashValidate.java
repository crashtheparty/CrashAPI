package org.ctp.crashapi.resources.util;

import java.util.Map;

public class CrashValidate {

	private static final String NOT_NULL = "Object cannot be null!";
	private static final String TRUE = "Variable must be true!";
	private static final String NOT_EMPTY = "Map must contain objects!";

	public static void notNull(Object object) {
		notNull(object, NOT_NULL, new Object[0]);
	}

	public static void notNull(Object object, String string) {
		notNull(object, string, new Object[0]);

	}

	public static void notNull(Object object, String string, Object... objects) {
		if (object == null) throw new NullPointerException(String.format(string, objects));
	}

	public static void isTrue(boolean b) {
		isTrue(b, TRUE, new Object[0]);
	}

	public static void isTrue(boolean b, String string) {
		isTrue(b, string, new Object[0]);
	}

	public static void isTrue(boolean b, String string, Object... objects) {
		if (!b) throw new IllegalArgumentException(String.format(string, objects));
	}

	public static void notEmpty(Map<?, ?> triggers) {
		notEmpty(triggers, NOT_EMPTY, new Object[0]);
	}

	public static void notEmpty(Map<?, ?> triggers, String string) {
		notEmpty(triggers, string, new Object[0]);
	}

	public static void notEmpty(Map<?, ?> triggers, String string, Object... objects) {
		if (triggers.isEmpty()) throw new IllegalArgumentException(String.format(string, objects));
	}

}
