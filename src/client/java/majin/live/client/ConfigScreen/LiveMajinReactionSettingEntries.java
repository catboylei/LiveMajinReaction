package majin.live.client.ConfigScreen;

import java.util.List;

public class LiveMajinReactionSettingEntries {
    public static final List<SettingEntry> entries = List.of(
            new SettingEntry("Live Majin Reaction", "this handsome gentleman ->", "reactionEnabled"),
            new SettingEntry("im a kitty btw", "", "exampleBool"),
            new SettingEntry("Enable Debug Prints", "dump debug prints to chat", "debugPrints")
    );
}