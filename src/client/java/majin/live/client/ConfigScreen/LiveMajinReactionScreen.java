package majin.live.client.ConfigScreen;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.util.NinePatchTexture;
import net.minecraft.util.Identifier;

public class LiveMajinReactionScreen extends BaseUIModelScreen<FlowLayout> {

    public LiveMajinReactionScreen() {
        super(FlowLayout.class, DataSource.asset(Identifier.of("live-majin-reaction", "ui-model")));
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.childById(FlowLayout.class, "window").surface((ctx, component) ->
                NinePatchTexture.draw(LiveMajinReactionTextures.windowTexture, ctx, component)
        );
    }
}

