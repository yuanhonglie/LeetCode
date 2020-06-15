package com.example.homlee.leetcode;

public class BinarySearch {

    /**
     * leetcode 704 二分查找
     *
     */
    public int search(int[] array, int val) {
        int low = 0;
        int high = array.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (array[mid] == val) {
                return mid;
            } else if (array[mid] < val) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 变体一：查找第一个值等于给定值的元素
     */
    public int search1(int[] array, int val) {
        int low = 0;
        int high = array.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (array[mid] == val) {
                if (mid == 0 || array[mid - 1] != val) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else if (array[mid] < val) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 变体二：查找最后一个值等于给定值的元素
     */
    public int search2(int[] array, int val) {
        int low = 0;
        int high = array.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (array[mid] == val) {
                if (mid == array.length - 1 || array[mid + 1] != val) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else if (array[mid] < val) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 变体三：查找第一个大于等于给定值的元素
     */
    public int search3(int[] array, int val) {
        int low = 0;
        int high = array.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (array[mid] < val) {
                low = mid + 1;
            } else {
                if (mid == 0 || array[mid - 1] < val) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }

    /**
     * 变体四：查找最后一个小于等于给定值的元素
     */
    public int search4(int[] array, int val) {
        int low = 0;
        int high = array.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (array[mid] <= val) {
                if (mid == array.length - 1 || array[mid + 1] > val) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,5,5,9,12};
        int target = 9;

        BinarySearch search = new BinarySearch();


        int index = search.search(nums, 9);
        System.out.println("9 is in " + index);
        index = search.search(nums, 2);
        System.out.println("2 is in " + index);

        System.out.println("查找第一个值等于5的元素：");
        System.out.println("" + search.search1(nums, 5));
        System.out.println("查找最后一个值等于5的元素：");
        System.out.println("" + search.search2(nums, 5));
        System.out.println("查找第一个大于等于4的元素：");
        System.out.println("" + search.search3(nums, 4));
        System.out.println("查找最后一个小于等于15的元素：");
        System.out.println("" + search.search4(nums, 15));
    }
}
