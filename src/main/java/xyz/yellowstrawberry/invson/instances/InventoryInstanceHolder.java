package xyz.yellowstrawberry.invson.instances;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class InventoryInstanceHolder implements InventoryHolder {
    private final long id;
    private Inventory inventory;

    protected InventoryInstanceHolder(long id) {
        this.id = id;
    }

    protected void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public long getId() {
        return id;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
