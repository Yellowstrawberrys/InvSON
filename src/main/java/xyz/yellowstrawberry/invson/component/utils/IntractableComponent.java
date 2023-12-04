package xyz.yellowstrawberry.invson.component.utils;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.json.JSONObject;
import xyz.yellowstrawberry.invson.component.IComponent;
import xyz.yellowstrawberry.invson.component.RepaintBehavior;

import java.util.function.Consumer;

public class IntractableComponent implements IComponent {

    private final String id;
    private ItemStack display;
    private RepaintBehavior requiresRepaint = RepaintBehavior.NONE;

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
    }

    @Override
    public ItemStack paint() {
        return display;
    }

    @Override
    public void onInteract(InventoryInteractEvent event) {
        System.out.println("Click!");
        IComponent.super.onInteract(event);
    }

    @Override
    public RepaintBehavior getRepaintBehavior() {
        return requiresRepaint;
    }

    public static IntractableComponent of(String id, ItemStack itm, JSONObject arguments) {
        return null;
    }
}
