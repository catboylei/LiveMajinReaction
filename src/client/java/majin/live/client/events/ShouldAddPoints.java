package majin.live.client.events;

import majin.live.client.Utils;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@FunctionalInterface
public interface ShouldAddPoints {
    void invoke(int points);

    Event<ShouldAddPoints> EVENT = EventFactory.createArrayBacked(
            ShouldAddPoints.class,
            listeners -> (points) -> {
                Utils.debugPrint("Invoked ShouldAddPoints with " + points + " points.");
                for (ShouldAddPoints listener : listeners) {listener.invoke(points);}
            }
    );
}