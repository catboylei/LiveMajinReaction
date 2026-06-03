package majin.live.client;

import majin.live.client.PointSystem.PointDecay;
import majin.live.client.config.LiveMajinReactionConfigGenerated;
import net.fabricmc.api.ClientModInitializer;

public class LiveMajinReactionClient implements ClientModInitializer {

	public static final LiveMajinReactionConfigGenerated CONFIG = LiveMajinReactionConfigGenerated.createAndLoad();

	@Override
	public void onInitializeClient() {

		// reduces majinpoints by provided rate every second
		PointDecay.init();

		Utils.registerCommand("test", ctx -> Utils.notifyChat("hai :3"));
	}
}