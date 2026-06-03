package majin.live.client.events;

import majin.live.client.Utils;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@FunctionalInterface
public interface RaidChallengeCompleted {
    void onChatMessage();

    Event<RaidChallengeCompleted> EVENT = EventFactory.createArrayBacked(
        RaidChallengeCompleted.class,
        listeners -> () -> {
            Utils.debugPrint("Invoked event: RaidChallengeCompleted");
            for (RaidChallengeCompleted listener : listeners) {listener.onChatMessage();}
        }
    );
}