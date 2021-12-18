/*
 * Copyright 2021 Chloe Dawn
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
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public final class RepairCostsTest {
  @Test
  public void originalRepairCostMatches() {
    int cost = 0;

    while (cost < 39) {
      // Note: This can only test against the default implementation
      final int increasedCost = AnvilMenu.calculateIncreasedRepairCost(cost);

      assertThat(RepairCosts.calculateOriginalRepairCost(increasedCost)).isEqualTo(cost);

      cost = increasedCost;
    }
  }
}
