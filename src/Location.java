/**
 * Location.java - Used for passing a location to other functions and such.
 *
 * @author James
 * @author Chris
 * @author Willem
 */
public class Location implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -511963348775597869L;

    /**
     * X location
     */
    public double x;

    /**
     * Z location
     */
    public double z;

    /**
     * Y location
     */
    public double y;

    /**
     * Rotation
     */
    public float  rotX;

    /**
     * Pitch
     */
    public float  rotY;

    /**
     * Dimension id
     */
    public int dimension;

    /**
     * World name
     */
    public String world;

    /**
     * Creates a location
     */
    public Location() {}

    /**
     * Creates a location with the specified coordinates in the default world in dimension 0 (NORMAL)
     *
     * @param X
     * @param Y
     * @param Z
     */
    public Location(double X, double Y, double Z) {
        x = X;
        y = Y;
        z = Z;
        dimension = 0;
        world = etc.getServer().getDefaultWorld().getName();
    }

    /**
     * Creates a location in the specified dimension with the specified coordinates
     *
     * @param world
     * @param x
     * @param y
     * @param z
     */
    public Location(World world, double x, double y, double z) {
        this(x, y, z);
        this.dimension = world.getType().getId();
        this.world = world.getName();
    }

    /**
     * Creates a location with the specified coordinates and rotation
     *
     * @param X
     * @param Y
     * @param Z
     * @param rotation
     * @param pitch
     */
    public Location(double X, double Y, double Z, float rotation, float pitch) {
        x = X;
        y = Y;
        z = Z;
        rotX = rotation;
        rotY = pitch;
        dimension = 0;
        world = etc.getServer().getDefaultWorld().getName();
    }

    /**
     * Creates a location in the specified dimension with the specified coordinates and rotation
     *
     * @param world
     * @param X
     * @param Y
     * @param Z
     * @param rotation
     * @param pitch
     */
    public Location(World world, double X, double Y, double Z, float rotation, float pitch) {
        this(X, Y, Z, rotation, pitch);
        this.dimension = world.getType().getId();
        this.world = world.getName();
    }

    /**
     * Returns the dimension of the world for this location
     *
     * @return a <tt>World</tt>-object representing the world at this location's
     *          dimension. May return null if the given world does not exist!
     */
    public World getWorld() {
        World[] worlds = etc.getServer().getWorld(world);
        if(worlds == null || worlds.length == 0) {
            return etc.getServer().getDefaultWorld();
        }
        return worlds[World.Dimension.fromId(dimension).toIndex()];
    }

    /**
     * Used to get the distance to another location.
     * If other location is in another world, this method returns <tt>-1</tt>.
     *
     * @param other the <tt>Location</tt> to get the distance to.
     * @return the distance to <tt>other</tt> if it is in the same world,
     *         <tt>-1</tt> otherwise.
     */
    public double distanceTo(Location other) {
        if (this.dimension != other.dimension) {
            return -1;
        }
        double dx = Math.abs(this.x - other.x),
               dy = Math.abs(this.y - other.y),
               dz = Math.abs(this.z - other.z);

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Location) && this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 3;

        hash = 19 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 19 * hash + Float.floatToIntBits(this.rotX);
        hash = 19 * hash + Float.floatToIntBits(this.rotY);
        hash = 19 * hash + this.dimension;
        return hash;
    }

    // Used by Java to read this class from serialized state, this makes it read
    // all fields and not error out on extra fields.
    private void readObject(java.io.ObjectInputStream in)
            throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    @Override
    public String toString() {
        return String.format("Location[x=%d, y=%d, z=%d, rotX=%d, rotY=%d, world=%s, dim=%d]", x, y, z, rotX, rotY, world, dimension);
    }
}
