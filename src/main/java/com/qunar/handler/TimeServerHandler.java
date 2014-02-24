package com.qunar.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用Unix rdate -o 8888 -p 192.168.116.70测试
 *
 * Date: 2014-02-24 18:51
 *
 * @author sen.chai
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    public static final Logger logger = LoggerFactory.getLogger(TimeServerHandler.class);

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        ByteBuf timeBuffer = ctx.alloc().buffer(4);
        timeBuffer.writeInt((int)(System.currentTimeMillis() / 1000L + 2208988800L));
        ChannelFuture future = ctx.writeAndFlush(timeBuffer);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                logger.debug("operationComplete!");
                ctx.close();
            }
        });
        //Alternatively
        //future.addListener(ChannelFutureListener.CLOSE);
    }
}
