package majin.live.client.PointSystem;

import majin.live.client.Utils;
import majin.live.client.events.RaidChallengeCompleted;
import net.fabricmc.fabric.api.event.Event;

import java.util.function.Consumer;

public class PointEvents {

    public static void init() {
        // bind all point events to their value here
        bind(RaidChallengeCompleted.EVENT, 10, action -> action::run);
    }

    // yeah this is miserable and the closest i can get to a somewhat convenient bind
    private static <T> void bind(Event<T> event, int points, ListenerFactory<T> factory) {
        event.register(factory.create(() -> Utils.addPoints(points)));
    }
    @FunctionalInterface
    private interface ListenerFactory<T> { T create(Runnable action);}
}
