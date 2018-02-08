/**
 * MIT License
 *
 * Copyright (c) 2017-2018 nuls.io
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.nuls.network.service.impl;

import io.nuls.core.event.BaseEvent;
import io.nuls.core.mesasge.NulsMessage;
import io.nuls.core.utils.log.Log;
import io.nuls.network.constant.NetworkConstant;
import io.nuls.network.entity.BroadcastResult;
import io.nuls.network.entity.Node;
import io.nuls.network.entity.param.AbstractNetworkParam;
import io.nuls.network.service.Broadcaster;

import java.io.IOException;
import java.nio.channels.NotYetConnectedException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vivi
 * @date 2017/11/29.
 */
public class BroadcasterImpl implements Broadcaster {
    @Override
    public BroadcastResult broadcast(BaseEvent event) {
        return null;
    }

    @Override
    public BroadcastResult broadcast(BaseEvent event, String excludeNodeId) {
        return null;
    }

    @Override
    public BroadcastResult broadcast(byte[] data) {
        return null;
    }

    @Override
    public BroadcastResult broadcast(byte[] data, String excludeNodeId) {
        return null;
    }

    @Override
    public BroadcastResult broadcastToNode(BaseEvent event, String nodeId) {
        return null;
    }

    @Override
    public BroadcastResult broadcastToNode(byte[] data, String nodeId) {
        return null;
    }

    @Override
    public BroadcastResult broadcastToGroup(BaseEvent event, String groupName) {
        return null;
    }

    @Override
    public BroadcastResult broadcastToGroup(String area, BaseEvent event, String groupName) {
        return null;
    }

    @Override
    public BroadcastResult broadcastToGroup(BaseEvent event, String groupName, String excludeNodeId) {
        return null;
    }

    @Override
    public BroadcastResult broadcastToGroup(BaseEvent event, String area, String groupName, String excludeNodeId) {
        return null;
    }

    @Override
    public BroadcastResult broadcastToGroup(byte[] data, String groupName) {
        return null;
    }

    @Override
    public BroadcastResult broadcastToGroup(String area, byte[] data, String groupName) {
        return null;
    }

    @Override
    public BroadcastResult broadcastToGroup(byte[] data, String groupName, String excludeNodeId) {
        return null;
    }

    @Override
    public BroadcastResult broadcastToGroup(String area, byte[] data, String groupName, String excludeNodeId) {
        return null;
    }

//    private NodesManager nodesManager;
//
//    private AbstractNetworkParam network;
//
//    public BroadcasterImpl(NodesManager nodesManager, AbstractNetworkParam network) {
//        this.nodesManager = nodesManager;
//        this.network = network;
//    }
//
//    /**
//     * At least maxOutCount() Nodes should be broadcast success
//     *
//     * @param message
//     * @param excludeNodeId
//     * @return
//     */
//    private BroadcastResult broadcast(NulsMessage message, String excludeNodeId) {
//        List<Node> broadNodes = nodesManager.getAvailableNodes(excludeNodeId);
//        //todo only one node connected can't send message
//        if (broadNodes.size() < NetworkConstant.NETWORK_BROAD_SUCCESS_MIN_COUNT) {
//            return new BroadcastResult(false, "no node can be broadcast");
//        }
//
//        int successCount = 0;
//        int count = 0;
//        BroadcastResult result = new BroadcastResult(true, "OK");
//        List<Node> successNodes = new ArrayList<>();
//
//        while (successCount < network.maxOutCount() && count < NetworkConstant.NETWORK_BROAD_MAX_TRY_COUNT) {
//            for (Node node : broadNodes) {
//                if (successNodes.contains(node)) {
//                    continue;
//                }
//                try {
//                    node.sendMessage(message);
//                    successNodes.add(node);
//                    successCount++;
//                } catch (NotYetConnectedException | IOException e) {
//                    Log.warn("broadcast message error ， maybe the node closed ! node ip :{}, {}", node.getIp(), e.getMessage());
//                }
//            }
//            if (successCount < network.maxOutCount()) {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                broadNodes = nodesManager.getAvailableNodes(excludeNodeId);
//            }
//            count++;
//        }
//
//        Log.debug("成功广播给{}个节点，消息{}", successCount, message);
//        result.setBroadcastNodes(successNodes);
//        return result;
//    }
//
//    /**
//     * Broadcast half of the nodes, waiting for the other half to reply
//     *
//     * @param message
//     * @return
//     */
//    private BroadcastResult broadcastSync(NulsMessage message, String excludeNodeId) {
////        List<Node> broadNodes = nodesManager.getAvailableNodes(excludeNodeId);
////
////        if (broadNodes.size() <= 0) {
////            return new BroadcastResult(false, "no node can be broadcast");
////        }
////
////        int numConnected = broadNodes.size();
////        int numToBroadcastTo = (int) Math.max(1, Math.round(Math.ceil(broadNodes.size() / 2.0)));
////        Collections.shuffle(broadNodes);
////        broadNodes = broadNodes.subList(0, numToBroadcastTo);
////
////        int successCount = 0;
////        for (Node node : broadNodes) {
////            try {
////                node.sendMessage(message);
////                successCount++;
////            } catch (IOException e) {
////                broadNodes.remove(node);
////                Log.warn("broadcast message error ， maybe the node closed ! node ip :{}, {}", node.getIp(), e.getMessage());
////            }
////        }
////        if (successCount == 0) {
////            return new BroadcastResult(false, "broadcast fail");
////        }
////
////        BroadcastResult result = new BroadcastResult(true, "OK");
////        result.setHash(Sha256Hash.twiceOf(message.getData()).toString());
////        result.setBroadcastNodes(broadNodes);
////        result.setWaitReplyCount(numConnected - numToBroadcastTo);
////        NetworkCacheService.getInstance().addBroadCastResult(result);
//
//        return null;
//    }
//
//
//    private BroadcastResult broadcastToNode(NulsMessage message, String nodeId) {
//        Node node = nodesManager.getNode(nodeId);
//        if (node == null || node.getStatus() != Node.HANDSHAKE) {
//            return new BroadcastResult(false, "no node can be broadcast");
//        }
//        try {
//            node.sendMessage(message);
//        } catch (NotYetConnectedException | IOException e) {
//            Log.warn("broadcast message error ， maybe the node closed ! node ip :{}, {}", node.getIp(), e.getMessage());
//            return new BroadcastResult(false, "broadcast fail");
//        }
//        List<Node> broadNodes = new ArrayList<>();
//        broadNodes.add(node);
//        return new BroadcastResult(true, "OK", broadNodes);
//    }
//
//    private BroadcastResult broadcastToGroup(NulsMessage message,String areaName, String groupName, String excludeNodeId) {
//        List<Node> broadNodes = nodesManager.getGroupAvailableNodes(areaName, groupName, excludeNodeId);
//        if (broadNodes.size() <= 1) {
//            return new BroadcastResult(false, "no node can be broadcast");
//        }
//
//        int successCount = 0;
//        for (Node node : broadNodes) {
//            try {
//                node.sendMessage(message);
//                successCount++;
//            } catch (NotYetConnectedException | IOException e) {
//                Log.warn("broadcast message error ， maybe the node closed ! node ip :{}, {}", node.getIp(), e.getMessage());
//            }
//        }
//
//        if (successCount == 0) {
//            new BroadcastResult(false, "broadcast fail", broadNodes);
//        }
//        Log.debug("成功广播给{}个节点，消息{}", successCount, message);
//        return new BroadcastResult(true, "OK");
//    }
//
//    private BroadcastResult broadcastToGroup(NulsMessage message, String groupName, String excludeNodeId) {
//        return  broadcastToGroup(message,null,groupName,excludeNodeId);
//    }
//
//
//    private BroadcastResult broadcastToGroupSync(NulsMessage message, String groupName, String excludeNodeId) {
//
//        return null;
//    }
//
//
//    @Override
//    public BroadcastResult broadcast(BaseEvent event) {
//        return broadcast(event, null);
//    }
//
//    @Override
//    public BroadcastResult broadcast(BaseEvent event, String excludeNodeId) {
//        NulsMessage message = null;
//        try {
//            message = new NulsMessage(network.packetMagic(), event.serialize());
//        } catch (IOException e) {
//            return new BroadcastResult(false, "event.serialize() error");
//        }
//
//        return broadcast(message, excludeNodeId);
//    }
//
//    @Override
//    public BroadcastResult broadcast(byte[] data) {
//        return broadcast(data, null);
//    }
//
//    @Override
//    public BroadcastResult broadcast(byte[] data, String excludeNodeId) {
//        NulsMessage message = new NulsMessage(network.packetMagic(), data);
//        return broadcast(message, excludeNodeId);
//    }
//
//    public BroadcastResult broadcastSync(BaseEvent event) {
//        return broadcastSync(event, null);
//    }
//
//    public BroadcastResult broadcastSync(BaseEvent event, String excludeNodeId) {
//        NulsMessage message = null;
//        try {
//            message = new NulsMessage(network.packetMagic(), event.serialize());
//        } catch (IOException e) {
//            return new BroadcastResult(false, "event.serialize() error");
//        }
//
//        return broadcastSync(message, excludeNodeId);
//    }
//
//    public BroadcastResult broadcastSync(byte[] data) {
//        return broadcastSync(data, null);
//    }
//
//    public BroadcastResult broadcastSync(byte[] data, String excludeNodeId) {
//        NulsMessage message = new NulsMessage(network.packetMagic(), data);
//        return broadcastSync(message, excludeNodeId);
//    }
//
//    @Override
//    public BroadcastResult broadcastToNode(BaseEvent event, String nodeId) {
//        NulsMessage message = null;
//        try {
//            message = new NulsMessage(network.packetMagic(), event.serialize());
//        } catch (IOException e) {
//            return new BroadcastResult(false, "event.serialize() error");
//        }
//        return broadcastToNode(message, nodeId);
//    }
//
//    @Override
//    public BroadcastResult broadcastToNode(byte[] data, String nodeId) {
//        NulsMessage message = new NulsMessage(network.packetMagic(), data);
//        return broadcastToNode(message, nodeId);
//    }
//
//    @Override
//    public BroadcastResult broadcastToGroup(BaseEvent event, String groupName) {
//        return broadcastToGroup(event, groupName, null);
//    }
//
//    @Override
//    public BroadcastResult broadcastToGroup(String area, BaseEvent event, String groupName) {
//        return null;
//    }
//
//    @Override
//    public BroadcastResult broadcastToGroup(BaseEvent event, String groupName, String excludeNodeId) {
//        return broadcastToGroup(event,null ,groupName, excludeNodeId);
//    }
//
//    @Override
//    public BroadcastResult broadcastToGroup(BaseEvent event,String areaName, String groupName, String excludeNodeId) {
//        NulsMessage message = null;
//        try {
//            message = new NulsMessage(network.packetMagic(), event.serialize());
//        } catch (IOException e) {
//            return new BroadcastResult(false, "event.serialize() error");
//        }
//        return broadcastToGroup(message,areaName, groupName, excludeNodeId);
//    }
//
//    @Override
//    public BroadcastResult broadcastToGroup(byte[] data, String groupName) {
//        return broadcastToGroup(null, data, groupName, null);
//    }
//
//    @Override
//    public BroadcastResult broadcastToGroup(String area,byte[] data, String groupName) {
//        return null;
//    }
//
//    @Override
//    public BroadcastResult broadcastToGroup(byte[] data, String groupName, String excludeNodeId) {
//        return broadcastToGroup(null, data, groupName, excludeNodeId);
//    }
//
//    @Override
//    public BroadcastResult broadcastToGroup(String area, byte[] data, String groupName, String excludeNodeId) {
//        NulsMessage message = new NulsMessage(network.packetMagic(), data);
//        return broadcastToGroup(message, groupName, excludeNodeId);
//    }
//
//    public BroadcastResult broadcastToGroupSync(BaseEvent event, String groupName) {
//        return broadcastToGroupSync(event, groupName, null);
//    }
//
//    public BroadcastResult broadcastToGroupSync(BaseEvent event, String groupName, String excludeNodeId) {
//        NulsMessage message = null;
//        try {
//            message = new NulsMessage(network.packetMagic(), event.serialize());
//        } catch (IOException e) {
//            return new BroadcastResult(false, "event.serialize() error");
//        }
//        return broadcastToGroupSync(message, groupName, excludeNodeId);
//    }
//
//    public BroadcastResult broadcastToGroupSync(byte[] data, String groupName) {
//        return broadcastToGroupSync(data, groupName, null);
//    }
//
//    public BroadcastResult broadcastToGroupSync(byte[] data, String groupName, String excludeNodeId) {
//        NulsMessage message = new NulsMessage(network.packetMagic(), data);
//        return broadcastToGroupSync(message, groupName, excludeNodeId);
//    }

}
