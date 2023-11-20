package xyz.yellowstrawberry.invson.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {
    public static ItemStack itemWithName(Component name, Material material, int size, List<? extends Component> lore) {
        ItemStack itm = new ItemStack(material, size);
        ItemMeta meta = itm.getItemMeta();
        if(name != null) meta.displayName(name);
        if(lore != null) meta.lore(lore);
        itm.setItemMeta(meta);
        return itm;
    }

    public static ItemStack itemWithName(Component name, Material material, int size, Component lore) {
        List<Component> comp = new ArrayList<>();
        for(String s : PlainTextComponentSerializer.plainText().serialize(lore).split("\n")) {
            comp.add(Component.text(s).mergeStyle(lore));
        }
        return itemWithName(name, material, size, comp);
    }

    public static ItemStack skullItem(Component name, String playerName, int size, List<? extends Component> lore) {
        ItemStack itm = new ItemStack(Material.PLAYER_HEAD, size);
        SkullMeta meta = (SkullMeta) itm.getItemMeta();
        if(name != null) meta.displayName(name);
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
        if(lore != null) meta.lore(lore);
        itm.setItemMeta(meta);
        return itm;
    }

    public static int getAmountOfMaterial(Inventory inv, Material type) {
        int i =0;
        for(ItemStack s : inv.getContents()) {
            if(s!=null&&s.getType()==type)i+=s.getAmount();
        }
        return i;
    }
}
