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
                //目标元素在当前元素的右边
                low = mid + 1;
            } else {
                //目标元素在当前元素的左边
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
                //如果当前是数组第一个元素，或者当前元素的前一个元素不等于当前元素，说明当前元素就是第一个等于目标值的元素
                if (mid == 0 || array[mid - 1] != val) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else if (array[mid] < val) {
                //目标元素在当前元素的右边
                low = mid + 1;
            } else {
                //目标元素在当前元素的左边
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
            //跟目标值相同
            if (array[mid] == val) {
                //如果当前元素是最后一个元素，或者当前元素的下一个元素与当前元素不相同，则说明当前元素就是目标元素
                if (mid == array.length - 1 || array[mid + 1] != val) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else if (array[mid] < val) {
                //目标元素在当前元素的右边
                low = mid + 1;
            } else {
                //目标元素在当前元素的左边
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
                //这个分支都是大于等于给定制的元素
                if (mid == 0 || array[mid - 1] < val) {
                    //如果当前元素是第一个元素，或者当前元素的前一个元素小于目标值，说明当前元素就是要找的目标元素
                    return mid;
                } else {
                    //否则要找的元素在当前元素的左边
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
                //当前分支都是小于等于目标值的情况
                if (mid == array.length - 1 || array[mid + 1] > val) {
                    //如果当前元素是最后一个元素，或者当前元素的下一个元素是大于等于目标值，那说明当前元素就是要找的元素
                    return mid;
                } else {
                    //否则，要找的元素在当前元素的右边
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
