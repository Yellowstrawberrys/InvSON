package xyz.yellowstrawberry.invson.instances;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import xyz.yellowstrawberry.invson.Frame;
import xyz.yellowstrawberry.invson.component.IComponent;
import xyz.yellowstrawberry.invson.component.utils.FormattableComponent;

import java.util.HashMap;
import java.util.Map;

public class InventoryInstance {
    private final long id;
    private final Frame frame;
    private final Inventory inventory;
    private final IComponent[] components;

    public InventoryInstance(Frame frame, IFG... ifgs) {
        this.id = InstanceGenerator.generateIdentifier();
        this.frame = frame;
        InventoryInstanceHolder h = new InventoryInstanceHolder(id);
        this.inventory = Bukkit.createInventory(h, frame.getSize(), frame.getTitle());
        h.setInventory(inventory);
        this.components = frame.getComponents();
        format(ifgs);
        frame.paint(inventory);
    }

    private void format(IFG... ifgs) {
        Map<String, IComponent> formatComponents = new HashMap<>();
        for(IFG ifg : ifgs) formatComponents.put(ifg.name(), ifg.type());
        for(int i=0; i<components.length; i++) {
            if(components[i] != null) {
                if(components[i] instanceof FormattableComponent f && formatComponents.containsKey(f.getId())) components[i] = formatComponents.get(f.getId());
            }
        }
    }

    protected void onInteractEvent(InventoryInteractEvent event) {
        if(event instanceof InventoryClickEvent e1 && components[e1.getRawSlot()] != null) {
            components[e1.getRawSlot()].onInteract(event);
        }else if(event instanceof InventoryDragEvent e1) {
            for(int i : e1.getRawSlots()) {
                if(components[i] != null) components[i].onInteract(event);
            }
        }else if(event instanceof InventoryCreativeEvent e1 && components[e1.getRawSlot()] != null) {
            components[e1.getRawSlot()].onInteract(event);
        }
    }

    public void repaint() {
        frame.repaint(inventory);
    }

    public InventoryView open(HumanEntity entity) {
        return entity.openInventory(inventory);
    }

    public long getInstanceIdentifier() {
        return id;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
