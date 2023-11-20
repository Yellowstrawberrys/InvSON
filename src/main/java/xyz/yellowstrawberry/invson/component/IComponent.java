package xyz.yellowstrawberry.invson.component;

import org.bukkit.inventory.ItemStack;

public interface IComponent {
    default boolean requiresRepaint() { return false; }
    ItemStack paint();
}
