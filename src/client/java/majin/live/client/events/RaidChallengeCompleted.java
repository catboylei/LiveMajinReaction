package majin.live.client.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@FunctionalInterface
public interface RaidChallengeCompleted {

    void onChatMessage();

    Event<RaidChallengeCompleted> EVENT = EventFactory.createArrayBacked(
        RaidChallengeCompleted.class,
        listeners -> () -> {
            for (RaidChallengeCompleted listener : listeners) {listener.onChatMessage();}
        }
    );
}