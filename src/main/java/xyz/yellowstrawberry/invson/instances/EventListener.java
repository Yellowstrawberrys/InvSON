package xyz.yellowstrawberry.invson.instances;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class EventListener implements Listener {

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event) {
        System.out.println("ddd");
        if(event.getInventory().getHolder() instanceof InventoryInstanceHolder h) {
            if(InstanceGenerator.inventoryInstances.containsKey(h.getId())) {
                InstanceGenerator.inventoryInstances.get(h.getId()).onInteractEvent(event);
                event.setCancelled(true);
            }else event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.CANT_USE);
        }
    }

    @EventHandler
    public void onInventoryInteract(InventoryDragEvent event) {
        System.out.println("ddd");
        if(event.getInventory().getHolder() instanceof InventoryInstanceHolder h) {
            if(InstanceGenerator.inventoryInstances.containsKey(h.getId())) {
                InstanceGenerator.inventoryInstances.get(h.getId()).onInteractEvent(event);
                event.setCancelled(true);
            }else event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.CANT_USE);
        }
    }
}
