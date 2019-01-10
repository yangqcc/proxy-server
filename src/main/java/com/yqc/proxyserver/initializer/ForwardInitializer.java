package com.yqc.proxyserver.initializer;

import com.yqc.proxyserver.handler.ForwardHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author yangqc
 */
public class ForwardInitializer extends ChannelInitializer<SocketChannel> {

    private final Channel inboundChannel;

    public ForwardInitializer(Channel inboundChannel) {
        this.inboundChannel = inboundChannel;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addFirst(new ForwardHandler(inboundChannel));
    }
}
