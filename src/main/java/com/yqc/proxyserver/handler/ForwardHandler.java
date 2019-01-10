package com.yqc.proxyserver.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author yangqc
 */
public class ForwardHandler extends ChannelInboundHandlerAdapter {

    private final Channel inboundChannel;

    public ForwardHandler(Channel inboundChannel) {
        this.inboundChannel = inboundChannel;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        inboundChannel.writeAndFlush(msg);
    }
}
