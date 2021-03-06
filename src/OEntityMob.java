public abstract class OEntityMob extends OEntityCreature implements OIMob {

    // CanaryMod start
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    protected Mob entity = new Mob(this);
    // CanaryMod end

    public OEntityMob(OWorld oworld) {
        super(oworld);
        this.bc = 5;
    }

    public void c() {
        this.bo();
        float f = this.c(1.0F);

        if (f > 0.5F) {
            this.bA += 2;
        }

        super.c();
    }

    public void j_() {
        super.j_();
        if (!this.p.J && this.p.t == 0) {
            this.x();
        }
    }

    protected OEntity j() {
        OEntityPlayer oentityplayer = this.p.b(this, 16.0D);

        return oentityplayer != null && this.n(oentityplayer) ? oentityplayer : null;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.ar()) {
            return false;
        } else if (super.a(odamagesource, i)) {
            OEntity oentity = odamagesource.g();

            if (this.n != oentity && this.o != oentity) {
                if (oentity != this) {
                    // CanaryMod start - MOB_TARGET hook
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, oentity.getEntity(), this.getEntity())) {
                        this.a_ = oentity;
                    } // CanaryMod end
                }

                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean m(OEntity oentity) {
        int i = this.c(oentity);

        if (this.a(OPotion.g)) {
            i += 3 << this.b(OPotion.g).c();
        }

        if (this.a(OPotion.t)) {
            i -= 2 << this.b(OPotion.t).c();
        }

        int j = 0;

        if (oentity instanceof OEntityLiving) {
            i += OEnchantmentHelper.a((OEntityLiving) this, (OEntityLiving) oentity);
            j += OEnchantmentHelper.b(this, (OEntityLiving) oentity);
        }

        boolean flag = oentity.a(ODamageSource.a((OEntityLiving) this), i);

        if (flag) {
            if (j > 0) {
                oentity.g((double) (-OMathHelper.a(this.z * 3.1415927F / 180.0F) * (float) j * 0.5F), 0.1D, (double) (OMathHelper.b(this.z * 3.1415927F / 180.0F) * (float) j * 0.5F));
                this.w *= 0.6D;
                this.y *= 0.6D;
            }

            int k = OEnchantmentHelper.a((OEntityLiving) this);

            if (k > 0) {
                oentity.c(k * 4);
            }
        }

        return flag;
    }

    protected void a(OEntity oentity, float f) {
        if (this.aY <= 0 && f < 2.0F && oentity.D.e > this.D.b && oentity.D.b < this.D.e) {
            this.aY = 20;
            this.m(oentity);
        }
    }

    public float a(int i, int j, int k) {
        return 0.5F - this.p.p(i, j, k);
    }

    protected boolean i_() {
        int i = OMathHelper.c(this.t);
        int j = OMathHelper.c(this.D.b);
        int k = OMathHelper.c(this.v);

        if (this.p.b(OEnumSkyBlock.a, i, j, k) > this.aa.nextInt(32)) {
            return false;
        } else {
            int l = this.p.m(i, j, k);

            if (this.p.M()) {
                int i1 = this.p.j;

                this.p.j = 10;
                l = this.p.m(i, j, k);
                this.p.j = i1;
            }

            return l <= this.aa.nextInt(8);
        }
    }

    public boolean bs() {
        return this.i_() && super.bs();
    }

    public int c(OEntity oentity) {
        return 2;
    }

    @Override
    public Mob getEntity() {
        return entity;
    } //
}
