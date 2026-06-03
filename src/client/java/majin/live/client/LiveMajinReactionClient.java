package majin.live.client;

import majin.live.client.config.LiveMajinReactionConfigGenerated;
import net.fabricmc.api.ClientModInitializer;

public class LiveMajinReactionClient implements ClientModInitializer {

	public static final LiveMajinReactionConfigGenerated CONFIG = LiveMajinReactionConfigGenerated.createAndLoad();

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}