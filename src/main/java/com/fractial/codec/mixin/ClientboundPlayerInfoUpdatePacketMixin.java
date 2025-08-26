package com.fractial.codec.mixin;

import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(ClientboundPlayerInfoUpdatePacket.class)
public interface ClientboundPlayerInfoUpdatePacketMixin {
    @Accessor("entries")
    List<ClientboundPlayerInfoUpdatePacket.Entry> getEntries();

    @Accessor("entries")
    void setEntries(List<ClientboundPlayerInfoUpdatePacket.Entry> entries);
}
