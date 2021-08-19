package CounterStriker.models.guns;

import static CounterStriker.common.ExceptionMessages.INVALID_GUN_BULLETS_COUNT;
import static CounterStriker.common.ExceptionMessages.INVALID_GUN_NAME;

public abstract class GunImpl implements Gun{
    private String name;
    private int bulletsCount;

    protected GunImpl(String name, int bulletsCount) {
        setName(name);
        setBulletsCount(bulletsCount);
    }

    private void setName(String name) {
        if(name == null || name.trim().length() == 0) {
            throw new NullPointerException(INVALID_GUN_NAME);
        }
        this.name = name;
    }

    protected void setBulletsCount(int bulletsCount) {
        if(bulletsCount < 0) {
            throw new IllegalArgumentException(INVALID_GUN_BULLETS_COUNT);
        }
        this.bulletsCount = bulletsCount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsCount() {
        return this.bulletsCount;
    }

    @Override
    public int fire() {
        if(this.getClass().getSimpleName().equals("Pistol")) {
            if(bulletsCount < 1){
                return 0;
            }
            bulletsCount -= 1;
            return 1;
        } else if (this.getClass().getSimpleName().equals("Rifle")) {
            if(bulletsCount < 10) {
                return 0;
            }
            bulletsCount -= 10;
            return 10;
        }
        return 0;
    }
}
