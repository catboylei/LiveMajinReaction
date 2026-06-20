package majin.live.client.ConfigScreen;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.UIComponents;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.ScrollContainer;
import io.wispforest.owo.ui.container.UIContainers;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.util.NinePatchTexture;
import majin.live.client.LiveMajinReactionClient;
import majin.live.client.Utils;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    // this is called after build, once all the sizes are calculated
    @Override
    protected void init() {
        super.init();

        //initPeacockDecoration(this.uiAdapter.rootComponent);
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
            container.child(scrollable()); // creates setting container

            for (SettingEntry entry : LiveMajinReactionSettingEntries.entries) {
                rootComponent.childById(FlowLayout.class,"putsettingsherethanks").child(makeSettingEntry(entry)); // appends to container above
            }
        }
        if (LiveMajinReactionClient.CONFIG.internalSettings.openCategory().equals("info-button")) {
            container.child(scrollable());

            for (SettingEntry entry : LiveMajinReactionSettingEntries.infoEntries) {
                rootComponent.childById(FlowLayout.class,"putsettingsherethanks").child(makeInfoEntry(entry.title(), entry.desc()));
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
        ParentUIComponent entryContainer = this.model.expandTemplate(
                FlowLayout.class,
                "setting-entry@live-majin-reaction:ui-model",
                Map.of("name", entry.title(), "configId", entry.configId(), "desc", entry.desc())
        ).surface((ctx, component) ->
                NinePatchTexture.draw(LiveMajinReactionTextures.activeButton, ctx, component)
        );

        ButtonComponent configButton = entryContainer.childById(ButtonComponent.class, entry.configId());

        // hook the setting entry button to its configId
        try {
            Method getter = LiveMajinReactionClient.CONFIG.getClass().getMethod(entry.configId());
            Method setter = LiveMajinReactionClient.CONFIG.getClass().getMethod(entry.configId(), boolean.class);

            configButton.renderer((matrices, button, meow) ->
                    NinePatchTexture.draw((getSwitchValue(getter)) ? LiveMajinReactionTextures.activeSwitch : LiveMajinReactionTextures.inactiveSwitch, matrices, button.x(), button.y(), button.width(), button.height())
            );
            configButton.onPress(button -> {
                boolean value = !getSwitchValue(getter);
                setSwitchValue(setter, value);
                Utils.debugPrint("set switch " + entry.configId() + " to " + value);
            });
        } catch(Exception e) {
            Utils.debugPrint("config id: " + entry.configId() + " returned " + e);
        }

        return (FlowLayout) entryContainer;
    }

    private ScrollContainer<FlowLayout> scrollable() {
        FlowLayout container = UIContainers.verticalFlow(Sizing.content(), Sizing.content());
        container.id("putsettingsherethanks");
        container.gap(6);

        return UIContainers.verticalScroll(Sizing.content(), Sizing.fill(100), container)
                .scrollbarThiccness(0);
    }

    private boolean getSwitchValue(Method getter) {
        try {
            return ((boolean) (getter.invoke(LiveMajinReactionClient.CONFIG)));
        } catch (InvocationTargetException | IllegalAccessException e) {
            Utils.debugPrint("failed to get switch value");
        }
        return false;
    }

    private void setSwitchValue(Method setter, boolean value) {
        try {
            setter.invoke(LiveMajinReactionClient.CONFIG, value);
        } catch (InvocationTargetException | IllegalAccessException e) {
            Utils.debugPrint("failed to get switch value");
        }
    }

    private void initPeacockDecoration(FlowLayout root) {
        FlowLayout window = root.childById(FlowLayout.class, "window");
        FlowLayout peacock = UIContainers.verticalFlow(Sizing.fixed(230), Sizing.fixed(190));

        root.removeChild(root.childById(FlowLayout.class, "peacock"));

        peacock.surface((ctx, component) ->
                NinePatchTexture.draw(LiveMajinReactionTextures.peacock, ctx, component)
        );
        peacock.positioning(Positioning.absolute(window.x() + 52, window.y() - 187)).id("peacock");

        Utils.debugPrint("rendering peacock at " + window.x() + " " + window.y());
        root.child(peacock);
    }

    private FlowLayout makeInfoEntry(String title, String desc) {
        FlowLayout entry =  UIContainers.verticalFlow(Sizing.fill(100), Sizing.content());
        entry.child(UIComponents.label(Text.literal(title))).child(UIComponents.label(Text.literal(desc)).color(Color.ofArgb(0xFFBCBCBC))).gap(6);
        entry.padding(Insets.of(6)).surface((ctx, component) ->
                NinePatchTexture.draw(LiveMajinReactionTextures.activeButton, ctx, component)
        );

        return entry;
    }
}