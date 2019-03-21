package com.xiaoniu.tools.Generator;

import java.util.Random;

public class RandomUtil {

	/**
	 * 生成指定最小值min与最大值max范围内的整数随机数，不包括指定最大值max本身
	 * 
	 * @param min
	 *            生成随机数的最小值范围
	 * @param max
	 *            生成随机数的最大值范围
	 * @return int 生成的整数随机数
	 */
	public static int getRandomNumber(int min, int max) {
		int number = (int) (Math.random() * (max - min) + min);
		return number;
	}

	/**
	 * 生成指定范围内的整数随机数，不包括指定范围本身
	 * 
	 * @param extent
	 *            生成随机数的范围
	 * 
	 * @return int 生成的整数随机数
	 */
	public static int getExtentRandomNumber(int extent) {
		int number = (int) (Math.random() * extent);
		return number;
	}

	/**
	 * 生成N个指定范围内的不重复的整数随机，不包括指定范围本身
	 * 
	 * @param extent
	 *            生成随机数的范围
	 * @param number
	 *            生成随机数的个数
	 * 
	 * @return int[] 生成的不重复的整数随机数数组
	 */
	public static int[] getExtentRandomNumbers(int extent, int number) {
		int[] rs = new int[number];
		for (int i = 0; i < number; i++) {
			int temp = getExtentRandomNumber(extent);
			boolean isHave = false;
			// 判断是否存在该随机数
			for (int j = 0; j < rs.length; j++) {
				if (rs[j] == temp) {
					isHave = true;
					break;
				}
			}
			if (isHave == true) {
				i--;
				continue;
			}
			rs[i] = temp;
		}
		return rs;
	}

	/**
	 * generate specified length string with numbers.
	 * 
	 * @param lengthOfNumber
	 *            the length of the number string to be created.
	 */
	public static String getRndNumByLen(int lengthOfNumber) {
		int i, count = 0;

		StringBuffer randomStr = new StringBuffer("");
		Random rnd = new Random();

		while (count < lengthOfNumber) {
			i = Math.abs(rnd.nextInt(9));
			if (i == 0 && count == 0) {
			} else {
				randomStr.append(String.valueOf(i));
				count++;
			}
		}
		return randomStr.toString();
	}

	/**
	 * generate specified length string with chars.
	 * 
	 * @param lengthOfString
	 *            the length of the string to be created.
	 */
	public static String getRndStrByLen(int lengthOfString) {
		int i, count = 0;
		final String chars = "1,2,3,4,5,6,7,8,9,0,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		String[] charArr = chars.split(",");

		StringBuffer randomStr = new StringBuffer("");
		Random rnd = new Random();

		while (count < lengthOfString) {
			i = Math.abs(rnd.nextInt(35) % charArr.length);
			randomStr.append(charArr[i]);
			count++;
		}
		return randomStr.toString();
	}

	public static void main(String[] args) {
		System.out.println("RandomNumberUtil.getRandomNumber:"
				+ RandomUtil.getRandomNumber(1000000, 9999999));
		System.out.println("RandomNumberUtil.getExtentRandomNumber:"
				+ RandomUtil.getExtentRandomNumber(9999999));
		System.out.println("RandomNumberUtil.getExtentRandomNumbers:"
				+ RandomUtil.getExtentRandomNumbers(9999999, 1)[0]);
		System.out.println("RandomNumberUtil.getRndNumByLen:"
				+ RandomUtil.getRndNumByLen(20));
		System.out.println("RandomNumberUtil.getRndStrByLen:"
				+ RandomUtil.getRndStrByLen(20));		
		
	}
}