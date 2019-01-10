package com.yqc.proxyserver.handler;

import com.yqc.proxyserver.cert.CertUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.ssl.SslContext;

/**
 * @author yangqc
 */
public class JudgeHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;
            String uri = httpRequest.uri();
            if (uri.contains("https")) {
                SslContext sslContext = CertUtil.createSslContext();
                if (sslContext != null) {
                    ChannelPipeline pipeline = ctx.channel().pipeline();
                    pipeline.addFirst(sslContext.newHandler(ctx.alloc()));
                } else {
                    ctx.writeAndFlush("sorry,please check the key!");
                }
            } else {

            }
        }
    }
}
