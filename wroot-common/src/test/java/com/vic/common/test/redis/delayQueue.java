package com.vic.common.test.redis;

import java.util.Set;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.Tuple;

/** * 延时队列 * @author Knight-Ran * */
public class delayQueue {
	private Jedis jedis;
	private JedisPool pool;
	private static final String QUEUE_NAME = "deplay_queue";

	@Before
	public void setUp() {
		//pool = new JedisPool(new JedisPoolConfig(), "localhost");
		pool = new JedisPool(new JedisPoolConfig(), "localhost", Protocol.DEFAULT_PORT, Protocol.DEFAULT_TIMEOUT, "123456!@#$%^");
		jedis = pool.getResource();
	}

	@After
	public void after() {
		jedis.close();
		pool.destroy();
	}
	// 模拟任务处理队列

	public static void addToTaskQue(String taskInfo) {
		System.out.println(taskInfo + "已经从延时队列中转至队列" + "当前时间:" + System.currentTimeMillis());
		System.out.println();
	}

	public void addToDeplayQueue(Task task) {
		System.out.println(task.toString() + "已经加入延时队列");
		jedis.zadd(QUEUE_NAME, task.getTime(), task.toString());
	}

	public void transferFromDelayQueue() throws InterruptedException {
		while (true) {
			Set<Tuple> item = jedis.zrangeWithScores(QUEUE_NAME, 0, 0);
			if (item != null && !item.isEmpty()) {
				Tuple tuple = item.iterator().next();
				if (System.currentTimeMillis() >= tuple.getScore()) {
					// TODO 获取锁
					jedis.zrem(QUEUE_NAME, tuple.getElement()); // 从延时队列中移除
					addToTaskQue(tuple.getElement());
					// 任务推入延时队列，因为这里只是延时 // TODO 释放锁
				}
			}
			Thread.sleep(100);
		}
	}

	@Test
	public void test() throws InterruptedException {
		long now = System.currentTimeMillis();
		Task task = new Task(UUID.randomUUID().toString(), now + 10 * 1000, 10 * 1000 + "后执行");
		addToDeplayQueue(task);
		task = new Task(UUID.randomUUID().toString(), now + 20 * 1000, 20 * 1000 + "后执行");
		addToDeplayQueue(task);
		task = new Task(UUID.randomUUID().toString(), now + 30 * 1000, 30 * 1000 + "后执行");
		addToDeplayQueue(task);
		task = new Task(UUID.randomUUID().toString(), now + 40 * 1000, 40 * 1000 + "后执行");
		transferFromDelayQueue();
	}

	static class Task {
		// 任务id
		private String id;
		// 任务执行时间
		private long time;

		// // 描述
		private String desc;

		public Task(String id, long time, String desc) {
			this.id = id;
			this.time = time;
			this.desc = desc;
		}

		public String getId() {
			return id;
		}

		public long getTime() {
			return time;
		}

		public String getDesc() {
			return desc;
		}

		@Override
		public String toString() {
			return "Task [id=" + id + ", time=" + time + ", desc=" + desc + "]";
		}
	}
}