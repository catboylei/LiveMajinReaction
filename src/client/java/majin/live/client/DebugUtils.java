package majin.live.client;

import io.wispforest.owo.mixin.ui.access.GuiGraphicsAccessor;
import majin.live.client.events.ShouldAddPoints;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.util.Identifier;

public class DebugUtils {
    public static void enable() {

        Utils.registerCommand("test", ctx -> {
            ShouldAddPoints.EVENT.invoker().invoke(20);
        });
        Utils.registerCommand("test2", ctx -> {
            ShouldAddPoints.EVENT.invoker().invoke(-20);
        });

        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Identifier.of("live-majin-reaction", "before_chat"), DebugUtils::render);
    }

    private static void render(DrawContext ctx, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        int x = client.getWindow().getScaledWidth() / 2 - 3;
        int y = client.getWindow().getScaledHeight() / 2 + 30;

        String text = String.valueOf(LiveMajinReactionClient.CONFIG.internalSettings.majinPoints());
        ctx.drawTextWithShadow(client.textRenderer, text, x, y, 0xFFFFCEFF);
    }
}