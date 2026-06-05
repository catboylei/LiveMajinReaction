package majin.live.client.config;

import io.wispforest.owo.config.annotation.Config;
//import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.Nest;
import io.wispforest.owo.config.annotation.SectionHeader;

//@Modmenu(modId = "live-majin-reaction") // this automatically makes a (bad) config screen from this
@Config(name = "live-majin-reaction", wrapperName = "LiveMajinReactionConfigGenerated")
public class LiveMajinReactionConfig {
    // add fields to the config here, datagen handles them automatically

    @SectionHeader("General")
    public boolean reactionEnabled = true;
    public boolean exampleBool = false;
    public boolean debugPrints = false;

    @SectionHeader("Other")
    @Nest
    public Internal internalSettings = new Internal();

    public static class Internal {
        public int pointDecay = 1;
        public int majinPoints = 0;
        public String openCategory = "options-button";
    }
}