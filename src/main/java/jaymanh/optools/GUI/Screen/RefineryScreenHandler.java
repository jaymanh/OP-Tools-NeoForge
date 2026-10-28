package jaymanh.optools.GUI.Screen;

import jaymanh.optools.Blocks.BlockEntitys.RefineryBlockEntity;
import jaymanh.optools.Items.ModItems;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

public class RefineryScreenHandler extends AbstractContainerMenu {
    private final Container inventory;
    private final ContainerData propertyDelegate;
    @Nullable
    public final RefineryBlockEntity blockEntity;

    public RefineryScreenHandler(int syncId, Inventory playerInventory, RegistryFriendlyByteBuf buf) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(buf.readBlockPos()), new SimpleContainerData(2));
    }

    public RefineryScreenHandler(int syncId, Inventory playerInventory, @Nullable BlockEntity blockEntity, ContainerData arrayPropertyDelegate) {
        super(ModScreenHandlers.REFINERY_SCREEN_HANDLER.get(), syncId);
        RefineryBlockEntity refinery = blockEntity instanceof RefineryBlockEntity found ? found : null;
        this.blockEntity = refinery;
        this.inventory = refinery != null ? refinery : new SimpleContainer(2);
        this.propertyDelegate = arrayPropertyDelegate;
        checkContainerSize(this.inventory, 2);
        if (refinery != null) {
            this.inventory.startOpen(playerInventory.player);
        }

        this.addSlot(new Slot(inventory, 0, 80, 11) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.RAW_DARKMATTER.get());
            }
        });
        this.addSlot(new Slot(inventory, 1, 80, 59) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addDataSlots(arrayPropertyDelegate);
    }

    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int progressArrowSize = 26;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.getContainerSize()) {
                if (!this.moveItemStackTo(originalStack, this.inventory.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(originalStack, 0, 1, false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return newStack;
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.blockEntity == null) {
            return false;
        }
        return Container.stillValidBlockEntity(this.blockEntity, player);
    }
}
