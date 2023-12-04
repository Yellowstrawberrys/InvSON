package xyz.yellowstrawberry.invson.component.utils;

import org.bukkit.inventory.ItemStack;
import xyz.yellowstrawberry.invson.component.IComponent;

public class ItemStackComponent implements IComponent {

    private final String id;
    private final ItemStack i;
    private ItemStackComponent(String id, ItemStack i) {
        this.id = id;
        this.i = i;
    }

    public String getID() {
        return id;
    }

    @Override
    public ItemStack paint() {
        return i;
    }

    @Override
    public String toString() {
        return "ItemStackComponent{" +
                "i=" + i +
                '}';
    }

    public static ItemStackComponent of(String id, ItemStack i) {
        return new ItemStackComponent(id, i);
    }
}
