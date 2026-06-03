package majin.live.client;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

import java.awt.*;
import java.util.function.Consumer;

public class Utils {

    private static final Text tag = makeRainbow("[Live Majin Reaction] ");

    public static void notifyChat(String msg) {
        if (MinecraftClient.getInstance().player == null) return;
        MinecraftClient.getInstance().player.sendMessage(tag.copy().append(Text.literal(msg)), false);
    }

    public static void registerCommand(String cmd, Consumer<CommandContext<FabricClientCommandSource>> action) {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal(cmd).executes(ctx -> {
                action.accept(ctx);
                return 1;
            }));
        });
    }

    // majin likes rainbow text so this might be useful idk
    public static Text makeRainbow(String text) {
        MutableText result = Text.literal("");

        float step = 1.0f / Math.max(1, text.length());
        float hue = 0f;

        for (char c : text.toCharArray()) {
            int rgb = Color.HSBtoRGB(hue, 0.9f, 1.0f);

            int hex = rgb & 0xFFFFFF;
            TextColor color = TextColor.fromRgb(hex);
            result.append(Text.literal(String.valueOf(c)).setStyle(Style.EMPTY.withColor(color)));

            hue += step;
        }
        return result;
    }

    // this is here just for readability tbh
    public static void debugPrint(String msg) {
        if (LiveMajinReactionClient.CONFIG.internalSettings.debugPrints()) notifyChat(msg);
    }
}