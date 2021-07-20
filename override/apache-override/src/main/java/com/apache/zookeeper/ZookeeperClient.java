package com.apache.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperClient {
    private static final Logger LOG = LoggerFactory.getLogger(ZookeeperClient.class);
    private static ZookeeperClient zookeeperClient;
    private CuratorFramework client;
    private String zookeeperList;
    private long sessionTimeoutMs;
    private long connectTimeoutMs;
    private static ReentrantLock lock = new ReentrantLock();

    private static int DEFAULT_SESSION_TIMEOUT = 20000;
    private static int DEFAULT_CONNECT_TIMEOUT = 2000;

    private ZookeeperClient() {

    }

    private ZookeeperClient(String zookeeperList, int sessionTimeoutMs, int connectTimeoutMs) {
        this.zookeeperList = zookeeperList;
        this.sessionTimeoutMs = sessionTimeoutMs;
        this.connectTimeoutMs = connectTimeoutMs;
        initCuratorClient(zookeeperList, sessionTimeoutMs, connectTimeoutMs);
    }

    public static ZookeeperClient getInstance(String connectStr, int sessionTimeoutMs, int connectTimeoutMs) {
        if (null == zookeeperClient) {
            lock.lock();
            try {
                if (null == zookeeperClient) {
                    zookeeperClient = new ZookeeperClient(connectStr, sessionTimeoutMs, connectTimeoutMs);
                }
            } catch (Exception e) {
                LOG.error("获取ZookeeperClient 实例时异常:" + e.getMessage(), e);
            } finally {
                lock.unlock();
            }
        }
        return zookeeperClient;
    }

    public static ZookeeperClient getInstance(String connectStr) {
        return getInstance(connectStr, DEFAULT_SESSION_TIMEOUT, DEFAULT_CONNECT_TIMEOUT);
    }


    private void initCuratorClient(String connectStr, int sessionTimeoutMs, int connectTimeoutMs) {
        if (client == null) {
            client = CuratorFrameworkFactory.builder()
                    .connectString(connectStr)
                    .sessionTimeoutMs(sessionTimeoutMs)
                    .connectionTimeoutMs(connectTimeoutMs)
                    .retryPolicy(new RetryNTimes(1, 3000))
                    .build();
            client.start();
        }
    }


    public void createNode(String nodePath, String data) throws Exception {
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(nodePath, data.getBytes());
    }

    public void createNode(String nodePath, byte[] data) throws Exception {
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(nodePath, data);
    }

    public void createNode(String nodePath) throws Exception {
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(nodePath);
    }

    public void setNodeData(String nodePath, String data) throws Exception {
        client.setData().forPath(nodePath, data.getBytes());
    }

    public void createTemporaryNode(String nodePath, String data) throws Exception {
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(nodePath, data.getBytes());
    }

    public void createTemporaryNode(String nodePath) throws Exception {
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(nodePath);
    }

    public void addSessionListener(ConnectionStateListener sessionConnectionListener) {
        client.getConnectionStateListenable().addListener(sessionConnectionListener);
    }

    public void addChildNodeListener(PathChildrenCacheListener listener, String path, boolean cacheData) throws Exception {
        PathChildrenCache cache = new PathChildrenCache(client, path, cacheData);
        cache.start();
        cache.getListenable().addListener(listener);
    }

    public void addNodeListener(NodeCacheListener listener, String path) throws Exception {
        final NodeCache cache = new NodeCache(client, path, false);
        cache.start(true);
        cache.getListenable().addListener(listener);
    }

    public void addTreeListener(TreeCacheListener listener, String path) throws Exception {
        TreeCache cache = new TreeCache(client, path);
        cache.start();
        cache.getListenable().addListener(listener);
    }

    public CuratorFramework getClient() {
        return client;
    }

    public void delete(String path) throws Exception {
        client.delete().forPath(path);
    }

    public void forceDelete(String path) throws Exception {
        client.delete().deletingChildrenIfNeeded().forPath(path);
    }

    public List<String> getChildren(String path) {
        List<String> children = null;
        try {
            children = client.getChildren().forPath(path);
        } catch (Exception e) {
            LOG.error("获取路径的子节点" + path + "异常", e);
        }

        return children;
    }

    /**
     * 校验是否存在
     * @param path
     * @return
     * @throws Exception
     */
    public boolean checkExist(String path) throws Exception {
        return client.checkExists().forPath(path) != null;
    }

    /**
     *
     * @param path
     * @return
     * @throws Exception
     */
    public Stat getStat(String path) throws Exception {
        return client.checkExists().forPath(path);
    }

    public String getData(String path) throws Exception {
        byte[] bytes = client.getData().forPath(path);
        if (null != bytes) {
            return new String(bytes);
        }
        return null;
    }

    private static String getThreadInfo() {
        return "当前线程名称：" + Thread.currentThread().getName() +
                "; 当前线程ID:" + Thread.currentThread().getId();
    }

    /**
     * @param lockPath 锁路径
     * @param msg      上锁时和上锁后打印的提示信息
     * @return 返回InterProcessMutex 对象
     */
    public InterProcessMutex lock(String lockPath, String msg) {

        InterProcessMutex lock = null;
        try {
            if (null == lock) {
                lock = new InterProcessMutex(client, lockPath);
            }

            long start = System.currentTimeMillis();
            LOG.debug(msg + "，开始获取分布式锁:" + lockPath + ";" + getThreadInfo());
            lock.acquire();
            long end = System.currentTimeMillis();
            long period = end - start;
            LOG.debug(msg + "，成功获取分布式锁:" + lockPath + ", 耗时" + period + "ms" + ";" + getThreadInfo());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lock;
    }

    /**
     * @param lock     将要释放的锁对象
     * @param lockPath 锁对象锁的路径
     * @param msg      释放锁时打印的提示消息
     */
    public void release(InterProcessMutex lock, String lockPath, String msg) {
        if (null == lock) {
            LOG.error(msg + ";释放分布式锁" + lockPath + "时，锁对象为空");
            return;
        }

        try {
            lock.release();
            LOG.debug(msg + "后,释放分布式锁:" + lockPath + ";" + getThreadInfo());

        } catch (Exception e) {
            LOG.error(msg + ";释放分布式锁" + lockPath + "异常" + ";" + getThreadInfo(), e);
        }
    }
}
