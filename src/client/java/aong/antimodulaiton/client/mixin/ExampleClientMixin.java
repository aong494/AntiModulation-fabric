package aong.antimodulaiton.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class ExampleClientMixin {

	private static final String[] BLOCKED_SCREENS = {
			"net.optifine.gui.GuiShaders",
			"net.irisshaders.iris.gui.screen.ShaderPackScreen",
			"net.irisshaders.iris.gui.screen.ShaderPackScreen$1"
	};

	@Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
	private void onSetScreen(Screen screen, CallbackInfo ci) {
		if (screen == null) return;

		String screenClassName = screen.getClass().getName();

		for (String blockedScreen : BLOCKED_SCREENS) {
			if (screenClassName.equals(blockedScreen)) {
				ci.cancel();
				System.out.println("보안 안내: 쉐이더 설정창 접근이 차단되었습니다. (Fabric)");
				break;
			}
		}
	}
}