public class OItemBed extends OItem {

    public OItemBed(int i) {
        super(i);
        this.a(OCreativeTabs.c);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        // CanaryMod: store the block that was clicked
        Block blockClicked = this.getBlockInfo(oworld, i, j, k, l);

        if (oworld.J) {
            return true;
        } else if (l != 1) {
            return false;
        } else {
            ++j;
            OBlockBed oblockbed = (OBlockBed) OBlock.V;
            int i1 = OMathHelper.c((double) (oentityplayer.z * 4.0F / 360.0F) + 0.5D) & 3;
            byte b0 = 0;
            byte b1 = 0;

            if (i1 == 0) {
                b1 = 1;
            }

            if (i1 == 1) {
                b0 = -1;
            }

            if (i1 == 2) {
                b1 = -1;
            }

            if (i1 == 3) {
                b0 = 1;
            }

            // CanaryMod: onItemUse hook
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), new Block(oworld.world, Block.Type.Bed.getType(), i, j, k), blockClicked, ((OEntityPlayerMP) oentityplayer).getPlayer().getItemStackInHand())) {
                return false;
            }

            if (oentityplayer.a(i, j, k, l, oitemstack) && oentityplayer.a(i + b0, j, k + b1, l, oitemstack)) {
                if (oworld.c(i, j, k) && oworld.c(i + b0, j, k + b1) && oworld.v(i, j - 1, k) && oworld.v(i + b0, j - 1, k + b1)) {
                    oworld.d(i, j, k, oblockbed.cm, i1);
                    if (oworld.a(i, j, k) == oblockbed.cm) {
                        oworld.d(i + b0, j, k + b1, oblockbed.cm, i1 + 8);
                    }

                    --oitemstack.a;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
