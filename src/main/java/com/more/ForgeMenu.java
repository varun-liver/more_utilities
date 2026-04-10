package com.more;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.RegistryObject;

public class ForgeMenu extends AbstractContainerMenu {
    private final Container container;
    private final Integer[] xpos ={29,47,65,124} ;
    private final Integer[] ypos ={16,34,52,35} ;
    public ForgeMenu(int id, Inventory inv) {
        this(id, inv, null);
    }
    @Override
    public void removed(Player player) {
        super.removed(player);

        // Only run on server
        if (!player.level().isClientSide) {
            for (int i = 0; i < this.container.getContainerSize(); i++) {
                ItemStack stack = this.container.getItem(i);
                if (!stack.isEmpty()) {
                    player.drop(stack, false); // drop items back to player
                }
            }
        }
    }
    public ForgeMenu(int id, Inventory inv, FriendlyByteBuf data) {
        super(utilities.FORGE_MENU.get(), id);
        this.container = new SimpleContainer(9); // 👈 3 custom slots
        // Add player inventory slots
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inv, k, 8 + k * 18, 142));
        }
        int counter = 0;
        for (int l = 0; l < 3; ++l) {
            for (int k1 = 0; k1 < 3; ++k1) {
                this.addSlot(new Slot(container,counter,xpos[k1],ypos[l]));
                counter++;
            }
        }
        this.addSlot(new Slot(container, 10, xpos[3], ypos[3]) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false; // ❌ cannot place items
            }
        });
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();

            // 🔥 If clicking inside custom slots (0–8)
            if (index < 9) {
                // Move to player inventory (9–44)
                if (!this.moveItemStackTo(stack, 9, 45, true)) {
                    return ItemStack.EMPTY;
                }
            }
            // 🔥 If clicking in player inventory (9–44)
            else {
                // Move into custom slots (0–8)
                if (!this.moveItemStackTo(stack, 0, 9, false)) {
                    return ItemStack.EMPTY;
                }
            }

            // Cleanup
            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
