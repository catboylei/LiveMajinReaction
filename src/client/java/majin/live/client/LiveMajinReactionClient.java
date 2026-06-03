package majin.live.client;

import majin.live.client.PointSystem.PointDecay;
import majin.live.client.PointSystem.PointEvents;
import majin.live.client.config.LiveMajinReactionConfigGenerated;
import majin.live.client.events.ShouldAddPoints;
import net.fabricmc.api.ClientModInitializer;

public class LiveMajinReactionClient implements ClientModInitializer {

	public static final LiveMajinReactionConfigGenerated CONFIG = LiveMajinReactionConfigGenerated.createAndLoad();

	@Override
	public void onInitializeClient() {

		// reduces majinpoints by provided rate every second
		PointDecay.init();

		// register point add event
		PointEvents.init();

		Utils.registerCommand("test", ctx -> {
			ShouldAddPoints.EVENT.invoker().invoke(20);
		});
	}
}