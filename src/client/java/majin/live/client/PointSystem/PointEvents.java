package majin.live.client.PointSystem;

import majin.live.client.Utils;
import majin.live.client.events.ShouldAddPoints;

import java.util.Map;
import java.util.function.Consumer;

public class PointEvents {

    public static void init() {
        ShouldAddPoints.EVENT.register(Utils::addPoints);
    }

    public static final Map<String, Integer> PointValues = Map.ofEntries(
            Map.entry("RaidChallengeComplete", 10)
    );
}
