package majin.live.client.ConfigScreen;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
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
                NinePatchTexture.draw(LiveMajinReactionTextures.window, ctx, component)
        );
        rootComponent.childById(FlowLayout.class, "separator").surface((ctx, component) ->
                NinePatchTexture.draw(LiveMajinReactionTextures.separator, ctx, component)
        );
        rootComponent.childById(ButtonComponent.class, "options-button").renderer((matrices, button, meow) ->
                NinePatchTexture.draw(LiveMajinReactionTextures.activeButton, matrices, button.x(), button.y(), button.width(), button.height())
        );
        rootComponent.childById(ButtonComponent.class, "other-button").renderer((matrices, button, meow) ->
                NinePatchTexture.draw(LiveMajinReactionTextures.inactiveButton, matrices, button.x(), button.y(), button.width(), button.height())
        );
        rootComponent.childById(ButtonComponent.class, "info-button").renderer((matrices, button, meow) ->
                NinePatchTexture.draw(LiveMajinReactionTextures.inactiveInfo, matrices, button.x(), button.y(), button.width(), button.height())
        );
    }
}

