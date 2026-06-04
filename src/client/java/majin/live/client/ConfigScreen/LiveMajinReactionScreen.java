package majin.live.client.ConfigScreen;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.UIComponents;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.ScrollContainer;
import io.wispforest.owo.ui.container.UIContainers;
import io.wispforest.owo.ui.core.Sizing;
import io.wispforest.owo.ui.util.NinePatchTexture;
import majin.live.client.LiveMajinReactionClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Map;

public class LiveMajinReactionScreen extends BaseUIModelScreen<FlowLayout> {

    public LiveMajinReactionScreen() {
        super(FlowLayout.class, DataSource.asset(Identifier.of("live-majin-reaction", "ui-model")));
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        applyCustomTextures(rootComponent);
        bindCategoryButtons(rootComponent);
        rebuildSettingsContainer(rootComponent);
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
        rootComponent.childById(ButtonComponent.class, "options-button").onPress(button -> {
            LiveMajinReactionClient.CONFIG.internalSettings.openCategory(button.id());
            rebuildSettingsContainer(rootComponent);
        });
        rootComponent.childById(ButtonComponent.class, "other-button").onPress(button -> {
            LiveMajinReactionClient.CONFIG.internalSettings.openCategory(button.id());
            rebuildSettingsContainer(rootComponent);
        });
        rootComponent.childById(ButtonComponent.class, "info-button").onPress(button -> {
            LiveMajinReactionClient.CONFIG.internalSettings.openCategory(button.id());
            rebuildSettingsContainer(rootComponent);
        });
    }

    private void rebuildSettingsContainer(FlowLayout rootComponent) {
        FlowLayout container = rootComponent.childById(FlowLayout.class, "settings-container");
        container.clearChildren();

        if (LiveMajinReactionClient.CONFIG.internalSettings.openCategory().equals("options-button")) {
            container.child(scrollable());

            for (SettingEntry entry : LiveMajinReactionSettingEntries.entries) {
                rootComponent.childById(FlowLayout.class,"putsettingsherethanks").child(makeSettingEntry(entry));
            }
        }
    }

    private Identifier getButtonTexture(ButtonComponent button) {
        boolean enabled = LiveMajinReactionClient.CONFIG.internalSettings.openCategory().equals(button.id());
        return (enabled) ? LiveMajinReactionTextures.activeButton : LiveMajinReactionTextures.inactiveButton;
    }

    private Identifier getInfoButtonTexture(ButtonComponent button) {
        boolean enabled = LiveMajinReactionClient.CONFIG.internalSettings.openCategory().equals(button.id());
        return (enabled) ? LiveMajinReactionTextures.activeInfo : LiveMajinReactionTextures.inactiveInfo;
    }

    private FlowLayout makeSettingEntry(SettingEntry entry) {
        return (FlowLayout) this.model.expandTemplate(
                FlowLayout.class,
                "setting-entry@live-majin-reaction:ui-model",
                Map.of("name", entry.title(), "configId", entry.configId(), "desc", entry.desc())
        ).surface((ctx, component) ->
                NinePatchTexture.draw(LiveMajinReactionTextures.activeButton, ctx, component)
        );
    }

    private ScrollContainer<FlowLayout> scrollable() {
        FlowLayout container = UIContainers.verticalFlow(Sizing.content(), Sizing.content());
        container.id("putsettingsherethanks");
        container.gap(6);

        return UIContainers.verticalScroll(Sizing.content(), Sizing.fill(100), container)
                .scrollbarThiccness(0);
    }
}
