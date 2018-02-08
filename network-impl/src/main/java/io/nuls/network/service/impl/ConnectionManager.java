///**
// * MIT License
// * <p>
// * Copyright (c) 2017-2018 nuls.io
// * <p>
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// * <p>
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// * <p>
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// */
//package io.nuls.network.service.impl;
//
//import io.nuls.core.constant.ErrorCode;
//import io.nuls.core.constant.ModuleStatusEnum;
//import io.nuls.core.constant.NulsConstant;
//import io.nuls.core.exception.NulsRuntimeException;
//import io.nuls.core.module.service.ModuleService;
//import io.nuls.core.thread.manager.TaskManager;
//import io.nuls.core.utils.log.Log;
//import io.nuls.network.constant.NetworkConstant;
//import io.nuls.network.entity.Node;
//import io.nuls.network.entity.NodeGroup;
//import io.nuls.network.entity.param.AbstractNetworkParam;
//import io.nuls.network.module.AbstractNetworkModule;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.nio.channels.SelectionKey;
//import java.nio.channels.Selector;
//import java.nio.channels.ServerSocketChannel;
//import java.nio.channels.SocketChannel;
//import java.nio.channels.spi.SelectorProvider;
//import java.util.Iterator;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * @author vivi
// * @date 2017-11-10
// */
//public class ConnectionManager implements Runnable {
//
//    private AbstractNetworkParam network;
//
//    private AbstractNetworkModule networkModule;
//
//    private NodesManager nodesManager;
//
//    private ServerSocketChannel serverSocketChannel;
//
//    private Selector selector;
//
//    private ReentrantLock lock;
//
//    private volatile boolean running;
//    //The storage will be connected
//
//    public ConnectionManager(  AbstractNetworkParam network) {
//        this.network = network;
//        this.networkModule = (AbstractNetworkModule) ModuleService.getInstance().getModule(NulsConstant.MODULE_ID_NETWORK);
//        lock = new ReentrantLock();
//    }
//
//    /**
//     * open the serverSocketChannel and register accept action
//     */
//    public void init() {
//        lock.lock();
//        try {
//            if (!running) {
//                selector = SelectorProvider.provider().openSelector();
//                serverSocketChannel = ServerSocketChannel.open();
//                serverSocketChannel.configureBlocking(false);
//                serverSocketChannel.bind(new InetSocketAddress(network.port()));
//                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
//            }
//        } catch (IOException e) {
//            serverClose();
//            throw new NulsRuntimeException(ErrorCode.NET_SERVER_START_ERROR, e);
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public void start() {
//        Log.info("----------- network connectionManager start -------------");
//        running = true;
//        TaskManager.createAndRunThread(NulsConstant.MODULE_ID_NETWORK, "connectionManager", this);
//    }
//
//    public void restart() {
//        init();
//        start();
//    }
//
//    @Override
//    public void run() {
//        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
//        try {
//            while (running) {
//                if (selector.select(1000) > 0) {
//                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
//                    while (keyIterator.hasNext()) {
//                        SelectionKey key = keyIterator.next();
//                        keyIterator.remove();
//                        handleKey(key);
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        finally {
////            serverClose();
////        }
//    }
//
//    public void serverClose() {
//        lock.lock();
//        try {
//            running = false;
//            for (SelectionKey key : selector.keys()) {
//                try {
//                    key.channel().close();
//                } catch (IOException e) {
//                    Log.warn("Error closing channel", e);
//                }
//                if (key.attachment() instanceof ConnectionHandler) {
//                    // Close connection if relevant
//                    ConnectionHandler.handleKey(key);
//                }
//                key.cancel();
//            }
//            try {
//                selector.close();
//                selector = null;
//                serverSocketChannel.close();
//                serverSocketChannel = null;
//            } catch (IOException e) {
//                Log.warn("Error closing manager selector and serverChannel", e);
//            }
//            Log.info("---------------------------Network closing connectManager---------------------");
//
//            //todo sent a event to other module
//            networkModule.setStatus(ModuleStatusEnum.DESTROYED);
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    /**
//     * out node try to connect
//     *
//     * @param node
//     */
//    public void openConnection(Node node) {
//
//        SocketChannel channel = null;
//        SelectionKey key = null;
//        try {
//            channel = SocketChannel.open();
//            channel.configureBlocking(false);
//            channel.socket().setReuseAddress(true);
//            InetSocketAddress socketAddress = new InetSocketAddress(node.getIp(), node.getPort());
//            channel.connect(socketAddress);
//            PendingConnect data = new PendingConnect(channel, node);
//            if (channel == null) {
//                System.out.println("----------------channel is null");
//            }
//            if (selector == null) {
//                System.out.println("----------------selector is null");
//            }
//            key = channel.register(selector, SelectionKey.OP_CONNECT);
//            key.attach(data);
//            selector.wakeup();
//        } catch (IOException e) {
//            e.printStackTrace();
//            if (channel != null) {
//                try {
//                    channel.close();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//            if (key != null) {
//                key.cancel();
//            }
//            node.destroy();
//        }
//    }
//
//
//    public boolean allowConnection(InetSocketAddress socketAddress) {
//        //check the connecting nodes count
//        boolean inAble = true;
//        boolean outAble = true;
//        NodeGroup inNodes = nodesManager.getNodeGroup(NetworkConstant.NETWORK_NODE_IN_GROUP);
//        if (inNodes.size() >= network.maxInCount()) {
//            inAble = false;
//        }
//        NodeGroup outNodes = nodesManager.getNodeGroup(NetworkConstant.NETWORK_NODE_OUT_GROUP);
//        if (outNodes.size() >= network.maxOutCount()) {
//            outAble = false;
//        }
//        if (!inAble && !outAble) {
//            return false;
//        }
//        //check myself
//        if (network.getLocalIps().contains(socketAddress.getAddress().getHostAddress())) {
//            return false;
//        }
//        //check it already connected
//        for (Node node : inNodes.getNodes().values()) {
//            if (node.getIp().equals(socketAddress.getAddress().getHostAddress()) &&
//                    node.getPort() == socketAddress.getPort()) {
//                return false;
//            }
//        }
//
//        for (Node node : outNodes.getNodes().values()) {
//            if (node.getIp().equals(socketAddress.getAddress().getHostAddress()) &&
//                    node.getPort() == socketAddress.getPort()) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//
//    public void handleKey(SelectionKey key) {
//
//        if (key.isValid() && key.isConnectable()) {
//            //out node
//            addOutNode(key);
//        } else if (key.isValid() && key.isAcceptable()) {
//            // in Node
//            addInNode(key);
//        } else {
//            // read or write
//            ConnectionHandler handler = (ConnectionHandler) key.attachment();
//            if (handler != null) {
//                ConnectionHandler.handleKey(key);
//            }
//        }
//    }
//
//    private void addOutNode(SelectionKey key) {
//        PendingConnect data = (PendingConnect) key.attachment();
//        if (data == null || data.node == null) {
//            return;
//        }
//        Node node = data.node;
//        node.setType(Node.OUT);
//        SocketChannel channel = (SocketChannel) key.channel();
//        ConnectionHandler handler = new ConnectionHandler(node, channel, key);
//        //Must be connected after the completion of registration to other events
//        try {
//            if (channel.finishConnect()) {
//                key.interestOps((key.interestOps() | SelectionKey.OP_READ) & ~SelectionKey.OP_CONNECT);
//                key.attach(handler);
//                node.setWriteTarget(handler);
//                node.connectionOpened();
//            } else {
//                // Failed to connect for some reason
//                throw new RuntimeException();
//            }
//        } catch (Exception e) {
//            Log.warn("out node Failed to connect:" + node.getIp() + ":" + node.getPort() + ",message:" + e.getMessage());
//            key.cancel();
//            node.destroy();
//        }
//    }
//
//    private void addInNode(SelectionKey key) {
//        SocketChannel socketChannel = null;
//        Node node = null;
//        SelectionKey newKey = null;
//        try {
//            socketChannel = serverSocketChannel.accept();
//            InetSocketAddress socketAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
//            if (!allowConnection(socketAddress)) {
//                socketChannel.close();
//                return;
//            }
//            socketChannel.configureBlocking(false);
//            newKey = socketChannel.register(selector, SelectionKey.OP_READ);
//
//            node = new Node(network, Node.IN, socketAddress);
//            nodesManager.addNodeToGroup(NetworkConstant.NETWORK_NODE_IN_GROUP, node);
//            ConnectionHandler handler = new ConnectionHandler(node, socketChannel, newKey);
//            node.setWriteTarget(handler);
//            newKey.attach(handler);
//            node.connectionOpened();
//        } catch (Exception e) {
//            Log.error(e);
//            if (socketChannel != null) {
//                Log.warn("-------------in node Failed to connect" + node.getIp() + "--------------");
//                try {
//                    socketChannel.close();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//            if (key != null) {
//                key.cancel();
//            }
//            if (newKey != null) {
//                key.cancel();
//            }
//            if (node != null) {
//                node.destroy();
//            }
//        }
//    }
//
//    public void setNodesManager(NodesManager nodesManager) {
//        this.nodesManager = nodesManager;
//    }
//
//    class PendingConnect {
//        SocketChannel channel;
//        Node node;
//
//        PendingConnect(SocketChannel channel, Node node) {
//            this.channel = channel;
//            this.node = node;
//        }
//    }
//
//}
