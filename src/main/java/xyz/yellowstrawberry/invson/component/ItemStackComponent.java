package xyz.yellowstrawberry.invson.component;

import org.bukkit.inventory.ItemStack;

public class ItemStackComponent implements IComponent {

    private final ItemStack i;
    private ItemStackComponent(ItemStack i) {
        this.i = i;
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

    public static ItemStackComponent of(ItemStack i) {
        return new ItemStackComponent(i);
    }
}
