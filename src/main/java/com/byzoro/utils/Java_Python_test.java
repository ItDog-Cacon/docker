package com.byzoro.utils;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class Java_Python_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile("K:\\0722-json\\to_sq.py");
				
		// 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
		PyFunction pyFunction = interpreter.get("readFile", PyFunction.class);
		String a = "K:\\0722-json\\msg_dst\\51_01.json";
		int b = 0;
//		调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
		while(true) {
			PyObject pyobj = pyFunction.__call__(new PyString(a), new PyInteger(b));
//		System.out.println(pyobj);
			String s = pyobj.toString();
			String s1 = s.split("kmh")[1];
			System.out.println(s1);
		}
//		System.out.println(s);
	}
}