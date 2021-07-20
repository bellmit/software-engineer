package com.apache.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/7/11 19:41
 * @Description
 */
public class ZookeeperClientTest {

    private final static Logger LOG = LoggerFactory.getLogger(ZookeeperClientTest.class);

    private ZookeeperClient zookeeper;
    private static ReentrantLock lock = new ReentrantLock();

    @Before
    public void init() {
        lock.lock();
        try {
            zookeeper = ZookeeperClient.getInstance("10.200.200.178:2181,10.200.40.176:2181,10.200.40.64:2181,10.200.40.177:2181", 2000, 2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void getInstanceTest() throws Exception {
        ZookeeperClient client = ZookeeperClient.getInstance("10.200.200.178:2181,10.200.40.176:2181,10.200.40.64:2181,10.200.40.177:2181", 2000, 2000);
        System.out.println(client.checkExist("/datasocket"));
        System.out.println(client.getChildren("/"));
    }

    @Test
    public void createNode() throws Exception {
        zookeeper.createNode("/datasocket/test-au1");
        //System.out.println(zookeeper.getChildren("/datasocket"));
        Assert.assertEquals(zookeeper.getChildren("/datasocket").contains("test-au1"), true);
    }


    @Test
    public void watchZkMsgTest() throws Exception {
        TreeCache treeCache = new TreeCache(getInstance(), "/datasocket-test-xql");
        treeCache.getListenable().addListener(plic);
        treeCache.start();
    }

    public TreeCacheListener plic = new TreeCacheListener() {
        @Override
        public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
            switch (treeCacheEvent.getType()) {
                case NODE_ADDED:
                case NODE_UPDATED: {
                    String runnerName = null;
                    try {
                        Runnable run = new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("监听到事件 " + curatorFramework.getData());
                            }
                        };
                        run.run();
                    } catch (Exception e) {
                        LOG.error("手动分发任务" + runnerName + "异常", e);
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


    @Test
    public void BeanCRUD() throws Exception {

        String path = "/mm/test";
        CuratorFramework client = getInstance();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "hello zk".getBytes());
//            client.inTransaction().check().forPath(path).and().setData().forPath(path, "hello zk too".getBytes());
//            client.delete().deletingChildrenIfNeeded().forPath(path);


    }


    public CuratorFramework getInstance() {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString("10.200.200.178:2181,10.200.40.176:2181,10.200.40.64:2181,10.200.40.177:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000, 2, 100));

        builder.sessionTimeoutMs(60000);
        builder.connectionTimeoutMs(15000);
        CuratorFramework client = builder.build();

        client.start();
        try {
            if (!client.blockUntilConnected(1000, TimeUnit.MILLISECONDS)) {
                client.close();
                throw new KeeperException.OperationTimeoutException();
            }
            //CHECKSTYLE:OFF
        } catch (final Exception ex) {
            //CHECKSTYLE:ON
        }
        return client;
    }


    // 待测试
    public boolean createNode(String nodePath, byte[] data, boolean tmp, CuratorFramework client) {
        boolean ok = true;
        try {
            if (tmp) {
                client.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(nodePath, data);
            } else {
                client.create().creatingParentContainersIfNeeded().forPath(nodePath, data);
            }
        } catch (Exception e) {
            ok = false;
            LOG.error("创建ZK node时发生异常;path:" + nodePath, e);
        }

        return ok;
    }



    // 获取锁
    public



}
