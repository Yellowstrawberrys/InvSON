package xyz.yellowstrawberry.invson;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import xyz.yellowstrawberry.invson.parser.InvSONParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ParserTester {
    private static ServerMock server;
    public static void main(String[] args) throws IOException {
        server = MockBukkit.mock();
        System.out.println(InvSONParser.parseFrame(new String(ParserTester.class.getResourceAsStream("/frame.json").readAllBytes(), StandardCharsets.UTF_8)));
    }
}
