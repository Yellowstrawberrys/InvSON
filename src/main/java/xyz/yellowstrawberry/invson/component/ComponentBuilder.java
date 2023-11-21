package xyz.yellowstrawberry.invson.component;

import org.bukkit.inventory.ItemStack;
import org.json.JSONObject;

public interface ComponentBuilder<V extends IComponent> {
    V loadComponent(String id, ItemStack itm, JSONObject arguments);
}
