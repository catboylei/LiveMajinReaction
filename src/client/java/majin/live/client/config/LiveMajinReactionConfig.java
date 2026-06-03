package majin.live.client.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;

@Modmenu(modId = "live-majin-reaction") // this automatically makes a (bad) config screen from this
@Config(name = "live-majin-reaction", wrapperName = "LiveMajinReactionConfigGenerated")
public class LiveMajinReactionConfig {

    // add fields to the config here, datagen handles them automatically
    public int pointDecay = 1;
    public int majinPoints = 0;
}