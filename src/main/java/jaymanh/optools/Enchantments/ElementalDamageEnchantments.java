package jaymanh.optools.Enchantments;

import jaymanh.optools.OpTools;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class ElementalDamageEnchantments {

    private static void createFireRadius(BlockPos center, Level world, int radius) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos posToCheck = center.offset(x, y, z);
                    if (x * x + y * y + z * z <= radius * radius) {
                        if (world.isEmptyBlock(posToCheck) || world.getBlockState(posToCheck).getBlock() == Blocks.TALL_GRASS || world.getBlockState(posToCheck).getBlock() == Blocks.SHORT_GRASS) {
                            world.setBlockAndUpdate(posToCheck, Blocks.FIRE.defaultBlockState());
                        }
                    }
                }
            }
        }
    }

    public static void initialise() {
        OpTools.register(OpTools.hook("elemental_fire"), (world, level, context, user, pos) -> {
            createFireRadius(user.blockPosition(), world, level);
        });

        OpTools.register(OpTools.hook("elemental_water"), (world, level, context, user, pos) -> {
            user.setDeltaMovement(user.getDeltaMovement().x, 0.02, user.getDeltaMovement().z);
            EnchantTick.suspendGravity(user);
            world.sendParticles(ParticleTypes.BUBBLE, user.getX(), user.getY(), user.getZ(), 100, 0.5, 1, 0.5, 10);
        });

        OpTools.register(OpTools.hook("elemental_earth"), (world, level, context, user, pos) -> {
            burySafely(world, user, level);
            if (user instanceof Mob mob) {
                mob.getNavigation().stop();
            }
        });

        OpTools.register(OpTools.hook("elemental_lightning"), (world, level, context, user, pos) -> {
            LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
            lightning.setPos(user.getX(), user.getY(), user.getZ());
            lightning.setVisualOnly(false);
            world.addFreshEntity(lightning);
        });

        OpTools.register(OpTools.hook("elemental_ice"), (world, level, context, user, pos) -> {
            if (user instanceof LivingEntity mob) {
                mob.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 100, level));
            }
        });
    }

    private static void burySafely(Level world, Entity user, int level) {
        user.setDeltaMovement(Vec3.ZERO);
        double distance = 0.8 * Math.max(level, 0);
        if (distance <= 0) {
            return;
        }
        Vec3 allowed = Entity.collideBoundingBox(user, new Vec3(0, -distance, 0), user.getBoundingBox(), world, List.<VoxelShape>of());
        if (allowed.y != 0.0) {
            user.teleportTo(user.getX() + allowed.x, user.getY() + allowed.y, user.getZ() + allowed.z);
        }
    }
}
