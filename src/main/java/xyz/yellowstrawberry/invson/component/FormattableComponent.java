package xyz.yellowstrawberry.invson.component;

import org.bukkit.inventory.ItemStack;

public class FormattableComponent implements IComponent {
    private final String id;
    private FormattableComponent(String id) {
        this.id = id;
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
