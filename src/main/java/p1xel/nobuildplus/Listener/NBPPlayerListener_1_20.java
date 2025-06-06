package p1xel.nobuildplus.Listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSignOpenEvent;
import p1xel.nobuildplus.Flags;
import p1xel.nobuildplus.Hook.HookedPlugins;
import p1xel.nobuildplus.Storage.Worlds;

public class NBPPlayerListener_1_20 implements Listener {

    // Flag: sign-edit
    @EventHandler
    public void onSignOpen(PlayerSignOpenEvent e) {

        Player p = e.getPlayer();

        if (HookedPlugins.cancel(p)) {
            return;
        }

        if (e.getCause() != PlayerSignOpenEvent.Cause.INTERACT) {
            return;
        }

        String world = p.getWorld().getName();
        if (!Flags.sign_edit.isEnabled(world)) {
            return;
        }

        if (p.hasPermission(Worlds.getPermission(world))) {
            return;
        }

        Worlds.sendMessage(p, world);
        e.setCancelled(true);

    }

    // Flag: books(helf) interact
    @EventHandler
    public void onBookShelfInteract(PlayerInteractEvent e) {
        Action action = e.getAction();
        Block block = e.getClickedBlock();

        if (action != Action.RIGHT_CLICK_BLOCK || block == null || block.getType() != Material.CHISELED_BOOKSHELF) {
            return;
        }

        if (HookedPlugins.cancel(block)) {
            return;
        }

        String world = block.getWorld().getName();
        if (!Flags.books_interact.isEnabled(world)) {
            return;
        }

        Player p = e.getPlayer();
        if (p.hasPermission(Worlds.getPermission(world))) {
            return;
        }

        Worlds.sendMessage(p, world);

        e.setCancelled(true);
    }

}
