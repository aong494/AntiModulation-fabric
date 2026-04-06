package aong.antimodulaiton.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
// 1.21.11 모장 공식 매핑에서는 ResourceLocation 대신 이걸 사용해!
import net.minecraft.resources.Identifier;

public record VerifyPayload() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<VerifyPayload> TYPE = new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath("antimodulation", "verify"));

    public static final StreamCodec<FriendlyByteBuf, VerifyPayload> CODEC = StreamCodec.unit(new VerifyPayload());

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}