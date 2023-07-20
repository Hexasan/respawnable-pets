/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.respawnablepets.client.packet;

import io.netty.buffer.Unpooled;
import moriyashiine.respawnablepets.common.RespawnablePets;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class SpawnSmokeParticlesPacket {
	public static final Identifier ID = RespawnablePets.id("spawn_smoke_particles");

	public static void send(ServerPlayerEntity player, Entity entity) {
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
		buf.writeInt(entity.getId());
		ServerPlayNetworking.send(player, ID, buf);
	}

	@Environment(EnvType.CLIENT)
	public static class Receiver implements ClientPlayNetworking.PlayChannelHandler {
		@Override
		public void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
			int id = buf.readInt();
			client.execute(() -> {
				ClientWorld world = client.world;
				if (world != null) {
					Entity entity = world.getEntityById(id);
					if (entity != null) {
						for (int i = 0; i < 32; i++) {
							world.addParticle(ParticleTypes.SMOKE, entity.getParticleX(1), entity.getRandomBodyY(), entity.getParticleZ(1), 0, 0, 0);
						}
					}
				}
			});
		}
	}
}
