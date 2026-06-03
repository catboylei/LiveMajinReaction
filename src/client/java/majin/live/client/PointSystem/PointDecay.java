package majin.live.client.PointSystem;

import majin.live.client.LiveMajinReactionClient;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class PointDecay {
    private static long lastDecayTick = 0;

    public static void init(int rate) {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            long now = System.currentTimeMillis();

            // run every 1000ms
            if (now - lastDecayTick >= 1000) {
                lastDecayTick = now;

                handleDecay(rate);
            }
        });
    }

    private static void handleDecay(int rate) {
        int current = LiveMajinReactionClient.CONFIG.majinPoints();
        if (current == 0) return;

        if (current > 0) {
            LiveMajinReactionClient.CONFIG.majinPoints(Math.max(0, current - rate));
        } else {
            LiveMajinReactionClient.CONFIG.majinPoints(Math.max(0, current + rate));
        }
    }
}
