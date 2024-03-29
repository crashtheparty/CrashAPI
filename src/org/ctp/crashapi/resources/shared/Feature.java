package org.ctp.crashapi.resources.shared;

/**
 * Specifies a structure in a world.
 * 
 * @see LocationObject
 */
enum Feature implements SharedEnum {
	END_CITY("EndCity"), FORTRESS("Fortress"), MANSION("Mansion"), MINESHAFT("Mineshaft"), MONUMENT("Monument"),
	STRONGHOLD("Stronghold"), TEMPLE("Temple"), VILLAGE("Village");

	private final String value;

	Feature(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}
