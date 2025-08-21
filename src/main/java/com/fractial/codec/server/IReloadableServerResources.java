package com.fractial.codec.server;

import com.fractial.codec.world.item.ItemStackManager;

@SuppressWarnings("unused")
public interface IReloadableServerResources {
    ItemStackManager codec$getItemStackManager();
}
