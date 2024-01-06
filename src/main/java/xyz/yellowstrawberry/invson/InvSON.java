package xyz.yellowstrawberry.invson;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import xyz.yellowstrawberry.invson.instances.EventListener;
import xyz.yellowstrawberry.invson.instances.InventoryInstance;
import xyz.yellowstrawberry.invson.parser.InvSONParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class InvSON extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equals("test")) {
            try {
                InventoryInstance i = InventoryInstance.newInstance(InvSONParser.parseFrame(new String(InvSON.class.getResourceAsStream("/example_frame.json").readAllBytes(), StandardCharsets.UTF_8)));
                i.open((HumanEntity) sender);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return super.onCommand(sender, command, label, args);
    }
}
