package com.example.homlee.otis;

public class OtisTest {
    private static final String TAG = "OtisTest";
    private boolean isOtisDataFrame(byte[] buffer) {
        if (buffer == null || buffer.length < 15) {
            System.out.println("isOtisDataFrame: data len error, size = " + (buffer == null ? 0 : buffer.length));
            return false;
        }

        System.out.println("isOtisDataFrame : " +  bytesToHex(buffer) + ", size : " + buffer.length);
        //帧头
        if (buffer[0] != 0x55) {
            System.out.println("isOtisDataFrame: frame header NOT match");
            return false;
        }

        //校验CRC
        /*
        int crc = combineValue(buffer[14], buffer[13]);
        int calCrc = otisCrc16(buffer);
        if (calCrc != crc) {
            Log.e(TAG, "isOtisDataFrame: crc error! expect value: " + crc + ", actual value: " + calCrc);
            return false;
        }*/

        return true;
    }

    synchronized public void otisDataProcess(ElevatorType type, byte[] buffer, int size) {
        System.out.println("otisDataProcess: start");
        //从机ID
        int clientId = (buffer[1] >> 4) & 0x0f;
        if (clientId != 1) {
            System.out.println("otisDataProcess: clientId = " + clientId);
            return;
        }

        int high = combineValue(buffer[2], buffer[3]);
        int low = combineValue(buffer[4], buffer[5]);

        //楼层显示
        String floor = OtisFloorDecoder.getInstance().getText(high, low);
        System.out.println("otisDataProcess: floor = " + floor);

        FloorInfo.RunningDirection runningDirection;
        if ((buffer[12] & 0x01) != 0) {
            runningDirection = FloorInfo.RunningDirection.RUNNING_UP;
        } else {
            runningDirection = FloorInfo.RunningDirection.RUNNING_DOWN;
        }

        OtisFloorInfo floorInfo = new OtisFloorInfo();
        floorInfo.setFloor(floor);
        floorInfo.setStatus(buffer[12]);
        floorInfo.setRunningDirection(runningDirection);
        System.out.println("floorInfo : " + floorInfo.toString());
    }

    private int combineValue(byte high, byte low) {
        return ((high << 8) & 0xff00) | (low & 0xff);
    }

    private int otisCrc16(byte[] array) {
        int crc16 = 0xffff;
        for (int i = 0; i < array.length; i++) {
            crc16 = crc16 ^ array[i];
            for (int j = 0; j < 8; j++) {
                if ((crc16 & 0x01) == 1) {
                    crc16 = crc16 >> 1;
                    crc16 ^= 0xa001;
                } else {
                    crc16 = crc16 >> 1;
                }
            }
        }
        return crc16;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            buf.append(String.format("%02x ", new Integer(b & 0xff)));
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        byte[] data = {0x55, 0x10, (byte)0xa9, (byte)0xab, (byte)0x80, (byte)0x02,
                        0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                        0x01, 0x00, 0x00};
        OtisTest test = new OtisTest();
        System.out.println("isOtisDataFrame = " + test.isOtisDataFrame(data));
        test.otisDataProcess(ElevatorType.ELEVATOR_TYPE_RIGHT, data, data.length);
    }
}
