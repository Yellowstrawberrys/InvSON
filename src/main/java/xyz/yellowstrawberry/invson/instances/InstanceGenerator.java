package xyz.yellowstrawberry.invson.instances;

import com.github.f4b6a3.tsid.TsidCreator;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import xyz.yellowstrawberry.invson.Frame;

public class InstanceGenerator {
    protected static final Long2ObjectOpenHashMap<InventoryInstance> inventoryInstances = new Long2ObjectOpenHashMap<>();

    public static InventoryInstance createInstance(Frame frame, IFG... ifgs) {
        InventoryInstance instance = new InventoryInstance(frame, ifgs);
        inventoryInstances.put(instance.getInstanceIdentifier(), instance);
        return instance;
    }

    public static long generateIdentifier() {
        long id =TsidCreator.getTsid().toLong();
        if(inventoryInstances.containsKey(id)) return generateIdentifier();
        return id;
    }
}
