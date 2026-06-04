package majin.live.client.ConfigScreen;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.util.NinePatchTexture;
import majin.live.client.LiveMajinReactionClient;
import net.minecraft.util.Identifier;

public class LiveMajinReactionScreen extends BaseUIModelScreen<FlowLayout> {

    public LiveMajinReactionScreen() {
        super(FlowLayout.class, DataSource.asset(Identifier.of("live-majin-reaction", "ui-model")));
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        applyCustomTextures(rootComponent);
        bindCategoryButtons(rootComponent);
    }

    private void applyCustomTextures(FlowLayout rootComponent) {
        rootComponent.childById(FlowLayout.class, "window").surface((ctx, component) ->
                NinePatchTexture.draw(LiveMajinReactionTextures.window, ctx, component)
        );
        rootComponent.childById(FlowLayout.class, "separator").surface((ctx, component) ->
                NinePatchTexture.draw(LiveMajinReactionTextures.separator, ctx, component)
        );
        rootComponent.childById(ButtonComponent.class, "options-button").renderer((matrices, button, meow) ->
                NinePatchTexture.draw(getButtonTexture(button), matrices, button.x(), button.y(), button.width(), button.height())
        );
        rootComponent.childById(ButtonComponent.class, "other-button").renderer((matrices, button, meow) ->
                NinePatchTexture.draw(getButtonTexture(button), matrices, button.x(), button.y(), button.width(), button.height())
        );
        rootComponent.childById(ButtonComponent.class, "info-button").renderer((matrices, button, meow) ->
                NinePatchTexture.draw(getInfoButtonTexture(button), matrices, button.x(), button.y(), button.width(), button.height())
        );
        rootComponent.childById(FlowLayout.class, "majin").surface((ctx, component) ->
                NinePatchTexture.draw(LiveMajinReactionTextures.majinNeutral, ctx, component)
        );
    }

    private void bindCategoryButtons(FlowLayout rootComponent) {
        rootComponent.childById(ButtonComponent.class, "options-button").onPress(button ->
                LiveMajinReactionClient.CONFIG.internalSettings.openCategory(button.id())
        );
        rootComponent.childById(ButtonComponent.class, "other-button").onPress(button ->
                LiveMajinReactionClient.CONFIG.internalSettings.openCategory(button.id())
        );
        rootComponent.childById(ButtonComponent.class, "info-button").onPress(button ->
                LiveMajinReactionClient.CONFIG.internalSettings.openCategory(button.id())
        );
    }

    private Identifier getButtonTexture(ButtonComponent button) {
        boolean enabled = LiveMajinReactionClient.CONFIG.internalSettings.openCategory().equals(button.id());
        return (enabled) ? LiveMajinReactionTextures.activeButton : LiveMajinReactionTextures.inactiveButton;
    }

    private Identifier getInfoButtonTexture(ButtonComponent button) {
        boolean enabled = LiveMajinReactionClient.CONFIG.internalSettings.openCategory().equals(button.id());
        return (enabled) ? LiveMajinReactionTextures.activeInfo : LiveMajinReactionTextures.inactiveInfo;
    }
}
