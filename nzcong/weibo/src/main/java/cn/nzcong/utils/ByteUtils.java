package cn.nzcong.utils;

import java.util.Random;

public class ByteUtils {

	/** 在字节数组bytes的位置index处开始，增加boolean类型 TURE 1 FALSE 0 */
	public static int addBoolean(byte bytes[], int index, boolean b) {
		if (b) {
			bytes[index] = 1;
		} else {
			bytes[index] = 0;
		}
		return index + 1;
	}

	/** 在字节数组bytes的位置index处开始，读取一个boolean类型 TURE 1 FALSE 0 */
	public static boolean readBoolean(byte bytes[], int index) {
		if (bytes[index] == 0)
			return false;
		else {
			return true;
		}
	}

	/** 在字节数组bytes的位置index处开始，增加有符号的8位整型 -128-127 */
	public static int addByte(byte bytes[], int index, byte b) {
		bytes[index] = b;
		return index + 1;
	}

	/** 在字节数组bytes的位置index处开始，增加无符号的8位整型 0-255 */
	public static int addUnsignedByte(byte bytes[], int index, int i) {
		bytes[index] = (byte) i;
		return index + 1;
	}

	/** 从字节数组bytes的位置index处，读取一个有符号的8位整型 -128-127 */
	public static byte readByte(byte bytes[], int index) {
		return bytes[index];
	}

	/** 从字节数组bytes的位置index处，读取一个无符号的8位整型 0-255 */
	public static int readUnsignedByte(byte bytes[], int index) {
		return bytes[index] & 0xFF;
	}

	/** 在字节数组bytes的位置index处开始，增加有符号的16位整型 -32768-32767 */
	public static int addShort(byte bytes[], int index, short s) {
		for (int k = 0; k < 2; k++) {
			bytes[(index + 1) - k] = (byte) s;
			s >>= 8;
		}

		return index + 2;
	}

	/** 在字节数组bytes的位置index处开始，增加无符号的16位整型 0-65535 */
	public static int addUnsignedShort(byte bytes[], int index, int i) {
		for (int k = 0; k < 2; k++) {
			bytes[(index + 1) - k] = (byte) i;
			i >>= 8;
		}

		return index + 2;
	}

	/** 从字节数组bytes的位置index处，读取一个有符号的16位整型 -32768-32767 */
	public static short readShort(byte bytes[], int index) {
		short s = 0;
		for (int k = 0; k < 2; k++) {
			s <<= 8;
			s |= bytes[index + k] & 0xff;
		}

		return s;

	}

	/** 从字节数组bytes的位置index处，读取一个无符号的16位整型 0-65535 */
	public static int readUnsignedShort(byte bytes[], int index) {
		short s = 0;
		for (int k = 0; k < 2; k++) {
			s <<= 8;
			s |= bytes[index + k] & 0xff;
		}
		return s & 0x0FFFF;
	}

	/** 在字节数组bytes的位置index处开始，增加有符号的32位整型 -2147483648-2147483647 */
	public static int addInteger(byte bytes[], int index, int i) {
		for (int k = 0; k < 4; k++) {
			bytes[(index + 3) - k] = (byte) i;
			i >>= 8;
		}

		return index + 4;
	}

	/** 在字节数组bytes的位置index处开始，增加无符号的32位整型 0-4294967295 */
	public static int addUnsignedInteger(byte bytes[], int index, long l) {
		for (int k = 0; k < 4; k++) {
			bytes[(index + 3) - k] = (byte) l;
			l >>= 8;
		}

		return index + 4;
	}

	/** 从字节数组bytes的位置index处，读取一个有符号的32位整型 -2147483648-2147483647 */
	public static int readInteger(byte bytes[], int index) {
		int i = 0;
		for (int k = 0; k < 4; k++) {
			i <<= 8;
			i |= bytes[index + k] & 0xff;
		}

		return i;
	}

	/** 从字节数组bytes的位置index处，读取一个无符号的32位整型 0-4294967295 */
	public static long readUnsignedInteger(byte bytes[], int index) {
		int i = 0;
		for (int k = 0; k < 4; k++) {
			i <<= 8;
			i |= bytes[index + k] & 0xff;
		}
		return i & 0x0FFFFFFFFL;
	}

	/** 在字节数组bytes的位置index处开始，增加有符号的64位整型 */
	public static int addLong(byte bytes[], int index, long l) {
		for (int k = 0; k < 8; k++) {
			bytes[(index + 7) - k] = (byte) (int) l;
			l >>= 8;
		}

		return index + 8;
	}

	/** 在字节数组bytes的位置index处开始，增加byte型数组值addBytes */
	public static int addBytes(byte bytes[], int index, byte addBytes[]) {
		System.arraycopy(addBytes, 0, bytes, index, addBytes.length);
		return index + addBytes.length;
	}

	/**
	 * 在字节数组bytes的位置index处开始，增加byte型数组值addBytes，增加的长度是len，数组超长将被截断,位数不够将后补0
	 **/
	public static int addBytes(byte bytes[], int index, byte addBytes[], int len) {
		if (addBytes.length > len) {
			System.arraycopy(addBytes, 0, bytes, index, len);
		} else {
			System.arraycopy(addBytes, 0, bytes, index, addBytes.length);
		}
		for (int l = addBytes.length; l < len; l++)
			bytes[index + l] = 0;

		return index + len;
	}

	/**
	 * 在字节数组bytes的位置index处开始，增加byte型数组值addBytes，增加的长度是len，数组超长将被截断,位数不够将前补0
	 **/
	public static int addBytes1(byte bytes[], int index, byte addBytes[], int len) {
		if (addBytes.length > len) {
			System.arraycopy(addBytes, 0, bytes, index, len);
		} else {
			System.arraycopy(addBytes, 0, bytes, index + (len - addBytes.length), addBytes.length);
		}
		// for (int l = addBytes.length; l < len; l++)
		// bytes[index + l] = 0;

		return index + len;
	}

	/** 从字节数组bytes的位置index处开始，读取一个长度为len的byte数组,如果从index开始bytes数组不足len的长度，返回null */
	public static byte[] readBytes(byte bytes[], int index, int len) {
		if (index + len > bytes.length) {
			return null;
		} else {
			byte data[] = new byte[len];
			System.arraycopy(bytes, index, data, 0, len);
			return data;
		}
	}

	/** 将字节数组按字节进行异或操作，生成校验码 */
	public static byte getCheckSum(byte bytes[]) {
		byte checkeSum = bytes[0];
		for (int i = 1; i < bytes.length; i++) {
			checkeSum = (byte) (checkeSum ^ bytes[i]);
		}
		return checkeSum;
	}

	/** 将16进制的字符串转换成字节数组 */
	public static byte[] getBytesFromHex(String hexstr) {
		byte[] bytes = new byte[hexstr.length() / 2];
		if ((hexstr.length()) % 2 != 0) {
			return null;
		}
		for (int i = 0; i < hexstr.length() / 2; i++) {
			byte[] temp = new byte[2];
			temp[0] = (byte) hexstr.charAt(i * 2);
			temp[1] = (byte) hexstr.charAt(i * 2 + 1);
			bytes[i] = (byte) (Integer.parseInt(new String(temp), 16));
		}

		return bytes;
	}

	/** 将字节数组bytes转换成16进制的字符串 */
	public static String getHexString(byte[] bytes) {
		StringBuffer str = new StringBuffer();
		if (bytes != null && bytes.length > 0) {
			for (int i = 0; i < bytes.length; i++) {
				int l4 = bytes[i] & 0x0f;
				int h4 = (bytes[i] >> 4) & 0x0f;
				str.append(Integer.toHexString(h4));
				str.append(Integer.toHexString(l4));
			}
		}
		return str.toString();
	}

	/** 将byte转换成16进制的字符串 */
	public static String getHexString(byte b) {
		String str = Integer.toHexString(b & 0xFF);
		if (str.length() < 2) {
			str = "0" + str;
		}
		return str;
	}

	/** 将short转换成16进制的字符串 */
	public static String getHexString(short b) {
		String str = Integer.toHexString(b);
		if (str.length() < 4) {
			for (int i = 0; i < (4 - str.length()); i++) {
				str = "0" + str;
			}
		}
		return str;
	}

	/** 将int转换成16进制的字符串 */
	public static String getHexString(int b) {
		String str = Integer.toHexString(b);
		if (str.length() < 8) {
			for (int i = 0; i < (8 - str.length()); i++) {
				str = "0" + str;
			}
		}
		return str;
	}

	/** 将long转换成16进制的字符串 */
	public static String getHexString(long b) {
		String str = Long.toHexString(b);
		if (str.length() < 16) {
			for (int i = 0; i < (16 - str.length()); i++) {
				str = "0" + str;
			}
		}
		return str;
	}
	
	public static byte[] getRandomByte(int length){
		byte[] b = new byte[length];
		for(int i = 0 ; i < length ; i++){
			b[i] = (byte)new Random().nextInt(255);
		}
		return b;
	}
	
	public static void main(String[] args) {
		byte bytes[] = new byte[20];
		int pos = 0;
		int a = -65535;
		addUnsignedByte(bytes, pos, a);
		System.out.print(getHexString(bytes));
	}

}
