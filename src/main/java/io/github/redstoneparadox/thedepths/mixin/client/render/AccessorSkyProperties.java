package io.github.redstoneparadox.thedepths.mixin.client.render;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.client.render.SkyProperties;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SkyProperties.class)
public interface AccessorSkyProperties {

    @Accessor(value = "BY_DIMENSION_TYPE")
    static Object2ObjectMap<RegistryKey<DimensionType>, SkyProperties> getByDimensionType() {
        return null;
    }
}
