/**
 * Interface for accessing the inventories of ender chests
 *
 * @author gregthegeek
 *
 */
public class EnderChestInventory extends ItemArray<OInventoryEnderChest> implements Inventory {
    private final Player owner;

    public EnderChestInventory(OInventoryEnderChest container, Player owner) {
        super(container);
        this.owner = owner;
    }

    /**
     * Returns an NBTTagList with data about the contents of this inventory.
     *
     * @return
     */
    public NBTTagList writeToTag() {
        return new NBTTagList(container.g());
    }

    /**
     * Sets this inventory's data to equal the contents of an NBTTagList.
     *
     * @param tag the tag to read data from
     */
    public void readFromTag(NBTTagList tag) {
        container.a(tag.getBaseTag());
    }

    @Override
    public void update() {
        owner.getEntity().j_();
    }

    @Override
    public String getName() {
        return container.getName();
    }

    @Override
    public void setName(String value) {
        container.setName(value);
    }

    /**
     * Returns the player that this ender chest inventory belongs to.
     *
     * @return
     */
    public Player getPlayer() {
        return owner;
    }
}
