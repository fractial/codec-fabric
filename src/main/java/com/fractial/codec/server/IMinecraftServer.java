package com.fractial.codec.server;

import net.minecraft.server.ReloadableServerResources;

@SuppressWarnings("unused")
public interface IMinecraftServer {
    ReloadableServerResources codec$getResources();
}
