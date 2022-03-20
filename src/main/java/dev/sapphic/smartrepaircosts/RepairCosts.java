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

package dev.sapphic.smartrepaircosts;

import net.minecraft.world.inventory.AnvilMenu;

public final class RepairCosts {
  private RepairCosts() {
    throw new UnsupportedOperationException();
  }

  /**
   * Calculates the original repair cost prior to its increase
   *
   * @param cost The previously increased repair cost
   * @return The decreased repair cost
   * @see AnvilMenu#calculateIncreasedRepairCost(int)
   */
  public static int calculateOriginalRepairCost(final int cost) {
    return (cost - 1) / 2;
  }
}
