package com.sinovatech.auto.excel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 通过list对象中的数据，进行按某个对象属性进行排序
 * 
 * 目前支持：字符串，数字，日期,布尔 四种数据类型排序
 * @author user
 */
public class OrderObjectUtil {

	/**
	 * 升序标志
	 */
	public static final String ASC = "ASC";

	/**
	 * 降序标志
	 */
	public static final String DESC = "DESC";

	/**
	 * 排序字段类型数字
	 */
	public static final String NUMBER = "Number";

	/**
	 * 排序字段类型文字
	 * 
	 */
	public static final String STRING = "String";

	/**
	 * 排序字段类型日期
	 */
	public static final String DATE = "Date";

	/**
	 * 排序字段Boolean
	 */
	public static final String BOOLEAN = "Boolean";

	/**
	 * 排序之后的对象
	 * 
	 */
	private List orberObjectBefor;

	/**
	 * 排序之胆的对象
	 * 
	 */
	private List orberObjectAfter;

	/**
	 * 排序类型 升序还是降序
	 */
	private String orderType = "ASC";

	/**
	 * 排序字段名
	 * 
	 * @return
	 */
	private String orderFieldName = null;

	/**
	 * 排序字段数据类型 数字还是文字
	 */
	private String OrderFieldDataType = "String";

	public String getOrderFieldName() {
		return orderFieldName;
	}

	public void setOrderFieldName(String orderFieldName) {
		this.orderFieldName = orderFieldName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * 获得排序后的list对象
	 * 
	 * @return
	 */
	public List getOrberObjectAfter() {
		return orberObjectAfter;
	}

	/**
	 * 设置需要排序的list对象
	 * 
	 * @param orberObjectBefor
	 */
	public void setOrberObjectBefor(List orberObjectBefor) {
		this.orberObjectBefor = orberObjectBefor;
	}

	/**
	 * 获得排序的数据类型
	 * 
	 * @return
	 */
	public String getOrderFieldDataType() {
		return OrderFieldDataType;
	}

	/**
	 * 设置排序的数据类型(String,Date,Number,Boolean)
	 * 
	 * @param orderFieldDataType
	 */
	public void setOrderFieldDataType(String orderFieldDataType) {
		OrderFieldDataType = orderFieldDataType;
	}

	/**
	 * 通过排离类型，排序字段
	 * 
	 * @param orderField
	 *            排序字段
	 * @param filedType
	 *            排序字段数据类型(数字或文字）
	 * @param orderType
	 *            排序类型（升序或降序）
	 * 
	 * @return
	 */
	public List findOrderByObject(String orderFieldName, String orderFieldDataType, String orderType) {
		this.orderType = orderType;
		this.OrderFieldDataType = orderFieldDataType;
		this.orderFieldName = orderFieldName;
		return this.findOrderByObject();

	}

	/**
	 * 通过排离类型，排序字段
	 * 
	 * @param orderObject
	 *            排序对象
	 * @param orderField
	 *            排序字段
	 * @param filedType
	 *            排序字段数据类型(数字或文字）
	 * @param orderType
	 *            排序类型（升序或降序）
	 * 
	 * @return
	 */
	public List findOrderByObject(List orderObject, String orderFieldName, String orderFieldDataType, String orderType) {
		this.orderType = orderType;
		this.orberObjectBefor = orderObject;
		this.OrderFieldDataType = orderFieldDataType;
		this.orderFieldName = orderFieldName;
		return this.findOrderByObject();

	}

	/**
	 * 返回排序后List对象
	 * 
	 * @return
	 */
	private List findOrderByObject() {
		if (this.orberObjectBefor == null || orderFieldName == null || "".equals(orderFieldName.trim())) {
			return this.orberObjectBefor;
		}
		String[][] OrderFiledBefor = this.getOrderFiledArray();
		String[][] OrderFiledAfter = this.orderFiled(OrderFiledBefor);
		this.orderObject(OrderFiledAfter);
		return this.orberObjectAfter;
	}

	/**
	 * 排序数组
	 * 
	 * @param field
	 * @return
	 */
	private String[][] orderFiled(String[][] fieldArray) {
		String[][] newFieldArray = null;
		if (fieldArray != null) {
			// 按中文排序

			if (OrderObjectUtil.STRING.equals(this.OrderFieldDataType)) {
				newFieldArray = this.orderByString(fieldArray);
			} else if (OrderObjectUtil.NUMBER.equals(this.OrderFieldDataType)) {
				// 按数字排序

				newFieldArray = this.orderByNumber(fieldArray);
			} else if (OrderObjectUtil.DATE.equals(this.OrderFieldDataType)) {
				// 按日期排序

				newFieldArray = this.orderByNumber(fieldArray);
			} else if (OrderObjectUtil.BOOLEAN.equals(this.OrderFieldDataType)) {
				// 布尔类型
				newFieldArray = this.orderByNumber(fieldArray);
			}
		}
		return newFieldArray;
	}

	/**
	 * 接字符串进行排序
	 * 
	 * @param fieldArray
	 *            待排序的数组
	 * @return
	 */
	private String[][] orderByString(String[][] fieldArray) {
		String[][] newFieldArray = null;
		String[] orderFieldDataAfter = new String[fieldArray.length];
		newFieldArray = new String[fieldArray.length][2];
		for (int i = 0; i < fieldArray.length; i++) {
			orderFieldDataAfter[i] = fieldArray[i][1];
		}
// 对值进行排序
		Arrays.sort(orderFieldDataAfter);


// 是否与上一次相同
		boolean islike = false;


// 当前数据值
		String curValue = null;


// 当前原始数据下标值
		int curIndex = -1;


		for (int i = 0; i < orderFieldDataAfter.length; i++) {
// 初始为false
			islike = false;

			if (i != 0 && orderFieldDataAfter[i].equals(curValue)) {
// 如果当前的
				islike = true;


			}
// 赋当前数据值
			curValue = orderFieldDataAfter[i];


// 开始长度
			int beginLength = 0;


			if (islike) {
// 如果有相同的则从后一个走
				beginLength = curIndex + 1;

			}
			for (int j = beginLength; j < fieldArray.length; j++) {
				if (fieldArray[j][1].equals(curValue)) {
					curIndex = j;
					break;
				}
			}
			newFieldArray[i][0] = Integer.toString(curIndex);
			newFieldArray[i][1] = curValue;
		}

		return newFieldArray;
	}

	/**
	 * 按数字进行排序
	 * 
	 * @param fieldArray
	 *            待排序的数组
	 * @return
	 */
	private String[][] orderByNumber(String[][] fieldArray) {
		String[][] newFieldArray = null;
		double[] orderFieldDataAfter = new double[fieldArray.length];
		newFieldArray = new String[fieldArray.length][2];
		// 获得待排序的列

		for (int i = 0; i < fieldArray.length; i++) {
			String tmpData = fieldArray[i][1];
			if (tmpData == null || "".equals(tmpData.trim())) {
				tmpData = "0";
			}
			orderFieldDataAfter[i] = Double.parseDouble(tmpData);
		}
// 对值进行排序
		Arrays.sort(orderFieldDataAfter);


// 是否与上一次相同
		boolean islike = false;


// 当前数据值
		double curValue = 0.0;


// 当前原始数据下标值
		int curIndex = -1;


		for (int i = 0; i < orderFieldDataAfter.length; i++) {
// 初始为false
			islike = false;

			if (i != 0 && orderFieldDataAfter[i] == curValue) {
// 如果当前的
				islike = true;


			}
// 赋当前数据值
			curValue = orderFieldDataAfter[i];


// 开始长度
			int beginLength = 0;


			if (islike) {
// 如果有相同的则从后一个走
				beginLength = curIndex + 1;

			}
			for (int j = beginLength; j < fieldArray.length; j++) {
				String tmpData = fieldArray[j][1];
				if (tmpData == null || "".equals(tmpData.trim())) {
					tmpData = "0";
				}
				double oldData = Double.parseDouble(tmpData);
				double newData = curValue;
				if (oldData == newData) {
					curIndex = j;
					break;
				}
			}
			newFieldArray[i][0] = Integer.toString(curIndex);
			newFieldArray[i][1] = Double.toString(curValue);
		}
		return newFieldArray;
	}

	/**
	 * 获得当前排序List对象中的字段属性字段
	 * 
	 * @param fieldName
	 * @return
	 */
	private String[][] getOrderFiledArray() {
		String[][] fieldArray = null;
		if (this.orberObjectBefor == null && this.orberObjectBefor.size() > 0) {
			return null;
		}
		fieldArray = new String[this.orberObjectBefor.size()][2];
		for (int i = 0; i < this.orberObjectBefor.size(); i++) {
			Object bean = this.orberObjectBefor.get(i);
			fieldArray[i][0] = Integer.toString(i);
			try {
				Object data = null;
				if (OrderObjectUtil.DATE.equals(this.OrderFieldDataType)) {
					data = this.getProperty(bean, this.orderFieldName);
				} else if (OrderObjectUtil.NUMBER.equals(this.OrderFieldDataType)
						|| this.OrderFieldDataType.equals(OrderObjectUtil.STRING)) {
					data = this.getProperty(bean, this.orderFieldName);
				} else if (OrderObjectUtil.BOOLEAN.equals(this.OrderFieldDataType)) {
					data = this.getProperty(bean, this.orderFieldName);
				}

				if (data != null) {
					if (data instanceof Date) {
						fieldArray[i][1] = Long.toString(((Date) data).getTime());
					} else if (OrderObjectUtil.NUMBER.equals(this.OrderFieldDataType)
							|| this.OrderFieldDataType.equals(OrderObjectUtil.STRING)) {
						fieldArray[i][1] = data.toString();
					} else if (OrderObjectUtil.BOOLEAN.equals(this.OrderFieldDataType)) {
						if ("true".equals(data.toString().toLowerCase())) {
							fieldArray[i][1] = "1";
						} else {
							fieldArray[i][1] = "0";
						}
					}
				} else {
					fieldArray[i][1] = "";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fieldArray;
	}

	/**
	 * 重新排序当前的List中的对象
	 * 
	 * @param OrderFiledAfter
	 */
	@SuppressWarnings("unchecked")
	private void orderObject(String[][] OrderFiledAfter) {
		if (OrderFiledAfter != null) {
			this.orberObjectAfter = new ArrayList();
			if (this.orderType.equals(OrderObjectUtil.ASC)) {
				for (int i = 0; i < OrderFiledAfter.length; i++) {
					Object object = this.orberObjectBefor.get(Integer.parseInt(OrderFiledAfter[i][0]));
					this.orberObjectAfter.add(object);
				}
			} else {
				for (int i = OrderFiledAfter.length - 1; i >= 0; i--) {
					Object object = this.orberObjectBefor.get(Integer.parseInt(OrderFiledAfter[i][0]));
					this.orberObjectAfter.add(object);
				}
			}
		}
	}

	/**
	 * 反射调用属性
	 * 
	 * @param owner
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	private Object getProperty(Object owner, String fieldName) throws Exception {
		Class ownerClass = owner.getClass();
		Field[] field = ownerClass.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			field[i].setAccessible(true);
			if (field[i].getName().equals(fieldName)) {
				return field[i].get(owner);
			}
		}
		return null;
	}
}
