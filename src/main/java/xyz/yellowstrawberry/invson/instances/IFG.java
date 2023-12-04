package xyz.yellowstrawberry.invson.instances;

import xyz.yellowstrawberry.invson.component.IComponent;

public record IFG(String name, IComponent type) {
    public static IFG of(String name, IComponent type) {
        return new IFG(name, type);
    }
}
