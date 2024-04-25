package org.ctp.crashapi.data;

import org.bukkit.Particle;

public class ParticleData {

	private Particle particle;
	private String particleName;

	public ParticleData(String name) {
		particleName = name.toUpperCase();
		try {
			particle = Particle.valueOf(name);
		} catch (Exception ex) {}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ParticleData) {
			ParticleData data = (ParticleData) obj;
			return data.hasParticle() && data.particle == particle;
		}
		return false;
	}

	public boolean hasParticle() {
		return particle != null;
	}

	public Particle getParticle() {
		return particle;
	}

	public String getParticleName() {
		return particleName;
	}

}
