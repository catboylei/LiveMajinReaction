package majin.live.client.mixin;

import majin.live.client.events.RaidChallengeCompleted;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.network.ClientPlayNetworkHandler;

// handler for clientside messages sent from the game/server (not players) (probably)
@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Inject(method = "onGameMessage", at = @At("HEAD"))
    private void onGameMessage(GameMessageS2CPacket packet, CallbackInfo ci) {
        String content = packet.content().getString();

        // call chat events down here

        // this is honestly really bad pls find a better match for it
        // genuinely its the only consistent way to detect challenge end, i guess u could make it into a stricter regex tho
        if (content.contains("Challenge Completed")) {
            RaidChallengeCompleted.EVENT.invoker().onChatMessage();
        }
    }
}