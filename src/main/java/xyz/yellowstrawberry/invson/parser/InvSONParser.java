package xyz.yellowstrawberry.invson.parser;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.yellowstrawberry.invson.Frame;
import xyz.yellowstrawberry.invson.component.*;
import xyz.yellowstrawberry.invson.component.utils.FormattableComponent;
import xyz.yellowstrawberry.invson.component.utils.IntractableComponent;
import xyz.yellowstrawberry.invson.component.utils.ItemStackComponent;

import java.util.*;

public class InvSONParser {
    private static final MiniMessage mini = MiniMessage.miniMessage();
    private static final Map<String, ComponentBuilder<?>> components = new HashMap<>(){{
        put("IntractableComponent", (ComponentBuilder<IntractableComponent>) IntractableComponent::of);
    }};

    public static Frame parseFrame(String s) {
        JSONObject o = new JSONObject(s);
        Frame f = new Frame(o.getString("id"), Component.text(o.getString("title")), o.getInt("size"));
        for(Object c : o.getJSONArray("contents")) {
            JSONObject content = (JSONObject) c;
            ContentType type = ContentType.valueOf(content.getString("type").toUpperCase());
            int xy = getXYFromContent(content);
            switch (type) {
                case ITEMSTACK -> f.setComponent(xy, ItemStackComponent.of(content.has("id")?content.getString("id"):null, buildItemStackFromContent(content)));
                case FORMATTABLE -> f.setComponent(xy, FormattableComponent.of(content.getString("id")));
                case COMPONENT -> {
                    if(content.has("class")) {
                        if(!components.containsKey(content.getString("class"))) throw new IllegalArgumentException("Cannot find component class named '%s'.".formatted(content.getString("class")));
                        f.setComponent(xy, components.get(content.getString("class")).loadComponent(
                                content.has("id")?content.getString("id"):null,
                                content.has("material")?buildItemStackFromContent(content):null,
                                content.getJSONObject("arguments")
                                )
                        );
                    }else throw new IllegalArgumentException("Argument 'class' is not found");
                }
                default -> throw new IllegalArgumentException("Cannot find type of `%s`.".formatted(content.getString("type").toUpperCase()));
            }
        }
        return f;
    }

    private static ItemStack buildItemStackFromContent(JSONObject o) {
        if(!o.has("material")) throw new MissingFormatArgumentException("Argument 'material' is not found");
        Material m = Material.getMaterial(o.getString("material").replaceAll("minecraft:", "").toUpperCase());
        int amount = o.has("amount")?o.getInt("amount"):1;
        if(m==null) throw new MissingFormatArgumentException("Argument for 'material' is not valid. (input: %s)".formatted(o.getString("material")));
        ItemStack itemStack = new ItemStack(m, amount);
        ItemMeta meta = itemStack.getItemMeta();
        if(o.has("name")) meta.displayName(mini.deserialize(o.getString("name")));
        if(o.has("lore")) meta.lore(deserializeLore(o.getJSONArray("lore")));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private static List<Component> deserializeLore(JSONArray a) {
        List<Component> list = new ArrayList<>();
        for(Object o : a) {
            String s = (String) o;
            list.add(mini.deserialize(s));
        }
        return list;
    }


    private static int getXYFromContent(JSONObject o) {
        if(o.has("slot")) return o.getInt("slot");
        else if(o.has("x") && o.has("y")) return o.getInt("x")+(o.getInt("y")*9);
        else throw new MissingFormatArgumentException("Argument 'slot' or 'x', 'y' are not found.");
    }
}
