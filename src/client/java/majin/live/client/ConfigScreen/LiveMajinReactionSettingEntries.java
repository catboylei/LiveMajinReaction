package majin.live.client.ConfigScreen;

import java.util.List;

public class LiveMajinReactionSettingEntries {
    private static final String creditsContent = """
Majin "The Morning Star" Thalas
PuppyGirlShift
catboylei""";

    private static final String changelogContent = """
v1.0.Majin

- mod exists now i guess""";

    private static final String infoContent = """
https://github.com/catboylei/
LiveMajinReaction

Contact:
Make sure to check known issues !
then dm @catboylei on discord
""";

    public static final List<SettingEntry> entries = List.of(
            new SettingEntry("Live Majin Reaction", "this handsome gentleman ->", "reactionEnabled"),
            new SettingEntry("im a kitty btw", "", "exampleBool"),
            new SettingEntry("Enable Debug Prints", "dump debug prints to chat", "debugPrints")
    );
    public static final List<SettingEntry> infoEntries = List.of(
            new SettingEntry("INFO/CONTACT", infoContent, ""),
            new SettingEntry("CHANGELOG", changelogContent, ""),
            new SettingEntry("CREDITS", creditsContent, "")
    );
}