package com.ruiziot.test;

import java.lang.reflect.Field;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantLock;

import javax.sql.rowset.spi.SyncResolver;

import model.TagInfo;

public class Test {

	public static TagInfo whatthefuck = new TagInfo();

	static {
		System.out.println("in test");
	}

	public class testt {

	}
	
	static volatile int c;

	public static <T> void main(String[] args) throws Exception {

		ReentrantLock lock = new ReentrantLock(true);
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				
				
					System.out.println("ma");
					for (int i = 0;  i < 100; i++) {
						
						try {
							lock.lock();
						    System.out.println("....");
							c++;
						    /*System.out.println("dong");
							if(c > 16) {
								System.out.println("bang!");
							}*/
						} finally {
							lock.unlock();
						}
					}
				
				
				
			}
		}).start();
		
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				if(c > 44) {
					System.out.println("t2");
				}
				
				
				for (int i = 0; i < 106; i++) {
					
					
					
					System.out.println("bout of snc" + i);
					
					
					   try {
						   lock.lock();
						   c++;
					}  finally {
						lock.unlock();
					}
						
						
						
						System.out.println(c);
					
					System.out.println("out of snc");
				}
			}
		}).start();

		// new Test().show();
		
		

	}
	
	
	private void show() throws NoSuchFieldException, SecurityException,
			ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException {

		String propertyName = "whatthefuck";
		System.out.println(Class.forName(this.getClass().getName())
				.getDeclaredField(propertyName));;

		Class<Test> cl = Test.class;

		System.out.println(cl.getField(propertyName).get(null));
		Field[] fels = cl.getDeclaredFields();
		for (Field field : fels) {
			System.out.println(field.getName());
		}
		// System.out.println(cl.getDeclaredField("ti").toString());;

		System.out.println("doneing sth else");
	}

}
