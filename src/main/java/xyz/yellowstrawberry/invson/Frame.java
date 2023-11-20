package xyz.yellowstrawberry.invson;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.Inventory;
import xyz.yellowstrawberry.invson.component.IComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Frame {
    private final String id;
    private final Component title;
    private final int size;
    private final IComponent[] components;
    private final List<Integer> requiresRepaint = new ArrayList<>();
    private final List<Pane> panes = new ArrayList<>();

    public Frame(String id, Component title, int size) {
        this.id = id;
        this.title = title;
        this.size = size;
        this.components = new IComponent[size];
    }

    public String getId() {
        return id;
    }

    public Component getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public void setComponent(int x, int y, IComponent comp) {
        setComponent(x+(y*9), comp);
    }

    public void setComponent(int xy, IComponent comp) {
        if(components[xy]!=null&&comp.requiresRepaint()) requiresRepaint.remove(requiresRepaint.indexOf(xy));
        if(comp!=null&&comp.requiresRepaint()) requiresRepaint.add(xy);
        components[xy] = comp;
    }

    public IComponent getComponent(int x, int y) {
        return getComponent(x+(y*9));
    }

    public IComponent getComponent(int xy) {
        return components[xy];
    }

    public void addPane(Pane pane) {
        panes.add(pane);
    }

    public void paint(Inventory inv) {
        inv.clear();

        for (int i=0; i<size; i++) {
            if(components[i]==null)continue;
            inv.setItem(i, components[i].paint());
        }
    }

    public void repaint(Inventory inv) {
        for (int i : requiresRepaint) {
            if(components[i]==null) continue;
            inv.setItem(i, components[i].paint());
        }
    }

    @Override
    public String toString() {
        return "Frame{" +
                "id='" + id + '\'' +
                ", title=" + title +
                ", size=" + size +
                ", components=" + Arrays.toString(components) +
                ", requiresRepaint=" + requiresRepaint +
                ", panes=" + panes +
                '}';
    }
}
