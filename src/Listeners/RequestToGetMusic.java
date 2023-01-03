package Listeners;

import Model.Request;

import java.io.IOException;

public interface RequestToGetMusic
{
    void send(Request request) throws IOException;
}
