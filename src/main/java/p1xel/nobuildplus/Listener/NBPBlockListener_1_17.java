package p1xel.nobuildplus.Listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import p1xel.nobuildplus.Flags;
import p1xel.nobuildplus.Hook.HookedPlugins;
import p1xel.nobuildplus.Storage.Worlds;

public class NBPBlockListener_1_17 implements Listener {

    // Flag: berries
    @EventHandler
    public void onHarvest(PlayerHarvestBlockEvent e) {

        Block block = e.getHarvestedBlock();

        if (HookedPlugins.cancel(block)) {
            return;
        }

        String world = block.getWorld().getName();

        if (!Flags.berries.isEnabled(world)) {
            return;
        }

        Player p = e.getPlayer();

        if (p.hasPermission(Worlds.getPermission(world))) {
            return;
        }

        Material harvested = block.getType();

        if (harvested.equals(Material.matchMaterial("CAVE_VINES")) || harvested.equals(Material.matchMaterial("CAVE_VINES_PLANT"))) {
            Worlds.sendMessage(p, world);
            e.setCancelled(true);
        }

    }

}
