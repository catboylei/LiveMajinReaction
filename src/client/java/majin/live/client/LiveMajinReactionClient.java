package majin.live.client;

import majin.live.client.PointSystem.PointDecay;
import majin.live.client.config.LiveMajinReactionConfigGenerated;
import net.fabricmc.api.ClientModInitializer;

public class LiveMajinReactionClient implements ClientModInitializer {

	// config accessor (dont touch that)
	// lets u access config slop through LiveMajinReactionClient.CONFIG.youroption()
	public static final LiveMajinReactionConfigGenerated CONFIG = LiveMajinReactionConfigGenerated.createAndLoad();

	@Override
	public void onInitializeClient() {

		// reduces majinpoints by provided rate every second
		PointDecay.init();

		// comment this out to yk obviously yeah
		DebugUtils.enable();
	}
}