package com.yqc.proxyserver.handler;

import com.yqc.proxyserver.initializer.ForwardInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;

import java.net.InetSocketAddress;

/**
 * @author yangqc
 */
public class ReceiveHandler extends SimpleChannelInboundHandler<HttpObject> {

    private final NioEventLoopGroup forwardGroup;

    private ReceiveHandler(NioEventLoopGroup forwardGroup) {
        this.forwardGroup = forwardGroup;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            String uri = ((HttpRequest) msg).uri();
            String[] strings = uri.split(":");
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(forwardGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ForwardInitializer(ctx.channel()));
            bootstrap.connect(new InetSocketAddress(strings[0], Integer.parseInt(strings[1])));
        }
    }

}
