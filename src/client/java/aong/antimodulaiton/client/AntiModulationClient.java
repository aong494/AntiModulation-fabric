package aong.antimodulaiton.client;

import aong.antimodulaiton.network.VerifyPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class AntiModulationClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		PayloadTypeRegistry.playC2S().register(VerifyPayload.TYPE, VerifyPayload.CODEC);

		ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
			ClientPlayNetworking.send(new VerifyPayload());
			System.out.println("보안 안내: 서버로 인증 신호를 전송했습니다! (Fabric)");
		});
	}
}
