package com.example.homlee.otis;

public class OtisFloorInfo extends FloorInfo {

    /**
     * 上行图标
     */
    private static final int MASK_OTIS_CUDL = 0x01;
    /**
     * 下行图标
     */
    private static final int MASK_OTIS_CDDL = 0x02;
    /**
     * 超载图标
     */
    private static final int MASK_OTIS_OLS = 0x04;
    /**
     * 消防图标
     */
    private static final int MASK_OTIS_FSL = 0x08;

    private static final int MASK_OTIS_DOL = 0x10;
    /**
     * 0 - 电梯工作，1 - 电梯空闲
     */
    private static final int MASK_OTIS_BL = 0x40;

    public boolean isMovingUp() {
        return (status & MASK_OTIS_CUDL) != 0;
    }

    public boolean isMovingDown() {
        return (status & MASK_OTIS_CDDL) != 0;
    }

    public boolean isOverload() {
        return (status & MASK_OTIS_OLS) != 0;
    }

    public boolean isFireSecurity() {
        return (status & MASK_OTIS_FSL) != 0;
    }

    public boolean isIdle() {
        return (status & MASK_OTIS_BL) != 0;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", isMovingUp = ").append(isMovingUp());
        sb.append(", isMovingDown = ").append(isMovingDown());
        sb.append(", isOverload = ").append(isOverload());
        sb.append(", isFireSecurity = ").append(isFireSecurity());
        sb.append(", isIdle = ").append(isIdle());
        return sb.toString();
    }
}
