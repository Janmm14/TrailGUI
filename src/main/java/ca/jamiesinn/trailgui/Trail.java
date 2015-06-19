package ca.jamiesinn.trailgui;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ca.jamiesinn.trailgui.util.Consumer;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.SetMultimap;

public class Trail {

    private static final Map<String, Trail> trails = Maps.newHashMapWithExpectedSize(50); //TODO set correct trail number
    private static final SetMultimap<UUID, Trail> trailsByUuid = HashMultimap.create();

    public static Collection<Trail> getTrails() {
        return trails.values();
    }

    public static Map<String, Trail> getTrailsMap() {
        return trails;
    }

    public static Trail getTrail(String name) {
        return trails.get(name);
    }

    public static Set<Trail> getTrailsOf(UUID uuid) {
        return trailsByUuid.get(uuid);
    }

    public static void removeAllTrails() {
        for (Trail trail : trails.values()) {
            trail.removeFromAll();
        }
        trailsByUuid.clear();
    }

    public static void removeTrails(UUID uuid) {
        final Set<Trail> trails = getTrailsOf(uuid);
        if (trails != null) {
            for (Trail trail : trails) {
                trail.removeTrail(uuid);
            }
        }
    }

    private final String name;
    private final String permission;
    private final ItemStack item;
    private final Consumer<Player> trailDrawer;
    private final Set<UUID> usedCurr = new HashSet<>();

    public Trail(String name, String permission, ItemStack item, Consumer<Player> trailDrawer) {
        this.name = name;
        this.permission = permission;
        this.item = item;
        this.trailDrawer = trailDrawer;
        trails.put(name, this);
    }

    public void drawTrailAt(Player player) {
        trailDrawer.accept(player);
    }

    public void removeFromAll() {
        usedCurr.clear();
    }

    public Set<UUID> getAllTrailEnabled() {
        return usedCurr;
    }

    public boolean hasTrail(UUID uuid) {
        return usedCurr.contains(uuid);
    }

    /**
     * @return {@code true} if the uuid did not had that trail before, otherwise {@code false}
     */
    public boolean addTrail(UUID uuid) {
        return usedCurr.add(uuid);
    }

    /**
     * @return {@code true} if the uuid did had that trail, otherwise {@code false}
     */
    public boolean removeTrail(UUID uuid) {
        return usedCurr.remove(uuid);
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public ItemStack getItem() {
        return item;
    }
}
