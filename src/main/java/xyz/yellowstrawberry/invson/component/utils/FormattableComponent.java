package xyz.yellowstrawberry.invson.component.utils;

import org.bukkit.inventory.ItemStack;
import xyz.yellowstrawberry.invson.component.IComponent;

public class FormattableComponent implements IComponent {
    private final String id;
    private FormattableComponent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public ItemStack paint() {
        return null;
    }

    @Override
    public String toString() {
        return "FormattableComponent{" +
                "id='" + id + '\'' +
                '}';
    }

    public static FormattableComponent of(String id) {
        return new FormattableComponent(id);
    }
}
