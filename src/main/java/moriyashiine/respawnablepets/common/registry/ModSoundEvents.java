/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.respawnablepets.common.registry;

import moriyashiine.respawnablepets.common.RespawnablePets;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

public class ModSoundEvents {
	public static final SoundEvent ENTITY_GENERIC_TELEPORT = SoundEvent.of(RespawnablePets.id("entity.generic.teleport"));

	public static void init() {
		Registry.register(Registries.SOUND_EVENT, ENTITY_GENERIC_TELEPORT.getId(), ENTITY_GENERIC_TELEPORT);
	}
}
