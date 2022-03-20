/*
 * Copyright 2022 Chloe Dawn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.sapphic.smartrepaircosts.mixin;

import dev.sapphic.smartrepaircosts.RepairCosts;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Items;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(AnvilMenu.class)
abstract class AnvilMenuMixin extends ItemCombinerMenu {
  AnvilMenuMixin(
      final MenuType<?> type,
      final int containerId,
      final Inventory inventory,
      final ContainerLevelAccess access) {
    super(type, containerId, inventory, access);
  }

  /**
   * Reverts the repair cost increase if this anvil contains an enchanted book. This can occur if it
   * is a book combining operation or an enchanting operation. Additionally, if the item in the left
   * slot is the enchanted book (the target item), we reset the repair cost entirely, as books in
   * general should not retain any repair cost.
   *
   * @param cost The increased repair cost
   * @return The original repair cost if this anvil contains an enchanted book
   */
  @ModifyVariable(
      method = "createResult()V",
      require = 1,
      allow = 1,
      index = 8,
      at =
          @At(
              value = "INVOKE_ASSIGN",
              opcode = Opcodes.INVOKEVIRTUAL,
              target =
                  "Lnet/minecraft/world/inventory/AnvilMenu;"
                      + "calculateIncreasedRepairCost("
                      + "I"
                      + ")I"))
  private int revertRepairCostIncreaseForEnchantedBooks(final int cost) {
    if (this.inputSlots.getItem(0).is(Items.ENCHANTED_BOOK)) {
      return 0;
    }

    if (this.inputSlots.getItem(1).is(Items.ENCHANTED_BOOK)) {
      return RepairCosts.calculateOriginalRepairCost(cost);
    }

    return cost;
  }
}
