package com.qunar.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 2014-02-24 17:23
 *
 * @author sen.chai
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(DiscardServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        try {
            StringBuilder inMsg = new StringBuilder();
            while (in.isReadable()) {
                inMsg.append((char) in.readByte());
            }
            logger.debug("msg: {}", inMsg);
            ByteBuf buffer = ctx.alloc().buffer().writeBytes("haha".getBytes());
            ctx.writeAndFlush(buffer);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("{}", cause);
        ctx.close();
    }
}
