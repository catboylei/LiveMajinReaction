package majin.live.client.events;

import majin.live.client.Utils;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@FunctionalInterface
public interface ShouldAddPoints {
    void invoke(int points);

    // event can be registered on maybe for later use ? you can like refresh some ui on it on something idk
    Event<ShouldAddPoints> EVENT = EventFactory.createArrayBacked(
            ShouldAddPoints.class,
            listeners -> (points) -> {
                Utils.debugPrint("Invoked ShouldAddPoints with " + points + " points.");
                Utils.addPoints(points); // directly interacts with point counter when invoked
                for (ShouldAddPoints listener : listeners) {listener.invoke(points);}
            }
    );
}