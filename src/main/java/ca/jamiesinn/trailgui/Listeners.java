package ca.jamiesinn.trailgui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import ca.jamiesinn.trailgui.libraries.ParticleEffect;

@SuppressWarnings("DynamicRegexReplaceableByCompiledPattern")
public class Listeners
    implements Listener
{

    @Deprecated public static List<String> cooldownAngryVillager = new ArrayList<>();
    @Deprecated public static List<String> cooldownEnderSignal = new ArrayList<>();
    @Deprecated public static List<String> cooldownHearts = new ArrayList<>();
    @Deprecated public static List<String> cooldownNote = new ArrayList<>();
    private final Main main;

    public Listeners(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onEntityDamageByEntity1(EntityDamageByEntityEvent event)
    {
        if (main.getConfig().getBoolean("removeTrailOnPlayerHit"))
        {
            if (event.getDamager() instanceof Player &&
                event.getEntity() instanceof Player)
            {
                Player hit = (Player) event.getEntity();

                Methodes.clearTrails(hit);
            }
        }
    }

    @EventHandler
    public void onInventoryClick1(InventoryClickEvent event)
    {
        if (event.getInventory().getTitle().contains(main.getConfig().getString("pageOneInventoryName")))
        {
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem() == null || event.getCurrentItem() == new ItemStack(Material.AIR))
            {
                return;
            }
            if (event.getCurrentItem().equals(Methodes.itemAngryVillager()))
            {
                if (!player.hasPermission("trailgui.inventory.angryvillager"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailAngryVillager.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailAngryVillager.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "AngryVillager"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailAngryVillager.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "AngryVillager"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemCloud()))
            {
                if (!player.hasPermission("trailgui.inventory.cloud"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailCloud.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailCloud.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "Cloud"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailCloud.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "Cloud"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemCriticals()))
            {
                if (!player.hasPermission("trailgui.inventory.criticals"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailCriticals.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailCriticals.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "Criticals"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailCriticals.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "Criticals"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemDripLava()))
            {
                if (!player.hasPermission("trailgui.inventory.driplava"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailDripLava.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailDripLava.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "DripLava"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailDripLava.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "DripLava"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemDripWater()))
            {
                if (!player.hasPermission("trailgui.inventory.dripwater"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailDripWater.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailDripWater.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "DripWater"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailDripWater.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "DripWater"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemEnchantment()))
            {
                if (!player.hasPermission("trailgui.inventory.enchantment"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailEnchantment.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailEnchantment.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "Enchantment"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailEnchantment.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "Enchantment"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemSpark()))
            {
                if (!player.hasPermission("trailgui.inventory.spark"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailSpark.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailSpark.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "Spark"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailSpark.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "Spark"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemFlame()))
            {
                if (!player.hasPermission("trailgui.inventory.flame"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailFlame.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailFlame.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "Flame"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailFlame.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "Flame"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemHappyVillager()))
            {
                if (!player.hasPermission("trailgui.inventory.happyvillager"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailHappyVillager.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailHappyVillager.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "HappyVillager"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailHappyVillager.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "HappyVillager"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemInstantSpell()))
            {
                if (!player.hasPermission("trailgui.inventory.instantspell"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailInstantSpell.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailInstantSpell.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "InstantSpell"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailInstantSpell.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "InstantSpell"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemLargeSmoke()))
            {
                if (!player.hasPermission("trailgui.inventory.largesmoke"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailLargeSmoke.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailLargeSmoke.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "LargeSmoke"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailLargeSmoke.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "LargeSmoke"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemLava()))
            {
                if (!player.hasPermission("trailgui.inventory.lava"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailLava.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailLava.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "Lava"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailLava.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "Lava"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemMagicCrit()))
            {
                if (!player.hasPermission("trailgui.inventory.magiccrit"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailMagicCrit.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailMagicCrit.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "MagicCrit"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailMagicCrit.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "MagicCrit"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemMobSpell()))
            {
                if (!player.hasPermission("trailgui.inventory.mobspell"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailMobSpell.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailMobSpell.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "MobSpell"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailMobSpell.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "MobSpell"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemMobSpellAmbient()))
            {
                if (!player.hasPermission("trailgui.inventory.mobspellambient"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailMobSpellAmbient.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailMobSpellAmbient.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "MobSpellAmbient"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailMobSpellAmbient.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "MobSpellAmbient"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemNote()))
            {
                if (!player.hasPermission("trailgui.inventory.note"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailNote.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailNote.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "Note"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailNote.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "Note"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemPortal()))
            {
                if (!player.hasPermission("trailgui.inventory.portal"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailPortal.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailPortal.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "Portal"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailPortal.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "Portal"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemRedDust()))
            {
                if (!player.hasPermission("trailgui.inventory.reddust"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailRedDust.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailRedDust.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "RedDust"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailRedDust.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "RedDust"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemColoredRedDust()))
            {
                if (!player.hasPermission("trailgui.inventory.coloredreddust"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailColoredRedDust.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailColoredRedDust.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "ColoredRedDust"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailColoredRedDust.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "ColoredRedDust"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemSlime()))
            {
                if (!player.hasPermission("trailgui.inventory.slime"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailSlime.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailSlime.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "Slime"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailSlime.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "Slime"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemSnowShovel()))
            {
                if (!player.hasPermission("trailgui.inventory.snowshovel"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailSnowShovel.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailSnowShovel.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "SnowShovel"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailSnowShovel.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "SnowShovel"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemSnowballPoof()))
            {
                if (!player.hasPermission("trailgui.inventory.snowballpoof"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailSnowballPoof.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailSnowballPoof.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "SnowballPoof"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailSnowballPoof.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "SnowballPoof"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemSpell()))
            {
                if (!player.hasPermission("trailgui.inventory.spell"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailSpell.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailSpell.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "Spell"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailSpell.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "Spell"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemSplash()))
            {
                if (!player.hasPermission("trailgui.inventory.splash"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailSplash.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailSplash.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "Splash"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailSplash.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "Splash"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemTownAura()))
            {
                if (!player.hasPermission("trailgui.inventory.townaura"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailTownAura.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailTownAura.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "TownAura"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailTownAura.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "TownAura"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemWake()))
            {
                if (!player.hasPermission("trailgui.inventory.wake"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailWake.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailWake.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "Wake"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailWake.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "Wake"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemWitchMagic()))
            {
                if (!player.hasPermission("trailgui.inventory.witchmagic"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailWitchMagic.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailWitchMagic.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "WitchMagic"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailWitchMagic.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "WitchMagic"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemRemoveTrails()))
            {
                if (!player.hasPermission("trailgui.inventory.removetrails"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                Methodes.clearTrails(player);

                player.sendMessage(main.getConfig().getString("RemoveTrails-message").replaceAll("&", "§"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemNextPage()))
            {
                if (!player.hasPermission("trailgui.inventory.nextpage"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                Methodes.openGUI2(player);
            }
        }
    }

    @EventHandler
    public void onInventoryClick2(InventoryClickEvent event)
    {
        if (event.getInventory().getTitle().contains(main.getConfig().getString("pageTwoInventoryName")))
        {
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem() == null || event.getCurrentItem() == new ItemStack(Material.AIR))
            {
                return;
            }
            if (event.getCurrentItem().equals(Methodes.itemHearts()))
            {
                if (!player.hasPermission("trailgui.inventory.hearts"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailHearts.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailHearts.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "Hearts"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailHearts.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "Hearts"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemEnderSignal()))
            {
                if (!player.hasPermission("trailgui.inventory.endersignal"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailEnderSignal.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailEnderSignal.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "EnderSignal"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailEnderSignal.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "EnderSignal"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemIconCrack()))
            {
                if (!player.hasPermission("trailgui.inventory.iconcrack"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (Main.trailIconCrack.contains(player.getUniqueId().toString()))
                {
                    if (main.getConfig().getBoolean("oneTrailAtATime"))
                    {
                        Methodes.clearTrails(player);
                    }
                    Main.trailIconCrack.remove(player.getUniqueId().toString());
                    player.sendMessage(main.getConfig().getString("GUI-removeTrailMessage")
                        .replaceAll("&", "§").replaceAll("%TrailName%", "IconCrack"));
                    if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                if (main.getConfig().getBoolean("oneTrailAtATime"))
                {
                    Methodes.clearTrails(player);
                }
                Main.trailIconCrack.add(player.getUniqueId().toString());
                player.sendMessage(main.getConfig().getString("GUI-selectTrailMessage").replaceAll("&", "§")
                    .replaceAll("%TrailName%", "IconCrack"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemRemoveTrails()))
            {
                if (!player.hasPermission("trailgui.inventory.removetrails"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                Methodes.clearTrails(player);

                player.sendMessage(main.getConfig().getString("RemoveTrails-message").replaceAll("&", "§"));
                if (main.getConfig().getBoolean("closeInventoryAferSelect"))
                {
                    player.closeInventory();
                }
            } else if (event.getCurrentItem().equals(Methodes.itemPreviousPage()))
            {
                if (!player.hasPermission("trailgui.inventory.previouspage"))
                {
                    player.sendMessage(main.getConfig().getString("GUI-denyPermissionMessage")
                        .replaceAll("&", "§"));
                    if (main.getConfig().getBoolean("closeInventoryOnDenyPermission"))
                    {
                        player.closeInventory();
                    }
                    return;
                }
                Methodes.openGUI1(player);
            }
        }
    }

    @EventHandler
    public void onPlayerMove1(PlayerMoveEvent event)
    {
        if (main.getConfig().getBoolean("disabledWhenSpinning") &&
            event.getFrom().getX() == event.getTo().getX() && event.getFrom().getY() == event.getTo().getY() && event
            .getFrom().getZ() == event.getTo().getZ())
        {
            return;
        }
        final Player player = event.getPlayer();
        for (String string : main.getConfig().getStringList("disabledWorlds"))
        {
            string.replace("[", "");
            string.replace("]", "");
            if (string.equals(player.getWorld().getName()))
            {
                return;
            }
        }
        if (Main.trailCloud.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.CLOUD.display(0, 0, 0, 0, main.getConfig().getInt("Cloud-amount"), player.getLocation()
                .add(0.0D, main.getConfig().getDouble("Cloud-displayLocation"), 0.0D), main.getConfig()
                .getInt("Cloud-range"));
        }
        if (Main.trailAngryVillager.contains(player.getUniqueId().toString()))
        {
            if (cooldownAngryVillager.contains(player.getUniqueId().toString()))
            {
                return;
            }
            ParticleEffect.VILLAGER_ANGRY
                .display(0, 0, 0, 0, main.getConfig().getInt("AngryVillager-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("AngryVillager-displayLocation"), 0.0D), main
                    .getConfig().getInt("AngryVillager-range"));
            cooldownAngryVillager.add(player.getUniqueId().toString());

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable()
            {
                public void run()
                {
                    Listeners.cooldownAngryVillager.remove(player.getUniqueId().toString());
                }
            }, main.getConfig().getInt("AngryVillager-cooldown"));
        }
        if (Main.trailCriticals.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.CRIT.display(0, 0, 0, main.getConfig().getInt("Criticals-speed"), main.getConfig()
                .getInt("Criticals-amount"), player.getLocation()
                .add(0.0D, main.getConfig().getDouble("Criticals-displayLocation"), 0.0D), main.getConfig()
                .getInt("Criticals-range"));
        }
        if (Main.trailDripLava.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.DRIP_LAVA
                .display(0, 0, 0, main.getConfig().getInt("DripLava-speed"), main.getConfig()
                    .getInt("DripLava-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("DripLava-displayLocation"), 0.0D), main.getConfig()
                    .getInt("DripLava-range"));
        }
        if (Main.trailDripWater.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.DRIP_WATER
                .display(0, 0, 0, main.getConfig().getInt("DripWater-speed"), main.getConfig()
                    .getInt("DripWater-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("DripWater-displayLocation"), 0.0D), main
                    .getConfig().getInt("DripWater-range"));
        }
        if (Main.trailEnchantment.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.ENCHANTMENT_TABLE
                .display(0, 0, 0, main.getConfig().getInt("Enchantment-speed"), main.getConfig()
                    .getInt("Enchantment-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("Enchantment-displayLocation"), 0.0D), main
                    .getConfig().getInt("Enchantment-range"));
        }
        if (Main.trailSpark.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.FIREWORKS_SPARK
                .display(0, 0, 0, main.getConfig().getInt("Spark-speed"), main.getConfig()
                    .getInt("Spark-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("Spark-displayLocation"), 0.0D), main.getConfig()
                    .getInt("Spark-range"));
        }
        if (Main.trailFlame.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.FLAME.display(0, 0, 0, main.getConfig().getInt("Flame-speed"), main.getConfig()
                .getInt("Flame-amount"), player.getLocation()
                .add(0.0D, main.getConfig().getDouble("Flame-displayLocation"), 0.0D), main.getConfig()
                .getInt("Flame-range"));
        }
        if (Main.trailHappyVillager.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.VILLAGER_HAPPY
                .display(0, 0, 0, main.getConfig().getInt("HappyVillager-speed"), main.getConfig()
                    .getInt("HappyVillager-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("HappyVillager-displayLocation"), 0.0D), main
                    .getConfig().getInt("HappyVillager-range"));
        }
        if (Main.trailInstantSpell.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.SPELL_INSTANT
                .display(0, 0, 0, main.getConfig().getInt("InstantSpell-speed"), main.getConfig()
                    .getInt("InstantSpell-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("InstantSpell-displayLocation"), 0.0D), main
                    .getConfig().getInt("InstantSpell-range"));
        }
        if (Main.trailLargeSmoke.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.SMOKE_LARGE
                .display(0, 0, 0, main.getConfig().getInt("LargeSmoke-speed"), main.getConfig()
                    .getInt("LargeSmoke-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("LargeSmoke-displayLocation"), 0.0D), main
                    .getConfig().getInt("LargeSmoke-range"));
        }
        if (Main.trailLava.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.LAVA.display(0, 0, 0, main.getConfig().getInt("Lava-speed"), main.getConfig()
                .getInt("Lava-amount"), player.getLocation()
                .add(0.0D, main.getConfig().getDouble("Lava-displayLocation"), 0.0D), main.getConfig()
                .getInt("Lava-range"));
        }
        if (Main.trailMagicCrit.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.CRIT_MAGIC
                .display(0, 0, 0, main.getConfig().getInt("MagicCrit-speed"), main.getConfig()
                    .getInt("MagicCrit-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("MagicCrit-displayLocation"), 0.0D), main
                    .getConfig().getInt("MagicCrit-range"));
        }
        if (Main.trailMobSpell.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.SPELL_MOB
                .display(0, 0, 0, main.getConfig().getInt("MobSpell-speed"), main.getConfig()
                    .getInt("MobSpell-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("MobSpell-displayLocation"), 0.0D), main.getConfig()
                    .getInt("MobSpell-range"));
        }
        if (Main.trailMobSpellAmbient.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.SPELL_MOB_AMBIENT
                .display(0, 0, 0, main.getConfig().getInt("MobSpellAmbient-speed"), main.getConfig()
                    .getInt("MobSpellAmbient-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("MobSpellAmbient-displayLocation"), 0.0D), main
                    .getConfig().getInt("MobSpellAmbient-range"));
        }
        if (Main.trailNote.contains(player.getUniqueId().toString()))
        {
            if (cooldownNote.contains(player.getUniqueId().toString()))
            {
                return;
            }
            ParticleEffect.NOTE.display(0, 0, 0, main.getConfig().getInt("Note-speed"), main.getConfig()
                .getInt("Note-amount"), player.getLocation()
                .add(0.0D, main.getConfig().getDouble("Note-displayLocation"), 0.0D), main.getConfig()
                .getInt("Note-range"));
            cooldownNote.add(player.getUniqueId().toString());

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable()
            {
                public void run()
                {
                    Listeners.cooldownNote.remove(player.getUniqueId().toString());
                }
            }, main.getConfig().getInt("Note-cooldown") * 1);
        }
        if (Main.trailPortal.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.PORTAL.display(0, 0, 0, main.getConfig().getInt("Portal-speed"), main.getConfig()
                .getInt("Portal-amount"), player.getLocation()
                .add(0.0D, main.getConfig().getDouble("Portal-displayLocation"), 0.0D), main.getConfig()
                .getInt("Portal-range"));
        }
        if (Main.trailRedDust.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.REDSTONE
                .display(0, 0, 0, main.getConfig().getInt("RedDust-speed"), main.getConfig()
                    .getInt("RedDust-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("RedDust-displayLocation"), 0.0D), main.getConfig()
                    .getInt("RedDust-range"));
        }
        if (Main.trailColoredRedDust.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.REDSTONE
                .display(0, 0, 0, main.getConfig().getInt("ColoredRedDust-speed"), main.getConfig()
                    .getInt("ColoredRedDust-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("ColoredRedDust-displayLocation"), 0.0D), main
                    .getConfig().getInt("ColoredRedDust-range"));
        }
        if (Main.trailSlime.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.SLIME.display(0, 0, 0, main.getConfig().getInt("Slime-speed"), main.getConfig()
                .getInt("Slime-amount"), player.getLocation()
                .add(0.0D, main.getConfig().getDouble("Slime-displayLocation"), 0.0D), main.getConfig()
                .getInt("Slime-range"));
        }
        if (Main.trailSnowShovel.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.SNOW_SHOVEL
                .display(0, 0, 0, main.getConfig().getInt("SnowShovel-speed"), main.getConfig()
                    .getInt("SnowShovel-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("SnowShovel-displayLocation"), 0.0D), main
                    .getConfig().getInt("SnowShovel-range"));
        }
        if (Main.trailSnowballPoof.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.SNOWBALL
                .display(0, 0, 0, main.getConfig().getInt("SnowballPoof-speed"), main.getConfig()
                    .getInt("SnowballPoof-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("SnowballPoof-displayLocation"), 0.0D), main
                    .getConfig().getInt("SnowballPoof-range"));
        }
        if (Main.trailSpell.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.SPELL.display(0, 0, 0, main.getConfig().getInt("Spell-speed"), main.getConfig()
                .getInt("Spell-amount"), player.getLocation()
                .add(0.0D, main.getConfig().getDouble("Spell-displayLocation"), 0.0D), main.getConfig()
                .getInt("Spell-range"));
        }
        if (Main.trailSplash.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.WATER_SPLASH
                .display(0, 0, 0, main.getConfig().getInt("Splash-speed"), main.getConfig()
                    .getInt("Splash-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("Splash-displayLocation"), 0.0D), main.getConfig()
                    .getInt("Splash-range"));
        }
        if (Main.trailTownAura.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.TOWN_AURA
                .display(0, 0, 0, main.getConfig().getInt("TownAura-speed"), main.getConfig()
                    .getInt("TownAura-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("TownAura-displayLocation"), 0.0D), main.getConfig()
                    .getInt("TownAura-range"));
        }
        if (Main.trailWake.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.WATER_WAKE.display(0, 0, 0, main.getConfig().getInt("Wake-speed"), main.getConfig()
                .getInt("Wake-amount"), player.getLocation()
                .add(0.0D, main.getConfig().getDouble("Wake-displayLocation"), 0.0D), main.getConfig()
                .getInt("Wake-range"));
        }
        if (Main.trailWitchMagic.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.SPELL_WITCH
                .display(0, 0, 0, main.getConfig().getInt("WitchMagic-speed"), main.getConfig()
                    .getInt("WitchMagic-amount"), player.getLocation()
                    .add(0.0D, main.getConfig().getDouble("WitchMagic-displayLocation"), 0.0D), main
                    .getConfig().getInt("WitchMagic-range"));
        }
        if (Main.trailHearts.contains(player.getUniqueId().toString()))
        {
            if (cooldownHearts.contains(player.getUniqueId().toString()))
            {
                return;
            }
            ParticleEffect.HEART.display(0, 0, 0, main.getConfig().getInt("Hearts-speed"), main.getConfig()
                .getInt("Hearts-amount"), player.getLocation()
                .add(0.0D, main.getConfig().getDouble("Hearts-displayLocation"), 0.0D), main.getConfig()
                .getInt("Hearts-range"));
            cooldownHearts.add(player.getUniqueId().toString());

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable()
            {
                public void run()
                {
                    Listeners.cooldownHearts.remove(player.getUniqueId().toString());
                }
            }, main.getConfig().getInt("Hearts-cooldown") * 1);
        }
        if (Main.trailEnderSignal.contains(player.getUniqueId().toString()))
        {
            if (cooldownEnderSignal.contains(player.getUniqueId().toString()))
            {
                return;
            }
            player.getWorld().playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 1);
            cooldownEnderSignal.add(player.getUniqueId().toString());

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable()
            {
                public void run()
                {
                    Listeners.cooldownEnderSignal.remove(player.getUniqueId().toString());
                }
            }, main.getConfig().getInt("EnderSignal-cooldown") * 1);
        }
        if (Main.trailIconCrack.contains(player.getUniqueId().toString()))
        {
            ParticleEffect.ItemData data = new ParticleEffect.ItemData(Material
                .valueOf(main.getConfig().getString("IconCrack-itemType").toUpperCase()), (byte) 0);
            ParticleEffect.ITEM_CRACK.display(data, player.getLocation().getDirection(), main.getConfig()
                .getInt("IconCrack-speed"), player.getLocation()
                .add(0.0D, main.getConfig().getDouble("IconCrack-displayLocation"), 0.0D), main.getConfig()
                .getInt("IconCrack-range"));
        }
    }
}

