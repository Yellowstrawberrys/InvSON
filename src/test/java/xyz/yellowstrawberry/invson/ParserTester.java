package xyz.yellowstrawberry.invson;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.yellowstrawberry.invson.instances.IFG;
import xyz.yellowstrawberry.invson.instances.InventoryInstance;
import xyz.yellowstrawberry.invson.parser.InvSONParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class ParserTester {
    private ServerMock server;
    private InvSON plugin;

    @BeforeEach
    public void setUp()
    {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(InvSON.class);
    }

    @Test
    public void test() {
        try {
            Frame f = InvSONParser.parseFrame(new String(ParserTester.class.getResourceAsStream("/frame.json").readAllBytes(), StandardCharsets.UTF_8));
            System.out.println(f.toString());
            InventoryInstance instance = InventoryInstance.newInstance(f);
            PlayerMock playerMock = new PlayerMock(server, "Jungeunsoo");
            instance.open(playerMock);
            playerMock.simulateInventoryClick(8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void tearDown()
    {
        MockBukkit.unmock();
    }
}
