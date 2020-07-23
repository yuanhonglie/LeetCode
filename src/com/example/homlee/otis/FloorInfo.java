package com.example.homlee.otis;

public class FloorInfo {

    public enum RunningDirection {
        RUNNING_UP,
        RUNNING_DOWN,
    }

    public static final int DISPLAY_NONE = 0x00;
    public static final int DISPLAY = 0x01;
    public static final int DISPLAY_SCROLL = 0x02;
    public static final int DISPLAY_FLASHING = 0x03;

    public static final int RUNNING_UP = 0;
    public static final int RUNNING_DOWN = 1;

    public static final int ARROW_DISPLAY_MODE_NO_DISPLAY = 0;
    public static final int ARROW_DISPLAY_MODE_DISPLAY = 1;

    public static final int FLOOR_STATUS_NORMAL = 0x00;
    public static final int FLOOR_STATUS_FAULT = 0x01;
    public static final int FLOOR_STATUS_FULL_STAFF = 0x02;
    public static final int FLOOR_STATUS_OVERHAUL = 0x03;
    public static final int FLOOR_STATUS_OVERLOAD = 0x04;
    public static final int FLOOR_STATUS_PARKED = 0x05;
    public static final int FLOOR_STATUS_LOCKLADDER = 0x06;
    public static final int FLOOR_STATUS_FIRE = 0x07;

    private String floor;
    private RunningDirection runningDirection;
    private int arrowDisplayMode = ARROW_DISPLAY_MODE_DISPLAY;
    protected int status;

    public FloorInfo() {
    }

    public FloorInfo (String floor, RunningDirection rd, int arrowDisplayMode) {
        this.floor = floor;
        this.runningDirection = rd;
        this.arrowDisplayMode = arrowDisplayMode;
        this.status = FLOOR_STATUS_NORMAL;
    }

    public FloorInfo (String floor, RunningDirection rd, int arrowDisplayMode, int status) {
        this.floor = floor;
        this.runningDirection = rd;
        this.arrowDisplayMode = arrowDisplayMode;
        this.status = status;
    }

    public FloorInfo (FloorInfo floorInfo) {
        this.floor = floorInfo.getFloor();
        this.runningDirection = floorInfo.getRunningDirection();
        this.arrowDisplayMode = floorInfo.getArrowDisplayMode();
        this.status = floorInfo.getStatus();
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public RunningDirection getRunningDirection() {
        return runningDirection;
    }

    public void setRunningDirection(RunningDirection runningDirection) {
        this.runningDirection = runningDirection;
    }

    public int getArrowDisplayMode() {
        return arrowDisplayMode;
    }

    public void setArrowDisplayMode(int arrowDisplayMode) {
        this.arrowDisplayMode = arrowDisplayMode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override public String toString() {
        return "FloorInfo floor : <" + floor + " runningDirection : " + runningDirection.toString()
                + " arrowDisplayMode : " + arrowDisplayMode + " status : " + status + ">";
    }

    public boolean isEquals(FloorInfo floorInfo) {
        if (floor == null || !floor.equals(floorInfo.floor)) {
            return false;
        }

        if (runningDirection != floorInfo.getRunningDirection()
            || arrowDisplayMode != floorInfo.getArrowDisplayMode()
            || status != floorInfo.getStatus()) {
            return false;
        }

        return true;
    }
}
