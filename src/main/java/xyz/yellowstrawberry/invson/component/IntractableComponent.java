package xyz.yellowstrawberry.invson.component;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.json.JSONObject;

import java.util.function.Consumer;

public class IntractableComponent implements IComponent {

    private final String id;
    private ItemStack display;
    private boolean requiresRepaint = false;

    private Consumer<InventoryClickEvent> onClick;
    private Consumer<InventoryDragEvent> onDrag;
    private Consumer<InventoryInteractEvent> onInteract;

    private IntractableComponent(String id, ItemStack itm, Consumer<InventoryClickEvent> onClick, Consumer<InventoryDragEvent> onDrag, Consumer<InventoryInteractEvent> onInteract) {
        this.id = id;
        this.display = itm;

        this.onClick = onClick;
        this.onDrag = onDrag;
        this.onInteract = onInteract;
    }

    public String getId() {
        return id;
    }

    public void setDisplay(ItemStack display) {
        this.display = display;
        requiresRepaint = true;
    }

    @Override
    public ItemStack paint() {
        requiresRepaint = false;
        return display;
    }

    @Override
    public void onInteract(InventoryInteractEvent event) {
        IComponent.super.onInteract(event);
    }

    @Override
    public boolean requiresRepaint() {
        return requiresRepaint;
    }

    public static IntractableComponent of(String id, ItemStack itm, JSONObject arguments) {
        return null;
    }
}
