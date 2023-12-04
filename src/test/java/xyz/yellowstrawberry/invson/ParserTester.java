package xyz.yellowstrawberry.invson;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import org.bukkit.inventory.Inventory;
import xyz.yellowstrawberry.invson.instances.InstanceGenerator;
import xyz.yellowstrawberry.invson.instances.InventoryInstance;
import xyz.yellowstrawberry.invson.parser.InvSONParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class ParserTester {
    private static ServerMock server;
    public static void main(String[] args) throws IOException {
        server = MockBukkit.mock();
        InventoryInstance instance = InstanceGenerator.createInstance(InvSONParser.parseFrame(new String(ParserTester.class.getResourceAsStream("/frame.json").readAllBytes(), StandardCharsets.UTF_8)));
        PlayerMock p = new PlayerMock(server, "yellowstrawberry");
        instance.open(p);
        p.simulateInventoryClick(5);
    }
}
