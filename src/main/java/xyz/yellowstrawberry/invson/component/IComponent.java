package xyz.yellowstrawberry.invson.component;

import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface IComponent {
    default RepaintBehavior getRepaintBehavior() { return RepaintBehavior.NONE; }
    ItemStack paint();
    default void onInteract(InventoryInteractEvent event) {event.setCancelled(true);}
}
